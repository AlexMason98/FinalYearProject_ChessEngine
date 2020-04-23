package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import application.Board;
import application.Controller;
import application.LegalMoves;
import application.Stalemate;
import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TestLegalMoves {

  private LegalMoves algo;
  private Controller controller;
  
  @BeforeEach
  void setUp() {
    algo = new LegalMoves();
    controller = new Controller();
    
  }

  /**
   * This tests to see if my legalMoves method is working. It will assess the player's possible 
   * moves print which moves are legal to the console.
   * Chess State is when pieces are all in starting positions.
   */
  @Test
  void test() {
    Board.board.map.printStatus();
    try {
      LegalMoves.legalMoves("Black");
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
   * This tests to see if my legalMoves method is working. It will assess the player's possible 
   * moves print which moves are legal to the console.
   * Chess State is when pieces are all in starting positions.
   */
  @Test
  void test2() {
    Board.board.map.printStatus();
    try {
      LegalMoves.legalMoves("White");
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  @Test
  void test3() {
    try {
      Controller.rook.moveRook("White", "WhiteRook1", "1A");
      assertTrue(Board.board.map.getTile("WhiteRook1") == "1A");
      LegalMoves.legalMoves("White");
      assertTrue(LegalMoves.legalMoves.contains("4E, WhitePawn5"));
      Board.board.map.setValue("piecePos", "4E", "WhitePawn5");
      Board.board.map.printStatus();
      LegalMoves.legalMoves("Black");
      assertTrue(LegalMoves.legalMoves.contains("5F, BlackPawn6"));
      Controller.pawn.movePawn("Black", "BlackPawn6", "5F");
      //Board.board.map.setValue("piecePos", "5F", "BlackPawn6");
      assertTrue(Controller.pawn.movedPawns.contains("BlackPawn6"));
      Board.board.map.printStatus();
      LegalMoves.legalMoves("White");
      assertTrue(LegalMoves.legalMoves.contains("5F, WhitePawn5"));
      Controller.pawn.movePawn("White", "WhitePawn5", "5F");
      assertTrue(Board.board.map.capturedPieces.contains("BlackPawn6"));
      Board.board.map.printStatus();
      LegalMoves.legalMoves("Black");
      
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /**
   * This tests to see if my legalMoves method is working. It will assess the player's possible 
   * moves print which moves are legal to the console.
   * Chess State Source: https://en.wikipedia.org/wiki/Stalemate
   */
  @Test
  void test4() {
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
    try {
      algo.legalMoves("Black");
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  /**
   * This tests to see if my legalMoves method is working. It will assess the player's possible 
   * moves print which moves are legal to the console.
   * Chess State Source: https://en.wikipedia.org/wiki/Stalemate
   */
  @Test
  void test5() {
    // USES SAME POSITIONS AS TEST 3
    Board.board.map.printStatus();
    try {
      algo.legalMoves("White");
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
  
  @Test
  void test6() {
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
    Controller.pawn.movedPawns.add("BlackPawn1");
    Board.board.map.setValue("piecePos", "7E", "BlackPawn1");
    Board.board.map.setValue("piecePos", "7F", "null");
    Controller.pawn.movedPawns.add("BlackPawn2");
    Board.board.map.setValue("piecePos", "7G", "BlackPawn2");
    Board.board.map.setValue("piecePos", "7H", "BlackQueen");
    Board.board.map.setValue("piecePos", "6E", "WhiteQueen");
    Controller.pawn.movedPawns.add("BlackPawn3");
    Board.board.map.setValue("piecePos", "6F", "BlackPawn3");
    Board.board.map.setValue("piecePos", "6G", "BlackKing");
    Controller.rook.movedRooks.add("BlackRook2");
    Board.board.map.setValue("piecePos", "6H", "BlackRook2");
    Controller.pawn.movedPawns.add("BlackPawn4");
    Board.board.map.setValue("piecePos", "5H", "BlackPawn4");
    Controller.pawn.movedPawns.add("WhitePawn1");
    Board.board.map.setValue("piecePos", "4H", "WhitePawn1");
    Controller.pawn.movedPawns.add("WhitePawn2");
    Board.board.map.setValue("piecePos", "3E", "WhitePawn2");
    Board.board.map.setValue("piecePos", "1D", "null");
    Controller.pawn.movedPawns.add("WhitePawn3");
    Board.board.map.setValue("piecePos", "2A", "WhitePawn3");
    Controller.pawn.movedPawns.add("WhitePawn4");
    Board.board.map.setValue("piecePos", "2B", "WhitePawn4");
    Controller.pawn.movedPawns.add("WhitePawn5");
    Board.board.map.setValue("piecePos", "2C", "WhitePawn5");
    Controller.pawn.movedPawns.add("WhitePawn6");
    Board.board.map.setValue("piecePos", "2D", "WhitePawn6");
    Board.board.map.setValue("piecePos", "2E", "null");
    Controller.pawn.movedPawns.add("WhitePawn7");
    Board.board.map.setValue("piecePos", "2F", "WhitePawn7");
    Controller.pawn.movedPawns.add("WhitePawn8");
    Board.board.map.setValue("piecePos", "2G", "WhitePawn8");
    Board.board.map.setValue("piecePos", "2H", "null");
    Board.board.map.printStatus();
    
    try {
      algo.legalMoves("Black");
    } catch (InvalidPieceException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    } catch (InvalidPlayerException e) {
      fail("Exception Thrown");
      e.printStackTrace();
    }
  }
 
}
  