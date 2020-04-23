package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CheckmateNew {
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
            //king.moveKing(oppPlayer, oppPlayer + "King", toTile);
            
            if (value.contains("Pawn")) {
              pawn.movePawn(player, value, kingPos);
              
              if (pawn.passedValidation) {
                return true;
              }
            	
            } else if (value.contains("Rook")) {
              rook.moveRook(player, value, kingPos);
              
              if (rook.passedValidation) {
                return true;
              }
              
            } else if (value.contains("Knight")) {
              knight.moveKnight(player, value, kingPos);
              
              if (knight.passedValidation) {
                return true;
              }
              
            } else if (value.contains("Bishop")) {
              bishop.moveBishop(player, value, kingPos);
              
              if (bishop.passedValidation) {
                return true;
              }
              
            } else if (value.contains("Queen")) {
              queen.moveQueen(player, value, kingPos);
              
              if (queen.passedValidation) {
                return true;
              }
            
            } else if (value.contains("King")) {
              king.moveKing(player, value, kingPos);
              
              if (king.passedValidation) {
                return true;
              }
            }
           
          }
        }
         
      }
    }
    
    return false;
  }
}
