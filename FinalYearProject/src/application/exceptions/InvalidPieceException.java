package application.exceptions;

public class InvalidPieceException extends Exception {

  /**
   * Gives this exception class a generated version number.
   */
  private static final long serialVersionUID = -5579552056410930290L;

  /**
   * Constructor for my exception. If the exception is thrown, it will display
   * the message below.
   */
  public InvalidPieceException() {
    super("Invalid Piece! A piece ID must contain player colour, piece type, and piece number");
  }
}