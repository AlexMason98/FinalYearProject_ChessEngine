package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;

public class Pawn {

  public Board board = new Board();
  private Piece piece;
  private ArrayList<String> movedPawns = new ArrayList<String>();

  /**
   * This method is responsible for validating, and then moving, a selected pawn piece.
   * 
   * @param player Takes the current player (either White or Black) to validate if the piece 
   *      during an capturing move is an opponent piece.
   * @param selectedPiece Takes the selected pawn piece to validate and move that piece.
   * @param toTile The tile the pawn intends to move to.
   * @throws InvalidPieceException if an invalid piece is passed to the method.
   * @throws InvalidPlayerException if the player passed to the method is not "White" or "Black".
   */
  public void movePawn(String player, String selectedPiece, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {

    String fromTile = board.map.getTile(selectedPiece);
    char fromRow = fromTile.charAt(0);
    char fromColumn = fromTile.charAt(1);
    char toRow = toTile.charAt(0);
    char toColumn = toTile.charAt(1);
  
    int fromRowNo = Integer.parseInt(String.valueOf(fromRow));
    int toRowNo = Integer.parseInt(String.valueOf(toRow));
    
    /* If statement checking if it is the pawn's first move and if so, the pawn is being moved one 
     * or two spaces forward.
     */
    if (firstMove(selectedPiece) && (toRowNo == fromRowNo + 1 || toRowNo == fromRowNo + 2) 
        && fromColumn == toColumn) {
      setPos(selectedPiece, toTile);
      movedPawns.add(selectedPiece);
      
    /* Else if statement for checking if the player wants to move the pawn forward 1 tile, assuming
     * its not the pawn's first move based on the previous if statement checking this */
    } else if ((toRowNo == fromRowNo + 1) && fromColumn == toColumn) {
      setPos(selectedPiece, toTile);
      
    /* Checking for moving the pawn forward diagonally and taking an opponent piece.
       It gets the column letter by using (int) so we get the ASCII value of the char.
       If the ASCII value is +/- 1 (a column to left or right of current pawn), the row is + 1
       (in front of the current pawn), and the target tile is occupied with an opponent piece,
       we have a valid move to capture an opponent piece. */
    } else if ((toRowNo == fromRowNo + 1) && (int)fromColumn == (int)toColumn + 1
        || (toRowNo == fromRowNo + 1) && (int)fromColumn == (int)toColumn - 1) {
      
      if (board.map.getPieceOrOccupation("tileOccupation", toTile).equals("Occupied") 
          && piece.isOpponentPiece(player, board.map.getPieceOrOccupation("piecePos", toTile)) 
          == true) {
 
        board.map.capturePiece(board.map.getPieceOrOccupation("piecePos", toTile));
        board.map.setValue("piecePos", toTile, selectedPiece);
      } else {
        System.out.println("Illegal Move. You cannot capture an opponent piece here");
      }
    } else {
      System.out.println("Illegal Move. Please move your piece in accordance to the game's rules");
    }
    
  }
  
  /**
   * This method is responsible for actually setting the new position of the selected pawn piece.
   * It is called from the movePawn method once all appropriate validation checks have taken place.
   * 
   * @param selectedPiece The selected pawn piece by the player.
   * @param toTile The destination tile the player intends to move the piece to.
   */
  public void setPos(String selectedPiece, String toTile) {
    if (board.map.getPieceOrOccupation("tileOccupation", toTile).equals("Empty")) {
      board.map.setValue("piecePos", toTile, selectedPiece);
    } else if (board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied") {
      System.out.println("You cannot move your pawn there (Tile is occupied)");
    } else {
      System.out.println("Maps Empty");
      throw new NullPointerException();
    }
  }
  
  /**
   * This method checks to see if a Pawn has moved before or not.
   * If the method returns false (a pawn has not moved yet), this can
   * be used to indicate that a pawn can move two squares. Else, if the
   * pawn has moved before, only one square forward or one square diagonal moves
   * are valid.
   * 
   * @param selectedPiece The pawn the player selected to move
   * @return True if pawn has not moved (not in list of moved pawns), false if
   *     pawn has moved (in list of moved pawns)
   */
  public boolean firstMove(String selectedPiece) {
  
    for (String s : movedPawns) {
      if (s.equals(selectedPiece)) {
        return false;
      }
    }
  
    return true;
  }
}
