package application;

import java.util.TreeMap;

/**
 * The board class contains my initialisation methods (pre-conditions) which must be simulated
 * to start a new chess game. This includes setting starting positions, setting tile occupations,
 * resetting captured pieces, and initialising a new chess board.
 * 
 * @author Alex Mason
 *
 */
public class Board {

  private TreeMap<String, String> tileOccupation;
  public TreeMap<String, String> piecePos;

  /**
   * The initTiles method sets up a TreeMap which takes two strings, the first being a tile
   * co-ordinate (containing row number and column letter), the second then being a occupation
   * status. I map the tile co-ord to the occupation status.
   * 
   * <p>The method sets all tiles in rows 1 and 2, as well as rows 7 and 8, to 'Occupied'.
   * The method also sets the tiles in rows 3 - 6 to 'Empty'.
   */
  public void initTiles() {
    tileOccupation = new TreeMap<String, String>();
    String concat = "";

    // For loop for marking rows 1 and 2 as occupied (for the white piece starting positions)
    for (int i = 1; i <= 2; i++) {

      // For loop for marking column letters A to H as occupied
      for (int j = 65; j <= 72; j++) {
        // Concatenating the row number with the column letter for the tile co-ord, e.g.: Tile 1A
        concat = "" + i + (char) j;
        tileOccupation.put(concat, "Occupied");
      }
    }

    // For loop for marking rows 3-6 as empty (as no pieces will be here when the game initialises)
    for (int i = 3; i <= 6; i++) {

      for (int j = 65; j <= 72; j++) {
        // Concatenating the row number with the column letter for the tile co-ord, e.g.: Tile 3C
        concat = "" + i + (char) j;
        tileOccupation.put(concat, "Empty");
      }
    }

    // For loop for marking rows 7 and 8 as occupied (for the black piece starting positions)
    for (int i = 7; i <= 8; i++) {

      // For loop for marking column letters A to H as occupied
      for (int j = 65; j <= 72; j++) {
        // Concatenating the row number with the column letter for the tile co-ord, e.g.: Tile 8A
        concat = "" + i + (char) j;
        tileOccupation.put(concat, "Occupied");
      }
    }
  }


  /**
   * The initPieces method maps a tile co-ord to a piece ID, where the tile co-ord will be the
   * starting position for that piece. The pieces ID consists of the player, plus the piece type,
   * plus the piece number if there is more than 1 of these pieces.
   */
  public void initPieces() {
    piecePos = new TreeMap<String, String>();

    piecePos.put("1A", "WhiteRook1");
    piecePos.put("1B", "WhiteKnight1");
    piecePos.put("1C", "WhiteBishop1");
    piecePos.put("1D", "WhiteQueen");
    piecePos.put("1E", "WhiteKing");
    piecePos.put("1F", "WhiteBishop2");
    piecePos.put("1G", "WhiteKnight2");
    piecePos.put("1H", "WhiteRook2");


    /* This is used as a way of generating 8 pawns in rows 2 and 7 of the board
     * as these two rows are where pawns start in a new game */
    int pawnCount = 1;

    /* I use 65 and 72 as the ASCII values of a character. These characters correspond
     * to the column letters within a 8 x 8 chess board */
    for (int i = 65; i <= 72; i++) {

      // Map the row number + column letter to the piece name + number of this type of piece 
      piecePos.put("2" + (char) i, "WhitePawn" + pawnCount);
      piecePos.put("7" + (char) i, "BlackPawn" + pawnCount);
      pawnCount++;
    }

    piecePos.put("8A", "BlackRook1");
    piecePos.put("8B", "BlackKnight1");
    piecePos.put("8C", "BlackBishop1");
    piecePos.put("8D", "BlackQueen");
    piecePos.put("8E", "BlackKing");
    piecePos.put("8F", "BlackBishop2");
    piecePos.put("8G", "BlackKnight2");
    piecePos.put("8H", "BlackRook2");

  }

  /**
   * This method simply gets the occupation status of a tile (E.g. 'Occupied' or 'Empty')
   * by taking a tile co-ord and getting the occupation mapped to the co-ord.
   * If the status is reported as 'Empty', a chess piece can freely move into that tile.
   * 
   * @param tileCoord Pass a tile co-ordinate (e.g. 1A) to see the occupation status of the tile
   * @return the occupation mapped to the tile co-ord in the TreeMap
   */
  public String getTileOccupation(String tileCoord) {
    return tileOccupation.get(tileCoord);
  }

  /**
   * This method simply gets the chess piece from a given tile co-ord.
   * 
   * @param tileCoord Pass a valid tile co-ord/position to see the piece in that tile.
   * @return the piece in the passed tile co-ord, if there is one in that tile.
   */
  public String getPiece(String tileCoord) {
    return piecePos.get(tileCoord);
  }

  
  /**
   * This method iterates over the piecePos TreeMap's tile co-ords (key set), and compares each
   * tile co-ord to see if the chess piece mapped to that tile matches the chess piece I am looking 
   * for. If a tile contains the piece I am looking for, I return the tile co-ord, else I return 
   * null.
   * 
   * @param piece The chess piece I am searching for in each of the tile co-ords
   * @return The tile co-ord of the chess piece
   */
  public String getTile(String piece) {
    for (String tileCoord : piecePos.keySet()) {
      if (piece.equals(piecePos.get(tileCoord))) {
        return tileCoord;
      }
    }
    return null;
  }
 
  
  /**
   * This method sets a new tile co-ord/position of a chess piece.
   * 
   * @param tileCoord The new tile co-ord of a chess piece
   * @param piece The chess piece which is moving tiles
   */
  public void setPiecePos(String tileCoord, String piece) {
  
    String oldPos = getTile(piece);
    
    piecePos.put(tileCoord, piece);
    tileOccupation.put(tileCoord, "Occupied");
    
    piecePos.replace(oldPos, null);
    tileOccupation.replace(oldPos, "Empty");
    
  }
  
  /**
   * This method is used to print the current status of my TileOccupation and PiecePos TreeMaps,
   * so that I can view which tiles are occupied and empty, and which piece is where on the board.
   */
  public void printStatus() {
  
    System.out.println(tileOccupation.toString());
    System.out.println(piecePos.toString());
  }


}
