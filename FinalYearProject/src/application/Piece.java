package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

public class Piece {
  
  /**
   * This method checks to see if the player has selected a valid piece (a piece which 
   * is theirs and not the other player's). It does this by checking if the piece contains 
   * the player's colour (White or Black), as every chess piece in the game contains the 
   * player's colour in the piece ID.
   * 
   * @return true if chess piece contains the colour of the current player, false otherwise.
   */
  public boolean isValidPiece(String player, String pieceID) throws InvalidPlayerException, 
      InvalidPieceException {
    
    if (!player.equals("White") && !player.equals("Black")) {
      throw new InvalidPlayerException();
    } else if (!pieceID.contains("White") && !pieceID.contains("Black")) {
      throw new InvalidPieceException();
    } else if (pieceID.contains(player)) {
      return true;
    } else {
      //System.out.println("You cannot select the other player's piece");
      return false;
    }
  }
 
  /**
   * This method checks to see what Piece Type we have. It does this
   * by taking the piece passed to the method and seeing what type it
   * contains, as every chess piece ID contains the type in its ID.
   * 
   * @param pieceID the chess piece ID we want to get the type from.
   * @return The type of the chess piece.
   */
  public String pieceType(String pieceID) throws InvalidPieceException {
    if (pieceID.contains("Pawn")) {
      return "Pawn";
    } else if (pieceID.contains("Rook")) {
      return "Rook";
    } else if (pieceID.contains("Knight")) {
      return "Knight";
    } else if (pieceID.contains("Bishop")) {
      return "Bishop";
    } else if (pieceID.contains("Queen")) {
      return "Queen";
    } else if (pieceID.contains("King")) {
      return "King";
    } else {
      throw new InvalidPieceException();
    }
  }

  /**
   * This method checks to see if the tile the player is moving to has an opponent's piece inside 
   * or not.
   * 
   * @param player takes the current player (White or Black)
   * @param pieceID takes the target piece 
   * @return true if the target piece is the opponent's, false if it isn't
   * @throws InvalidPlayerException if player is anything other than White or Black
   * @throws InvalidPieceException if piece ID doesn't contain White or Black
   */
  public boolean isOpponentPiece(String player, String pieceID) throws InvalidPlayerException,
      InvalidPieceException {

    if (!player.equals("White") && !player.equals("Black")) {
      throw new InvalidPlayerException();
    } else if (!pieceID.contains("White") && !pieceID.contains("Black")) {
      throw new InvalidPieceException();
    } else if (!pieceID.contains(player)) {
      return true;
    } else {
      return false;
    }
  }
}
