package view;

/**
 * main view interface.
 */
public interface MainViewInterface {

  void displayMessage(String message);

  void displayInvalidSelectionMessage();

  void displayInvalidNumberMessage();

  void displayUnexpectedErrorMessage(String message);

  String getRawMainMenuSelection();

  String getRawMemberMenuSelection();

  String getRawAdvanceTimeInput();

  String getRawContractMenuSelection();

  String getRawItemMenuSelection();

  String getSelectedItemIndex();

  void close();
}
