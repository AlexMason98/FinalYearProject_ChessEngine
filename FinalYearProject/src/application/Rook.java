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

  private Piece piece = new Piece();
  public ArrayList<String> movedRooks = new ArrayList<String>();
  private int pieceInPath = 0;
  public boolean passedValidation = false;
  
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
  
    if (toTile != Board.board.map.getTile(selectedRook)) {
      String fromTile = Board.board.map.getTile(selectedRook);
      int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
      char fromColumn = fromTile.charAt(1);
      int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
      char toColumn = toTile.charAt(1);
    
      /* MOVING LEFT. Where the row number remains the same, but the fromColumn is greater than the
       * toColumn. For example, moving from 8H (where H in ASCII is 72) to 8A (where A in ASCII is 
       * 65).
       */
      if (fromRow == toRow && fromColumn > toColumn) {
        /* CHECKING FOR ANY PIECES IN ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
        I search each tile to the left of the current one to see if it contains any pieces, until
        reaching one tile before the Rook's destination tile */

        for (int i = fromColumn - 1; i > toColumn; i--) {
         
          // If a tile in the Rook's path is not null (occupied by a piece), move is invalid.
          if (Board.board.map.getPieceOrOccupation("tileOccupation", toRow + String.valueOf(
              + (char)i)) == "Occupied" && piece.isOpponentPiece(player, 
                  Board.board.map.getPieceOrOccupation("piecePos", toRow + String.valueOf(
                      (char)i))) == false) {
            if (!Board.board.map.valueLock) {
              System.out.println("Illegal Move. There is a piece in your Rook's movement path "
                  + "(Tile: " + toRow + String.valueOf((char)i) + ")");
            }
            pieceInPath++;
          }
        }
        setPos(player, selectedRook, toTile);
      
      /*
       * MOVING RIGHT. Where the row number remains the same, but the toColumn number is greater
       * than the fromColumn number. For example, moving from 3B (where B in ASCII is 66) to 3E
       * (where E in ASCII is 69).
       */
      } else if (fromRow == toRow && toColumn > fromColumn) {
        /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
        I search each tile to the right of the current tile to see if it contains any pieces
        (player or opponent), until reaching one tile before the Rook's destination tile */

        for (int i = fromColumn + 1; i < toColumn; i++) {
        
          // If a tile in the Rook's path is not null (occupied by a piece, move is invalid.
          if (Board.board.map.getPieceOrOccupation("tileOccupation", toRow + String.valueOf(
              (char)i)) == "Occupied" && piece.isOpponentPiece(player, 
                Board.board.map.getPieceOrOccupation("piecePos", toRow + String.valueOf(
                    (char)i))) == false) {
            if (!Board.board.map.valueLock) {
              System.out.println("Illegal Move. There is a piece in your Rook's movement path "
                  + "(Tile: " + toRow + String.valueOf((char)i) + ")");
            }
            pieceInPath++;
          }
        }
        setPos(player, selectedRook, toTile);
      /*
       * MOVING UP. Where the column letter (as an ASCII value) remains the same, but the fromRow
       * number is less than the toRow number. For example, moving from 1A (where 1 is the row) to
       * 3A.
       */
      } else if (fromColumn == toColumn && (fromRow < toRow)) {
        /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
        I search each tile above the current tile to see if it contains any pieces (player or 
        opponent), until reaching one tile before the Rook's destination tile. */

        for (int i = fromRow + 1; i < toRow; i++) {

          if (Board.board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf(
              fromColumn)) == "Occupied" && piece.isOpponentPiece(player, 
                  Board.board.map.getPieceOrOccupation("piecePos", i + String.valueOf(
                      fromColumn))) == false) {

            if (!Board.board.map.valueLock) {
              System.out.println("Illegal Move. There is a piece in your Rook's movement path "
                  + "(Tile: " + i + fromColumn + ")");
            }
            pieceInPath++;
          }
        }
        setPos(player, selectedRook, toTile);
      /*
       * MOVING DOWN. Where the column letter (as an ASCII value) remains the same, but the fromRow
       * number is greater than the toRow number. For example, moving from 8A (where 8 is the row)
       * to 4A.
       */
      } else if (fromColumn == toColumn && (fromRow > toRow)) {
        /* CHECKING FOR ANY PIECES IN THE ROOK'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
        I search each tile below the current tile to see if it contains any pieces (player or 
        opponent), until reaching one tile before the Rook's destination tile. */

        for (int i = fromRow - 1; i > toRow; i--) {

          if (Board.board.map.getPieceOrOccupation("tileOccupation", i 
              + String.valueOf(fromColumn)) == "Occupied" && piece.isOpponentPiece(player, 
                  Board.board.map.getPieceOrOccupation("piecePos", i + String.valueOf(
                      fromColumn))) == false) {
            if (!Board.board.map.valueLock) {
              System.out.println("Illegal Move. There is a piece in your Rook's movement path "
                  + "(Tile: " + i + fromColumn + ")");
            }
            pieceInPath++;
          }
        
        }
        setPos(player, selectedRook, toTile);
    
      /*
       * If we do not have a horizontal or vertical move, the move must be an illegal move.
       */
      } else {
        if (!Board.board.map.valueLock) {
          System.out.println("Illegal Move. Please move your piece in accordance to the game's "
              + "rules");
        }
      }
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
    /*
     * This 'if' statement is checking there are zero pieces in the Rook's movement path,
     * and the destination tile is empty, so that the Rook can move there.
     */
    if (pieceInPath == 0 && Board.board.map.getPieceOrOccupation("tileOccupation", toTile) 
        == "Empty") {
      passedValidation = true;
      Board.board.map.setValue("piecePos", toTile, selectedRook);
      movedRooks.add(selectedRook);
    
    /*
     * This 'else if' is for checking there are zero pieces in the Rook's movement path, and
     * the destination tile contains an opponent piece which the Rook can capture.
     */
    } else if (pieceInPath == 0 && piece.isOpponentPiece(player, 
        Board.board.map.getPieceOrOccupation("piecePos", toTile)) == true) {
      passedValidation = true;
      Board.board.map.capturePiece(Board.board.map.getPieceOrOccupation("piecePos", toTile));
      Board.board.map.setValue("piecePos", toTile, selectedRook);
    
    /*
     * This 'else if' is to check if the piece in the toTile is a King, which is the own player's
     * King piece, as this would indicate the player might be trying to castle their Rook. 
     */
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied" 
        && !movedRooks.contains(selectedRook) && piece.isOpponentPiece(player, 
        Board.board.map.getPieceOrOccupation("piecePos", toTile)) == false 
        && Board.board.map.getPieceOrOccupation("piecePos", toTile).contains("King")) {
      passedValidation = false;
    
    /* 
     * This 'else if' assumes the current player's piece is in the destination tile, as the tile is 
     * occupied but not with an opponent piece) 
     */
    } else if (piece.isOpponentPiece(player, selectedRook) == false 
        && Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied") {
      if (!Board.board.map.valueLock) {
        System.out.println("Illegal Move. You cannot move your Rook to a tile containing your own "
            + "piece");
      }
    }
    
    pieceInPath = 0; // Reset counter to avoid potential conflicts later
    
  }
  
  /**
   * This method checks to see if a Rook has moved before or not.
   * If the method returns false (a Rook has not moved yet), this can
   * be used to indicate that a Rook can castle with the player's King. Else, if 
   * the Rook has moved before, the Rook cannot then castle with the player's King.
   * 
   * @param selectedPiece The pawn the player selected to move
   * @return True if pawn has not moved (not in list of moved pawns), false if
   *     pawn has moved (in list of moved pawns)
   */
  public boolean firstMove(String selectedPiece) {
  
    for (String s : movedRooks) {
      if (s.equals(selectedPiece)) {
        return false;
      }
    }
  
    return true;
  }
}
