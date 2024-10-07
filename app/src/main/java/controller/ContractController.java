package controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.Contract;
import model.ContractRepository;
import model.Item;
import model.ItemRepository;
import model.Member;
import model.MemberRepository;
import model.ModelExceptions;
import view.ContractViewInterface;

/**
 * Controller class to manage contract operations.
 */
public class ContractController {
  private final ContractRepository contractRepository;
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;
  private final ContractViewInterface contractView;

  /**
   * Constructs a ContractController with the specified repositories and view.
   * modifications.
   *
   * @param contractRepository the repository for managing contract data.
   * @param itemRepository     the repository for managing item data.
   * @param memberRepository   the repository for managing member data.
   * @param contractView       the view interface for interacting with the user.
   */
  public ContractController(ContractRepository contractRepository, ItemRepository itemRepository,
      MemberRepository memberRepository, ContractViewInterface contractView) {
    // Defensive copies to protect internal state
    this.contractRepository = new ContractRepository(contractRepository); // Assuming a copy constructor exists
    this.itemRepository = new ItemRepository(itemRepository); // Assuming a copy constructor exists
    this.memberRepository = new MemberRepository(memberRepository); // Assuming a copy constructor exists
    this.contractView = contractView; // No need to copy, as this is an interface
  }

  /**
   * Copy constructor to create a new ContractController from an existing one.
   *
   * @param original the original ContractController to copy from.
   */
  public ContractController(ContractController original) {
    this.contractRepository = new ContractRepository(original.contractRepository); // Assuming a copy constructor exists
    this.itemRepository = new ItemRepository(original.itemRepository); // Assuming a copy constructor exists
    this.memberRepository = new MemberRepository(original.memberRepository); // Assuming a copy constructor exists
    this.contractView = original.contractView; // Interface, no need to copy
  }

  /**
   * Advances the application time by a specified number of days.
   *
   * @param days the number of days to advance
   */
  public void advanceTime(int days) {
    LocalDate newDate = LocalDate.now().plusDays(days);

    List<Contract> contracts = contractRepository.getAllContracts();
    for (Contract contract : contracts) {
      if (contract.getEndDate().isBefore(newDate) && contract.isActive()) {
        contract.setActive(false);
        Member borrower = contract.getBorrower();
        Item item = contract.getItem();
        try {
          borrower.deductCredits(item.getCost());
        } catch (ModelExceptions.NegativeAmountException e) {
          contractView.displayNegativeAmountMessage();
        }
      }
    }

    contractView.displayAdvanceTimeMessage(days, newDate);
  }

  /**
   * Creates a new contract based on user input.
   */
  public void createContract() {
    while (true) {
      try {
        List<Item> allItems = itemRepository.getAllItems();
        List<Member> allMembers = memberRepository.getAllMembers();

        Item selectedItem = selectItemFromInput(allItems);
        Member selectedBorrower = selectBorrowerFromInput(allMembers);

        if (selectedItem == null || selectedBorrower == null) {
          contractView.displayItemOrBorrowerNotFoundMessage();
          continue;
        }

        if (selectedBorrower.getCredits() < selectedItem.getCost()) {
          contractView.displayInsufficientFundsMessage();
          continue;
        }

        String[] contractInput = contractView.getContractInput();
        Contract contract = createContract(contractInput, selectedItem, selectedBorrower);

        if (contract == null || isConflictingContract(contract)) {
          contractView.displayConflictingContractMessage();
          continue;
        }

        contractRepository.addContract(contract);
        contractView.displayContractDetails(contract);
        contractView.displayCreateSuccessMessage();
        break;

      } catch (Exception e) {
        contractView.displayUnexpectedErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * Creates a Contract object from provided data.
   *
   * @param contractData the contract data
   * @param item         the item associated with the contract
   * @param borrower     the member borrowing the item
   * @return the created contract
   */
  private Contract createContract(String[] contractData, Item item, Member borrower) {
    try {
      String id = contractData[0];
      LocalDate startDate = LocalDate.parse(contractData[1]);
      LocalDate endDate = LocalDate.parse(contractData[2]);
      return new Contract(id, item, borrower, startDate, endDate);
    } catch (DateTimeParseException e) {
      contractView.displayInvalidDateFormatMessage();
      return null;
    } catch (ModelExceptions.InvalidEndDateException e) {
      contractView.displayInvalidEndDateMessage();
      return null;
    }
  }

  /**
   * Checks if the new contract conflicts with any existing contract.
   *
   * @param newContract the new contract
   * @return true if the contract conflicts with any existing contract, otherwise
   *         false
   */
  private boolean isConflictingContract(Contract newContract) {
    return contractRepository.getAllContracts().stream()
        .anyMatch(existingContract -> existingContract.conflictsWith(newContract));
  }

  /**
   * Deletes a contract based on user input.
   */
  public void deleteContract() {
    while (true) {
      Contract contract = contractRepository.getContract(contractView.getContractIdInput());
      if (contract != null) {
        contractRepository.deleteContract(contract);
        contractView.displayDeleteSuccessMessage();
        break;
      } else {
        contractView.displayContractNotFoundMessage();
      }
    }
  }

  /**
   * Displays all contracts.
   */
  public void viewAllContracts() {
    contractView.displayAllContracts(contractRepository.getAllContracts());
  }

  /**
   * Selects an item from user input.
   *
   * @param items the list of available items
   * @return the selected item
   */
  public Item selectItemFromInput(List<Item> items) {
    contractView.displayAllItems(items); // Display items in the view
    String selectedItemIndex = contractView.getSelectedItemIndex(); // Get raw input from the view
    int index = parseInputToIndex(selectedItemIndex, items.size()); // Parse and validate input

    if (index >= 0) {
      return items.get(index); // Return the selected item
    } else {
      contractView.displayInvalidSelectionMessage(); // Notify user of invalid selection
      return null;
    }
  }

  /**
   * Parses input to an index.
   *
   * @param input   the raw input
   * @param maxSize the maximum size for validation
   * @return the parsed index, or -1 if invalid
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
   * Selects a borrower from user input.
   *
   * @param members the list of available members
   * @return the selected member
   */
  public Member selectBorrowerFromInput(List<Member> members) {
    contractView.displayMembers(members);
    try {
      int index = Integer.parseInt(contractView.getSelectedBorrowerIndex()) - 1;
      if (index >= 0 && index < members.size()) {
        return members.get(index);
      } else {
        contractView.displayInvalidSelectionMessage();
        return null;
      }
    } catch (NumberFormatException e) {
      contractView.displayInvalidDataMessage();
      return null;
    }
  }
}
