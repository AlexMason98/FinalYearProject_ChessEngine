package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import application.Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

  private Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  /**
   * This tests to see if my tile initialisation (in the Board classes' initTiles method)
   * successfully returns "Occupied" to a tile which should be occupied when setting up
   * the white pieces ready for gameplay.
   */
  @Test
  void test1() {
    assertEquals(board.map.getPieceOrOccupation("tileOccupation", "1A"), "Occupied");
  }

  /**
   * This tests to see if my tile initialisation (in the Board classes' initTiles method)
   * successfully returns "Empty" to a tile which should be empty when setting up the Chess
   * board for gameplay.
   */
  @Test
  void test2() {
    assertEquals(board.map.getPieceOrOccupation("tileOccupation", "3H"), "Empty");
  }

  /**
   * This tests to see if my tile initialisation (in the Board classes' initTiles method)
   * successfully returns "Occupied" to a tile which should be occupied when setting up
   * the black pieces ready for gameplay.
   */
  @Test
  void test3() {
    assertEquals(board.map.getPieceOrOccupation("tileOccupation", "8C"), "Occupied");
  }

  /**
  * This tests to see if a piece is in its correct starting co-ordinate. For example,
  * in 1E, I would expect the returned piece to be "WhiteKing" as this is its starting position
  * in the game
  */
  @Test
  void test4() {
    assertEquals(board.map.getPieceOrOccupation("piecePos", "1E"), "WhiteKing");
  }

  /** This tests to see if a pawn is in its correct starting co-ordinate (as pawns are put into
   * the TreeMap differently compared to the special pieces).
   */
  @Test
  void test5() {
    assertEquals(board.map.getPieceOrOccupation("piecePos", "2F"), "WhitePawn6");
  }
  
  /**
   * This tests to see if null is returned when trying to get a piece from an empty tile (a 
   * tile that doesn't have a piece in it).
   */
  @Test
  void test6() {
    assertEquals(board.map.getPieceOrOccupation("piecePos", "6D"), null);
  }
  
  /**
   * This tests to see if I can get the key (the current tile position) for a specified
   * chess piece.
   */
  @Test
  void test7() {
    assertEquals(board.map.getTile("BlackKing"), "8E");
  }
  
  /**
   * This tests to see if my print status method successfully prints the keys and values in my
   * tile occupation and piece position TreeMaps.
   */
  @Test
  void test8() {
    board.map.printStatus();
  }
 
  
  /**
   * This tests to see if I can set a new piece position by providing the tile co-ord and piece,
   * where the tile co-ord will be where the new piece's position is. I use my getPiece and
   * getPieceOrOccupation("tileOccupation",  methods, which have been tested above, to check to see 
   * if the tile occupation has updated, so 6A is now set to Occupied and 8E (the previous piece 
   * position) is now set to Empty. The final check is that the piece in 8E is now null (as no piece
   * sits there anymore).
   */
  @Test
  void test9() {
    // This line of the code below is the only line actually being tested here
    board.map.setValue("piecePos", "6A", "BlackKing");
    /* The lines below simply verify the setter has worked for setting the new tile position
     * of a chess piece, and has set the old tile to empty and null for containing a chess piece.
     */
    assertEquals(board.map.getPieceOrOccupation("piecePos", "6A"), "BlackKing");
    assertEquals(board.map.getPieceOrOccupation("tileOccupation", "6A"), "Occupied");
    assertEquals(board.map.getPieceOrOccupation("piecePos", "8E"), null);
    assertEquals(board.map.getPieceOrOccupation("tileOccupation", "8E"), "Empty");
    board.map.printStatus();
  }

}
