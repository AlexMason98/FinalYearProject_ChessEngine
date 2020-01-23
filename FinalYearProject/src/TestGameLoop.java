import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.GameLoop;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;


class TestGameLoop {

  private GameLoop gameLoop;

  @BeforeEach
  void setUp() {
    gameLoop = new GameLoop();
  }

  /**
   * This test will be checking to see if the 'isCheckmate' method inside the game loop is working.
   * This is critical as it is the main condition for the game loop continuing or terminating, 
   * depending on if a King is captured or not.
   */
  @Test
  void test() {
    gameLoop.board.map.capturedPieces.add("WhiteKing");
    assertEquals(gameLoop.isCheckmate(), true);
    
    gameLoop.board.map.capturedPieces.remove("WhiteKing");
    assertEquals(gameLoop.isCheckmate(), false);
  }

}
