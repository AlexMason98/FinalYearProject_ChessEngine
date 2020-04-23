package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

/**
 * This class is responsible for validating and then setting the position of a Queen piece
 * during a move.
 * 
 * @author Alex Mason
 *
 */
public class Queen {

  private Piece piece = new Piece();
  private int pieceInPath = 0;
  public boolean passedValidation = false;
  
  /**
   * This method is responsible for validating a move with a Queen piece.
   * 
   * @param player The current player (Either "White" or "Black").
   * @param selectedPiece The selected queen.
   * @param toTile The destination tile the player intends the Queen to move to.
   * @throws InvalidPieceException If we receive an invalid piece (doesn't contain a type).
   * @throws InvalidPlayerException  If the player isn't "White" or "Black"
   */
  public void moveQueen(String player, String selectedPiece, String toTile) 
      throws InvalidPlayerException, InvalidPieceException {
    /* Queen can move any direction, any spaces */
    String fromTile = Board.board.map.getTile(selectedPiece);

    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
    char toColumn = toTile.charAt(1);
    
    /* MOVING LEFT. Where the row number remains the same, but the fromColumn is greater than the
     * toColumn. For example, moving from 8H (where H in ASCII is 72) to 8A (where A in ASCII is 
     * 65).
     */
    if (fromRow == toRow && fromColumn > toColumn) {
      /* CHECKING FOR ANY PIECES IN QUEEN'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile to the left of the current one to see if it contains any pieces, until
      reaching one tile before the Queen's destination tile */
      System.out.println("----- Moving Left  -----");
      for (int i = fromColumn - 1; i > toColumn; i--) {
        // If a tile in the Queen's path is not null (occupied by a piece), move is invalid.
        System.out.println("The tile is: " + toRow + String.valueOf((char)i));
        
        if (Board.board.map.getPieceOrOccupation("tileOccupation", toRow + String.valueOf((char)i)) 
            == "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Queen's movement path "
              + "(Tile: " + toRow + String.valueOf((char)i) + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedPiece, toTile);
      
    /*
     * MOVING RIGHT. Where the row number remains the same, but the toColumn number is greater than 
     * thefromColumn number. For example, moving from 3B (where B in ASCII is 66) to 3E (where E in 
     * ASCII is 69).
     */
    } else if (fromRow == toRow && toColumn > fromColumn) {
      /* CHECKING FOR ANY PIECES IN THE QUEEN'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile to the right of the current tile to see if it contains any pieces (player 
      or opponent), until reaching one tile before the Queen's destination tile */
      System.out.println("----- Moving Right -----");
      for (int i = fromColumn + 1; i < toColumn; i++) {
        // If a tile in the Queen's path is not null (occupied by a piece, move is invalid.
        System.out.println("The tile is: " + toRow + String.valueOf((char)i));
        if (Board.board.map.getPieceOrOccupation("tileOccupation", toRow + String.valueOf((char)i))
            == "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Queen's movement path "
              + "(Tile: " + toRow + String.valueOf((char)i) + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedPiece, toTile);
      
    /*
     * MOVING UP. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is less than the toRow number. For example, moving from 1A (where 1 is the row) to
     * 3A.
     */
    } else if (fromColumn == toColumn && (fromRow < toRow)) {
      /* CHECKING FOR ANY PIECES IN THE QUEEN'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile above the current tile to see if it contains any pieces (player or 
      opponent), until reaching one tile before the Queen's destination tile. */
      System.out.println("----- Moving Up -----");
      for (int i = fromRow + 1; i < toRow; i++) {
        System.out.println("The tile is: " + i + fromColumn);
        if (Board.board.map.getPieceOrOccupation("tileOccupation", "" + i + fromColumn) 
            == "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Queen's movement path "
              + "(Tile: " + i + fromColumn + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedPiece, toTile);
      
    /*
     * MOVING DOWN. Where the column letter (as an ASCII value) remains the same, but the fromRow
     * number is greater than the toRow number. For example, moving from 8A (where 8 is the row) to
     * 4A.
     */
    } else if (fromColumn == toColumn && (fromRow > toRow)) {
      /* CHECKING FOR ANY PIECES IN THE QUEEN'S MOVEMENT PATH (EXCLUDING DESTINATION TILE)
      I search each tile below the current tile to see if it contains any pieces (player or 
      opponent), until reaching one tile before the Queen's destination tile. */
      System.out.println("----- Moving Down -----");
      for (int i = fromRow - 1; i > toRow; i--) {
        System.out.println("The tile is: " + i + fromColumn);
        if (Board.board.map.getPieceOrOccupation("tileOccupation", "" + i + fromColumn) 
            == "Occupied") {
          System.out.println("Illegal Move. There is a piece in your Queen's movement path "
              + "(Tile: " + i + fromColumn + ")");
          pieceInPath++;
        }
      }
      setPos(player, selectedPiece, toTile);

    /* MOVING NORTH EAST DIAGONALLY. Where the toColumn letter (as an ASCII value) is greater than
     * the fromColumn letter, and the toRow number is greater than the fromRow number. For example,
     * moving from 1D (where 1 is the row and D is the column) to 3F.
     */
    } else if ((toColumn > fromColumn) && (toRow > fromRow)) {
      System.out.println("----- Moving North East -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Queen
       * is moving the same number of spaces in both directions, therefore we
       * don't get a North East move which could be 2 spaces up and 1 space
       * right for example.
       */
      int colDifference = toColumn - fromColumn;
      int rowDifference = toRow - fromRow;
      int j = fromColumn;
      
      if (colDifference == rowDifference) {
      
        for (int i = fromRow + 1; i < toRow; i++) {
          ++j; // Also increments column to move East as part of moving diagonally North East
          if (Board.board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j)) 
              == "Occupied") {
            System.out.println("Illegal Move. There is a piece in your Queen's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        setPos(player, selectedPiece, toTile);
      } else {
        System.out.println("Illegal Move. Please move your Queen in accordance to the game rules");
      }
    
    /* MOVING SOUTH EAST DIAGONALLY. Where the toColumn letter (as an ASCII value) is greater than
     * the fromColumn letter, and the fromRow number is greater than the toRow number. For example,
     * moving from 8D (where 8 is the row and D is the column) to 6F.
     */
    } else if ((toColumn > fromColumn) && (toRow < fromRow)) {
      System.out.println("----- Moving South East -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Queen
       * is moving the same number of spaces in both directions, therefore we
       * don't get a South East move which could be 2 spaces down and 1 space
       * right for example.
       */
      int colDifference = toColumn - fromColumn;
      int rowDifference = fromRow - toRow;
      int j = fromColumn;
       
      if (colDifference == rowDifference) {
        for (int i = fromRow - 1; i > toRow; i--) {
          ++j; // Also increments column to move East as part of moving diagonally South East
          if (Board.board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j))
              == "Occupied") {
            System.out.println("Illegal Move. There is a piece in your Queen's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        setPos(player, selectedPiece, toTile);
      } else {
        System.out.println("Illegal Move. Please move your Queen in accordance to the game rules");
      }
    
    /* MOVING SOUTH WEST DIAGONALLY. Where the fromColumn letter (as an ASCII value) is greater than
     * the toColumn letter, and the fromRow number is greater than the toRow number. For example,
     * moving from 8D (where 8 is the row and D is the column) to 6B.
     */
    } else if ((toColumn < fromColumn) && (toRow < fromRow)) {
      System.out.println("----- Moving South West -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Queen
       * is moving the same number of spaces in both directions, therefore we
       * don't get a South West move which could be 2 spaces left and 1 space
       * down for example.
       * 
       */
      int colDifference = fromColumn - toColumn;
      int rowDifference = fromRow - toRow;
      int j = fromColumn;
      
      if (colDifference == rowDifference) {
        for (int i = fromRow - 1; i > toRow; i--) {
          --j; // Also decrements column to move West as part of moving diagonally South West.
          if (Board.board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j))
              == "Occupied") {
            System.out.println("Illegal Move. There is a piece in your Queen's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        setPos(player, selectedPiece, toTile);
      } else {
        System.out.println("Illegal Move. Please move your Queen in accordance to the game rules");
      }
    
    /* MOVING NORTH WEST DIAGONALLY. Where the fromColumn letter (as an ASCII value) is greater than
     * the toColumn letter, and the toRow number is greater than the fromRow number. For example,
     * moving from 1D (where 1 is the row and D is the column) to 3B.
     */ 
    } else if ((toColumn < fromColumn) && (toRow > fromRow)) {
      System.out.println("----- Moving North West -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Queen
       * is moving the same number of spaces in both directions, therefore we
       * don't get a North West move which could be 2 spaces up and 1 space left,
       * for example.
       */
      int colDifference = fromColumn - toColumn;
      int rowDifference = toRow - fromRow;
      int j = fromColumn;
       
      if (colDifference == rowDifference) {
        for (int i = fromRow + 1; i < toRow; i++) {
          --j; // Also decrements column to move West as part of moving diagonally North West.
          if (Board.board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j))
              == "Occupied") {
            System.out.println("Illegal Move. There is a piece in your Queen's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        setPos(player, selectedPiece, toTile);
      } else {
        System.out.println("Illegal Move. Please move your Queen in accordance to the game rules");
      }
    
    } else {
      System.out.println("Illegal Move. Please move your Queen in accordance to the game's rules");
    }
  }
  
  /**
   *  This method is responsible for setting the final position of the Queen to the destination tile
   *  after validation has taken place in the moveQueen method. The setPos method also checks for 
   *  the piece in the destination tile, so that if there's an opponent piece in the destination 
   *  tile, the Queen can capture it. If there is the player's own piece in the destination tile, 
   *  the method will indicate this to the player and not move the Queen.
   *  
   * @param player Takes the current player (either "White" or "Black") to validate if the piece 
   *      during an capturing move is an opponent piece.
   * @param selectedQueen Takes the selected Queen piece to set it to its destination tile.
   * @param toTile The tile the selected Queen intends to move to (destination tile).
   * @throws InvalidPlayerException if the player passed to the method is not "White" or "Black".
   * @throws InvalidPieceException if an invalid piece is passed to the method.
   */
  public void setPos(String player, String selectedQueen, String toTile) throws 
      InvalidPieceException, InvalidPlayerException {
    System.out.println("Lands in setPos");
    /*
     * This 'if' statement is checking there are zero pieces in the Queen's movement path,
     * and the destination tile is empty, so that the Queen can move there.
     */
    if (pieceInPath == 0 && Board.board.map.getPieceOrOccupation("tileOccupation", toTile) 
        == "Empty") {
      passedValidation = true;
      Board.board.map.setValue("piecePos", toTile, selectedQueen);
    
    /*
     * This 'else if' is for checking there are zero pieces in the Queen's movement path, and
     * the destination tile contains an opponent piece which the Queen can capture.
     */
    } else if (pieceInPath == 0 && piece.isOpponentPiece(player, 
        Board.board.map.getPieceOrOccupation("piecePos", toTile)) == true) {
      passedValidation = true;
      Board.board.map.capturePiece(Board.board.map.getPieceOrOccupation("piecePos", toTile));
      Board.board.map.setValue("piecePos", toTile, selectedQueen);
    
    /* 
     * This 'else if' assumes the current player's piece is in the destination tile, as the tile is 
     * occupied but not with an opponent piece) 
     */
    } else if (piece.isOpponentPiece(player, selectedQueen) == false 
        && Board.board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied") {
      System.out.println("Illegal Move. You cannot move your Queen to a tile containing your own "
          + "piece");
    }
    
    pieceInPath = 0; // Reset counter to avoid potential conflicts later
    
    /* If none of the above conditions are met, do nothing. No error message is needed since this is
    dealt with in the 'moveQueen' method */
  }
}
