package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Stalemate;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



class TestStalemate {

  private Stalemate stalemate;
  
  @BeforeEach
  void setUp() {
    stalemate = new Stalemate();  
  }

  /**
   * This tests to see if my stalemate method is working. It will assess the King's possible moves
   * and deduct the number of moves where the King is in a stalemate. If the result is 0 (as in zero
   * legal moves), the method will return true.
   * Stalemate Source: https://en.wikipedia.org/wiki/Stalemate
   */
  @Test
  void test() {
    try {
      Board.board.map.setValue("piecePos", "8A", "null");
      Board.board.map.setValue("piecePos", "8B", "null");
      Board.board.map.setValue("piecePos", "8C", "null");
      Board.board.map.setValue("piecePos", "8D", "null");
      Board.board.map.setValue("piecePos", "8F", "null");
      Board.board.map.setValue("piecePos", "8G", "null");
      Board.board.map.setValue("piecePos", "8H", "BlackKing");
      Board.board.map.setValue("piecePos", "7A", "null");
      Board.board.map.setValue("piecePos", "7B", "null");
      Board.board.map.setValue("piecePos", "7C", "null");
      Board.board.map.setValue("piecePos", "7D", "null");
      Board.board.map.setValue("piecePos", "7E", "null");
      Board.board.map.setValue("piecePos", "7F", "WhiteKing");
      Board.board.map.setValue("piecePos", "7G", "null");
      Board.board.map.setValue("piecePos", "7H", "null");
      Board.board.map.setValue("piecePos", "6G", "WhiteQueen");
      Board.board.map.setValue("piecePos", "1A", "null");
      Board.board.map.setValue("piecePos", "1B", "null");
      Board.board.map.setValue("piecePos", "1C", "null");
      Board.board.map.setValue("piecePos", "1D", "null");
      Board.board.map.setValue("piecePos", "1F", "null");
      Board.board.map.setValue("piecePos", "1G", "null");
      Board.board.map.setValue("piecePos", "1H", "null");
      Board.board.map.setValue("piecePos", "2A", "null");
      Board.board.map.setValue("piecePos", "2B", "null");
      Board.board.map.setValue("piecePos", "2C", "null");
      Board.board.map.setValue("piecePos", "2D", "null");
      Board.board.map.setValue("piecePos", "2E", "null");
      Board.board.map.setValue("piecePos", "2F", "null");
      Board.board.map.setValue("piecePos", "2G", "null");
      Board.board.map.setValue("piecePos", "2H", "null");
      Board.board.map.printStatus();
      assertEquals(stalemate.stalemate(), true);
      Board.board.map.printStatus();
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
    }
  }
  
  /**
   * Stalemate Source: https://en.wikipedia.org/wiki/Stalemate (Diagram 5)
   */
  @Test
  void test2() {
    try {
      
      // USING NEW POSITIONS
      Board.board.map.setValue("piecePos", "8A", "BlackKing");
      Board.board.map.setValue("piecePos", "8B", "null");
      Board.board.map.setValue("piecePos", "8C", "null");
      Board.board.map.setValue("piecePos", "8D", "null");
      Board.board.map.setValue("piecePos", "8E", "null");
      Board.board.map.setValue("piecePos", "8F", "null");
      Board.board.map.setValue("piecePos", "8G", "null");
      Board.board.map.setValue("piecePos", "8H", "null");
      Board.board.map.setValue("piecePos", "7A", "WhitePawn1");
      Board.board.map.setValue("piecePos", "7B", "null");
      Board.board.map.setValue("piecePos", "7C", "null");
      Board.board.map.setValue("piecePos", "7D", "null");
      Board.board.map.setValue("piecePos", "7E", "null");
      Board.board.map.setValue("piecePos", "7F", "null");
      Board.board.map.setValue("piecePos", "7G", "null");
      Board.board.map.setValue("piecePos", "7H", "null");
      Board.board.map.setValue("piecePos", "6A", "WhiteKing");
      Board.board.map.setValue("piecePos", "6G", "null");
      Board.board.map.setValue("piecePos", "4F", "WhiteBishop1");
      Board.board.map.setValue("piecePos", "1A", "null");
      Board.board.map.setValue("piecePos", "1B", "null");
      Board.board.map.setValue("piecePos", "1C", "null");
      Board.board.map.setValue("piecePos", "1D", "null");
      Board.board.map.setValue("piecePos", "1E", "null");
      Board.board.map.setValue("piecePos", "1F", "null");
      Board.board.map.setValue("piecePos", "1G", "null");
      Board.board.map.setValue("piecePos", "1H", "null");
      Board.board.map.setValue("piecePos", "2A", "null");
      Board.board.map.setValue("piecePos", "2B", "null");
      Board.board.map.setValue("piecePos", "2C", "null");
      Board.board.map.setValue("piecePos", "2D", "null");
      Board.board.map.setValue("piecePos", "2E", "null");
      Board.board.map.setValue("piecePos", "2F", "null");
      Board.board.map.setValue("piecePos", "2G", "null");
      Board.board.map.setValue("piecePos", "2H", "null");
      Board.board.map.printStatus();
      assertEquals(stalemate.stalemate(), true);
      System.out.println("PRINT STATUS");
      Board.board.map.printStatus();
      
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
    }
  }
 
