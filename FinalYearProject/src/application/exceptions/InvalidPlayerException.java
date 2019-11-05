package application.exceptions;

public class InvalidPlayerException extends Exception {

  /**
   * Gives this exception class a generated version number.
   */
  private static final long serialVersionUID = -5393817741315290571L;

  /**
   * Constructor for my exception. If the exception is thrown, it will display
   * the message below.
   */
  public InvalidPlayerException() {
    super("Invalid Player, Must be White or Black");
  }
}
