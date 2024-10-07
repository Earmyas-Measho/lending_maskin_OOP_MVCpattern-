package view;

import java.time.LocalDate;
import java.util.List;
import model.Contract;
import model.Item;
import model.Member;

/**
 * Interface for handling the user interactions related to contracts.
 */
public interface ContractViewInterface {

  /**
   * Displays the details of a single contract.
   *
   * @param contract the contract whose details are to be displayed.
   */
  void displayContractDetails(Contract contract);

  /**
   * Prompts the user for input to create or edit a contract.
   *
   * @return an array of strings representing the contract input data.
   */
  String[] getContractInput();

  /**
   * Prompts the user to input the ID of a contract.
   *
   * @return the contract ID as a string.
   */
  String getContractIdInput();

  /**
   * Displays a list of all contracts.
   *
   * @param contracts an iterable collection of contracts to display.
   */
  void displayAllContracts(Iterable<Contract> contracts);

  /**
   * Displays a generic message to the user.
   *
   * @param message the message to display.
   */
  void displayMessage(String message);

  /**
   * Displays a message when the selected item or borrower is not found.
   */
  void displayItemOrBorrowerNotFoundMessage();

  /**
   * Displays a message when there are insufficient funds to create a contract.
   */
  void displayInsufficientFundsMessage();

  /**
   * Displays a message when a conflict with an existing contract is detected.
   */
  void displayConflictingContractMessage();

  /**
   * Displays a message when the specified contract is not found.
   */
  void displayContractNotFoundMessage();

  /**
   * Displays a message when the end date is before the start date in a contract.
   */
  void displayEndDateBeforeStartDateMessage();

  /**
   * Displays a success message when a contract is successfully created.
   */
  void displayCreateSuccessMessage();

  /**
   * Displays a success message when a contract is successfully deleted.
   */
  void displayDeleteSuccessMessage();

  /**
   * Displays a message when the input data provided by the user is invalid.
   */
  void displayInvalidDataMessage();

  /**
   * Displays a message showing how many days have been advanced and the new date.
   *
   * @param days    the number of days advanced.
   * @param newDate the new date after advancing.
   */
  void displayAdvanceTimeMessage(int days, LocalDate newDate);

  /**
   * Displays a message when the provided end date for a contract is invalid.
   */
  void displayInvalidEndDateMessage();

  /**
   * Displays a message when a contract ID already exists in the system.
   */
  void displayIdExistsMessage();

  /**
   * Displays a message when the provided date format is invalid.
   */
  void displayInvalidDateFormatMessage();

  /**
   * Closes any open resources or streams, such as a scanner.
   */
  void close();

  /**
   * Displays a generic unexpected error message.
   */
  void displayUnexpectedErrorMessage();

  /**
   * Displays a specific unexpected error message with additional details.
   *
   * @param message the detailed error message to display.
   */
  void displayUnexpectedErrorMessage(String message);

  /**
   * Displays a message when an item is not found.
   */
  void displayItemNotFoundMessage();

  /**
   * Displays a message when a borrower is not found.
   */
  void displayBorrowerNotFoundMessage();

  /**
   * Displays a message when a negative amount is encountered.
   */
  void displayNegativeAmountMessage();

  /**
   * Prompts the user to select a borrower by index.
   *
   * @return the selected borrower index as a string.
   */
  String getSelectedBorrowerIndex();

  /**
   * Prompts the user to select an item by index.
   *
   * @return the selected item index as a string.
   */
  String getSelectedItemIndex();

  /**
   * Displays a list of all members for selection.
   *
   * @param members the list of members to display.
   */
  void displayMembers(List<Member> members);

  /**
   * Displays a message when an invalid selection is made by the user.
   */
  void displayInvalidSelectionMessage();

  /**
   * Displays a list of all items for selection.
   *
   * @param items the list of items to display.
   */
  void displayAllItems(List<Item> items);
}
