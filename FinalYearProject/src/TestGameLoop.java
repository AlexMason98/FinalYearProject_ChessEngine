import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.GameLoop;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;


class GameLoopTest {

  private GameLoop gameLoop;

  @BeforeEach
  void setUp() {
    gameLoop = new GameLoop();
  }

  /**
   * This test will be testing my game loop by checking if it is the white piece player's turn
   * first, which it should be when normally starting a game of Chess.
   */
  @Test
  void test() {
    fail("Not yet implemented");
  }

}
