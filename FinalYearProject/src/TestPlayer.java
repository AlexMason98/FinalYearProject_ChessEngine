import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import application.Player;
import application.exceptions.InvalidPlayerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestPlayer {

  private Player player;

  @BeforeEach
  void setUp() {
    try {
      player = new Player("White");
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
    }
    
  }

  /**
   * This tests that my constructor to see if I can set the current player to White.
   */
  @Test
  void test() {
    assertEquals(player.getPlayer(), "White");
  }
  
  /**
   * This tests to see if I can reuse the constructor to set the current player
   * as Black.
   */
  @Test
  void test2() {
    try {
      player = new Player("Black");
      assertEquals(player.getPlayer(), "Black");
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * This test checks to see if it is the white player's turn or not. It returns true if it is,
   * false if its the Black player's turn.
   */
  @Test
  void test3() {
    assertEquals(player.isWhiteTurn(), true);
  }
  
  /**
   * This test checks to see if it is the black player's turn or not.
   */
  @Test
  void test4() {
    try {
      player = new Player("Black");
      assertEquals(player.isWhiteTurn(), false);
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * This tests to see if my InvalidPlayerException works if I accidentally pass anything other
   * than White or Black as the player.
   */
  @Test
  void test5() {
    try {
      player = new Player("ThrowAnException");
    } catch (InvalidPlayerException e) {
      System.out.println(e.getMessage());
    }
  }

}
