package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Checkmate;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCheckmate {

  private Checkmate checkmate;
  
  @BeforeEach
  void setUp() {
    checkmate = new Checkmate();
  }
  
  /**
   * This tests to see if my Checkmate method correctly returns true when using
   * an example of a game state where the opponent's King is in checkmate.
   */
  @Test
  void test() {
    
    try {
      Board.board.map.setValue("piecePos", "4H", "BlackQueen");
      Board.board.map.setValue("piecePos", "5E", "BlackPawn5");
      Board.board.map.setValue("piecePos", "3F", "WhitePawn6");
      Board.board.map.setValue("piecePos", "4G", "WhitePawn7");
      checkmate.legalTilesWhite.clear();
      checkmate.legalTilesBlack.clear();
      
      assertEquals(checkmate.checkmate(), true);
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
   * This tests to see if my Checkmate method correctly returns false when
   * using an example of a game state where all pieces are in their starting
   * positions.
   */
  @Test
  void test2() {
  	
    try {
      
      assertEquals(checkmate.checkmate(), false);
      Board.board.map.printStatus();
      
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /*@Test
  void test2() {
    Board.board.map.setValue("piecePos", "4H", "BlackQueen");
    Board.board.map.setValue("piecePos", "5E", "BlackPawn5");
    Board.board.map.setValue("piecePos", "3F", "WhitePawn6");
    Board.board.map.setValue("piecePos", "4G", "WhitePawn7");
    
    try {
      assertEquals(checkmate.escapeCheck("White"), false);
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }*/

}
