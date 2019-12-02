import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    queen.board.map.setValue("piecePos", "1C", "null");
    queen.board.map.setValue("piecePos", "1B", "null");
    try {
      System.out.println("Test 1");
      queen.moveQueen("White", "WhiteQueen", "1B");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "1B"), "WhiteQueen");
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
    queen.board.map.setValue("piecePos", "1E", "null");
    queen.board.map.setValue("piecePos", "1F", "null");
    try {
      System.out.println("Test 2");
      queen.moveQueen("White", "WhiteQueen", "1F");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "1F"), "WhiteQueen");
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
    queen.board.map.setValue("piecePos", "2D", "null");
    try {
      System.out.println("Test 3");
      queen.moveQueen("White", "WhiteQueen", "3D");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "3D"), "WhiteQueen");
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
    queen.board.map.setValue("piecePos", "7D", "null");
    try {
      System.out.println("Test 4");
      queen.moveQueen("White", "BlackQueen", "6D");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "6D"), "BlackQueen");
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
    queen.board.map.setValue("piecePos", "2E", "null");
    try {
      System.out.println("Test 5");
      queen.moveQueen("White", "WhiteQueen", "3F");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "3F"), "WhiteQueen");
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
    queen.board.map.setValue("piecePos", "7E", "null");
    try {
      System.out.println("Test 6");
      queen.moveQueen("Black", "BlackQueen", "6F");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "6F"), "BlackQueen");
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
    queen.board.map.setValue("piecePos", "7C", "null");
    try {
      System.out.println("Test 7");
      queen.moveQueen("Black", "BlackQueen", "6B");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "6B"), "BlackQueen");
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
    queen.board.map.setValue("piecePos", "2C", "null");
    try {
      System.out.println("Test 8");
      queen.moveQueen("White", "WhiteQueen", "3B");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "3B"), "WhiteQueen");
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
      System.out.println("Test 9");
      /* When moving up from WhiteQueen's starting position (1D) to 3F, it is blocked
      by WhitePawn5 in 2E. Therefore the method should prevent the move */
      queen.moveQueen("White", "WhiteQueen", "3F");
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "3F"), null);
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
    queen.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("Test 10");
      queen.moveQueen("White", "WhiteQueen", "4D");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "4D"), "WhiteQueen");
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
    queen.board.map.setValue("piecePos", "3D", "WhitePawn2");
    try {
      System.out.println("Test 11");
      queen.moveQueen("White", "WhiteQueen", "3D");
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "3D"), "WhitePawn2");
      queen.board.map.printStatus();
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
    queen.board.map.setValue("piecePos", "2D", "null");
    queen.board.map.setValue("piecePos", "4D", "WhitePawn1");
    
    try {
      System.out.println("Test 12");
      queen.moveQueen("White", "WhiteQueen", "4D");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "4D"), "WhitePawn1");
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
    queen.board.map.setValue("piecePos", "2D", "null");
    
    try {
      System.out.println("Test 13");
      queen.moveQueen("White", "WhiteQueen", "7D");
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "7D"), "WhiteQueen");
      assertEquals(queen.board.map.capturedPieces.get(0), "BlackPawn4");
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
    queen.board.map.setValue("piecePos", "1C", "BlackPawn1");
    queen.board.map.setValue("piecePos", "1B", "BlackPawn2");
    queen.board.map.setValue("piecePos", "1A", "WhitePawn1");
    
    try {
      System.out.println("Test 14");
      queen.moveQueen("White", "WhiteQueen", "1A");
      queen.board.map.printStatus();
      assertEquals(queen.board.map.getPieceOrOccupation("piecePos", "1A"), "WhitePawn1");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  

}
