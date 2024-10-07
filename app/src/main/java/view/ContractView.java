package view;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;

/**
 * View class for managing contract-related user interactions.
 */
public class ContractView implements ContractViewInterface {
  private final Scanner scanner;

  /**
   * Constructs a ContractView with a new Scanner instance.
   */
  public ContractView() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
  }

  @Override
  public void displayContractDetails(Contract contract) {
    if (contract != null) {
      System.out.println("Contract ID: " + contract.getId());
      System.out.println("Item Name: " + contract.getItem().getName());
      System.out.println("Borrower Name: " + contract.getBorrower().getName());
      System.out.println("Start Date: " + contract.getStartDate());
      System.out.println("End Date: " + contract.getEndDate());
      System.out.println("Active: " + contract.isActive());
    } else {
      System.out.println("Contract not found.");
    }
  }

  @Override
  public String[] getContractInput() {
    System.out.println("Enter Contract Details:");
    System.out.print("ID: ");
    String id = scanner.nextLine();
    System.out.print("Start Date (YYYY-MM-DD): ");
    String startDate = scanner.nextLine();
    System.out.print("End Date (YYYY-MM-DD): ");
    String endDate = scanner.nextLine();
    return new String[] { id, startDate, endDate };
  }

  @Override
  public String getSelectedBorrowerIndex() {
    return getInput("Enter the number of the borrower: ");
  }

  @Override
  public String getSelectedItemIndex() {
    return getInput("Enter the number of the item: ");
  }

  @Override
  public String getContractIdInput() {
    System.out.print("Enter Contract ID: ");
    return scanner.nextLine();
  }

  @Override
  public void displayAllContracts(Iterable<Contract> contracts) {
    for (Contract contract : contracts) {
      displayContractDetails(contract);
      System.out.println("-----");
    }
  }

  @Override
  public void displayNegativeAmountMessage() {
    System.out.println("The amount cannot be negative.");
  }

  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void displayItemOrBorrowerNotFoundMessage() {
    System.out.println("Item or Borrower not found. Please enter valid details.");
  }

  @Override
  public void displayInsufficientFundsMessage() {
    System.out.println("Contract creation failed due to insufficient funds.");
  }

  @Override
  public void displayConflictingContractMessage() {
    System.out.println("Contract creation failed due to conflicting time.");
  }

  @Override
  public void displayContractNotFoundMessage() {
    System.out.println("Contract not found. Please enter a valid contract ID.");
  }

  @Override
  public void displayEndDateBeforeStartDateMessage() {
    System.out.println("End date cannot be before start date. Please enter valid dates.");
  }

  @Override
  public void displayCreateSuccessMessage() {
    System.out.println("Contract created successfully.");
  }

  @Override
  public void displayDeleteSuccessMessage() {
    System.out.println("Contract deleted successfully.");
  }

  @Override
  public void displayInvalidDataMessage() {
    System.out.println("Invalid input. Please enter valid data.");
  }

  @Override
  public void displayAdvanceTimeMessage(int days, LocalDate newDate) {
    System.out.println("Time advanced by " + days + " days. New date: " + newDate);
  }

  @Override
  public void displayInvalidEndDateMessage() {
    System.out.println("End date cannot be before start date.");
  }

  @Override
  public void displayIdExistsMessage() {
    System.out.println("Contract ID already exists. Contract not created.");
  }

  @Override
  public void displayInvalidDateFormatMessage() {
    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
  }

  @Override
  public void close() {
    scanner.close();
  }

  @Override
  public void displayUnexpectedErrorMessage() {
    System.out.println("An unexpected error occurred. Please try again.");
  }

  @Override
  public void displayUnexpectedErrorMessage(String message) {
    System.out.println("An unexpected error occurred: " + message);
  }

  @Override
  public void displayItemNotFoundMessage() {
    System.out.println("Item not found. Please provide a valid item.");
  }

  @Override
  public void displayBorrowerNotFoundMessage() {
    System.out.println("Borrower not found. Please provide a valid borrower.");
  }

  @Override
  public void displayMembers(List<Member> members) {
    System.out.println("Available Borrowers:");
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + ". " + members.get(i).getName());
    }
  }

  @Override
  public void displayInvalidSelectionMessage() {
    System.out.println("Invalid selection. Please try again.");
  }

  @Override
  public void displayAllItems(List<Item> items) {
    System.out.println("Available Items:");
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getName());
    }
  }

  private String getInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }
}
