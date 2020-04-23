package application;

import java.util.ArrayList;
import java.util.TreeMap;

public class TreeMaps {

  public TreeMap<String, String> piecePos = new TreeMap<String, String>();
  private TreeMap<String, String> tileOccupation = new TreeMap<String, String>();
  public ArrayList<String> capturedPieces = new ArrayList<String>();
  public boolean valueLock = false;
  
  /**
   * This is a constructor for creating/initialising the TreeMaps I will need
   * for storing my Chess game data.
   */
  /*public TreeMaps() {
    piecePos = new TreeMap<String, String>();
    tileOccupation = new TreeMap<String, String>();
    capturedPieces = new ArrayList<String>();
  }*/
  

  /**
   * This method sets a new key and value in my TreeMap. It uses
   * in-built methods to help achieve this.
   * 
   * @param key the key which will be mapped to my value
   * @param value the value associated with my key
   */
  public void setValue(String map, String key, String value) {
    if (map == "piecePos" && value != "null" && valueLock == false) {  
      String oldPos = getTile(value);
      
      piecePos.put(key, value);
      tileOccupation.put(key, "Occupied");
      
      // If the old position of the piece previously contained a value:
      if (oldPos != null) {
        piecePos.replace(oldPos, "null");
        tileOccupation.replace(oldPos, "Empty");
      }
  
    } else if (map == "piecePos" && value == "null" && valueLock == false) {
      piecePos.put(key, value);
      tileOccupation.put(key, "Empty");
     
    } else if (map == "tileOccupation") {
      tileOccupation.put(key, value);
  
    } else if (valueLock == true) {
      System.out.println("Checking for Checkmate");
    } else {
      System.out.println("Not a valid TreeMap (TreeMap.setValue)");
    }
  }
  
  /**
   * This method gets the piece or occupation (value) inside a given tile co-ord (key).
   * @param key the tile co-ord I want search for a piece or occupation in.
   * @return the occupation or the piece, if any, inside the given key/tile co-ord
   */
  public String getPieceOrOccupation(String map, String key) {
    if (map == "piecePos") {
      return piecePos.get(key);
  
    } else if (map == "tileOccupation") {
      return tileOccupation.get(key);
  
    } else {
      System.out.println("Not a valid TreeMap (TreeMaps.getPiece)");
      return null;
    }
  }
  
  /**
   * This method is exclusively for the piecePos TreeMap.
   * 
   * <p>It iterates over the piecePos TreeMap's tile co-ords (key set), and compares each tile
   * co-ord to see if the chess piece mapped to that tile matches the chess piece I am looking 
   * for. If a tile contains the piece I am looking for, I return the tile co-ord, else I return 
   * null.
   * 
   * @param piece The chess piece I am searching for in each of the tile co-ords
   * @return The tile co-ord of the chess piece
   */
  public String getTile(String piece) {
    for (String tileCoord : piecePos.keySet()) {
      if (piece.equals(piecePos.get(tileCoord))) {
        return tileCoord;
      }
    }
    return null;
  }
 
 
  /**
   * This method is used to print the current status of my TileOccupation and PiecePos TreeMaps,
   * so that I can view which tiles are occupied and empty, and which piece is where on the board.
   */
  public void printStatus() {
  
    System.out.println(tileOccupation.toString());
    System.out.println(piecePos.toString());
  }
  
  /**
   * This method is used to capture a piece by adding it to the 
   * 'capturedPieces' ArrayList.
   * 
   * @param piece The piece being captured by the opponent.
   */
  public void capturePiece(String piece) {
    capturedPieces.add(piece);
  }
}
