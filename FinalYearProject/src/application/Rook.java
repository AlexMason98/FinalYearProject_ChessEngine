package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;

/**
 * This class is responsible for validating and then setting the position of a Rook piece
 * during a move.
 * 
 * @author Alex Mason
 *
 */
public class Rook {

  public Board board = new Board();
  private Piece piece = new Piece();
  private ArrayList<String> movedRooks = new ArrayList<String>();
  private int pieceInPath = 0;
  
  /**
   * This method is responsible for validating a move when the player has selected a Rook piece.
   * If all validation conditions are met, the method will then call a set position method to move/
   * update the selected Rook's position on the board.
   * 
   * @param player Takes the current player (either "White" or "Black") so this can passed to the
   *       sub-method 'setPos' later on to set the Rook's position.
   * @param selectedRook Takes the selected Rook piece to validate and move that piece.
   * @param toTile The tile the selected Rook intends to move to.
   * @throws InvalidPlayerException if the player passed to the method is not "White" or "Black".
   * @throws InvalidPieceException if an invalid piece is passed to the method.
   */
  public void moveRook(String player, String selectedRook, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
  
    String fromTile = board.map.getTile(selectedRook);
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
      System.out.println("----- Moving Left  -----");
      for (int i = (int)fromColumn - 1; i > (int)toColumn; i--) {
        // If a tile in the Rook's path is not null (occupied by a piece), move is invalid.
        System.out.println("The tile is: " + toRow + String.valueOf((char)i));
        
        if (board.map.getPieceOrOccupation("piecePos", toRow + String.valueOf((char)i)) != null) {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + toRow + String.valueOf((char)i) + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedRook, toTile);
      
    /*
     * MOVING RIGHT. Where the row number remains the same, but the toColumn number is greater than 
     * thefromColumn number. For example, moving from 3B (where B in ASCII is 66) to 3E (where E in 
     * ASCII is 69).
     */
    } else if (fromRow == toRow && (int)toColumn > (int)fromColumn) {
      /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile to the right of the current tile to see if it contains any pieces (player 
      or opponent), until reaching one tile before the Rook's destination tile */
      System.out.println("----- Moving Right -----");
      for (int i = (int)fromColumn + 1; i < (int)toColumn; i++) {
        // If a tile in the Rook's path is not null (occupied by a piece, move is invalid.
        System.out.println("The tile is: " + toRow + String.valueOf((char)i));
        if (board.map.getPieceOrOccupation("tileOccupation", toRow + String.valueOf((char)i)) 
            == "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + toRow + String.valueOf((char)i) + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedRook, toTile);
    /*
     * MOVING UP. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is less than the toRow number. For example, moving from 1A (where 1 is the row) to
     * 3A.
     */
    } else if ((int)fromColumn == (int)toColumn && (fromRow < toRow)) {
      /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile above the current tile to see if it contains any pieces (player or 
      opponent), until reaching one tile before the Rook's destination tile. */
      System.out.println("----- Moving Up -----");
      for (int i = fromRow + 1; i < toRow; i++) {
        System.out.println("The tile is: " + i + fromColumn);
        if (board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf(fromColumn)) 
            == "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + i + fromColumn + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedRook, toTile);
    /*
     * MOVING DOWN. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is greater than the toRow number. For example, moving from 8A (where 8 is the row) to
     * 4A.
     */
    } else if ((int)fromColumn == (int)toColumn && (fromRow > toRow)) {
      /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile below the current tile to see if it contains any pieces (player or 
      opponent), until reaching one tile before the Rook's destination tile. */
      System.out.println("----- Moving Down -----");
      for (int i = fromRow - 1; i > toRow; i--) {
        System.out.println("The tile is: " + i + fromColumn);
        if (board.map.getPieceOrOccupation("piecePos", i + String.valueOf(fromColumn)) 
            != "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Rook's movement path "
              + "(Tile: " + i + fromColumn + ")");
          pieceInPath++;
        }
        
      }
      setPos(player, selectedRook, toTile);
    
    /*
     * If we do not have a horizontal or vertical move, the move must be an illegal move.
     */
    } else {
      System.out.println("Illegal Move. Please move your piece in accordance to the game's rules");
    }
  }
  
  /**
   *  This method is responsible for setting the final position of the Rook to the destination tile
   *  after validation has taken place in the moveRook method. The setPos method also checks for 
   *  the piece in the destination tile, so that if there's an opponent piece in the destination 
   *  tile, the Rook can capture it. If there is the player's own piece in the destination tile, 
   *  the method will indicate this to the player and not move the Rook.
   *  
   * @param player Takes the current player (either "White" or "Black") to validate if the piece 
   *      during an capturing move is an opponent piece.
   * @param selectedRook Takes the selected Rook piece to set it to its destination tile.
   * @param toTile The tile the selected Rook intends to move to (destination tile).
   * @throws InvalidPlayerException if the player passed to the method is not "White" or "Black".
   * @throws InvalidPieceException if an invalid piece is passed to the method.
   */
  public void setPos(String player, String selectedRook, String toTile) throws 
      InvalidPieceException, InvalidPlayerException {
    System.out.println("Lands in setPos");
    /*
     * This 'if' statement is checking there are zero pieces in the Rook's movement path,
     * and the destination tile is empty, so that the Rook can move there.
     */
    if (pieceInPath == 0 && board.map.getPieceOrOccupation("tileOccupation", toTile) == "Empty") {
      board.map.setValue("piecePos", toTile, selectedRook);
    
    /*
     * This 'else if' is for checking there are zero pieces in the Rook's movement path, and
     * the destination tile contains an opponent piece which the Rook can capture.
     */
    } else if (pieceInPath == 0 && piece.isOpponentPiece(player, board.map.getPieceOrOccupation(
        "piecePos", toTile)) == true) {
      board.map.capturePiece(board.map.getPieceOrOccupation("piecePos", toTile));
      board.map.setValue("piecePos", toTile, selectedRook);
    
    /* 
     * This 'else if' assumes the current player's piece is in the destination tile, as the tile is 
     * occupied but not with an opponent piece) 
     */
    } else if (piece.isOpponentPiece(player, selectedRook) == false 
        && board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied") {
      System.out.println("Illegal Move. You cannot move your Rook to a tile containing your own "
          + "piece");
    }
    
    pieceInPath = 0; // Reset counter to avoid potential conflicts later
    
    /* If none of the above conditions are met, do nothing. No error message is needed since this is
    dealt with in the 'movePawn' method */
  }
}
