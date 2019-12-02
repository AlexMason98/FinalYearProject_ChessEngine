package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

public class Bishop {

  public Board board = new Board();
  private Piece piece = new Piece();
  private int pieceInPath = 0;

  /**
   * This method is responsible for validating and then moving a Bishop piece.
   * 
   * @param player Takes the current player (Either "White" or "Black").
   * @param selectedBishop Takes the selected Bishop piece.
   * @param toTile Takes the destination tile the player intends to move the Bishop to.
   * @throws InvalidPlayerException If an invalid player is passed to the method.
   * @throws InvalidPieceException If an invalid piece is passed to the method.
   */
  public void moveBishop(String player, String selectedBishop, String toTile) 
      throws InvalidPlayerException, InvalidPieceException {
  
    String fromTile = board.map.getTile(selectedBishop);

    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
    char toColumn = toTile.charAt(1);
    
    /* MOVING NORTH EAST DIAGONALLY. Where the toColumn letter (as an ASCII value) is greater than
     * the fromColumn letter, and the toRow number is greater than the fromRow number. For example,
     * moving from 1D (where 1 is the row and D is the column) to 3F.
     */
    if (((int)toColumn > (int)fromColumn) && (toRow > fromRow)) {
      System.out.println("----- Moving North East -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Bishop
       * is moving the same number of spaces in both directions, therefore we
       * don't get a North East move which could be 2 spaces up and 1 space
       * right for example.
       */
      int colDifference = (int)toColumn - (int)fromColumn;
      int rowDifference = toRow - fromRow;
      int j = (int)fromColumn;
      
      if (colDifference == rowDifference) {
      
        for (int i = fromRow + 1; i < toRow; i++) {
          ++j; // Also increments column to move East as part of moving diagonally North East
          if (board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j)) 
              == "Occupied" && piece.isOpponentPiece(player, board.map.getPieceOrOccupation(
              "piecePos", i + String.valueOf((char)j))) == false) {
            System.out.println("Illegal Move. There is a piece in your Bishop's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        
        setPos(player, selectedBishop, toTile);
        
      } else {
        System.out.println("Illegal Move. Please move your Bishop in accordance to the game rules");
      }
   
    /* MOVING SOUTH EAST DIAGONALLY. Where the toColumn letter (as an ASCII value) is greater than
     * the fromColumn letter, and the fromRow number is greater than the toRow number. For example,
     * moving from 8C (where 8 is the row and C is the column) to 6E.
     */
    } else if (((int)toColumn > (int)fromColumn) && (toRow < fromRow)) {
      System.out.println("----- Moving South East -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Bishop
       * is moving the same number of spaces in both directions, therefore we
       * don't get a South East move which could be 2 spaces down and 1 space
       * right for example.
       */
      int colDifference = (int)toColumn - (int)fromColumn;
      int rowDifference = fromRow - toRow;
      int j = (int)fromColumn;
       
      if (colDifference == rowDifference) {
        for (int i = fromRow - 1; i > toRow; i--) {
          System.out.println("i is: " + i);
          ++j; // Also increments column to move East as part of moving South East diagonally.
          System.out.println("j is " + (char)j);
          if (board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j))
              == "Occupied" && piece.isOpponentPiece(player, board.map.getPieceOrOccupation(
              "piecePos", i + String.valueOf((char)j))) == false) {
            System.out.println("Illegal Move. There is a piece in your Bishop's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        
        setPos(player, selectedBishop, toTile);
        
      } else {
        System.out.println("Illegal Move. Please move your Bishop in accordance to the game rules");
      }
    
    /* MOVING SOUTH WEST DIAGONALLY. Where the fromColumn letter (as an ASCII value) is greater than
     * the toColumn letter, and the fromRow number is greater than the toRow number. For example,
     * moving from 8D (where 8 is the row and D is the column) to 6B.
     */
    } else if (((int)toColumn < (int)fromColumn) && (toRow < fromRow)) {
      System.out.println("----- Moving South West -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Bishop
       * is moving the same number of spaces in both directions, therefore we
       * don't get a South West move which could be 2 spaces left and 1 space
       * down for example.
       * 
       */
      int colDifference = (int)fromColumn - (int)toColumn;
      int rowDifference = fromRow - toRow;
      int j = (int)fromColumn;
      
      if (colDifference == rowDifference) {
        for (int i = fromRow - 1; i > toRow; i--) {
          --j; // Also decrements column to move West as part of moving diagonally South West.
          if (board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j))
              == "Occupied" && piece.isOpponentPiece(player, board.map.getPieceOrOccupation(
              "piecePos", i + String.valueOf((char)j))) == false) {
            System.out.println("Illegal Move. There is a piece in your Bishop's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        
        setPos(player, selectedBishop, toTile);
       
      } else {
        System.out.println("Illegal Move. Please move your Bishop in accordance to the game rules");
      }
    
    /* MOVING NORTH WEST DIAGONALLY. Where the fromColumn letter (as an ASCII value) is greater than
     * the toColumn letter, and the toRow number is greater than the fromRow number. For example,
     * moving from 1D (where 1 is the row and D is the column) to 3B.
     */ 
    } else if (((int)toColumn < (int)fromColumn) && (toRow > fromRow)) {
      System.out.println("----- Moving North West -----");
      
      /* Ensuring that the diagonal move is valid by checking that the Bishop
       * is moving the same number of spaces in both directions, therefore we
       * don't get a North West move which could be 2 spaces up and 1 space left,
       * for example.
       */
      int colDifference = (int)fromColumn - (int)toColumn;
      int rowDifference = toRow - fromRow;
      int j = (int)fromColumn;
       
      if (colDifference == rowDifference) {
        for (int i = fromRow + 1; i < toRow; i++) {
          --j; // Also decrements column to move West as part of moving diagonally North West.
          if (board.map.getPieceOrOccupation("tileOccupation", i + String.valueOf((char)j))
              == "Occupied" && piece.isOpponentPiece(player, board.map.getPieceOrOccupation(
              "piecePos", i + String.valueOf((char)j))) == false) {
            System.out.println("Illegal Move. There is a piece in your Bishop's movement path "
                + "(Tile: " + i + (char)j + ")");
            pieceInPath++;
          }
        }
        
        setPos(player, selectedBishop, toTile);
        
      } else {
        System.out.println("Illegal Move. Please move your Bishop in accordance to the game rules");
      }
    
    } else {
      System.out.println("Illegal Move. Please move your Bishop in accordance to the game's rules");
    }
  }
  
  
  /**
   *  This method is responsible for setting the final position of the Bishop to the destination
   *  tile after validation has taken place in the moveBishop method. The setPos method also checks
   *  for the piece in the destination tile, so that if there's an opponent piece in the destination
   *  tile, the Bishop can capture it. If there is the player's own piece in the destination tile, 
   *  the method will indicate this to the player and not move the Bishop.
   *  
   * @param player Takes the current player (either "White" or "Black") to validate if the piece 
   *      during an capturing move is an opponent piece.
   * @param selectedBishop Takes the selected Bishop piece to set it to its destination tile.
   * @param toTile The tile the selected Bishop intends to move to (destination tile).
   * @throws InvalidPlayerException if the player passed to the method is not "White" or "Black".
   * @throws InvalidPieceException if an invalid piece is passed to the method.
   */
  public void setPos(String player, String selectedBishop, String toTile) throws 
      InvalidPieceException, InvalidPlayerException {
    System.out.println("Lands in setPos");
    /*
     * This 'if' statement is checking there are zero pieces in the Bishop's movement path,
     * and the destination tile is empty, so that the Bishop can move there.
     */
    if (pieceInPath == 0 && board.map.getPieceOrOccupation("tileOccupation", toTile) == "Empty") {
      board.map.setValue("piecePos", toTile, selectedBishop);
    
    /*
     * This 'else if' is for checking there are zero pieces in the Bishop's movement path, and
     * the destination tile contains an opponent piece which the Bishop can capture.
     */
    } else if (pieceInPath == 0 && piece.isOpponentPiece(player, board.map.getPieceOrOccupation(
        "piecePos", toTile)) == true) {
      board.map.capturePiece(board.map.getPieceOrOccupation("piecePos", toTile));
      board.map.setValue("piecePos", toTile, selectedBishop);
    
    /* 
     * This 'else if' assumes the current player's piece is in the destination tile, as the tile is 
     * occupied but not with an opponent piece) 
     */
    } else if (piece.isOpponentPiece(player, selectedBishop) == false 
        && board.map.getPieceOrOccupation("tileOccupation", toTile) == "Occupied") {
      System.out.println("Illegal Move. You cannot move your Bishop to a tile containing your own "
          + "piece");
    }
    
    pieceInPath = 0; // Reset counter to avoid potential conflicts later
    
    /* If none of the above conditions are met, do nothing. No error message is needed since this is
    dealt with in the 'moveBishop' method */
  }
}
