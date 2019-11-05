import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Piece;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPiece {

  private Piece piece;
  private Board board;
 
  @BeforeEach
  void setUp() {
    piece = new Piece();
    board = new Board();
  }
 
  /**
   * Tests the isValidPiece method. It checks if the piece in the selected tile (1A) contains
   * the colour of the current player. For example, if it is the White player's turn, I want to
   * test the piece they selected contains "White", such as "WhitePawn1" and not "BlackPawn1".
   */
  @Test
  void test1() {
    try {
      assertEquals(piece.isValidPiece("White", board.map.getPieceOrOccupation("piecePos", "1A")), 
          true);
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests the isValidPiece method, but for the other player. With the Black player selecting
   * a Black piece, I want the method to return true to the piece being a valid piece selected.
   */
  @Test
  void test2() {
    try {
      assertEquals(piece.isValidPiece("Black", board.map.getPieceOrOccupation("piecePos", "8E")), 
          true);
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests the isValidPiece method for a final time. I want to see that the method returns
   * false if a player tries to select a piece that isn't one of their own pieces.
   */
  @Test
  void test3() {
    try {
      assertEquals(piece.isValidPiece("White", board.map.getPieceOrOccupation("piecePos", "8E")),
          false);
    } catch (InvalidPlayerException e) {
      e.getMessage();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can get the piece type from a piece ID passed to the method.
   */
  @Test
  void test4() {
    try {
      assertEquals(piece.pieceType("BlackKing1"), "King");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if the player is moving onto a tile where an opponent's piece is.
   */
  @Test
  void test5() {
    try {
      assertEquals(piece.isOpponentPiece("White", board.map.getPieceOrOccupation("piecePos", 
          "8E")), true);
  
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }

}
