package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Checkmate {

  public static Pawn pawn = new Pawn();
  private static Rook rook = new Rook();
  private static Knight knight = new Knight();
  private static Bishop bishop = new Bishop();
  private static Queen queen = new Queen();
  private static King king = new King();
  public static TreeMap<String, String> checkmateAt = new TreeMap<String, String>();
  public static int legalMovesWhite = 0;
  public static int legalMovesBlack = 0;
  public static ArrayList<String> illegalTilesWhite = new ArrayList<String>();
  public static ArrayList<String> illegalTilesBlack = new ArrayList<String>();
  public static ArrayList<String> legalTilesWhite = new ArrayList<String>();
  public static ArrayList<String> legalTilesBlack = new ArrayList<String>();
 
  /**
   * NA.
   * @return .
   * @throws InvalidPlayerException .
   * @throws InvalidPieceException .
   */
  public static boolean checkmate() throws InvalidPieceException, InvalidPlayerException {
    
    for (Map.Entry<String, String> entry : Board.board.map.piecePos.entrySet()) {
      String value = entry.getValue();
      String player = "";
      String oppPlayer = "";
      if (value == null) {
        ; // Ignore Value (Prevents NullPointerException)
      } else if (value.equals("null")) {
        ; // Ignore Value (Not a Valid Piece)
      } else {
        // Seeing if player has put opponent's King into checkmate
        if (value.contains("White")) {
          player = "White";
          oppPlayer = "Black";
        } else if (value.contains("Black")) {
          player = "Black";
          oppPlayer = "White";
        }
        
        for (int i = 1; i <= 8; i++) {
          for (int j = 65; j <= 72; j++) {
            String kingPos = "";
            String toTile = "" + i + (char)j;
            Board.board.map.valueLock = true;
          
            System.out.println("To Tile is: " + toTile);
            kingPos = Board.board.map.getTile(oppPlayer + "King");
            king.moveKing(oppPlayer, oppPlayer + "King", toTile);
              
            if (value.contains(player)) {
              if (value.contains("Pawn")) {
                pawn.movePawn(player, value, kingPos);
                  
                if (pawn.passedValidation == true) {
                  checkmateAt.put(kingPos,  player + " has put " + oppPlayer + "King into "
                        + "Checkmate at tile " + kingPos + " by piece " + value);
                }
                  
                // Checking to see if King could escape check in this tile
                pawn.movePawn(player, value, toTile);
                if (pawn.passedValidation == true && king.passedValidation == true) {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                  pawn.passedValidation = false;
                } else if (king.passedValidation == true) {
                  ignoreDuplicates("legalTiles" + oppPlayer, "Legal Tile " + toTile, toTile);
                } else {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                }
                  
              } else if (value.contains("Rook")) {
                rook.moveRook(player, value, toTile);
                  
                if (rook.passedValidation == true) {
                  checkmateAt.put(kingPos,  player + " has put " + oppPlayer + "King into "
                        + "Checkmate at tile " + kingPos + " by piece " + value);
                }
                  
                // Checking to see if King could escape check in this tile
                rook.moveRook(player, value, toTile);
                if (rook.passedValidation == true && king.passedValidation == true) {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                  rook.passedValidation = false;
                } else if (king.passedValidation == true) {
                  ignoreDuplicates("legalTiles" + oppPlayer, "Legal Tile " + toTile, toTile);
                } else {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                }
                  
              } else if (value.contains("Knight")) {
                knight.moveKnight(player, value, kingPos);
                  
                if (knight.passedValidation == true) {
                  checkmateAt.put(kingPos,  player + " has put " + oppPlayer + "King into "
                        + "Checkmate at tile " + kingPos + " by piece " + value);
                }
                  
                // Checking to see if King could escape check in this tile
                knight.moveKnight(player, value, toTile);
                if (knight.passedValidation == true && king.passedValidation == true) {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                  knight.passedValidation = false;
                } else if (king.passedValidation == true) {
                  ignoreDuplicates("legalTiles" + oppPlayer, "Legal Tile " + toTile, toTile);
                } else {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                }
                  
              } else if (value.contains("Bishop")) {
                bishop.moveBishop(player, value, kingPos);
                  
                if (bishop.passedValidation == true) {
                  checkmateAt.put(kingPos,  player + " has put " + oppPlayer + "King into "
                        + "Checkmate at tile " + kingPos + " by piece " + value);
                }
                  
                // Checking to see if King could escape check in this tile
                bishop.moveBishop(player, value, toTile);
                if (bishop.passedValidation == true && king.passedValidation == true) {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                  bishop.passedValidation = false;
                } else if (king.passedValidation == true) {
                  ignoreDuplicates("legalTiles" + oppPlayer, "Legal Tile " + toTile, toTile);
                } else {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                }
                  
              } else if (value.contains("Queen")) {
                queen.moveQueen(player, value, kingPos);
                  
                // Checking to see if King can be reached in its current position
                if (queen.passedValidation == true) {
                  checkmateAt.put(kingPos,  player + " has put " + oppPlayer + "King into "
                      + "Checkmate at tile " + kingPos + " by piece " + value);
                  queen.passedValidation = false;
                }
                  
                // Checking to see if King could escape check in this tile
                queen.moveQueen(player, value, toTile);
                if (queen.passedValidation == true && king.passedValidation == true) {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                  queen.passedValidation = false;
                } else if (king.passedValidation == true) {
                  ignoreDuplicates("legalTiles" + oppPlayer, "Legal Tile " + toTile, toTile);
                } else {
                  ignoreDuplicates("illegalTiles" + oppPlayer, "Illegal Tile " + toTile, toTile);
                }
                  
              } else {
                ; // Ignore opponent's King as King cannot put opponent King into check
              }
                
              pawn.passedValidation = false;
              rook.passedValidation = false;
              knight.passedValidation = false;
              bishop.passedValidation = false;
              queen.passedValidation = false;
              king.passedValidation = false;
            
            }
          }
        }
      }
    }
    
    Board.board.map.valueLock = false;
    print("legalTilesWhite");
    System.out.println("White King Legal Tiles: " + legalTilesWhite.size());
    print("illegalTilesWhite");
    System.out.println("White King Illegal Tiles: " + illegalTilesWhite.size());
    print("legalTilesBlack");
    System.out.println("Black King Legal Tiles: " + legalTilesBlack.size());
    print("illegalTilesBlack");
    System.out.println("Black King Illegal Tiles: " + illegalTilesBlack.size());
    print("checkmateAt");

    if (checkmateAt.size() != 0 && (legalMovesWhite == 0 || legalMovesBlack == 0) 
        && (illegalTilesWhite.size() == 64 || illegalTilesBlack.size() == 64)) {
      return true;
    }
    return false;

  }
  
  /**
   * This method is responsible for printing the contents of my data structures.
   * This helps with testing to see the logic and state of the game.
   */
  public static void print(String dataStructure) {
    if (dataStructure.equals("checkmateAt")) {
      System.out.println(checkmateAt.values());
    } else if (dataStructure.equals("illegalTilesWhite")) {
      illegalTilesWhite.forEach(s -> System.out.println(s));
    } else if (dataStructure.equals("legalTilesWhite")) {
      legalTilesWhite.forEach(s -> System.out.println(s));
    } else if (dataStructure.equals("illegalTilesBlack")) {
      illegalTilesBlack.forEach(s -> System.out.println(s));
    } else if (dataStructure.equals("legalTilesBlack")) {
      legalTilesBlack.forEach(s -> System.out.println(s));
    }
  }
  
  /**
   * Ignores duplicate Array entries which happens due to numerous iterations
   * of tile and piece checking.
   */
  public static void ignoreDuplicates(String dataStructure, String string, String toTile) {
    if (dataStructure.equals("checkmateAt")) {
      checkmateAt.put(toTile, string);
    } else if (dataStructure.equals("legalTilesWhite")) {
      if (legalTilesWhite.contains(string)) {
        ; // Do Nothing
        
      /* Checks that the tile hasn't already been checked/marked as illegal before adding to 
       * legal tile list */
      } else if (!illegalTilesWhite.contains("Illegal Tile " + toTile)) {
        legalMovesWhite++;
        legalTilesWhite.add(string);
      } 
      
    } else if (dataStructure.equals("legalTilesBlack")) {
      if (legalTilesBlack.contains(string)) {
        ; // Do Nothing
        
        /* Checks that the tile hasn't already been checked/marked as illegal before adding to 
         * legal tile list */
      } else if (!illegalTilesBlack.contains("Illegal Tile " + toTile)) {
        legalMovesBlack++;
        legalTilesBlack.add(string);
      } 
    } else if (dataStructure.equals("illegalTilesWhite")) {
      if (illegalTilesWhite.contains(string)) {
        ; // Do Nothing
      } else {
        illegalTilesWhite.add(string);
      }
    } else if (dataStructure.equals("illegalTilesBlack")) {
      if (illegalTilesBlack.contains(string)) {
        ; // Do Nothing
      } else {
        illegalTilesBlack.add(string);
      }
    }

  }
  
}
