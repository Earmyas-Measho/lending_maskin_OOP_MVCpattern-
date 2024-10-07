package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a member with an ID, name, email, phone number, credits, and a
 * list of items. Ensures that the credits are not negative.
 */
public class Member {
  private final String id;
  private String name;
  private String email;
  private String phone;
  private int credits;
  private final List<Item> items;
  private final List<Contract> contracts;

  /**
   * Constructs a Member with the specified ID, name, email, phone number.
   *
   * @param id           the ID of the member
   * @param name         the name of the member
   * @param email        the email of the member
   * @param phone        the phone number of the member
   * @param credits      the credits of the member
   * @param emailPattern the pattern to validate the email format
   * @param phonePattern the pattern to validate the phone number format
   * @throws ModelExceptions.NegativeCreditsException    if the credits are
   *                                                     negative
   * @throws ModelExceptions.InvalidEmailFormatException if the email format is
   *                                                     invalid
   * @throws ModelExceptions.InvalidPhoneNumberException if the phone number
   *                                                     format is invalid
   */
  public Member(String id, String name, String email, String phone, int credits, String emailPattern,
      String phonePattern) {
    if (credits < 0) {
      throw new ModelExceptions.NegativeCreditsException();
    }
    if (!isValidEmail(email, emailPattern)) {
      throw new ModelExceptions.InvalidEmailFormatException();
    }
    if (!isValidPhoneNumber(phone, phonePattern)) {
      throw new ModelExceptions.InvalidPhoneNumberException();
    }
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.credits = credits;
    this.items = new ArrayList<>();
    this.contracts = new ArrayList<>();
  }

  /**
   * Constructs a new Member by copying the attributes of another Member.
   *
   * @param other the Member object to copy
   */
  public Member(Member other) {
    this.id = other.id;
    this.name = other.name;
    this.email = other.email;
    this.phone = other.phone;
    this.credits = other.credits;
    this.items = new ArrayList<>();
    this.contracts = new ArrayList<>();
    // Deep copy of items and contracts
    for (Item item : other.items) {
      this.addItem(new Item(item, this));
    }
    for (Contract contract : other.contracts) {
      this.addContract(new Contract(contract, contract.getItem(), this));
    }
  }

  /**
   * Returns the ID of the member.
   *
   * @return the ID of the member
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the name of the member.
   *
   * @return the name of the member
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the member.
   *
   * @param name the new name of the member
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the email of the member.
   *
   * @return the email of the member
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the member.
   *
   * @param email        the new email of the member.
   *
   * @param emailPattern the pattern to validate the email format
   * @throws ModelExceptions.InvalidEmailFormatException if the email format is
   *                                                     invalid
   */
  public void setEmail(String email, String emailPattern) {
    if (!isValidEmail(email, emailPattern)) {
      throw new ModelExceptions.InvalidEmailFormatException();
    }
    this.email = email;
  }

  /**
   * Returns the phone number of the member.
   *
   * @return the phone number of the member
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the phone number of the member.
   *
   * @param phone        the new phone number of the member.
   *
   * @param phonePattern the pattern to validate the phone number format
   * @throws ModelExceptions.InvalidPhoneNumberException if the phone number
   *                                                     format is invalid
   */
  public void setPhone(String phone, String phonePattern) {
    if (!isValidPhoneNumber(phone, phonePattern)) {
      throw new ModelExceptions.InvalidPhoneNumberException();
    }
    this.phone = phone;
  }

  /**
   * Returns the credits of the member.
   *
   * @return the credits of the member
   */
  public int getCredits() {
    return credits;
  }

  /**
   * Adds credits to the member's account.
   * Throws a NegativeAmountException if the amount is negative.
   *
   * @param amount the amount of credits to be added
   * @throws ModelExceptions.NegativeAmountException if the amount is negative
   */
  public void addCredits(int amount) {
    if (amount < 0) {
      throw new ModelExceptions.NegativeAmountException();
    }
    this.credits += amount;
  }

  /**
   * Deducts credits from the member's account.
   * Throws a NegativeAmountException if the amount is negative.
   *
   * @param amount the amount of credits to be deducted
   * @throws ModelExceptions.NegativeAmountException if the amount is negative
   */
  public void deductCredits(int amount) {
    if (amount < 0) {
      throw new ModelExceptions.NegativeAmountException();
    }
    this.credits -= amount;
  }

  /**
   * Returns the list of items associated with the member.
   *
   * @return an unmodifiable list of items
   */
  public List<Item> getItems() {
    return Collections.unmodifiableList(new ArrayList<>(items));
  }

  /**
   * Adds an item to the member's list of items and sets this member as the
   * owner.
   *
   * @param item the item to be added
   */
  public void addItem(Item item) {
    // Check if the item is not already owned by this member
    if (item != null && !items.contains(item)) {
      items.add(item);

      // Set the item's owner to this member
      if (item.getOwner() != this) {
        item.setOwner(this);
      }
    }
  }

  /**
   * Removes an item from the member's list of items.
   *
   * @param item the item to be removed
   */
  public void removeItem(Item item) {
    if (items.remove(item)) {
      item.setOwner(null); // Break bidirectional association
    }
  }

  /**
   * Returns the list of contracts associated with the member.
   *
   * @return an unmodifiable list of contracts
   */
  public List<Contract> getContracts() {
    return Collections.unmodifiableList(new ArrayList<>(contracts));
  }

  /**
   * Adds a contract to the member's list of contracts.
   *
   * @param contract the contract to be added
   */
  public void addContract(Contract contract) {
    if (contract != null && !contracts.contains(contract)) {
      this.contracts.add(contract);
    }
  }

  /**
   * Removes a contract from the member's list of contracts.
   *
   * @param contract the contract to be removed
   */
  public void removeContract(Contract contract) {
    this.contracts.remove(contract);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Member member = (Member) o;
    return id.equals(member.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  /**
   * Validates the email format.
   *
   * @param email        the email to validate
   * @param emailPattern the pattern to validate the email format
   * @return true if the email is valid, false otherwise
   */
  private boolean isValidEmail(String email, String emailPattern) {
    return email != null && email.matches(emailPattern);
  }

  /**
   * Validates the phone number format.
   *
   * @param phone        the phone number to validate
   * @param phonePattern the pattern to validate the phone number format
   * @return true if the phone number is valid, false otherwise
   */
  private boolean isValidPhoneNumber(String phone, String phonePattern) {
    return phone != null && phone.matches(phonePattern);
  }
}
