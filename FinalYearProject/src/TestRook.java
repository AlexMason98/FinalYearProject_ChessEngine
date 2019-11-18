import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Rook;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRook {

  private Rook rook;
 
  @BeforeEach
  void setUp() {
    rook = new Rook();
  }
 
  /**
   * This tests to see if my validation code is working in my 'moveRook' method when
   * trying to move a Rook to the left.
   */
  @Test
  void test() {
    // WhiteRook2 is in 1H and is moving 3 tiles to the left to 1E.
    try {
      rook.moveRook("White", "WhiteRook2", "1E");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if my validation code is working in my 'moveRook' method when
   * trying to move a Rook to the right.
   */
  @Test
  void test2() {
    // WhiteRook1 is in 1A and is moving 3 tiles to the right to 1D.
    try {
      rook.moveRook("White", "WhiteRook1", "1D");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if my validation code is working in my 'moveRook' method when
   * trying to move a Rook upwards.
   */
  @Test
  void test3() {
    // WhiteRook1 is in 1A and is moving 3 tiles up to 4A.
    try {
      rook.moveRook("White", "WhiteRook1", "4A");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if my validation code is working in my 'moveRook' method when
   * trying to move a Rook downwards.
   */
  @Test
  void test4() {
    //BlackRook1 is in 8A and is moving 3 tiles down to 5A.
    try {
      rook.moveRook("Black", "BlackRook1", "5A");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Rook or not in my 'setPos' method, 
   * when there ARE pieces inside its movement path, and IS NOT a piece in its destination tile.
   */
  @Test
  void test5() {
    try {
      /* When moving up from WhiteRook1's starting position (1A) to 3A, it is blocked
      by WhitePawn1 in 2A. Therefore the method should prevent the move */
      rook.moveRook("White", "WhiteRook1", "3A");
      assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "3A"), null);
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Rook or not in my 'setPos' method, 
   * when there ARE NOT pieces inside its movement path, and IS NOT a piece in its destination tile.
   */
  @Test
  void test6() {
    /* The line below overrides all validation methods, to remove the Pawn in 2A so
    the WhiteRook has a clear path to move. */
    rook.board.map.setValue("piecePos", "2A", "null");
    
    try {
      rook.moveRook("White", "WhiteRook1", "3A");
      rook.board.map.printStatus();
      assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "3A"), "WhiteRook1");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
    
  }
  
  /**
   * This tests to see if I can set the new position of a Rook or not in my 'setPos' method, 
   * when there ARE pieces inside its movement path, and IS a piece in its destination tile. 
   */
  @Test
  void test7() {
    // Setting a test piece in the Rook's destination tile.
    rook.board.map.setValue("piecePos", "3A", "TestPiece");
    try {
      System.out.println("Test 7");
      rook.moveRook("White", "TestPiece", "3A");
      /* Intending on TestPiece still being the piece in 3A instead of WhiteRook1, because if the
      validation is correct, the Rook cannot move there. */
      assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "3A"), "TestPiece");
      rook.board.map.printStatus();
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Rook or not in my 'setPos' method, 
   * when there ARE NOT pieces inside its movement path, and IS a piece in its destination tile 
   * which is the own player's piece.
   */
  @Test
  void test8() {
    /* The lines below override all validation methods, to remove the Pawn in 2A so
    the WhiteRook has a clear path to move. */
    rook.board.map.setValue("piecePos", "2A", "null");
    rook.board.map.setValue("piecePos", "3A", "WhiteTest1");
    
    try {
      System.out.println("Test 8");
      rook.moveRook("White", "WhiteRook1", "3A");
      assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "3A"), "WhiteTest1");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can set the new position of a Rook or not in my 'setPos' method, 
   * when there ARE NOT pieces inside its movement path, and IS a piece in its destination tile 
   * which is an opponent's piece.
   */
  @Test
  void test9() {
    /* The line below overrides all validation methods, to remove the Pawn in 2A so
    the WhiteRook has a clear path to move. */
    rook.board.map.setValue("piecePos", "2A", "null");
    
    try {
      System.out.println("Test 9");
      rook.moveRook("White", "WhiteRook1", "7A");
      assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "7A"), "WhiteRook1");
      assertEquals(rook.board.map.capturedPieces.get(0), "BlackPawn1");
    } catch (InvalidPieceException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    } catch (InvalidPlayerException e) {
      e.printStackTrace();
      fail("Exception Thrown");
    }
  }
}
