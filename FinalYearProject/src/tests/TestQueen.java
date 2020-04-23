package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Queen;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestQueen {

  private Queen queen;
  
  @BeforeEach
  void setUp() {
    queen = new Queen();
  }

  /**
   * This tests to see if I can move the Queen left.
   */
  @Test
  void test() {
    Board.board.map.setValue("piecePos", "1C", "null");
    Board.board.map.setValue("piecePos", "1B", "null");
    try {
      System.out.println("----- Test 1 -----");
      Board.board.map.printStatus();
      queen.moveQueen("White", "WhiteQueen", "1B");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "1B"), "WhiteQueen");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "1C", "WhiteBishop1");
      Board.board.map.setValue("piecePos", "1B", "WhiteKnight1");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can move the Queen right.
   */
  @Test
  void test2() {
    Board.board.map.setValue("piecePos", "1E", "null");
    Board.board.map.setValue("piecePos", "1F", "null");
    try {
      System.out.println("----- Test 2 -----");
      queen.moveQueen("White", "WhiteQueen", "1F");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "1F"), "WhiteQueen");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "1E", "WhiteKing");
      Board.board.map.setValue("piecePos", "1F", "WhiteBishop2");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }

  /**
   * This tests to see if I can move the Queen up.
   */
  @Test
  void test3() {
    Board.board.map.setValue("piecePos", "2D", "null");
    try {
      System.out.println("----- Test 3 -----");
      queen.moveQueen("White", "WhiteQueen", "3D");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3D"), "WhiteQueen");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can move the Queen down.
   */
  @Test
  void test4() {
    Board.board.map.setValue("piecePos", "7D", "null");
    try {
      System.out.println("----- Test 4 -----");
      queen.moveQueen("Black", "BlackQueen", "6D");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6D"), "BlackQueen");
      Board.board.map.setValue("piecePos", "8D", "BlackQueen");
      Board.board.map.setValue("piecePos", "7D", "BlackPawn4");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can move the Queen diagonally North East.
   */
  @Test
  void test5() {
    Board.board.map.setValue("piecePos", "2E", "null");
    try {
      System.out.println("----- Test 5 -----");
      queen.moveQueen("White", "WhiteQueen", "3F");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3F"), "WhiteQueen");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "2E", "WhitePawn5");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can move the Queen diagonally South East.
   */
  @Test
  void test6() {
    Board.board.map.setValue("piecePos", "7E", "null");
    try {
      System.out.println("----- Test 6 -----");
      queen.moveQueen("Black", "BlackQueen", "6F");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6F"), "BlackQueen");
      Board.board.map.setValue("piecePos", "8D", "BlackQueen");
      Board.board.map.setValue("piecePos", "7E", "BlackPawn5");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can move the Queen diagonally South West.
   */
  @Test
  void test7() {
    Board.board.map.setValue("piecePos", "7C", "null");
    try {
      System.out.println("----- Test 7 -----");
      queen.moveQueen("Black", "BlackQueen", "6B");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6B"), "BlackQueen");
      Board.board.map.setValue("piecePos", "8D", "BlackQueen");
      Board.board.map.setValue("piecePos", "7C", "BlackPawn3");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can move the Queen diagonally North West.
   */
  @Test
  void test8() {
    Board.board.map.setValue("piecePos", "2C", "null");
    try {
      System.out.println("----- Test 8 -----");
      queen.moveQueen("White", "WhiteQueen", "3B");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3B"), "WhiteQueen");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "2C", "WhitePawn3");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }

  /**
   * This tests to see if I can set the new position of a Queen or not in my 'setPos' method, 
   * when there ARE pieces inside its movement path, and IS NOT a piece in its destination tile.
   */
  @Test
  void test9() {
    try {
      System.out.println("----- Test 9 -----");
      /* When moving up from WhiteQueen's starting position (1D) to 3F, it is blocked
      by WhitePawn5 in 2E. Therefore the method should prevent the move */
      queen.moveQueen("White", "WhiteQueen", "3F");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3F"), "null");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Queen or not in my 'setPos' method, 
   * when there ARE NOT pieces inside its movement path, and IS NOT a piece in its destination tile.
   */
  @Test
  void test10() {
    /* The line below overrides all validation methods, to remove the Pawn in 2D so
    the WhiteQueen has a clear path to move. */
    Board.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("----- Test 10 -----");
      queen.moveQueen("White", "WhiteQueen", "4D");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "4D"), "WhiteQueen");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
      Board.board.map.printStatus();
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if I can set the new position of a Queen or not in my 'setPos' method, 
   * when there ARE pieces inside its movement path, and IS a piece in its destination tile. 
   */
  @Test
  void test11() {
    // Setting a test piece (WhitePawn2) in the queen's destination tile.
    Board.board.map.setValue("piecePos", "3D", "WhitePawn2");
    try {
      System.out.println("----- Test 11 -----");
      queen.moveQueen("White", "WhiteQueen", "3D");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3D"), "WhitePawn2");
      Board.board.map.printStatus();
      Board.board.map.setValue("piecePos", "2B", "WhitePawn2");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Queen or not in my 'setPos' method, 
   * when there ARE NOT pieces inside its movement path, and IS a piece in its destination tile 
   * which is the own player's piece.
   */
  @Test
  void test12() {
    /* The lines below override all validation methods, to remove the Pawn in 2D so
    the WhiteQueen has a clear path to move. */
    Board.board.map.setValue("piecePos", "2D", "null");
    Board.board.map.setValue("piecePos", "4D", "WhitePawn1");
    
    try {
      System.out.println("----- Test 12 -----");
      queen.moveQueen("White", "WhiteQueen", "4D");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "4D"), "WhitePawn1");
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Queen or not in my 'setPos' method, 
   * when there ARE NOT pieces inside its movement path, and IS a piece in its destination tile 
   * which is an opponent's piece.
   */
  @Test
  void test13() {
    /* The line below overrides all validation methods, to remove the Pawn in 2A so
    the WhiteQueen has a clear path to move. */
    Board.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("----- Test 13 -----");
      queen.moveQueen("White", "WhiteQueen", "7D");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "7D"), "WhiteQueen");
      assertEquals(Board.board.map.capturedPieces.get(0), "BlackPawn4");
      Board.board.map.setValue("piecePos", "1D", "WhiteQueen");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4");
      Board.board.map.setValue("piecePos", "7D", "BlackPawn4");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Queen or not in my 'setPos' method, 
   * when there ARE opponent pieces inside its movement path, and IS a piece in its destination
   * tile which is the own player's piece.
   */
  @Test
  void test14() {
    /* The lines below override all validation methods, to remove the Pawn in 2D so
    the WhiteQueen has a clear path to move. */
    Board.board.map.setValue("piecePos", "1C", "BlackPawn1");
    Board.board.map.setValue("piecePos", "1B", "BlackPawn2");
    Board.board.map.setValue("piecePos", "1A", "WhitePawn1");
    
    try {
      System.out.println("----- Test 14 -----");
      queen.moveQueen("White", "WhiteQueen", "1A");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "1A"), "WhitePawn1");
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1");
      Board.board.map.setValue("piecePos", "7A", "BlackPawn1");
      Board.board.map.setValue("piecePos", "7B", "BlackPawn2");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  

}
