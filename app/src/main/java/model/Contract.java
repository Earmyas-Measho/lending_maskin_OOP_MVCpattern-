package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a contract between a borrower and an item for a specified period.
 */
public class Contract {
  private final String id;
  private final Item item;
  private final Member borrower;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private boolean active;

  /**
   * Constructs a Contract with the specified details.
   *
   * @param id        the unique identifier for the contract
   * @param item      the item involved in the contract
   * @param borrower  the member who is borrowing the item
   * @param startDate the start date of the contract
   * @param endDate   the end date of the contract
   * @throws ModelExceptions.InvalidEndDateException if the end date is before the
   *                                                 start date
   */
  public Contract(String id, Item item, Member borrower, LocalDate startDate, LocalDate endDate) {
    if (endDate.isBefore(startDate)) {
      throw new ModelExceptions.InvalidEndDateException();
    }
    this.id = id;
    this.item = new Item(item); // Create a defensive copy of the Item
    this.borrower = new Member(borrower); // Create a defensive copy of the Member
    this.startDate = startDate;
    this.endDate = endDate;
    this.active = true;
  }

  /**
   * Copy constructor for Contract.
   *
   * @param other       the contract to copy
   * @param newItem     the new item involved in the contract
   * @param newBorrower the new borrower of the item
   */
  public Contract(Contract other, Item newItem, Member newBorrower) {
    this(other.id, newItem, newBorrower, other.startDate, other.endDate);
    this.active = other.active;
  }

  public String getId() {
    return id;
  }

  public Item getItem() {
    return new Item(item); // Return a defensive copy of the Item
  }

  public Member getBorrower() {
    return new Member(borrower); // Return a defensive copy of the Member
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Checks if this contract conflicts with another contract.
   *
   * @param other the other contract to check for a conflict
   * @return true if the contracts conflict, false otherwise
   */
  public boolean conflictsWith(Contract other) {
    return this.item.equals(other.getItem())
        && this.active
        && other.isActive()
        && !(this.endDate.isBefore(other.getStartDate())
            || this.startDate.isAfter(other.getEndDate()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contract contract = (Contract) o;
    return id.equals(contract.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
