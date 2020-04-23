package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Bishop;
import application.Board;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBishop {

  private Bishop bishop;
  
  @BeforeEach
  void setUp() {
    bishop = new Bishop();
  }
  
  /**
   * This first test is to see if my Bishop correctly moves diagonally North East,
   * when providing a toTile destination which is North East to its current position.
   */
  @Test
  void test() {
    /*
    * Temporarily setting the tile 2D to null, as this contains a player's piece
    * which will block the path of the Bishop we are trying to test.
    */
    Board.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("Test 1");
      bishop.moveBishop("White", "WhiteBishop1", "6H");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6H"), "WhiteBishop1");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
      Board.board.map.setValue("piecePos", "1C", "WhiteBishop1");
      Board.board.map.setValue("piecePos", "6H", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if my Bishop correctly moves diagonally South East,
   * when providing a toTile destination which is South East to the Bishop's current position.
   */
  @Test
  void test2() {
    /*
     * Temporarily setting the tile 7D to null, as this contains a player's piece
     * which will block the path of the Bishop we are trying to test.
     */
    Board.board.map.setValue("piecePos", "7D", "null");
    
    try {
      System.out.println("Test 2");
      bishop.moveBishop("Black", "BlackBishop1", "3H");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3H"), "BlackBishop1");
      Board.board.map.setValue("piecePos", "7D", "BlackPawn4");
      Board.board.map.setValue("piecePos", "8C", "BlackBishop1");
      Board.board.map.setValue("piecePos", "3H", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if my Bishop correctly moves diagonally South West,
   * when providing a toTile destination which is South West to the Bishop's current position.
   */
  @Test
  void test3() {
    /*
     * Temporarily setting the tile 7E to null, as this contains a player's piece
     * which will block the path of the Bishop we are trying to test.
     */
    Board.board.map.setValue("piecePos", "7E", "null");
     
    try {
      System.out.println("Test 3");
      bishop.moveBishop("Black", "BlackBishop2", "3A");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3A"), "BlackBishop2");
      Board.board.map.setValue("piecePos", "7E", "BlackBishop5");
      Board.board.map.setValue("piecePos", "8F", "BlackBishop2");
      Board.board.map.setValue("piecePos", "3A", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if my Bishop correctly moves diagonally North West,
   * when providing a toTile destination which is North West to the Bishop's current position.
   */
  @Test
  void test4() {
    /*
     * Temporarily setting the tile 2E to null, as this contains a player's piece
     * which will block the path of the Bishop we are trying to test.
     */
    Board.board.map.setValue("piecePos", "2E", "null");
    
    try {
      System.out.println("Test 4");
      bishop.moveBishop("White", "WhiteBishop2", "6A");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6A"), "WhiteBishop2");
      Board.board.map.setValue("piecePos", "1F", "WhiteBishop2");
      Board.board.map.setValue("piecePos", "2E", "WhitePawn5");
      Board.board.map.setValue("piecePos", "6A", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Bishop, when there ARE pieces inside its
   * movement path, and IS NOT a piece in its destination tile.
   */
  @Test
  void test5() {
    /*
     * Temporarily setting the tile 3E to BlackPawn1, so that there is a piece in the Bishop's
     * movement path.
     */
    Board.board.map.setValue("piecePos", "3E", "BlackPawn1");
    
    try {
      System.out.println("Test 5");
      bishop.moveBishop("White", "WhiteBishop1", "4F");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "4F"), null);
      Board.board.map.setValue("piecePos", "7A", "BlackPawn1");
      Board.board.map.setValue("piecePos", "3E", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Bishop, when there ARE pieces inside its
   * movement path, and IS a piece in its destination tile which is the own player's piece.
   */
  @Test
  void test6() {
    /*
     * Temporarily setting the tile 3E to WhitePawn1, so that there is a piece in the Bishop's
     * destination tile which is the own player's piece.
     */
    Board.board.map.setValue("piecePos", "3E", "WhitePawn1");
    
    try {
      System.out.println("Test 6");
      Board.board.map.printStatus();
      bishop.moveBishop("White", "WhiteBishop1", "3E");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3E"), "WhitePawn1");
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1");
      Board.board.map.setValue("piecePos", "3E", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Bishop, when there ARE NOT pieces inside
   * its movement path, and IS a piece in its destination tile which is the opponent player's piece.
   */
  @Test
  void test7() {
    /*
     * Temporarily setting the tile 2D to null, as this contains a player's piece
     * which will block the path of the Bishop we are trying to test. Also setting
     * the Bishop's destination tile to contain an opponent player's piece.
     */
    Board.board.map.setValue("piecePos", "2D", "null");
    Board.board.map.setValue("piecePos", "3E", "BlackPawn1");
    
    try {
      System.out.println("Test 7");
      bishop.moveBishop("White", "WhiteBishop1", "3E");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3E"), "WhiteBishop1");
      assertEquals(Board.board.map.capturedPieces.get(0), "BlackPawn1");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
      Board.board.map.setValue("piecePos", "7A", "BlackPawn1");
      Board.board.map.setValue("piecePos", "1C", "WhiteBishop1");
      Board.board.map.setValue("piecePos", "3E", "null");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Bishop, when there ARE NOT pieces inside
   * its movement path, and IS a piece in its destination tile which is the own player's piece.
   */
  @Test
  void test8() {
    /*
     * Temporarily setting the tile 2D to null, as this contains a player's piece
     * which will block the path of the Bishop we are trying to test. Also setting
     * the Bishop's destination tile to contain the player's own piece.
     */
    Board.board.map.setValue("piecePos", "2D", "null");
    Board.board.map.setValue("piecePos", "3E", "WhitePawn1");
    
    try {
      System.out.println("Test 8");
      bishop.moveBishop("White", "WhiteBishop1", "3E");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3E"), "WhitePawn1");
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
      Board.board.map.setValue("piecePos", "3E", "null");
      Board.board.map.printStatus();
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }

}
