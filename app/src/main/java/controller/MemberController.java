package controller;

import java.util.List;
import model.Member;
import model.MemberRepository;
import model.ModelExceptions;
import view.MemberViewInterface;

/**
 * Controller class to manage member operations.
 * This class handles the interaction between the Member model and the view,
 * coordinating user input with business logic.
 */
public class MemberController {
  private final MemberRepository memberRepository;
  private final MemberViewInterface memberView;

  /**
   * Constructs a MemberController with the specified member repository and view.
   *
   * @param memberRepository the repository for managing member data.
   * @param memberView       the view interface for interacting with the user.
   */
  public MemberController(MemberRepository memberRepository, MemberViewInterface memberView) {
    this.memberRepository = new MemberRepository(memberRepository); // Defensive copy to fix FindBugs issue
    this.memberView = memberView; // Interface, no need to copy
  }

  /**
   * Copy constructor to create a new MemberController from an existing one.
   *
   * @param original the original MemberController to copy from.
   */
  public MemberController(MemberController original) {
    this.memberRepository = new MemberRepository(original.memberRepository); // Defensive copy
    this.memberView = original.memberView; // Interface, no need to copy
  }

  /**
   * Creates a new member based on user input. This method continuously prompts
   * the user for input until valid data is provided or an exception is caught.
   * It handles all the necessary validations and notifies the user of any errors
   * through the view.
   */
  public void createMember() {
    while (true) {
      try {
        // Get member input from the view
        String[] memberInput = memberView.getMemberInput();
        String id = memberInput[0];
        String name = memberInput[1];
        String email = memberInput[2];
        String phone = memberInput[3];
        int credits = parseCreditsInput(memberInput[4]);

        Member member = new Member(id, name, email, phone, credits,
            memberView.getEmailRegexPattern(), memberView.getPhoneRegexPattern());

        // Validate and add member to repository
        memberRepository.addMember(member);

        // Display success messages
        memberView.displayMemberDetails(member);
        memberView.displayCreateSuccessMessage();
        break;

      } catch (ModelExceptions.EmailExistsException e) {
        memberView.displayEmailExistsMessage();
      } catch (ModelExceptions.PhoneExistsException e) {
        memberView.displayPhoneExistsMessage();
      } catch (ModelExceptions.NegativeCreditsException e) {
        memberView.displayNegativeCreditsMessage();
      } catch (NumberFormatException e) {
        memberView.displayInvalidDataMessage();
      }
    }
  }

  /**
   * Deletes an existing member by directly passing the Member object.
   */
  public void deleteMember(Member member) {
    if (member != null) {
      memberRepository.deleteMember(member);
      memberView.displayDeleteSuccessMessage();
    } else {
      memberView.displayMemberNotFoundMessage();
    }
  }

  /**
   * Deletes an existing member by selecting the member from the repository.
   * The user selects the member from the list of members.
   */
  public void deleteMember() {
    while (true) {
      List<Member> members = memberRepository.getAllMembers();
      Member member = selectMemberFromInput(members);
      if (member != null) {
        deleteMember(member); // Call the overloaded deleteMember method
        break;
      } else {
        memberView.displayMemberNotFoundMessage();
      }
    }
  }

  /**
   * Displays all members currently stored in the repository.
   */
  public void viewAllMembers() {
    List<Member> members = memberRepository.getAllMembers();
    memberView.displayAllMembers(members);
  }

  /**
   * Adds a specified amount of credits to a member's account by passing the
   * Member object.
   */
  public void addCreditsToMember(Member member, int amount) {
    try {
      if (amount <= 0) {
        memberView.displayNegativeAmountMessage();
        return;
      }

      if (member != null) {
        member.addCredits(amount);
        memberView.displayAddCreditsSuccessMessage();
      } else {
        memberView.displayMemberNotFoundMessage();
      }
    } catch (ModelExceptions.NegativeAmountException e) {
      memberView.displayNegativeAmountMessage();
    } catch (Exception e) {
      memberView.displayUnexpectedErrorMessage(e.getMessage());
    }
  }

  /**
   * Deducts a specified amount of credits from a member's account by passing the
   * Member object.
   */
  public void deductCreditsFromMember(Member member, int amount) {
    try {
      if (amount <= 0) {
        memberView.displayNegativeAmountMessage();
        return;
      }

      if (member != null) {
        member.deductCredits(amount);
        memberView.displayDeductCreditsSuccessMessage();
      } else {
        memberView.displayMemberNotFoundMessage();
      }
    } catch (ModelExceptions.NegativeAmountException e) {
      memberView.displayNegativeAmountMessage();
    } catch (Exception e) {
      memberView.displayUnexpectedErrorMessage(e.getMessage());
    }
  }

  /**
   * Finds a member by their unique ID from a list of members.
   *
   * @param members  the list of members to search through.
   * @param memberId the unique ID of the member to find.
   * @return the member with the specified ID, or null if no such member is found.
   */
  public Member findMemberById(List<Member> members, String memberId) {
    return members.stream().filter(member -> member.getId().equals(memberId)).findFirst().orElse(null);
  }

  /**
   * Selects an owner by retrieving all members and then selecting one based on
   * user input.
   *
   * @return the selected member, or null if the selection is invalid.
   */
  public Member selectOwner() {
    List<Member> members = memberRepository.getAllMembers();
    return selectMemberFromInput(members);
  }

  /**
   * Parses the raw input string into an integer representing credits.
   *
   * @param rawInput the raw string input representing the number of credits.
   * @return the parsed integer value of credits.
   * @throws NumberFormatException if the input cannot be parsed into an integer.
   */
  public int parseCreditsInput(String rawInput) {
    try {
      return Integer.parseInt(rawInput);
    } catch (NumberFormatException e) {
      memberView.displayInvalidDataMessage();
      throw e;
    }
  }

  /**
   * Parses the raw input into an index and validates it.
   *
   * @param input   the raw string input representing the user's selection.
   * @param maxSize the maximum valid index size.
   * @return the parsed index, or -1 if the input is invalid.
   */
  private int parseInputToIndex(String input, int maxSize) {
    try {
      int index = Integer.parseInt(input) - 1; // Convert to zero-based index
      return (index >= 0 && index < maxSize) ? index : -1; // Validate index
    } catch (NumberFormatException e) {
      return -1; // Return -1 if the input is invalid
    }
  }

  /**
   * Selects a member from the list based on user input.
   *
   * @param members the list of available members.
   * @return the selected member, or null if the selection is invalid.
   */
  public Member selectMemberFromInput(List<Member> members) {
    memberView.displayAllMembers(members); // Display all members in the view
    String input = memberView.getSelectedMemberInput(); // Get raw input from the view
    int selection = parseInputToIndex(input, members.size()); // Parse and validate input in the controller

    if (selection >= 0) { // Check if the selection is valid
      return members.get(selection); // Return the selected member
    } else {
      memberView.displayInvalidSelectionMessage(); // Notify the user of an invalid selection
      return null;
    }
  }

}