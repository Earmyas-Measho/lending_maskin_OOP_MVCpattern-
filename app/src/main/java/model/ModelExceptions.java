package model;

/**
 * Contains custom exception classes for various model-related errors.
 */
public class ModelExceptions {

  /**
   * Exception thrown when an email already exists.
   */
  public static class EmailExistsException extends RuntimeException {
  }

  /**
   * Exception thrown when a phone number already exists.
   */
  public static class PhoneExistsException extends RuntimeException {
  }

  /**
   * Exception thrown when negative credits are encountered.
   */
  public static class NegativeCreditsException extends RuntimeException {
  }

  /**
   * Exception thrown when a negative amount is encountered.
   */
  public static class NegativeAmountException extends RuntimeException {
  }

  /**
   * Exception thrown when a negative cost is encountered.
   */
  public static class NegativeCostException extends RuntimeException {
  }

  /**
   * Exception thrown when an invalid end date is encountered.
   */
  public static class InvalidEndDateException extends RuntimeException {
  }

  /**
   * Exception thrown when an ID already exists.
   */
  public static class IdExistsException extends RuntimeException {
  }

  /**
   * Exception thrown when an email has an invalid format.
   */
  public static class InvalidEmailFormatException extends RuntimeException {
  }

  /**
   * Exception thrown when a phone number has an invalid format.
   */
  public static class InvalidPhoneNumberException extends RuntimeException {
  }

  /**
   * Exception thrown when an item is not found.
   */
  public static class ItemNotFoundException extends RuntimeException {
  }

  /**
   * Exception thrown when a borrower has an invalid format.
   */
  public static class BorrowerNotFoundException extends RuntimeException {
  }
}
