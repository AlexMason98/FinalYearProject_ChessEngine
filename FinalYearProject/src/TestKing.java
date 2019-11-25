import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.King;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestKing {
  
  private King king;
 
  @BeforeEach
  void setUp() {
    king = new King();
  }

  /**
   * This tests to see if my validation to move the King left one space is correct
   * or not, where the King's starting position is 1E and is moving to 1D.
   */
  @Test
  void test() {
  
    king.board.map.setValue("piecePos", "1D", "null");
  
    try {
      System.out.println("Test 1");
      king.moveKing("White", "WhiteKing", "1D");
      assertEquals(king.board.map.getPieceOrOccupation("piecePos", "1D"), "WhiteKing");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if my validation to move a King right one space is correct
   * or not, where the King's starting position is 1E and is moving to 1F.
   */
  @Test
  void test2() {
    king.board.map.setValue("piecePos", "1F", "null");
    
    try {
      System.out.println("Test 2");
      king.moveKing("White", "WhiteKing", "1F");
      assertEquals(king.board.map.getPieceOrOccupation("piecePos", "1F"), "WhiteKing");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if my validation to move a King up one space is correct or
   * not, where the King's starting position is 1E and is moving to 2E.
   */
  @Test
  void test3() {
    king.board.map.setValue("piecePos", "2E", "null");
    
    try {
      System.out.println("Test 3");
      king.moveKing("White", "WhiteKing", "2E");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if my validation to move a King down one space is correct or not,
   * where the King's starting position is 8E and is moving down to 7E.
   */
  @Test
  void test4() {
    king.board.map.setValue("piecePos", "7E", "null");
    
    try {
      System.out.println("Test 4");
      king.moveKing("Black", "BlackKing", "7E");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if my validation to move a King diagonally North East is correct or not,
   * where the King's starting position is 1E and is moving to 2F.
   */
  @Test
  void test5() {
    king.board.map.setValue("piecePos", "2F", "null");
    
    try {
      System.out.println("Test 5");
      king.moveKing("White", "WhiteKing", "2F");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if my validation to move a King diagonally South East is correct or not,
   * where the King's starting position is 8E and is moving to 7F.
   */
  @Test
  void test6() {
    king.board.map.setValue("piecePos", "7F", "null");
    
    try {
      System.out.println("Test 6");
      king.moveKing("Black", "BlackKing", "7F");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if my validation to move a King diagonally South West is correct or not,
   * where the King's starting position is 8E and is moving to 7D.
   */
  @Test
  void test7() {
    king.board.map.setValue("piecePos", "7D", "null");
    
    try {
      System.out.println("Test 7");
      king.moveKing("Black", "BlackKing", "7D");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if my validation to move a King diagonally North West is correct or not,
   * where the King's starting position is 1E and is moving to 2D.
   */
  @Test
  void test8() {
    king.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("Test 8");
      king.moveKing("White", "WhiteKing", "2D");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This test is to ensure that I cannot move a King piece into a tile containing another
   * of the player's own pieces. Note that this is before adding Castling functionality.
   */
  @Test
  void test9() {
    try {
      System.out.println("Test 9");
      king.moveKing("Black", "BlackKing", "7E");
      assertEquals(king.board.map.getPieceOrOccupation("piecePos", "7E"), "BlackPawn5");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to ensure that a King piece can successfully capture another piece when
   * the King is moved into a tile containing an opponent piece.
   */
  @Test
  void test10() {
    king.board.map.setValue("piecePos", "7E", "WhitePawn1");
    
    try {
      System.out.println("Test 10");
      king.moveKing("Black", "BlackKing", "7E");
      assertEquals(king.board.map.getPieceOrOccupation("piecePos", "7E"), "BlackKing");
      assertEquals(king.board.map.capturedPieces.get(0), "WhitePawn1");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if my movedKing ArrayList contains the King I have just moved after
   * using the moveKing method.
   */
  @Test
  void test11() {
    king.board.map.setValue("piecePos", "7E", "null");
  
    try {
      System.out.println("Test 11");
      king.moveKing("Black", "BlackKing", "7E");
      assertEquals(king.movedKing.get(0), "BlackKing");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }

}