  /**
   * This tests my Stalemate method by testing if one or both of the players are in a
   * Stalemate (such as when there are no legal moves that can be made by a player).
   * I used the source below as an example for testing my Stalemate method.
   * Source: https://www.quora.com/What-is-stalemate-condition-in-chess (Image 3)
   */
  @Test
  void test3() {
    try {
      
      // USING NEW POSITIONS
      Board.board.map.setValue("piecePos", "8B", "null");
      Board.board.map.setValue("piecePos", "8C", "null");
      Board.board.map.setValue("piecePos", "8D", "null");
      Board.board.map.setValue("piecePos", "8F", "BlackBishop1");
      Board.board.map.setValue("piecePos", "8G", "BlackKnight1");
      Board.board.map.setValue("piecePos", "8H", "BlackRook1");
      Board.board.map.setValue("piecePos", "7B", "null");
      Board.board.map.setValue("piecePos", "7C", "null");
      Board.board.map.setValue("piecePos", "7D", "null");
      Stalemate.pawn.movedPawns.add("BlackPawn1");
      Board.board.map.setValue("piecePos", "7E", "BlackPawn1");
      Board.board.map.setValue("piecePos", "7F", "null");
      Stalemate.pawn.movedPawns.add("BlackPawn2");
      Board.board.map.setValue("piecePos", "7G", "BlackPawn2");
      Board.board.map.setValue("piecePos", "7H", "BlackQueen");
      Board.board.map.setValue("piecePos", "6E", "WhiteQueen");
      Stalemate.pawn.movedPawns.add("BlackPawn3");
      Board.board.map.setValue("piecePos", "6F", "BlackPawn3");
      Board.board.map.setValue("piecePos", "6G", "BlackKing");
      stalemate.rook.movedRooks.add("BlackRook2");
      Board.board.map.setValue("piecePos", "6H", "BlackRook2");
      Stalemate.pawn.movedPawns.add("BlackPawn4");
      Board.board.map.setValue("piecePos", "5H", "BlackPawn4");
      Board.board.map.setValue("piecePos", "4H", "WhitePawn1");
      Board.board.map.setValue("piecePos", "3E", "WhitePawn2");
      Board.board.map.setValue("piecePos", "1D", "null");
      Board.board.map.setValue("piecePos", "2A", "WhitePawn3");
      Board.board.map.setValue("piecePos", "2B", "WhitePawn4");
      Board.board.map.setValue("piecePos", "2C", "WhitePawn5");
      Board.board.map.setValue("piecePos", "2D", "WhitePawn6");
      Board.board.map.setValue("piecePos", "2E", "null");
      Board.board.map.setValue("piecePos", "2F", "WhitePawn7");
      Board.board.map.setValue("piecePos", "2G", "WhitePawn8");
      Board.board.map.setValue("piecePos", "2H", "null");
      Board.board.map.printStatus();
      assertEquals(stalemate.stalemate(), true);
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests my Stalemate method by testing if one or both of the players are in a
   * Stalemate (such as when there are no legal moves that can be made by a player).
   * In this test, I am checking to see if the test case correctly returns false to being
   * in a Stalemate when both/all player's pieces are in their starting positions.
   */
  @Test
  void test4() {
    Board.board.map.printStatus();
    try {
      // USES DEFAULT POSITIONS
      
      //Board.board.map.setValue("piecePos", "");
      Stalemate.pawn.movedPawns.clear();
      assertEquals(stalemate.stalemate(), false);
      Board.board.map.printStatus();
      //stalemate.pawn.movedPawnLock = false;
      for (int i = 0; i < Stalemate.pawn.movedPawns.size(); i++) {
        System.out.println(Stalemate.pawn.movedPawns.get(i));
      }
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  

}
