package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import java.util.ArrayList;

public class Pawn {

  private Piece piece = new Piece();
  public ArrayList<String> movedPawns = new ArrayList<String>();
  public boolean passedValidation = false;
  private boolean movingForward = false;
  public boolean attackMove = false;

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

    String fromTile = Board.board.map.getTile(selectedPiece);
    char fromRow = fromTile.charAt(0);
    char fromColumn = fromTile.charAt(1);
    char toRow = toTile.charAt(0);
    char toColumn = toTile.charAt(1);
    int fromRowNo = Integer.parseInt(String.valueOf(fromRow));
    int toRowNo = Integer.parseInt(String.valueOf(toRow));
    
    /* If statement checking if it is the pawn's first move and belongs to White player. If so,
     * if then checks the pawn is being moved one or two spaces forward.
     */
    if (firstMove(selectedPiece) && player.equals("White") && ((toRowNo == fromRowNo + 2 
        || toRowNo == fromRowNo + 1) && fromColumn == toColumn)) {
      movingForward = true;
      
      /*
       * Checking there isn't a piece of the player's directly in front of the Pawn intending to 
       * move. This check is applicable in the instance that the Pawn is moving 1 space and 2 spaces
       * forwards.
       */
      String tempPiece = Board.board.map.getPieceOrOccupation("piecePos", "" + (fromRowNo + 1) 
          + toColumn);
      if (tempPiece != null && tempPiece.contains("White")) {
        if (Board.board.map.valueLock == false) {
          System.out.println("Illegal Move. Please move your Pawn in accordance to the "
              + "game's rules");
        }
      } else {
        if (Board.board.map.valueLock == false) {
          movedPawns.add(selectedPiece);
        }
      
        setPos(player, selectedPiece, toTile);
      }
    
    /* Else if statement checking if it is the pawn's first move and belongs to Black player. If so,
     * it then checks the pawn is being moved one or two spaces forward.
     * 
     */
    } else if (firstMove(selectedPiece) && player.equals("Black") && ((toRowNo == fromRowNo - 2
        || toRowNo == fromRowNo - 1) && fromColumn == toColumn)) {
      movingForward = true;
      
      /*
       * Checking there isn't a piece of the player's directly in front of the Pawn intending to 
       * move. This check is applicable in the instance that the Pawn is moving 1 space and 2 spaces
       * forwards.
       */
      String tempPiece = Board.board.map.getPieceOrOccupation("piecePos", "" + (fromRowNo - 1)
          + toColumn);
      if (tempPiece != null && tempPiece.contains("Black")) {
        if (Board.board.map.valueLock == false) {
          System.out.println("Illegal Move. Please move your Pawn in accordance to the "
              + "game's rules");
        }
      } else {
        if (Board.board.map.valueLock == false) {
          movedPawns.add(selectedPiece);
        }
      
        setPos(player, selectedPiece, toTile);
      }
      
    /* Else if statement for checking if it is the pawn's first move and a Pawn belonging to the
     * White player is being used immediately for an attack move.
     */
    } else if (firstMove(selectedPiece) && player.equals("White") && ((toRowNo == fromRowNo + 1) 
        && fromColumn == toColumn + 1 || (toRowNo == fromRowNo + 1) 
        && fromColumn == toColumn - 1)) {
      movingForward = false;
      attackMove = true;
      
      if (Board.board.map.valueLock == false) {
        movedPawns.add(selectedPiece);
      }
      
      setPos(player, selectedPiece, toTile);
     
    
    /* Else if statement checking if it is the pawn's first move and a Pawn belonging to the
     * Black player is being used immediately for an attack move.
     */
    } else if (firstMove(selectedPiece) && player.equals("Black") && ((toRowNo == fromRowNo - 1) 
        && fromColumn == toColumn + 1 || (toRowNo == fromRowNo - 1) 
        && fromColumn == toColumn - 1)) {
      movingForward = false;
      attackMove = true;
      
      if (Board.board.map.valueLock == false) {
        movedPawns.add(selectedPiece);
      }
      
      setPos(player, selectedPiece, toTile);

      
    /* Else if statement for checking if the player wants to move the pawn forward 1 tile, assuming
     * its not the pawn's first move based on the previous if statement checking this */      
    } else if (player.equals("White") && (fromRowNo != 8) && toRowNo == fromRowNo + 1
        && fromColumn == toColumn) {
      movingForward = true;
      setPos(player, selectedPiece, toTile);
    
    /* Else if statement for checking if the Black player wants to move their pawn downwards 1 tile,
     * (towards the White player's starting position) assuming its now the pawn's first move. */
    } else if (player.equals("Black") && (fromRowNo != 1) && toRowNo == fromRowNo - 1
        && fromColumn == toColumn) {
      movingForward = true;
      
      setPos(player, selectedPiece, toTile);
      
    /* Checking for moving the pawn forward diagonally and taking an opponent piece.
       It gets the column letter by using (int) so we get the ASCII value of the char.
       If the ASCII value is +/- 1 (a column to left or right of current pawn), the row is + 1
       (in front of the current pawn), and the target tile is occupied with an opponent piece,
       we have a valid move to capture an opponent piece. */
    } else if (player.equals("White") && selectedPiece.contains("White") && (toRowNo 
        == fromRowNo + 1) && fromColumn == toColumn + 1 || selectedPiece.contains("White") 
        && (fromRowNo != 8) && (toRowNo == fromRowNo + 1) && fromColumn == toColumn - 1) {
      movingForward = false;
      attackMove = true;
      
      setPos(player, selectedPiece, toTile);
      
    } else if (player.equals("Black") && selectedPiece.contains("Black") && (toRowNo 
        == fromRowNo - 1) && fromColumn == toColumn + 1 || selectedPiece.contains("Black") 
        && (fromRowNo != 1) && (toRowNo == fromRowNo - 1) && fromColumn == toColumn - 1) {
      movingForward = false;
      attackMove = true;
      setPos(player, selectedPiece, toTile);
     
    } else {
      if (!Board.board.map.valueLock) {
        System.out.println("Illegal Move. Please move your piece in accordance to the game's "
            + "rules");
      }
    } 
  }
  
  /**
   * This method is called from the movePawn method above. Its purpose is to set the
   * Pawn's position once validation is completed from the movePawn method.
   * 
   * @param player Takes the current player (Either "White" or "Black").
   * @param selectedPiece Takes the current piece.
   * @param toTile Takes the destination tile the player intends the King to move to.
   */
  public void setPos(String player, String selectedPiece, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
    // If the destination tile is empty
    if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Empty" 
        && movingForward == true) {
      passedValidation = true;
      
      Board.board.map.setValue("piecePos", toTile, selectedPiece);
      movingForward = false;
    
    // If the destination tile is occupied with an opponent player's piece
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
        && piece.isOpponentPiece(player, Board.board.map.getPieceOrOccupation("piecePos", toTile)) 
        == true && movingForward == false) {
      passedValidation = true;

      Board.board.map.capturePiece(Board.board.map.getPieceOrOccupation("piecePos", toTile));
      Board.board.map.setValue("piecePos", toTile, selectedPiece);
      movingForward = false;
    
    // If the destination tile is occupied with player's own piece
    } else if (Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied"
         && piece.isOpponentPiece(player, Board.board.map.getPieceOrOccupation("piecePos", toTile)) 
         == false) {
      if (!Board.board.map.valueLock) {
        System.out.println("Illegal Move. You cannot move your Pawn on your own piece");
      }
      movingForward = false;
      
    // Else, do not set the position of Pawn and display message.
    } else {
      if (!Board.board.map.valueLock) {
        System.out.println("Invalid Move");
      }
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
