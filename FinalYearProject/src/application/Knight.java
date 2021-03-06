package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

public class Knight {

  private Piece piece = new Piece();
  public boolean passedValidation = false;
  
  /**
   * This method is responsible for validating and then moving a Knight
   * chess piece, in accordance to the game's rules.
   * 
   * @param player Takes the current player (Either "White" or "Black").
   * @param selectedKnight Takes the selected Knight piece.
   * @param toTile The destination tile that the Knight intends to move to.
   * @throws InvalidPlayerException If the player is not "White" or "Black".
   * @throws InvalidPieceException If an invalid piece is passed to the method.
   */
  public void moveKnight(String player, String selectedKnight, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
    
    String fromTile = Board.board.map.getTile(selectedKnight);
    
    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
    char toColumn = toTile.charAt(1);
    
    // MOVING UP 2 SPACES AND 1 SPACE LEFT
    if ((toRow == fromRow + 2) && (toColumn == fromColumn - 1)) {
      setPos(player, selectedKnight, toTile);
    
    // MOVING UP 2 SPACES AND 1 SPACE RIGHT
    } else if ((toRow == fromRow + 2) && (toColumn == fromColumn + 1)) {
      setPos(player, selectedKnight, toTile);
    
    // MOVING RIGHT 2 SPACES AND 1 SPACE UP
    } else if ((toColumn == fromColumn + 2) && (toRow == fromRow + 1)) {
      setPos(player, selectedKnight, toTile);
    
    // MOVING RIGHT 2 SPACES AND 1 SPACE DOWN
    } else if ((toColumn == fromColumn + 2) && (toRow == fromRow - 1)) {
      setPos(player, selectedKnight, toTile);
      
    // MOVING DOWN 2 SPACES AND 1 SPACE LEFT
    } else if ((toRow == fromRow - 2) && (toColumn == fromColumn - 1)) {
      setPos(player, selectedKnight, toTile);
    
    // MOVING DOWN 2 SPACES AND 1 SPACE RIGHT
    } else if ((toRow == fromRow - 2) && (toColumn == fromColumn + 1)) {
      setPos(player, selectedKnight, toTile);
    
    // MOVING LEFT 2 SPACES AND 1 SPACE UP
    } else if ((toColumn == fromColumn - 2) && (toRow == fromRow + 1)) {
      setPos(player, selectedKnight, toTile);
    
    // MOVING LEFT 2 SPACES AND 1 SPACE DOWN
    } else if ((toColumn == fromColumn - 2) && (toRow == fromRow - 1)) {
      setPos(player, selectedKnight, toTile);
    
    /* If we have none of the above combinations which are valid moves for a Knight, print "Illegal
     * Move". */
    } else {
      if (!Board.board.map.valueLock) {
        System.out.println("Illegal Move. Please move your Knight in accordance to the game's "
            + "rules");
      }
    }
  }
  
  /**
   * This method is called from the moveKnight method above. Its purpose is to minimise redundant/
   * duplicate code by setting the position of the Knight's new tile in here.
   * 
   * @param player Takes the current player (Either "White" or "Black").
   * @param selectedKnight Takes the selected Knight piece.
   * @param toTile Takes the destination tile the player intends the Knight to move to.
   */
  public void setPos(String player, String selectedKnight, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
    // If the destination tile is empty
    if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Empty") {
      passedValidation = true;
      Board.board.map.setValue("piecePos", toTile, selectedKnight);
    
    // If the destination tile is occupied with an opponent player's piece
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
        && piece.isOpponentPiece(player, Board.board.map.getPieceOrOccupation("piecePos", toTile)) 
        == true) {
      passedValidation = true;
      Board.board.map.capturePiece(Board.board.map.getPieceOrOccupation("piecePos", toTile));
      Board.board.map.setValue("piecePos", toTile, selectedKnight);
    
    // If the destination tile is occupied with player's own piece
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
         && piece.isOpponentPiece(player, Board.board.map.getPieceOrOccupation("piecePos", toTile)) 
         == false) {
      if (!Board.board.map.valueLock) {
        System.out.println("Illegal Move. You cannot move your Knight in a tile containing "
            + "your own piece");
      }
      
    // Else, do not set the position of Knight and display message.
    } else {
      if (!Board.board.map.valueLock) {
        System.out.println("Invalid Move");
      } 
    }
  }
}
