package controller;

import java.util.List;
import model.ContractRepository;
import model.Item;
import model.ItemRepository;
import model.Member;
import model.MemberRepository;
import view.ItemViewInterface;

/**
 * Controller class to manage item operations.
 * This class handles the interaction between the Item model and the view,
 * coordinating user input with business logic.
 */
public class ItemController {
  private final ItemRepository itemRepository;
  private final ItemViewInterface itemView;
  private final ContractRepository contractRepository;
  private final MemberRepository memberRepository;

  /**
   * Constructs an ItemController with the specified repositories and view.
   *
   * @param itemRepository     the repository for managing item data.
   * @param itemView           the view interface for interacting with the user.
   * @param contractRepository the repository for managing contract data.
   * @param memberRepository   the repository for managing member data.
   */
  public ItemController(ItemRepository itemRepository, ItemViewInterface itemView,
      ContractRepository contractRepository, MemberRepository memberRepository) {
    // Defensive copies to protect internal state
    this.itemRepository = new ItemRepository(itemRepository); // Assuming there's a copy constructor
    this.itemView = itemView; // Interface, no need to copy
    this.contractRepository = new ContractRepository(contractRepository); // Assuming there's a copy constructor
    this.memberRepository = new MemberRepository(memberRepository); // Assuming there's a copy constructor
  }

  /**
   * Copy constructor to create a new ItemController from an existing one.
   *
   * @param original the original ItemController to copy from.
   */
  public ItemController(ItemController original) {
    // Copy the necessary fields from the original object
    this.itemRepository = new ItemRepository(original.itemRepository); // Assuming a copy constructor exists
    this.itemView = original.itemView; // Interface, no need to copy
    this.contractRepository = new ContractRepository(original.contractRepository); // Assuming a copy constructor exists
    this.memberRepository = new MemberRepository(original.memberRepository); // Assuming a copy constructor exists
  }

  /**
   * Creates a new item based on user input.
   *
   * @param owner the member who owns the item
   */
  public void createItem(Member owner) {
    while (true) {
      try {
        String[] itemInput = itemView.getItemInput();
        Item item = createItem(itemInput, owner);

        if (item == null) {
          continue; // If item creation failed, continue the loop
        }

        if (itemRepository.itemExists(item)) {
          itemView.displayIdExistsMessage();
          continue;
        }

        itemRepository.addItem(item);
        owner.addCredits(item.getCost());
        itemView.displayItemDetails(item);
        itemView.displayCreateSuccessMessage();
        break;

      } catch (Exception e) {
        itemView.displayUnexpectedErrorMessage(e.getMessage());
      }
    }
  }

  /**
   * Creates an Item object from provided data.
   *
   * @param itemData the item data
   * @param owner    the member who owns the item
   * @return the created item, or null if the input data is invalid
   */
  private Item createItem(String[] itemData, Member owner) {
    try {
      String name = itemData[0];
      int cost = Integer.parseInt(itemData[1]);
      if (cost < 0) {
        itemView.displayNegativeCostMessage();
        return null;
      }
      return new Item(owner, name, cost);
    } catch (NumberFormatException e) {
      itemView.displayInvalidDataMessage();
      return null;
    }
  }

  /**
   * Deletes the specified item.
   *
   * @param item the item to be deleted
   */
  public void deleteItem(Item item) {
    if (item != null) {
      contractRepository.cancelContractsForItem(item);
      itemRepository.deleteItem(item);
      itemView.displayDeleteSuccessMessage();
    } else {
      itemView.displayItemNotFoundMessage();
    }
  }

  /**
   * Retrieves and displays all items.
   *
   * @return a list of all items
   */
  public List<Item> viewAllItems() {
    List<Item> allItems = itemRepository.getAllItems();
    itemView.displayAllItems(allItems);
    return allItems; // Return the list of items
  }

  /**
   * Selects an item based on user input.
   *
   * @param items the list of available items
   * @return the selected item, or null if the selection is invalid
   */
  public Item selectItemFromInput(List<Item> items) {
    itemView.displayAllItems(items); // Display all items in the view
    String input = itemView.getSelectedItemInput(); // Get raw input from the view
    int selection = parseInputToIndex(input, items.size()); // Parse and validate input in the controller

    if (selection >= 0) { // Check if the selection is valid
      return items.get(selection); // Return the selected item
    } else {
      itemView.displayInvalidSelectionMessage(); // Notify the user of an invalid selection
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
   * Selects an owner based on user input.
   *
   * @return the selected owner, or null if the selection is invalid
   */
  public Member selectOwner() {
    List<Member> members = memberRepository.getAllMembers();
    itemView.displayMembers(members);
    try {
      int index = Integer.parseInt(itemView.getSelectedItemIndex()) - 1;
      if (index >= 0 && index < members.size()) {
        return members.get(index);
      } else {
        itemView.displayInvalidSelectionMessage();
        return null;
      }
    } catch (NumberFormatException e) {
      itemView.displayInvalidDataMessage();
      return null;
    }
  }
}
