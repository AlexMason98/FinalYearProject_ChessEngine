package application;

import java.util.ArrayList;

public class Rook {

  public Board board = new Board();
  private ArrayList<String> movedRooks = new ArrayList<String>();
  
  /**
   * This method is responsible for validating a move when the player has selected a Rook piece.
   * If all validation conditions are met, the method will then call a set position method to move/
   * update the selected Rook's position on the board.
   * 
   * @param player Takes the current player (either "White" or "Black") to validate if the piece 
   *      during an capturing move is an opponent piece.
   * @param selectedPiece Takes the selected Rook piece to validate and move that piece.
   * @param toTile The tile the selected Rook intends to move to.
   */
  public void moveRook(String player, String selectedPiece, String toTile) {
    /*
     * Rules to Remember:
     * 1) If Rook has moved, Rook cannot castle
     * 2) Can move any number of squares vertically or horizontally,
     * as long as the squares are vacant.
     * 
     * Row = Row Number, Column = Column Letter
     */
    
    String fromTile = board.map.getTile(selectedPiece);
    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
    char toColumn = toTile.charAt(1);
    
    /* MOVING LEFT. Where the row number remains the same, but the fromColumn is greater than the
     * toColumn. For example, moving from 8H (where H in ASCII is 72) to 8A (where A in ASCII is 
     * 65).
     */
    if (fromRow == toRow && (int)fromColumn > (int)toColumn) {
      // CHECK FOR OPPOSITION PIECES
    
    /*
     * MOVING RIGHT. Where the row number remains the same, but the fromColumn is less than the
     * toColumn. For example, moving from 3B (where B in ASCII is 66) to 3E (where E in ASCII is 
     * 69).
     */
    } else if (fromRow == toRow && (int)fromColumn > (int)toColumn) {
      // CHECK FOR OPPOSITION PIECES
    
    /*
     * MOVING UP. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is less than the toRow number. For example, moving from 1A (where 1 is the row) to
     * 3A.
     */
    } else if ((int)fromColumn == (int)toColumn && (fromRow < toRow)) {
      // CHECK FOR OPPOSITION PIECES
      
    /*
     * MOVING DOWN. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is greater than the toRow number. For example, moving from 8A (where 8 is the row) to
     * 4A.
     */
    } else if ((int)fromColumn == (int)toColumn && (fromRow > toRow)) {
      // CHECK FOR OPPOSITION PIECES
    
    /*
     * If we do not have a horizontal or vertical move, the move must be an illegal move.
     */
    } else {
      System.out.println("Illegal Move. Please move your piece in accordance to the game's rules");
    }
  }
}
