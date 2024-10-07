package view;

import java.util.List;
import java.util.Scanner;
import model.Item;
import model.Member;

/**
 * Implementation of the ItemViewInterface that handles user interactions
 * related to items.
 * This class provides methods to input and display item-related data.
 */
public class ItemView implements ItemViewInterface {
  private final Scanner scanner;

  /**
   * Constructs an ItemView object and initializes the scanner for input.
   * The scanner is set to use UTF-8 encoding for reading input.
   */
  public ItemView() {
    this.scanner = new Scanner(System.in, "UTF-8");
  }

  @Override
  public String[] getItemInput() {
    String name = getInput("Enter Name: ");
    String cost = getInput("Enter Cost: ");
    return new String[] { name, cost };
  }

  @Override
  public void displayItemDetails(Item item) {
    System.out.println("Owner: " + item.getOwner().getName());
    System.out.println("Name: " + item.getName());
    System.out.println("Cost: " + item.getCost());
  }

  @Override
  public void displayMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void displayOwnerNotFoundMessage() {
    displayMessage("Owner not found.");
  }

  @Override
  public void displayItemNotFoundMessage() {
    displayMessage("Item not found.");
  }

  @Override
  public void displayCreateSuccessMessage() {
    displayMessage("Item created successfully.");
  }

  @Override
  public void displayDeleteSuccessMessage() {
    displayMessage("Item deleted successfully.");
  }

  @Override
  public void displayInvalidDataMessage() {
    displayMessage("Invalid input.");
  }

  @Override
  public void displayNegativeCostMessage() {
    displayMessage("Cost cannot be negative.");
  }

  @Override
  public void displayIdExistsMessage() {
    displayMessage("Item already exists.");
  }

  @Override
  public void displayUnexpectedErrorMessage(String message) {
    displayMessage("Unexpected error: " + message);
  }

  @Override
  public void displayMembers(List<Member> members) {
    displayMessage("Available Members:");
    for (int i = 0; i < members.size(); i++) {
      System.out.println((i + 1) + ". " + members.get(i).getName());
    }
  }

  @Override
  public String getSelectedOwnerName() {
    return getInput("Enter Owner Name: ");
  }

  @Override
  public String getSelectedItemIndex() {
    return getInput("Enter the number of the item: ");
  }

  @Override
  public void displayInvalidSelectionMessage() {
    displayMessage("Invalid selection. Please try again.");
  }

  private String getInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  @Override
  public void displayAllItems(Iterable<Item> items) {
    for (Item item : items) {
      displayItemDetails(item);
      System.out.println("-----");
    }
  }

  @Override
  public void displayAllItems(List<Item> items) {
    for (int i = 0; i < items.size(); i++) {
      System.out.println((i + 1) + ". " + items.get(i).getName()); // Display each item
    }
  }

  @Override
  public String getSelectedItemInput() {
    System.out.print("Select an item by number: ");
    return scanner.nextLine(); // Return the raw user input as a string
  }

  @Override
  public void close() {
    scanner.close();
  }
}
