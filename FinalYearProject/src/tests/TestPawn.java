package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Pawn;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPawn {

  private Pawn pawn;
  
  @BeforeEach
  void setUp() {
    pawn = new Pawn();
  }
  
  /**
   * This tests to see if I can successfully move a pawn from one tile to another.
   * Not only this, but if the move from the source tile is valid to the destination tile
   * by using validation programmed in the Pawn class.
   */
  @Test
  void test() {
    try {
      System.out.println("----- Test 1 -----");
      pawn.movePawn("White", "WhitePawn1", "3A");
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "3A"), "WhitePawn1");
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1"); //Reset piece (Bypassing validation)
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * Second test to see if I can successfully move a Pawn from one tile to another. This is with one
   * of the Black player's Pawns, as the validation this differs from the White player's pawns.
   * Namely, the Black player's Pawns are moving downwards whereas the White Player's pawns are 
   * moving upwards.
   */
  @Test
  void test2() {
    try {
      System.out.println("----- Test 2 -----");
      pawn.movePawn("Black", "BlackPawn1", "6A");
      Board.board.map.printStatus();
      assertEquals(Board.board.map.getPieceOrOccupation("piecePos", "6A"), "BlackPawn1");
      Board.board.map.setValue("piecePos", "7A", "BlackPawn1"); //Reset piece (Bypassing validation)
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests to see if I can check if a selected Pawn piece has moved before or not.
   * If it has moved already, the first move method should return false (meaning it can only
   * move one space forward or capture diagonally). If the pawn hasn't moved yet, the first
   * move method should return true (allowing the pawn to move two spaces forwards only in its
   * first go).
   */
  @Test
  void test3() {
    System.out.println("----- Test 3 -----");
    Board.board.map.printStatus();
    assertEquals(pawn.firstMove("WhitePawn1"), true);
  }
  
  /**
   * This test further expands on my 'firstMove' method. It checks if a pawn returns false to being
   * its first move after just moving that pawn.
   */
  @Test
  void test4() {
    try {
      System.out.println("----- Test 4 -----");
      Board.board.map.printStatus();
      assertEquals(pawn.firstMove("WhitePawn1"), true);
      pawn.movePawn("White", "WhitePawn1", "3A");
      assertEquals(pawn.firstMove("WhitePawn1"), false);
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1"); //Reset piece (Bypassing validation)
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * This is the final test for my 'firstMove' method, it checks to see that on a pawn's first move
   * that I can also move that pawn two spaces forward, and then only one space forward after this 
   * move.
   */
  @Test
  void test5() {
    try {
      System.out.println("----- Test 5 -----");
      assertEquals(pawn.firstMove("WhitePawn1"), true);
      pawn.movePawn("White", "WhitePawn1", "4A");
      assertEquals(pawn.firstMove("WhitePawn1"), false);
      pawn.movePawn("White", "WhitePawn1", "5A");
      Board.board.map.printStatus();
      Board.board.map.setValue("piecePos", "2A", "WhitePawn1"); //Reset piece (Bypassing validation)
      Board.board.map.printStatus();
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
}
