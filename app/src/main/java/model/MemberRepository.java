package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository class for managing a collection of members.
 */
public class MemberRepository {
  private List<Member> members;

  /**
   * Constructs a MemberRepository with an empty list of members.
   */
  public MemberRepository() {
    this.members = new ArrayList<>();
  }

  /**
   * Copy constructor for MemberRepository.
   *
   * <p>
   * This constructor creates a new MemberRepository instance by copying the
   * members from another MemberRepository instance. This helps to prevent
   * exposing the internal representation of the members list.
   * </p>
   *
   * @param other the MemberRepository instance to copy from
   */
  public MemberRepository(MemberRepository other) {
    this.members = new ArrayList<>(other.members);
  }

  /**
   * Validates a member to ensure there are no duplicate IDs, emails, or phone
   * numbers.
   * Throws exceptions if any duplicates are found.
   *
   * @param member the member to be validated
   * @throws ModelExceptions.IdExistsException    if a member with the same ID
   *                                              already exists
   * @throws ModelExceptions.EmailExistsException if a member with the same email
   *                                              already exists
   * @throws ModelExceptions.PhoneExistsException if a member with the same phone
   *                                              number already exists
   */
  public void validateMember(Member member) {
    // Validate ID uniqueness
    if (members.stream().anyMatch(m -> m.getId().equals(member.getId()))) {
      throw new ModelExceptions.IdExistsException();
    }

    // Validate email uniqueness
    if (members.stream().anyMatch(m -> m.getEmail().equals(member.getEmail()))) {
      throw new ModelExceptions.EmailExistsException();
    }

    // Validate phone number uniqueness
    if (members.stream().anyMatch(m -> m.getPhone().equals(member.getPhone()))) {
      throw new ModelExceptions.PhoneExistsException();
    }
  }

  /**
   * Adds a member to the repository after validation.
   *
   * @param member the member to be added
   */
  public void addMember(Member member) {
    validateMember(member);
    members.add(member);
  }

  /**
   * Deletes a member from the repository.
   *
   * @param member the member to be deleted
   */
  public void deleteMember(Member member) {
    members.remove(member);
  }

  /**
   * Retrieves a member from the repository by their email.
   *
   * @param email the email of the member to be retrieved
   * @return the member with the specified email, or null if not found
   */
  public Member getMemberByEmail(String email) {
    return members.stream().filter(member -> member.getEmail().equals(email)).findFirst().orElse(null);
  }

  /**
   * Retrieves a member from the repository by their phone number.
   *
   * @param phone the phone number of the member to be retrieved
   * @return the member with the specified phone number, or null if not found
   */
  public Member getMemberByPhone(String phone) {
    return members.stream().filter(member -> member.getPhone().equals(phone)).findFirst().orElse(null);
  }

  /**
   * Checks if a member exists in the repository.
   *
   * @param member the member to check for
   * @return true if the member exists, false otherwise
   */
  public boolean memberExists(Member member) {
    return members.contains(member);
  }

  /**
   * Checks if a member with the specified email exists in the repository.
   *
   * @param email the email to check for
   * @return true if a member with the specified email exists, false otherwise
   */
  public boolean emailExists(String email) {
    return members.stream().anyMatch(member -> member.getEmail().equals(email));
  }

  /**
   * Checks if a member with the specified phone number exists in the repository.
   *
   * @param phone the phone number to check for
   * @return true if a member with the specified phone number exists, false
   *         otherwise
   */
  public boolean phoneExists(String phone) {
    return members.stream().anyMatch(member -> member.getPhone().equals(phone));
  }

  /**
   * Returns a list of all members in the repository.
   *
   * @return a list of all members
   */
  public List<Member> getAllMembers() {
    return Collections.unmodifiableList(new ArrayList<>(members));
  }
}
