package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Knight;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestKnight {

  private Knight knight;
  
  @BeforeEach
  void setUp() {
    knight = new Knight();
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces up and 1 space left.
   */
  @Test
  void test() {
    try {
      System.out.println("Test 1");
      knight.moveKnight("White", "WhiteKnight1", "3A");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3A"), "WhiteKnight1");
      Board.board.map.setValue("piecePos", "1B", "WhiteKnight1");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces up and 1 space right.
   */
  @Test
  void test2() {
    try {
      System.out.println("Test 2");
      knight.moveKnight("White", "WhiteKnight1", "3C");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3C"), "WhiteKnight1");
      Board.board.map.setValue("piecePos", "1B", "WhiteKnight1");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces right and 1 space up.
   */
  @Test
  void test3() {
    /* The line below overrides all validation methods, to remove the Pawn in 2D so
    the WhiteKnight1 has a clear path to move. */
    Board.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("Test 3");
      knight.moveKnight("White", "WhiteKnight1", "2D");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "2D"), "WhiteKnight1");
      Board.board.map.setValue("piecePos", "1B", "WhiteKnight1");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces right and 1 space down.
   */
  @Test
  void test4() {
    /* The line below overrides all validation methods, to remove the Pawn in 7D so
    the BlackKnight1 has a clear path to move. */
    Board.board.map.setValue("piecePos", "7D", "null");
    
    try {
      System.out.println("Test 4");
      knight.moveKnight("Black", "BlackKnight1", "7D");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "7D"), "BlackKnight1");
      Board.board.map.setValue("piecePos", "8B", "BlackKnight1");
      Board.board.map.setValue("piecePos", "7D", "BlackPawn4");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces down and 1 space left.
   */
  @Test
  void test5() {
    try {
      System.out.println("Test 5");
      knight.moveKnight("Black", "BlackKnight1", "6A");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6A"), "BlackKnight1");
      Board.board.map.setValue("piecePos", "8B", "BlackKnight1");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces down and 1 space right.
   */
  @Test
  void test6() {
    try {
      System.out.println("Test 6");
      knight.moveKnight("Black", "BlackKnight1", "6C");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6C"), "BlackKnight1");
      Board.board.map.setValue("piecePos", "8B", "BlackKnight1");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces left and 1 space up.
   */
  @Test
  void test7() {
    /* The line below overrides all validation methods, to remove the Pawn in 2E so
    the WhiteKnight2 has a clear path to move. */
    Board.board.map.setValue("piecePos", "2E", "null");
  
    try {
      System.out.println("Test 7");
      knight.moveKnight("White", "WhiteKnight2", "2E");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "2E"), "WhiteKnight2");
      Board.board.map.setValue("piecePos", "1G", "WhiteKnight2");
      Board.board.map.printStatus();
      Board.board.map.setValue("piecePos", "2E", "WhitePawn5");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces left and 1 space down.
   */
  @Test
  void test8() {
    /* The line below overrides all validation methods, to remove the Pawn in 7E so
    the BlackKnight2 has a clear path to move. */
    Board.board.map.setValue("piecePos", "7E", "null");
  
    try {
      System.out.println("Test 8");
      knight.moveKnight("Black", "BlackKnight2", "7E");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "7E"), "BlackKnight2");
      Board.board.map.setValue("piecePos", "8G", "BlackKnight2");
      Board.board.map.setValue("piecePos", "7E", "BlackPawn5");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This test is to see if I can move my Knight into a destination tile which contains the own
   * player's piece, or if the method correctly identifies that I cannot move my Knight there.
   */
  @Test
  void test9() {    
    try {
      System.out.println("Test 9");
      knight.moveKnight("White", "WhiteKnight1", "2D");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "2D"), "WhitePawn4");
      Board.board.map.printStatus();
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }

  /**
   * This test is to see if I can move my Knight into a destination tile which contains an opponent
   * player's piece, and if the Knight successfully captures the opponent player's piece or not.
   */
  @Test
  void test10() {
    /* The line below overrides all validation methods, to add an opponent player's piece 
     * in 3C to see if WhiteKnight1 captures it or not. */
    Board.board.map.setValue("piecePos", "3C", "BlackPawn1");
    
    try {
      System.out.println("Test 10");
      knight.moveKnight("White", "WhiteKnight1", "3C");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3C"), "WhiteKnight1");
      assertEquals(Board.board.map.capturedPieces.get(0), "BlackPawn1");
      System.out.println("Captured: " + Board.board.map.capturedPieces.get(0));
      Board.board.map.setValue("piecePos", "1B", "WhiteKnight1");
      Board.board.map.setValue("piecePos", "7A", "BlackPawn1");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
}
