package model;

import java.util.Objects;

/**
 * Represents an item with an owner, name, and cost.
 */
public class Item {
  private final String id;
  private String name;
  private int cost;
  private Member owner;

  /**
   * Constructs an Item with the specified owner, name, and cost.
   *
   * @param owner the owner of the item
   * @param name  the name of the item
   * @param cost  the cost of the item
   * @throws ModelExceptions.NegativeCostException if the cost is negative
   */
  public Item(Member owner, String name, int cost) {
    if (cost < 0) {
      throw new ModelExceptions.NegativeCostException();
    }
    this.id = generateId(); // Assuming ID is generated automatically
    this.name = name;
    this.cost = cost;
    this.owner = new Member(owner); // Store a defensive copy of the owner
  }

  /**
   * Copy constructor for Item.
   *
   * @param other the item to copy
   */
  public Item(Item other) {
    this.id = other.id; // Copy the ID
    this.name = other.name; // Copy the name
    this.cost = other.cost; // Copy the cost
    this.owner = new Member(other.owner); // Store a defensive copy of the owner
  }

  /**
   * Copy constructor for Item with a new owner.
   *
   * @param other    the item to copy
   * @param newOwner the new owner of the item
   */
  public Item(Item other, Member newOwner) {
    this.id = other.id; // Copy the ID
    this.name = other.name; // Copy the name
    this.cost = other.cost; // Copy the cost
    this.owner = new Member(newOwner); // Store a defensive copy of the new owner
  }

  /**
   * Generates a unique ID for the item.
   *
   * @return a unique ID string
   */
  private String generateId() {
    return java.util.UUID.randomUUID().toString();
  }

  /**
   * Returns the unique identifier for this item.
   *
   * @return the unique ID of the item.
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the name of this item.
   *
   * @return the name of the item.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this item.
   *
   * @param name the new name of the item.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the cost of this item.
   *
   * @return the cost of the item.
   */
  public int getCost() {
    return cost;
  }

  /**
   * Sets the cost of this item.
   *
   * @param cost the new cost of the item.
   * @throws ModelExceptions.NegativeCostException if the cost is negative.
   */
  public void setCost(int cost) {
    if (cost < 0) {
      throw new ModelExceptions.NegativeCostException();
    }
    this.cost = cost;
  }

  /**
   * Returns a defensive copy of the owner.
   *
   * @return a copy of the owner
   */
  public Member getOwner() {
    return new Member(owner); // Return a defensive copy of the owner
  }

  /**
   * Sets the owner with a defensive copy.
   *
   * @param owner the new owner of the item
   */
  public void setOwner(Member owner) {
    this.owner = new Member(owner); // Store a defensive copy of the owner
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return id.equals(item.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
