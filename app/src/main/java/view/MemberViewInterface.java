package view;

import java.util.List;
import model.Member;

/**
 * Interface for managing user interactions related to members.
 * This interface defines the methods required for inputting, displaying, and
 * managing member-related data.
 */
public interface MemberViewInterface {

  /**
   * Prompts the user to input details for creating or editing a member.
   *
   * @return an array of strings containing the member input data.
   */
  String[] getMemberInput();

  /**
   * Displays the details of a specific member.
   *
   * @param member the member whose details are to be displayed.
   */
  void displayMemberDetails(Member member);

  /**
   * Displays a list of all members.
   *
   * @param members the list of members to display.
   */
  void displayAllMembers(List<Member> members);

  /**
   * Displays a message when an invalid selection is made by the user.
   */
  void displayInvalidSelectionMessage();

  /**
   * Prompts the user for input with a custom prompt message.
   *
   * @param prompt the prompt message to display to the user.
   * @return the user's input as a string.
   */
  String getInput(String prompt);

  /**
   * Returns the regular expression pattern for validating email addresses.
   *
   * @return the regex pattern for email validation.
   */
  String getEmailRegexPattern();

  /**
   * Returns the regular expression pattern for validating phone numbers.
   *
   * @return the regex pattern for phone number validation.
   */
  String getPhoneRegexPattern();

  /**
   * Displays a message when the email format provided by the user is invalid.
   */
  void displayInvalidEmailFormatMessage();

  /**
   * Displays a message when a negative amount is encountered.
   */
  void displayNegativeAmountMessage();

  /**
   * Displays a message when the end date provided by the user is invalid.
   */
  void displayInvalidEndDateMessage();

  /**
   * Displays a message when a negative cost value is encountered.
   */
  void displayNegativeCostMessage();

  /**
   * Displays a success message when credits are successfully added to a member's
   * account.
   */
  void displayAddCreditsSuccessMessage();

  /**
   * Displays a success message when credits are successfully deducted from a
   * member's account.
   */
  void displayDeductCreditsSuccessMessage();

  /**
   * Displays a message when the input data provided by the user is invalid.
   */
  void displayInvalidDataMessage();

  /**
   * Displays a message when a negative credits value is encountered.
   */
  void displayNegativeCreditsMessage();

  /**
   * Displays a message when the provided email address already exists in the
   * system.
   */
  void displayEmailExistsMessage();

  /**
   * Displays a message when the provided phone number already exists in the
   * system.
   */
  void displayPhoneExistsMessage();

  /**
   * Displays a message when the provided member ID already exists in the system.
   */
  void displayIdExistsMessage();

  /**
   * Displays a message when the specified member is not found.
   */
  void displayMemberNotFoundMessage();

  /**
   * Displays a success message when a member is successfully created.
   */
  void displayCreateSuccessMessage();

  /**
   * Displays a success message when a member is successfully deleted.
   */
  void displayDeleteSuccessMessage();

  /**
   * Displays an unexpected error message with additional details.
   *
   * @param message the detailed error message to display.
   */
  void displayUnexpectedErrorMessage(String message);

  /**
   * Displays a generic message to the user.
   *
   * @param message the message to display.
   */
  void displayMessage(String message);

  /**
   * Prompts the user to input the credits value.
   *
   * @return the raw credits input as a string.
   */
  String getRawCreditsInput();

  /**
   * Prompts the user to input a member ID.
   *
   * @return the member ID as a string.
   */
  String getMemberIdInput();

  /**
   * Prompts the user to select a member by inputting the selection index.
   *
   * @return the selected member input as a string.
   */
  String getSelectedMemberInput();

  /**
   * Closes any open resources or streams, such as a scanner.
   */
  void close();
}
