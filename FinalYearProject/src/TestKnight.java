import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import application.Knight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestKnight {

  private Knight knight;
  
  @BeforeEach
  void setUp() {
    knight = new Knight();
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces up and 1 space left.
   */
  @Test
  void test() {
    System.out.println("Test 1");
    knight.moveKnight("White", "WhiteKnight1", "3A");
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces up and 1 space right.
   */
  @Test
  void test2() {
    System.out.println("Test 2");
    knight.moveKnight("White", "WhiteKnight1", "3C");
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces right and 1 space up.
   */
  @Test
  void test3() {
    System.out.println("Test 3");
    knight.moveKnight("White", "WhiteKnight1", "2D");
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces right and 1 space down.
   */
  @Test
  void test4() {
    System.out.println("Test 4");
    knight.moveKnight("Black", "BlackKnight1", "7D");
  }
  
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces down and 1 space left.
   */
  @Test
  void test5() {
    System.out.println("Test 5");
    knight.moveKnight("Black", "BlackKnight1", "6A");
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces down and 1 space right.
   */
  @Test
  void test6() {
    System.out.println("Test 6");
    knight.moveKnight("Black", "BlackKnight1", "6C");
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces left and 1 space up.
   */
  @Test
  void test7() {
    System.out.println("Test 7");
    knight.moveKnight("White", "WhiteKnight2", "2E");
  }
  
  /**
   * This test is to see if I can move my Knight piece 2 spaces left and 1 space down.
   */
  @Test
  void test8() {
    System.out.println("Test 8");
    knight.moveKnight("Black", "BlackKnight2", "7E");
  }

}
