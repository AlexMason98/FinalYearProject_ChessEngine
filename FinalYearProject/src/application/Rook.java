package application;

import java.util.ArrayList;

public class Rook {

  public Board board = new Board();
  private ArrayList<String> movedRooks = new ArrayList<String>();
  private int pieceInPath = 0;
  
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
      /* CHECKING FOR ANY PIECES IN ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile to the left of the current one to see if it contains any pieces, until
      reaching one tile before the Rook's destination tile */
      System.out.println("Moving Left");
      for (int i = (int)fromColumn - 1; i > (int)toColumn; i--) {
        // If a tile in the Rook's path is not null (occupied by a piece), move is invalid.
        System.out.println("The tile is: " + toRow + String.valueOf((char)i));
        
        if (board.map.getPieceOrOccupation("piecePos", toRow + String.valueOf((char)i)) != null) {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + toRow + String.valueOf((char)i) + ")");
          pieceInPath++;
        }
      }
      
      /* CHECKING FOR PIECE IN ROOK'S DESTINATION TILE.
      This is to see if there is an opponent piece the Rook is intending to capture. There is also
      validation to ensure there isn't a piece of the current player's in the destination tile 
      before finalising a move there. */
      
      //System.out.println("Illegal Move. There is a piece in the movement path of your Rook");
    /*
     * MOVING RIGHT. Where the row number remains the same, but the toColumn number is greater than 
     * thefromColumn number. For example, moving from 3B (where B in ASCII is 66) to 3E (where E in 
     * ASCII is 69).
     */
    } else if (fromRow == toRow && (int)toColumn > (int)fromColumn) {
      /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile to the right of the current tile to see if it contains any pieces (player 
      or opponent), until reaching one tile before the Rook's destination tile */
      System.out.println("Moving Right");
      for (int i = (int)fromColumn + 1; i < (int)toColumn; i++) {
        // If a tile in the Rook's path is not null (occupied by a piece, move is invalid.
        System.out.println("The tile is: " + toRow + String.valueOf((char)i));
        if (board.map.getPieceOrOccupation("piecePos", toRow + String.valueOf((char)i)) != null) {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + toRow + String.valueOf((char)i) + ")");
          pieceInPath++;
        }
      }
    /*
     * MOVING UP. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is less than the toRow number. For example, moving from 1A (where 1 is the row) to
     * 3A.
     */
    } else if ((int)fromColumn == (int)toColumn && (fromRow < toRow)) {
      /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile above the current tile to see if it contains any pieces (player or 
      opponent), until reaching one tile before the Rook's destination tile. */
      System.out.println("Moving Up");
      for (int i = fromRow + 1; i < toRow; i++) {
        System.out.println("The tile is: " + i + fromColumn);
        if (board.map.getPieceOrOccupation("piecePos", i + String.valueOf(fromColumn)) != null) {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + i + fromColumn + ")");
          pieceInPath++;
        }
      }
    /*
     * MOVING DOWN. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is greater than the toRow number. For example, moving from 8A (where 8 is the row) to
     * 4A.
     */
    } else if ((int)fromColumn == (int)toColumn && (fromRow > toRow)) {
      // CHECK FOR OPPOSITION PIECES
      System.out.println("Moving Down");
      for (int i = fromRow - 1; i > toRow; i--) {
        System.out.println("The tile is: " + i + fromColumn);
        if (board.map.getPieceOrOccupation("piecePos", i + String.valueOf(fromColumn)) != null) {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + i + fromColumn + ")");
          pieceInPath++;
        }
      }
    
    /*
     * If we do not have a horizontal or vertical move, the move must be an illegal move.
     */
    } else {
      System.out.println("Illegal Move. Please move your piece in accordance to the game's rules");
    }
  }
}
