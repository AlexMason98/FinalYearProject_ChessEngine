import static org.junit.jupiter.api.Assertions.assertEquals;

import application.Rook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRook {

  private Rook rook;
 
  @BeforeEach
  void setUp() {
    rook = new Rook();
  }
 
  /**
   * This tests to see if I can successfully move a Rook to the left, with correct validation
   * being undertaken so that the Rook moves legally (follows game rules).
   */
  @Test
  void test() {
    // WhiteRook2 is in 1H and is moving 3 tiles to the left to 1E.
    rook.moveRook("White", "WhiteRook2", "1E");
    //assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "3A"), "WhiteRook1");
  }
  
  /**
   * This tests to see if I can successfully move a Rook to the right, with correct validation
   * being undertaken so that the Rook moves legally.
   */
  @Test
  void test2() {
    // WhiteRook1 is in 1A and is moving 3 tiles to the right to 1D.
    rook.moveRook("White", "WhiteRook1", "1D");
  }
  
  /**
   * This tests to see if I can successfully move a Rook up from its origin tile, with
   * correct validation being undertaken so that the Rook moves legally.
   */
  @Test
  void test3() {
    // WhiteRook1 is in 1A and is moving 3 tiles up to 4A.
    rook.moveRook("White", "WhiteRook1", "4A");
  }
  
  /**
   * This tests to see if my validation code is working in my 'moveRook' method when
   * trying to move a Rook downwards.
   */
  @Test
  void test4() {
    //BlackRook1 is in 8A and is moving 3 tiles down to 5A.
    rook.moveRook("Black", "BlackRook1", "5A");
  }
}
