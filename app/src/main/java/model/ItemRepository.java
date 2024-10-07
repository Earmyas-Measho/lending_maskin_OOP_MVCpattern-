package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository class for managing a collection of items.
 */
public class ItemRepository {
  private List<Item> items;

  /**
   * Constructs an ItemRepository with an empty list of items.
   */
  public ItemRepository() {
    this.items = new ArrayList<>();
  }

  /**
   * Copy constructor for ItemRepository.
   * Creates a new ItemRepository instance by copying the items from the provided
   * ItemRepository.
   *
   * @param other the ItemRepository to copy items from
   */
  public ItemRepository(ItemRepository other) {
    this.items = new ArrayList<>(other.items);
  }

  /**
   * Adds an item to the repository after checking for duplicates.
   * Throws an IdExistsException if an item with the same ID already exists.
   *
   * @param item the item to be added
   * @throws ModelExceptions.IdExistsException if an item with the same ID already
   *                                           exists
   */
  public void addItem(Item item) {
    if (itemExists(item)) {
      throw new ModelExceptions.IdExistsException();
    }
    items.add(item);
  }

  /**
   * Deletes an item from the repository.
   *
   * @param item the item to be deleted
   */
  public void deleteItem(Item item) {
    items.remove(item);
  }

  /**
   * Returns a list of all items in the repository.
   *
   * @return a list of all items
   */
  public List<Item> getAllItems() {
    return Collections.unmodifiableList(new ArrayList<>(items));
  }

  /**
   * Checks if an item exists in the repository.
   *
   * @param item the item to check for
   * @return true if the item exists, false otherwise
   */
  public boolean itemExists(Item item) {
    return items.contains(item);
  }
}
