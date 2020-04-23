package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;

public class King {

  private Piece piece = new Piece();
  public ArrayList<String> movedKing = new ArrayList<String>();
  public boolean passedValidation = false;
  
  /**
   * This method is responsible for validating and then moving a King piece.
   * 
   * @param player Takes the current player (either "White" or "Black").
   * @param selectedPiece Takes the selected King piece.
   * @param toTile Takes the destination tile the piece intends to move to.
   * @throws InvalidPlayerException If the player passed is not "White" or "Black".
   * @throws InvalidPieceException If we have an invalid piece.
   */
  public void moveKing(String player, String selectedPiece, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
    String fromTile = Board.board.map.getTile(selectedPiece);
    
    int fromRow = Character.getNumericValue(fromTile.charAt(0));
    int fromColumn = Character.getNumericValue(fromTile.charAt(1));
    int toRow = Character.getNumericValue(toTile.charAt(0));
    int toColumn = Character.getNumericValue(toTile.charAt(1));
    // 1A, 1 is row, A is column
    
    // MOVING LEFT
    if (fromRow == toRow && (toColumn == fromColumn - 1)) {
      System.out.println("Lands in Left");
      setPos(player, selectedPiece, toTile);
    
    // MOVING RIGHT
    } else if (fromRow == toRow && (toColumn == fromColumn + 1)) {
      System.out.println("Lands in Right");
      setPos(player, selectedPiece, toTile);
    
    // MOVING UP
    } else if (fromColumn == toColumn && (toRow == fromRow + 1)) {
      System.out.println("Lands in Up");
      setPos(player, selectedPiece, toTile);
    
    // MOVING DOWN
    } else if (fromColumn == toColumn && (toRow == fromRow - 1)) {
      System.out.println("Lands in Down");
      setPos(player, selectedPiece, toTile);

    // MOVING NORTH EAST DIAGONALLY
    } else if ((toColumn == fromColumn + 1) && (toRow == fromRow + 1)) {
      System.out.println("Lands in North East");
      setPos(player, selectedPiece, toTile);
    
    // MOVING SOUTH EAST DIAGONALLY
    } else if ((toColumn == fromColumn + 1) && (toRow == fromRow - 1)) {
      System.out.println("Lands in South East");
      setPos(player, selectedPiece, toTile);
    
    // MOVING SOUTH WEST DIAGONALLY
    } else if ((toColumn == fromColumn - 1) && (toRow == fromRow - 1)) {
      System.out.println("Lands in South West");
      setPos(player, selectedPiece, toTile);
    
    // MOVING NORTH WEST DIAGONALLY
    } else if ((toColumn == fromColumn - 1) && (toRow == fromRow + 1)) {
      System.out.println("Lands in North West");
      setPos(player, selectedPiece, toTile);
    
    } else {
      System.out.println("Illegal Move. Please move your King in accordance to the game's rules");
      passedValidation = false;
    }
  }
  
  /**
   * This method is called from the moveKing method above. Its purpose is to minimise redundant/
   * duplicate code by setting the position of the King's new tile in here.
   * 
   * @param player Takes the current player (Either "White" or "Black").
   * @param selectedPiece Takes the current piece.
   * @param toTile Takes the destination tile the player intends the King to move to.
   */
  public void setPos(String player, String selectedPiece, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
    // If the destination tile is empty
    if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Empty") {
    	System.out.println("Lands in Tile Empty");
      passedValidation = true;
      movedKing.add(selectedPiece);
      Board.board.map.setValue("piecePos", toTile, selectedPiece);
    
    // If the destination tile is occupied with an opponent player's piece
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
        && piece.isOpponentPiece(player, Board.board.map.getPieceOrOccupation("piecePos", toTile)) 
        == true) {
    	System.out.println("Lands in Opponent Piece");
      passedValidation = true;
      movedKing.add(selectedPiece);
      Board.board.map.capturePiece(Board.board.map.getPieceOrOccupation("piecePos", toTile));
      Board.board.map.setValue("piecePos", toTile, selectedPiece);
    
    // If the destination tile is occupied with player's own piece
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
         && piece.isOpponentPiece(player, Board.board.map.getPieceOrOccupation("piecePos", toTile)) 
         == false) {
      System.out.println("KING CLASS: LANDS IN HERE");
      // ADD A CONDITION TO CHECK FOR CASES WHEN CASTLING
      System.out.println("Illegal Move. You cannot move your King on your own piece");
      
    // Else, do not set the position of King and display message.
    } else {
      System.out.println("Invalid Move");
    }
  }
  
}
