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
   * This tests to see if I can successfully move a Rook from one tile to another,
   * with correct validation being undertaken so that the Rook moves legally (follows
   * game rules).
   */
  @Test
  void test() {
    rook.moveRook("White", "WhiteRook1", "3A");
    assertEquals(rook.board.map.getPieceOrOccupation("piecePos", "3A"), "WhiteRook1");
  }

}
