package view;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.Member;

/**
 * View class for managing member-related user interactions.
 */
public class MemberView implements MemberViewInterface {
  private final Scanner scanner;

  public MemberView() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
  }

  @Override
  public String[] getMemberInput() {
    System.out.println("Enter Member Details:");
    String id = getInput("ID: ");
    String name = getInput("Name: ");
    String email = getInput("Email: ");
    String phone = getInput("Phone: ");
    String rawCredits = getRawCreditsInput();
    return new String[] { id, name, email, phone, rawCredits };
  }

  @Override
  public void displayMemberDetails(Member member) {
    System.out.println("Member ID: " + member.getId());
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone: " + member.getPhone());
    System.out.println("Credits: " + member.getCredits());
  }

  @Override
  public void displayAllMembers(List<Member> members) {
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + ". " + members.get(i).getName()); // Display each member
    }
  }

  @Override
  public String getSelectedMemberInput() {
    System.out.print("Select a member by number: ");
    return scanner.nextLine(); // Return the raw user input as a string
  }

  @Override
  public String getInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  @Override
  public String getRawCreditsInput() {
    return getInput("Enter Credits: ");
  }

  @Override
  public String getMemberIdInput() {
    return getInput("Enter Member ID to select: ");
  }

  @Override
  public void displayInvalidEmailFormatMessage() {
    System.out.println("Invalid email format. Expected format: string@string.string");
  }

  @Override
  public void displayNegativeAmountMessage() {
    System.out.println("Amount cannot be negative.");
  }

  @Override
  public void displayInvalidEndDateMessage() {
    System.out.println("End date cannot be before start date.");
  }

  @Override
  public void displayNegativeCostMessage() {
    System.out.println("Cost cannot be negative.");
  }

  @Override
  public void displayAddCreditsSuccessMessage() {
    System.out.println("Credits added successfully.");
  }

  @Override
  public void displayDeductCreditsSuccessMessage() {
    System.out.println("Credits deducted successfully.");
  }

  @Override
  public void displayInvalidDataMessage() {
    System.out.println("Invalid input. Please enter valid data.");
  }

  @Override
  public void displayNegativeCreditsMessage() {
    System.out.println("Credits cannot be negative.");
  }

  @Override
  public void displayEmailExistsMessage() {
    System.out.println("Email already exists. Member not created.");
  }

  @Override
  public void displayPhoneExistsMessage() {
    System.out.println("Phone already exists. Member not created.");
  }

  @Override
  public void displayIdExistsMessage() {
    System.out.println("ID already exists. Member not created.");
  }

  @Override
  public void displayMemberNotFoundMessage() {
    System.out.println("Member not found. Please enter a valid member ID.");
  }

  @Override
  public void displayCreateSuccessMessage() {
    System.out.println("Member created successfully.");
  }

  @Override
  public void displayDeleteSuccessMessage() {
    System.out.println("Member deleted successfully.");
  }

  @Override
  public void displayUnexpectedErrorMessage(String message) {
    System.out.println("An unexpected error occurred: " + message);
  }

  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void displayInvalidSelectionMessage() {
    System.out.println("Invalid selection. Please try again.");
  }

  @Override
  public void close() {
    scanner.close();
  }

  @Override
  public String getEmailRegexPattern() {
    return "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
  }

  @Override
  public String getPhoneRegexPattern() {
    return "\\d+";
  }

}