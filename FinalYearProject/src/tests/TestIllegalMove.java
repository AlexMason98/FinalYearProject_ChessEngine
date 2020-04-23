package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.IllegalMove;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TestIllegalMove {

  private IllegalMove move;

  @BeforeEach
  void setUp() {
    move = new IllegalMove();
  }
  
  /**
   * Testing my illegalMove method. This is an important sub-method which will be used for Checkmate
   * and Stalemate goal states. It tests to see if a King is being moved illegally into a position
   * where it is "committing suicide" (where an opponent player can take the King in their next 
   * single move).
   */
  @Test
  void test1() {
    try {
      System.out.println("----- Test 1 -----");
      Board.board.map.setValue("piecePos", "3A", "WhiteKing");
      Board.board.map.printStatus();
      assertEquals(move.illegalMove("White", "4A"), false);
      Board.board.map.setValue("piecePos", "1E", "WhiteKing");
      Board.board.map.printStatus();
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /**
   * Testing illegalMove method. Tests to see if a King is illegally being moved into a position
   * where the opponent's Pawn can also move to (in an attacking move).
   */
  @Test
  void test2() {
    try {
      System.out.println("----- Test 2 -----");
      
      Board.board.map.setValue("piecePos", "3D", "BlackPawn1");
      Board.board.map.setValue("piecePos", "2D", "null");
      Board.board.map.printStatus();
      assertEquals(move.illegalMove("White", "2D"), true);
      Board.board.map.setValue("piecePos", "7A", "BlackPawn1"); //Reset piece (Bypassing validation)
      Board.board.map.setValue("piecePos", "2D", "WhitePawn4"); //Reset piece (Bypassing validation)
      Board.board.map.printStatus();
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /**
   * Testing illegalMove method. Tests to see if a King is illegally being moved into a position
   * where the opponent's Rook can also move to.
   */
  @Test
  void test3() {
    try {
      System.out.println("----- Test 3 -----");
      
      Board.board.map.setValue("piecePos", "3A", "BlackRook1");
      Board.board.map.setValue("piecePos", "2E", "WhiteKing");
      Board.board.map.printStatus();
      assertEquals(move.illegalMove("White", "3E"), true);
      Board.board.map.setValue("piecePos", "8A", "BlackRook1"); //Reset piece (Bypassing validation)
      Board.board.map.setValue("piecePos", "1E", "WhiteKing"); //Reset piece (Bypassing validation)
      Board.board.map.setValue("piecePos", "2E", "WhitePawn5"); //Reset piece (Bypassing validation)
      Board.board.map.printStatus();
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /*
   * Testing illegalMove method. Tests to see if a King is illegally being moved into a position
   * where the opponent's Knight can also move to.
   */
  @Test
  void test4() {
    try {
      System.out.println("----- Test 4 -----");
      
      Board.board.map.setValue("piecePos", "5D", "BlackKnight1");
      Board.board.map.setValue("piecePos", "2E", "WhiteKing");
      Board.board.map.printStatus();
      assertEquals(move.illegalMove("White", "3E"), true);
      Board.board.map.setValue("piecePos", "8B", "BlackKnight1"); //Reset piece
      Board.board.map.setValue("piecePos", "1E", "WhiteKing"); //Reset piece
      Board.board.map.setValue("piecePos", "2E", "WhitePawn5"); //Reset piece
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /*
   * Testing illegalMove method. Tests to see if a King is illegally being moved into a position
   * where the opponent's Bishop can move to.
   */
  @Test
  void test5() {
    try {
      System.out.println("----- Test 5 -----");
      
      Board.board.map.setValue("piecePos", "6G", "BlackBishop2");
      Board.board.map.setValue("piecePos", "2E", "WhiteKing");
      Board.board.map.printStatus();
      assertEquals(move.illegalMove("White", "3D"), true);
      Board.board.map.setValue("piecePos", "8F", "BlackBishop2");
      Board.board.map.setValue("piecePos", "1E", "WhiteKing");
      Board.board.map.setValue("piecePos", "2E", "WhitePawn5");
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /*
   * Testing illegalMove method. Tests to see if a King is illegally being moved into a position
   * where the opponent's Queen can move to.
   */
  @Test
  void test6() {
    try {
      System.out.println("----- Test 6 -----");
      
      Board.board.map.setValue("piecePos", "7C", "BlackQueen");
      Board.board.map.setValue("piecePos", "3F", "WhiteKing");
      Board.board.map.printStatus();
      assertEquals(move.illegalMove("White", "4F"), true);
      Board.board.map.setValue("piecePos", "8D", "BlackQueen");
      Board.board.map.setValue("piecePos", "7C", "BlackPawn3");
      Board.board.map.setValue("piecePos", "1E", "WhiteKing");
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /*
   * Testing illegalMove method. Tests to see if a King is illegally being moved into a position
   * where the opponent's King can also move to.
   */
  @Test
  void test7() {
    try {
      System.out.println("----- Test 7 -----");
    
      Board.board.map.setValue("piecePos", "6A", "WhiteKing");
      Board.board.map.setValue("piecePos", "6C", "BlackKing");
      assertEquals(move.illegalMove("White", "6B"), true);
      Board.board.map.setValue("piecePos", "1E", "WhiteKing");
      Board.board.map.setValue("piecePos", "8E", "BlackKing");
      Board.board.map.printStatus();
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /*
   * Testing illegalMove method. Tests to see that method returns false to a King being illegally
   * moved when it hasn't been moved/remains in its starting position.
   */
  @Test
  void test8() {
    try {
      System.out.println("----- Test 8 -----");
    
      assertEquals(move.illegalMove("Black", "8E"), false);
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }

}
