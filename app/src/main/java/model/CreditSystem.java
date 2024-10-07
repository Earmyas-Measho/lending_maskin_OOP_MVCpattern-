package model;

/**
 * Handles the credit operations for members in the system.
 */
public class CreditSystem {
  private final MemberRepository memberRepository;

  /**
   * Constructs a CreditSystem with the specified member repository.
   *
   * @param memberRepository the member repository to use for member operations
   */
  public CreditSystem(MemberRepository memberRepository) {
    this.memberRepository = new MemberRepository(memberRepository);
  }

  /**
   * Adds credits to a member's account.
   *
   * @param member the member to add credits to
   * @param amount the amount of credits to add
   */
  public void addCredits(Member member, int amount) {
    if (member != null) {
      member.addCredits(amount);
    }
  }

  /**
   * Deducts credits from a member's account.
   *
   * @param member the member to deduct credits from
   * @param amount the amount of credits to deduct
   */
  public void deductCredits(Member member, int amount) {
    if (member != null) {
      member.deductCredits(amount);
    }
  }
}
