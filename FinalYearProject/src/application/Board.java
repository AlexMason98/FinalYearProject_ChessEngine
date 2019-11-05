package application;

/**
 * The board class contains my initialisation methods (pre-conditions) which must be simulated
 * to start a new chess game. This includes setting starting positions, setting tile occupations,
 * resetting captured pieces, and initialising a new chess board.
 * 
 * @author Alex Mason
 *
 */
public class Board {

  public TreeMaps map;
  
  /**
   * This is a constructor for my board class, it initialises the tiles and pieces.
   */
  public Board() {
    map = new TreeMaps();
    initTiles();
    initPieces();
  }

  /**
   * The initTiles method uses the setValue method, which takes three strings, the first being the 
   * TreeMap I am going to use (which will be tileOccupation), the second being a tile co-ordinate 
   * (containing row number and column letter), the third then being a occupation status. 
   * I map the tile co-ord to the occupation status.
   * 
   * <p>The method sets all tiles in rows 1 and 2, as well as rows 7 and 8, to 'Occupied'.
   * The method also sets the tiles in rows 3 - 6 to 'Empty'.
   */
  public void initTiles() {
    String concat = "";

    // For loop for marking rows 1 and 2 as occupied (for the white piece starting positions)
    for (int i = 1; i <= 2; i++) {

      // For loop for marking column letters A to H as occupied
      for (int j = 65; j <= 72; j++) {
        // Concatenating the row number with the column letter for the tile co-ord, e.g.: Tile 1A
        concat = "" + i + (char) j;
        map.setValue("tileOccupation", concat, "Occupied");
      }
    }

    // For loop for marking rows 3-6 as empty (as no pieces will be here when the game initialises)
    for (int i = 3; i <= 6; i++) {

      for (int j = 65; j <= 72; j++) {
        // Concatenating the row number with the column letter for the tile co-ord, e.g.: Tile 3C
        concat = "" + i + (char) j;
        map.setValue("tileOccupation", concat, "Empty");
      }
    }

    // For loop for marking rows 7 and 8 as occupied (for the black piece starting positions)
    for (int i = 7; i <= 8; i++) {

      // For loop for marking column letters A to H as occupied
      for (int j = 65; j <= 72; j++) {
        // Concatenating the row number with the column letter for the tile co-ord, e.g.: Tile 8A
        concat = "" + i + (char) j;
        map.setValue("tileOccupation", concat, "Occupied");
      }
    }
  }


  /**
   * The initPieces method maps a tile co-ord to a piece ID (in the piecePos TreeMap), where 
   * the tile co-ord will be the starting position for that piece. The pieces ID consists of the 
   * player, plus the piece type, plus the piece number if there is more than 1 of these pieces.
   */
  public void initPieces() {

    map.setValue("piecePos", "1A", "WhiteRook1");
    map.setValue("piecePos", "1B", "WhiteKnight1");
    map.setValue("piecePos", "1C", "WhiteBishop1");
    map.setValue("piecePos", "1D", "WhiteQueen");
    map.setValue("piecePos", "1E", "WhiteKing");
    map.setValue("piecePos", "1F", "WhiteBishop2");
    map.setValue("piecePos", "1G", "WhiteKnight2");
    map.setValue("piecePos", "1H", "WhiteRook2");


    /* This is used as a way of generating 8 pawns in rows 2 and 7 of the board
     * as these two rows are where pawns start in a new game */
    int pawnCount = 1;

    /* I use 65 and 72 as the ASCII values of a character. These characters correspond
     * to the column letters within a 8 x 8 chess board */
    for (int i = 65; i <= 72; i++) {

      // Map the row number + column letter to the piece name + number of this type of piece 
      map.setValue("piecePos", "2" + (char) i, "WhitePawn" + pawnCount);
      map.setValue("piecePos", "7" + (char) i, "BlackPawn" + pawnCount);
      pawnCount++;
    }

    map.setValue("piecePos", "8A", "BlackRook1");
    map.setValue("piecePos", "8B", "BlackKnight1");
    map.setValue("piecePos", "8C", "BlackBishop1");
    map.setValue("piecePos", "8D", "BlackQueen");
    map.setValue("piecePos", "8E", "BlackKing");
    map.setValue("piecePos", "8F", "BlackBishop2");
    map.setValue("piecePos", "8G", "BlackKnight2");
    map.setValue("piecePos", "8H", "BlackRook2");

  }

}
