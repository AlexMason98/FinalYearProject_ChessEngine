package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import application.TreeMaps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTreeMaps {

  private TreeMaps map;

  @BeforeEach
  void setUp() {
    map = new TreeMaps();
  }
  
  /**
   * This tests to see if the getPiece method works. As the TreeMap currently contains
   * no keys and values, the expected result from any key is null.
   */
  @Test
  void test1() {
    assertEquals(map.getPieceOrOccupation("piecePos", "1A"), null);
  }
  
  /**
   * This tests to see if I can set a key and value inside the piecePos TreeMap to add data to it.
   * I then use the getPieceOrOccupation method to get the piece (value) from a tile (key) which 
   * now exists in the map.
   */
  @Test
  void test2() {
    map.setValue("piecePos", "1A", "WhiteRook1");
    assertEquals(map.getPieceOrOccupation("piecePos", "1A"), "WhiteRook1");
  }
  
  /**
   * This tests to see if I can set a key and value inside the tileOccupation TreeMap to add data to
   * it. I then use the getPieceOrOccupation method to get the tile occupation (value) from a tile 
   * co-ord (key).
   */
  @Test
  void test3() {
    map.setValue("tileOccupation", "1A", "Occupied");
    assertEquals(map.getPieceOrOccupation("tileOccupation", "1A"), "Occupied");
  }
  
  /**
   * This tests to see if I can iterate over the piecePos TreeMap's key set and find the tile co-ord
   * (key) based on the chess piece (value) I am searching for. It basically does a reverse search 
   * on the map.
   */
  @Test
  void test4() {
    map.setValue("piecePos", "1A", "WhiteRook1");
    assertEquals(map.getTile("WhiteRook1"), "1A");
  }
  
  /**
   * This tests to see if I can print the keys and values inside my TreeMaps to see the status of 
   * each tile.
   */
  @Test
  void test5() {
    map.setValue("piecePos", "1A", "WhiteRook1");
    map.setValue("tileOccupation", "1A", "Occupied");
    map.printStatus();
  }
  
  /** 
   * This tests my capture piece method and sees if I can successfully store the captured
   * pieces within a new TreeMap.
   */
  @Test
  void test6() {
    map.capturePiece("WhiteRook1");
    assertEquals(map.capturedPieces.contains("WhiteRook1"), true);
  }
  
  /**
   * This tests my refactored setValue method. When trying to update a tile containing a piece
   * to null (thus removing the piece), that it also automatically updates the tileOccupation
   * for that tile to "Empty", without needing to do that manually separately.
   */
  @Test
  void test7() {
    map.setValue("piecePos", "1A", "WhiteRook1");
    map.setValue("piecePos", "2A", "WhitePawn1");
    map.printStatus();
    map.setValue("piecePos", "1A", "null");
    assertEquals(map.getPieceOrOccupation("tileOccupation", "1A"), "Empty");
    map.printStatus();
  }
  

}
