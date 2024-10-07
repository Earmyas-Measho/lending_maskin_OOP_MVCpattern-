package view;

import java.util.List;
import model.Item;
import model.Member;

/**
 * Interface for handling user interactions related to items.
 * This interface defines the methods required for inputting, displaying, and
 * managing item-related data.
 */
public interface ItemViewInterface {

  /**
   * Prompts the user to input details for creating or editing an item.
   *
   * @return an array of strings containing the item input data.
   */
  String[] getItemInput();

  /**
   * Displays the details of a specific item.
   *
   * @param item the item whose details are to be displayed.
   */
  void displayItemDetails(Item item);

  /**
   * Displays a generic message to the user.
   *
   * @param message the message to display.
   */
  void displayMessage(String message);

  /**
   * Displays a message when the owner of an item is not found.
   */
  void displayOwnerNotFoundMessage();

  /**
   * Displays a message when an item is not found.
   */
  void displayItemNotFoundMessage();

  /**
   * Displays a success message when an item is successfully created.
   */
  void displayCreateSuccessMessage();

  /**
   * Displays a success message when an item is successfully deleted.
   */
  void displayDeleteSuccessMessage();

  /**
   * Displays a message when the input data provided by the user is invalid.
   */
  void displayInvalidDataMessage();

  /**
   * Displays a message when a negative cost value is encountered.
   */
  void displayNegativeCostMessage();

  /**
   * Displays a message when an item ID already exists in the system.
   */
  void displayIdExistsMessage();

  /**
   * Displays an unexpected error message with additional details.
   *
   * @param message the detailed error message to display.
   */
  void displayUnexpectedErrorMessage(String message);

  /**
   * Displays a list of all members for selection.
   *
   * @param members the list of members to display.
   */
  void displayMembers(List<Member> members);

  /**
   * Prompts the user to input the selected owner name.
   *
   * @return the selected owner name as a string.
   */
  String getSelectedOwnerName();

  /**
   * Prompts the user to input the selected item index.
   *
   * @return the selected item index as a string.
   */
  String getSelectedItemIndex();

  /**
   * Displays a message when an invalid selection is made by the user.
   */
  void displayInvalidSelectionMessage();

  /**
   * Displays a list of all items.
   *
   * @param items an iterable collection of items to display.
   */
  void displayAllItems(Iterable<Item> items);

  /**
   * Displays a list of all items for selection.
   *
   * @param items the list of items to display.
   */
  void displayAllItems(List<Item> items);

  /**
   * Prompts the user to input the selected item in raw format.
   *
   * @return the selected item input as a string.
   */
  String getSelectedItemInput();

  /**
   * Closes any open resources or streams, such as a scanner.
   */
  void close();
}
