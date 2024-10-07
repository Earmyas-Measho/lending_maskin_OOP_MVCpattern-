package view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * View class for managing main menu user interactions.
 */
public class MainView implements MainViewInterface {
  private final Scanner scanner;

  /**
   * Constructs a MainView with a new Scanner instance.
   */
  public MainView() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
  }

  @Override
  public String getRawMainMenuSelection() {
    System.out.println("Main Menu:");
    System.out.println("1. Manage Members");
    System.out.println("2. Manage Items");
    System.out.println("3. Manage Contracts");
    System.out.println("4. Advance Time");
    System.out.println("5. Exit");
    System.out.print("Select an option: ");
    return scanner.nextLine();
  }

  @Override
  public String getRawMemberMenuSelection() {
    System.out.println("Member Menu:");
    System.out.println("1. Create Member");
    System.out.println("2. Delete Member");
    System.out.println("3. View All Members");
    System.out.println("4. Back to Main Menu");
    System.out.print("Select an option: ");
    return scanner.nextLine(); // Return raw input as a string
  }

  @Override
  public String getRawItemMenuSelection() {
    System.out.println("Item Menu:");
    System.out.println("1. Create Item");
    System.out.println("2. Delete Item");
    System.out.println("3. View All Items");
    System.out.println("4. Back to Main Menu");
    System.out.print("Select an option: ");
    return scanner.nextLine();
  }

  @Override
  public String getRawContractMenuSelection() {
    System.out.println("Contract Menu:");
    System.out.println("1. Create Contract");
    System.out.println("2. Delete Contract");
    System.out.println("3. View All Contracts");
    System.out.println("4. Back to Main Menu");
    System.out.print("Select an option: ");
    return scanner.nextLine();
  }

  @Override
  public String getRawAdvanceTimeInput() {
    System.out.print("Enter the number of days to advance: ");
    return scanner.nextLine();
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
  public void displayInvalidNumberMessage() {
    System.out.println("Invalid input. Please enter a valid number.");
  }

  @Override
  public void displayUnexpectedErrorMessage(String message) {
    System.out.println("An unexpected error occurred: " + message);
  }

  @Override
  public String getSelectedItemIndex() {
    System.out.print("Select an item by number: ");
    return scanner.nextLine(); // Return the raw user input as a string
  }

  @Override
  public void close() {
    scanner.close();
  }
}
