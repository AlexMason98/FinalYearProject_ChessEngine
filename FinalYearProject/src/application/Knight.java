package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

public class Knight {

  public Board board = new Board();
  private Piece piece = new Piece();
  
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
    
    String fromTile = board.map.getTile(selectedKnight);
    
    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
    char toColumn = toTile.charAt(1);
    
    // MOVING UP 2 SPACES AND 1 SPACE LEFT
    if ((toRow == fromRow + 2) && (toColumn == fromColumn - 1)) {
      System.out.println("----- Moving 2 Spaces Up and 1 Space Left -----");
      setPos(player, selectedKnight, toTile);
    
    // MOVING UP 2 SPACES AND 1 SPACE RIGHT
    } else if ((toRow == fromRow + 2) && (toColumn == fromColumn + 1)) {
      System.out.println("----- Moving 2 Spaces Up and 1 Space Right -----");
      setPos(player, selectedKnight, toTile);
    
    // MOVING RIGHT 2 SPACES AND 1 SPACE UP
    } else if ((toColumn == fromColumn + 2) && (toRow == fromRow + 1)) {
      System.out.println("----- Moving 2 Spaces Right and 1 Space Up -----");
      setPos(player, selectedKnight, toTile);
    
    // MOVING RIGHT 2 SPACES AND 1 SPACE DOWN
    } else if ((toColumn == fromColumn + 2) && (toRow == fromRow - 1)) {
      System.out.println("----- Moving 2 Spaces Right and 1 Space Down -----");
      setPos(player, selectedKnight, toTile);
      
    // MOVING DOWN 2 SPACES AND 1 SPACE LEFT
    } else if ((toRow == fromRow - 2) && (toColumn == fromColumn - 1)) {
      System.out.println("----- Moving 2 Spaces Down and 1 Space Left -----");
      setPos(player, selectedKnight, toTile);
    
    // MOVING DOWN 2 SPACES AND 1 SPACE RIGHT
    } else if ((toRow == fromRow - 2) && (toColumn == fromColumn + 1)) {
      System.out.println("----- Moving 2 Spaces Down and 1 Space Right -----");
      setPos(player, selectedKnight, toTile);
    
    // MOVING LEFT 2 SPACES AND 1 SPACE UP
    } else if ((toColumn == fromColumn - 2) && (toRow == fromRow + 1)) {
      System.out.println("----- Moving 2 Spaces Left and 1 Space Up -----");
      setPos(player, selectedKnight, toTile);
    
    // MOVING LEFT 2 SPACES AND 1 SPACE DOWN
    } else if ((toColumn == fromColumn - 2) && (toRow == fromRow - 1)) {
      System.out.println("----- Moving 2 Spaces Left and 1 Space Down -----");
      setPos(player, selectedKnight, toTile);
    
    /* If we have none of the above combinations which are valid moves for a Knight, print "Illegal
     * Move". */
    } else {
      System.out.println("Illegal Move. Please move your Knight in accordance to the game's rules");
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
    if (board.map.getPieceOrOccupation("tileOccupation", toTile) == "Empty") {
      board.map.setValue("piecePos", toTile, selectedKnight);
    
    // If the destination tile is occupied with an opponent player's piece
    } else if (board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
        && piece.isOpponentPiece(player, board.map.getPieceOrOccupation("piecePos", toTile)) 
        == true) {
      board.map.capturePiece(board.map.getPieceOrOccupation("piecePos", toTile));
      board.map.setValue("piecePos", toTile, selectedKnight);
    
    // If the destination tile is occupied with player's own piece
    } else if (board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
         && piece.isOpponentPiece(player, board.map.getPieceOrOccupation("piecePos", toTile)) 
         == false) {
      System.out.println("Illegal Move. You cannot move your Knight in a tile containing "
          + "your own piece");
      
    // Else, do not set the position of Knight and display message.
    } else {
      System.out.println("Invalid Move");
    }
  }
}
