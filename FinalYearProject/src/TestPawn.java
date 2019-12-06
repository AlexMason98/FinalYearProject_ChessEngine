import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Pawn;
import application.Piece;
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
      pawn.movePawn("White", "WhitePawn1", "3A");
      assertEquals(pawn.board.map.getPieceOrOccupation("piecePos", "3A"), "WhitePawn1");
      //pawn.board.map.printStatus();
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
  void test2() {
    assertEquals(pawn.firstMove("WhitePawn1"), true);
  }
  
  /**
   * This test further expands on my 'firstMove' method. It checks if a pawn returns false to being
   * its first move after just moving that pawn.
   */
  @Test
  void test3() {
    try {
      assertEquals(pawn.firstMove("WhitePawn1"), true);
      pawn.movePawn("White", "WhitePawn1", "3A");
      assertEquals(pawn.firstMove("WhitePawn1"), false);
      
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
  void test4() {
    try {
      assertEquals(pawn.firstMove("WhitePawn1"), true);
      pawn.movePawn("White", "WhitePawn1", "4A");
      assertEquals(pawn.firstMove("WhitePawn1"), false);
      pawn.movePawn("White", "WhitePawn1", "5A");
      pawn.board.map.printStatus();
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }
  }
  
  /**
   * This tests my isCheckmate method in parent class 'Piece'. It should return true if a Pawn is
   * putting the opponent's King into checkmate, whilst taking into account the validation rules
   * for a Pawn.
   */
  @Test
  void test5() {
    pawn.board.map.setValue("piecePos", "7D", "WhitePawn1");
   
    //try {
    try {
      pawn.board.map.printStatus();
      pawn.movePawn("White", "WhitePawn1", "8E");
      pawn.board.map.printStatus();
      assertEquals(pawn.piece.isCheckmate("White", "8E"), true);
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    } catch (InvalidPieceException e) {
      System.out.println(e.getMessage());
      fail("Exception Thrown");
    }

  }
  
}
