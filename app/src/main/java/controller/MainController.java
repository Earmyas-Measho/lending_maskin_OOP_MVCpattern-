package controller;

import java.util.List;
import java.util.Objects;
import model.Item;
import model.Member;
import view.MainViewInterface;

/**
 * Controller class to manage the main application operations.
 */
public class MainController {
  private final MemberController memberController;
  private final ItemController itemController;
  private final ContractController contractController;
  private final MainViewInterface mainView;

  /**
   * Constructs a MainController with the specified controllers and main view.
   * exposing the internal state of this MainController to external modifications.
   *
   * @param memberController   the member controller to manage member operations
   * @param itemController     the item controller to manage item operations
   * @param contractController the contract controller to manage contract
   *                           operations
   * @param mainView           the main view to use for displaying the main menu
   *                           and selections
   */
  public MainController(MemberController memberController, ItemController itemController,
      ContractController contractController, MainViewInterface mainView) {
    this.memberController = new MemberController(memberController); // Defensive copy
    this.itemController = new ItemController(itemController); // Defensive copy
    this.contractController = new ContractController(contractController); // Defensive copy
    this.mainView = Objects.requireNonNull(mainView);
  }

  /**
   * Starts the main application loop, displaying the main menu and handling user
   * selections.
   */
  public void start() {
    boolean running = true;
    while (running) {
      try {
        String rawMainSelection = mainView.getRawMainMenuSelection(); // Get raw input
        int mainSelection = parseMainMenuSelection(rawMainSelection); // Parse and validate input

        switch (mainSelection) {
          case 1:
            handleMemberMenu();
            break;
          case 2:
            handleItemMenu();
            break;
          case 3:
            handleContractMenu();
            break;
          case 4:
            handleAdvanceTime();
            break;
          case 5:
            running = false;
            break;
          default:
            mainView.displayInvalidSelectionMessage();
        }
      } catch (Exception e) {
        mainView.displayUnexpectedErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * Advances the application time by a specified number of days.
   */
  private void handleAdvanceTime() {
    String rawAdvanceTimeInput = mainView.getRawAdvanceTimeInput();
    int days = parseAdvanceTimeInput(rawAdvanceTimeInput);

    contractController.advanceTime(days);
  }

  /**
   * Handles the member menu operations, allowing the user to create, delete, or
   * view members.
   */
  private void handleMemberMenu() {
    boolean backToMain = false;
    while (!backToMain) {
      try {
        String rawMemberSelection = mainView.getRawMemberMenuSelection(); // Step 1: Get raw input
        int memberSelection = parseMemberMenuSelection(rawMemberSelection); // Step 2: Parse and validate input

        switch (memberSelection) {
          case 1:
            memberController.createMember();
            break;
          case 2:
            memberController.deleteMember();
            break;
          case 3:
            memberController.viewAllMembers();
            break;
          case 4:
            backToMain = true;
            break;
          default:
            mainView.displayInvalidSelectionMessage();
        }
      } catch (Exception e) {
        mainView.displayUnexpectedErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * Parses and validates the raw member menu selection input.
   *
   * @param rawSelection the raw string input representing the user's menu
   *                     selection
   * @return the parsed integer representing the menu selection
   * @throws NumberFormatException if the input cannot be parsed into an integer
   */
  private int parseMemberMenuSelection(String rawSelection) {
    try {
      return Integer.parseInt(rawSelection);
    } catch (NumberFormatException e) {
      mainView.displayInvalidNumberMessage();
      throw e;
    }
  }

  /**
   * Handles the item menu operations, allowing the user to create, delete, or
   * view items.
   */
  private void handleItemMenu() {
    boolean backToMain = false;
    while (!backToMain) {
      try {
        String rawItemSelection = mainView.getRawItemMenuSelection();
        int itemSelection = parseItemMenuSelection(rawItemSelection);

        switch (itemSelection) {
          case 1:
            Member owner = memberController.selectOwner(); // Interacting directly with the Member object
            if (owner != null) {
              itemController.createItem(owner); // Passing the Member object directly
            } else {
              mainView.displayInvalidSelectionMessage();
            }
            break;
          case 2:
            List<Item> allItems = itemController.viewAllItems(); // Get all items
            String selectedItemIndex = mainView.getSelectedItemIndex(); // Get raw input from the view
            int index = parseInputToIndex(selectedItemIndex, allItems.size()); // Parse and validate input

            if (index >= 0) {
              Item itemToDelete = allItems.get(index); // Select the Item object
              itemController.deleteItem(itemToDelete); // Passing the Item object directly
            } else {
              mainView.displayInvalidSelectionMessage();
            }
            break;
          case 3:
            itemController.viewAllItems();
            break;
          case 4:
            backToMain = true;
            break;
          default:
            mainView.displayInvalidSelectionMessage();
        }
      } catch (Exception e) {
        mainView.displayUnexpectedErrorMessage(e.getMessage());
      }
    }
  }

  private int parseInputToIndex(String input, int maxSize) {
    try {
      int index = Integer.parseInt(input) - 1; // Convert to zero-based index
      return (index >= 0 && index < maxSize) ? index : -1; // Validate index
    } catch (NumberFormatException e) {
      return -1; // Return -1 if the input is invalid
    }
  }

  /**
   * Handles the contract menu operations, allowing the user to create, delete, or
   * view contracts.
   */
  private void handleContractMenu() {
    boolean backToMain = false;
    while (!backToMain) {
      try {
        String rawContractSelection = mainView.getRawContractMenuSelection();
        int contractSelection = parseContractMenuSelection(rawContractSelection);

        switch (contractSelection) {
          case 1:
            contractController.createContract();
            break;
          case 2:
            contractController.deleteContract();
            break;
          case 3:
            contractController.viewAllContracts();
            break;
          case 4:
            backToMain = true;
            break;
          default:
            mainView.displayInvalidSelectionMessage();
        }
      } catch (Exception e) {
        mainView.displayUnexpectedErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * Parses the raw input for the main menu selection.
   * parsed into an integer, it displays an error message and rethrows the
   * {@link NumberFormatException}.
   *
   * @param rawSelection the raw string input representing the user's menu
   *                     selection
   * @return the parsed integer representing the menu selection
   * @throws NumberFormatException if the input cannot be parsed into an integer
   */
  public int parseMainMenuSelection(String rawSelection) {
    try {
      return Integer.parseInt(rawSelection);
    } catch (NumberFormatException e) {
      mainView.displayInvalidNumberMessage();
      throw e;
    }
  }

  private int parseAdvanceTimeInput(String rawInput) {
    try {
      return Integer.parseInt(rawInput);
    } catch (NumberFormatException e) {
      mainView.displayInvalidNumberMessage();
      throw e;
    }
  }

  private int parseContractMenuSelection(String rawSelection) {
    try {
      return Integer.parseInt(rawSelection);
    } catch (NumberFormatException e) {
      mainView.displayInvalidNumberMessage();
      throw e;
    }
  }

  private int parseItemMenuSelection(String rawSelection) {
    try {
      return Integer.parseInt(rawSelection);
    } catch (NumberFormatException e) {
      mainView.displayInvalidNumberMessage();
      throw e;
    }
  }

}
