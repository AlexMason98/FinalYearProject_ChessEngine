package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LegalMoves {

  public static ArrayList<String> legalMoves;
  public static ArrayList<String> illegalMoves;
  public static ArrayList<String> playerPieces;
  public static ArrayList<String> oppPlayerPieces;
  public static ArrayList<String> whiteReachKing;
  public static ArrayList<String> blackReachKing;
  public static ArrayList<String> whiteReachKingTile;
  public static ArrayList<String> blackReachKingTile;
  public static ArrayList<String> threatenPieceTiles;
  public static boolean stalemate = false;
	
  /**
   * This method is responsible for adding all legal moves to an ArrayList.
   * The algorithm can then choose which of those moves to take based on values
   * to specify which have a higher payoff than others.
   * 
   * @param player Takes the current player (either "White" or "Black").
   * @return An ArrayList of legalMoves intended to be used by the AI algorithm.
   * @throws InvalidPieceException If an invalid piece is received (not matching a piece type).
   * @throws InvalidPlayerException If an invalid player is received (not "White" or "Black").
   */
  public static ArrayList<String> legalMoves(String player) throws InvalidPieceException, 
        InvalidPlayerException {
    legalMoves = new ArrayList<String>();
    illegalMoves = new ArrayList<String>();
    playerPieces = new ArrayList<String>();
    oppPlayerPieces = new ArrayList<String>();
    whiteReachKing = new ArrayList<String>();
    blackReachKing = new ArrayList<String>();
    whiteReachKingTile = new ArrayList<String>();
    blackReachKingTile = new ArrayList<String>();
    threatenPieceTiles = new ArrayList<String>();
    String oppPlayer = "";
    String toTile = "";
    String prevTile = "";
    String oppPrevTile = "";
    Board.board.map.valueLock = true;
    
    if (player.equals("White")) {
      oppPlayer = "Black";
    } else if (player.equals("Black")) {
      oppPlayer = "White";
    }
    
    // Creating a list of opponent player's piece
    for (Map.Entry<String, String> entry : Board.board.map.piecePos.entrySet()) {
      String piece = entry.getValue();
      
      if (piece.contains(player)) {
        playerPieces.add(piece);
      } else {
        oppPlayerPieces.add(piece);
      }
    }
	
    // Iterate Rows
    for (int i = 1; i <= 8; i++) {
      // Iterate Columns
      for (int j = 65; j <= 72; j++) {
        for (int k = 0; k < playerPieces.size(); k++) {
          String piece = playerPieces.get(k);
          String kingPos = Board.board.map.getTile(player + "King");
          System.out.println("---- Value is: " + piece + " ----");
          toTile = "" + i + (char)j;

          if (piece.contains(player) && (toTile != Board.board.map.getTile(piece))) {
            if (piece.contains("Pawn")) {
              System.out.println("Player is " + player + ", piece is: " + piece + " to tile is: " 
                  + toTile);
              Board.board.map.valueLock = true;
             
              Controller.pawn.movePawn(player, piece, toTile);
              
              if (Controller.pawn.passedValidation) {
                
                if (reachKing(oppPlayer, kingPos) == false) {
                  System.out.println("Lands Pawn After Reach King");
                  /* If false to reaching king, add pawn move as legal as it doesn't
                     put own player's king in danger */
                  legalMoves.add(toTile + ", " + piece);
                } else {
                  illegalMoves.add(piece + " puts " + player + "King in danger when moving to " 
                      + toTile);
                }
                
              }
              
              Controller.pawn.passedValidation = false;
              Controller.pawn.attackMove = false;
              
            } else if (piece.contains("Rook")) {
              System.out.println("Moving " + piece + " to " + toTile);
              System.out.println("Rook's Prev Tile: " + Board.board.map.getTile(piece));
              Controller.rook.moveRook(player, piece, toTile);
            	
              if (Controller.rook.passedValidation) {
                if (reachKing(oppPlayer, kingPos) == false) {
                  legalMoves.add(toTile + ", " + piece); 
                } else {
                  illegalMoves.add(piece + " puts " + player + "King in danger when moving to " 
                      + toTile);
                }
              }
              Controller.rook.passedValidation = false;
            
            } else if (piece.contains("Knight")) {
              Controller.knight.moveKnight(player, piece, toTile);
              
              if (Controller.knight.passedValidation) {
                if (reachKing(oppPlayer, kingPos) == false) {
                  legalMoves.add(toTile + ", " + piece);
                } else {
                  illegalMoves.add(piece + " puts " + player + "King in danger when moving to " 
                      + toTile);
                }
              }
              Controller.knight.passedValidation = false;
            
            } else if (piece.contains("Bishop")) {
              Controller.bishop.moveBishop(player, piece, toTile);
            	
              if (Controller.bishop.passedValidation) {
                String tempPiece = Board.board.map.getPieceOrOccupation("piecePos", toTile);
                if (reachKing(oppPlayer, kingPos) == false) {
                  legalMoves.add(toTile + ", " + piece);
                } else {
                  illegalMoves.add(piece + " puts " + player + "King in danger when moving to " 
                      + toTile);
                }
              }
              Controller.bishop.passedValidation = false;
            
            } else if (piece.contains("Queen")) {
              Controller.queen.moveQueen(player, piece, toTile);
            	
              if (Controller.queen.passedValidation) {
              	
                if (reachKing(oppPlayer, kingPos) == false) {
                  legalMoves.add(toTile + ", " + piece);
                } else { 	
                  Controller.queen.passedValidation = false;
                  String tempPiece = Board.board.map.getPieceOrOccupation("piecePos", toTile);
                  if (tempPiece != null && tempPiece.contains(oppPlayer) && reachKing(oppPlayer, 
                      kingPos)) {
                    legalMoves.add(toTile + ", " + piece);
                  } else {
                    illegalMoves.add(piece + " puts " + player + "King in danger when moving to " 
                        + toTile);
                  }
                }
              }
              Controller.queen.passedValidation = false;
              
            } else if (piece.contains("King")) {
              Controller.king.moveKing(player, piece, toTile);
              
              if (Controller.king.passedValidation) {
              	
                // Checking the King is not put in danger after this move
                System.out.println("Lands King Before Reach King");
                prevTile = Board.board.map.getTile(piece);
                
                String tempPiece = Board.board.map.getPieceOrOccupation("piecePos", toTile);
                if (tempPiece != null && tempPiece.contains(oppPlayer)) { 
                  legalMoves.add(toTile + ", " + piece);
                } else {
                  Board.board.map.valueLock = false;
                  Board.board.map.setValue("piecePos", toTile, piece);
                  Board.board.map.valueLock = true;
                
                  if (reachKing(oppPlayer, toTile) == false) {
                    legalMoves.add(toTile + ", " + piece);
                  } else {
                    illegalMoves.add(piece + " puts " + player + "King in danger when moving to " 
                        + toTile);
                  }
                
                  // Resetting Move
                  Board.board.map.valueLock = false;
                  Board.board.map.setValue("piecePos", prevTile, piece);
                  Board.board.map.valueLock = true;
                }
              }
              
              Controller.king.passedValidation = false;
            }
          }
        }
      }
    }
    
    Board.board.map.valueLock = false;
    Board.board.map.printStatus();
	
    
    if (legalMoves.size() == 0) {
      System.out.println("There are no legal moves for this player");
      stalemate = true;
    } else {
      for (int i = 0; i < legalMoves.size(); i++) {
        System.out.println(legalMoves.get(i));
      }
      
      for (int a = 0; a < Controller.pawn.movedPawns.size(); a++) {
        System.out.println("Moved Pawn: " + Controller.pawn.movedPawns.get(a));
      }
    }
    
    if (illegalMoves.size() == 0) {
      System.out.println("There are no illegal moves for this player");
    } else {
      for (int i = 0; i < illegalMoves.size(); i++) {
        System.out.println(illegalMoves.get(i));
      }
    }
    
    if (threatenPieceTiles.size() != 0) {
      for (int i = 0; i < threatenPieceTiles.size(); i++) {
        System.out.println("Threatening Piece Tile: " + threatenPieceTiles.get(i));
      }
    }
    
    /*for (int i = 0; i < whiteReachKing.size(); i++) {
      System.out.println("White can Reach Black King with piece: " + whiteReachKing.get(i) + ", at "
          + "tile: " + whiteReachKingTile.get(i));
    }
    
    for (int i = 0; i < blackReachKing.size(); i++) {
      System.out.println("Black can reach White King with piece: " + blackReachKing.get(i) + ", at "
          + "tile: " + blackReachKingTile.get(i));
    }*/
    return legalMoves;
  }
  
  /**
   * Checks to see if an opponent player's piece can reach the player's King or not.
   * 
   * @param oppPlayer Takes the opposite player (either "White" or "Black").
   * @return true if oppPlayer can reach the player's king
   * @throws InvalidPlayerException If an invalid player is received (not "White" or "Black").
   * @throws InvalidPieceException If an invalid piece is received (not matching a piece type).
   */
  public static boolean reachKing(String oppPlayer, String toTile) throws 
        InvalidPieceException, InvalidPlayerException {
    int reachKingCount = 0;
    
    for (int i = 0; i < oppPlayerPieces.size(); i++) {
      String piece = oppPlayerPieces.get(i);
      Board.board.map.valueLock = true;
      
      if (piece.contains(oppPlayer)) {
        if (piece.contains("Pawn")) {
          Controller.pawn.passedValidation = false;
          System.out.println("Opp Player is: " + oppPlayer + ", piece is: " + piece 
              + ", to tile is: " + toTile);
          Controller.pawn.movePawn(oppPlayer, piece, toTile);
        
          if (Controller.pawn.passedValidation && Controller.pawn.attackMove) {
            System.out.println("Lands Pawn Reach King");
            System.out.println(oppPlayer + " is moving " + piece + " to " + toTile);

            if (oppPlayer.equals("White")) {
              whiteReachKing.add(piece);
              whiteReachKingTile.add(toTile);
              blockThreat("Black", piece, toTile);
            } else {
              blackReachKing.add(piece);
              blackReachKingTile.add(toTile);
              blockThreat("White", piece, toTile);
            }

            return true;
          }
        
          Controller.pawn.passedValidation = false;
          Controller.pawn.attackMove = false;
        
        } else if (piece.contains("Rook")) {
          Controller.rook.passedValidation = false;
          Controller.rook.moveRook(oppPlayer, piece, toTile);
        
          if (Controller.rook.passedValidation) {
            System.out.println("Lands Rook Reach King");
            System.out.println(oppPlayer + " is moving " + piece + " to " + toTile);

            if (oppPlayer.equals("White")) {
              whiteReachKing.add(piece);
              whiteReachKingTile.add(toTile);
              blockThreat("Black", piece, toTile);
            } else {
              blackReachKing.add(piece);
              blackReachKingTile.add(toTile);
              blockThreat("White", piece, toTile);
            }

            return true;
          }
        
          Controller.rook.passedValidation = false;
        
        } else if (piece.contains("Bishop")) {
          Controller.bishop.passedValidation = false;
          Controller.bishop.moveBishop(oppPlayer, piece, toTile);
        
          if (Controller.bishop.passedValidation) {
            System.out.println("Lands Bishop Reach King");
            System.out.println(oppPlayer + " is moving " + piece + " to " + toTile);

            if (oppPlayer.equals("White")) {
              whiteReachKing.add(piece);
              whiteReachKingTile.add(toTile);
              blockThreat("Black", piece, toTile);
            } else {
              blackReachKing.add(piece);
              blackReachKingTile.add(toTile);
              blockThreat("White", piece, toTile);
            }

            return true;
          }
        
          Controller.bishop.passedValidation = false;
        
        } else if (piece.contains("Knight")) {
          Controller.knight.passedValidation = false; // Ensure false before verifying if true again
          Controller.knight.moveKnight(oppPlayer, piece, toTile);
        
          if (Controller.knight.passedValidation) {
            System.out.println("Lands Knight Reach King");
            System.out.println(oppPlayer + " is moving " + piece + " to " + toTile);

            if (oppPlayer.equals("White")) {
              whiteReachKing.add(piece);
              whiteReachKingTile.add(piece);
              blockThreat("Black", piece, toTile);
            } else {
              blackReachKing.add(piece);
              blackReachKingTile.add(toTile);
              blockThreat("White", piece, toTile);
            }

            return true;
          }
        
          Controller.knight.passedValidation = false;
      	
        } else if (piece.contains("Queen")) {
          Controller.queen.passedValidation = false;
          Controller.queen.moveQueen(oppPlayer, piece, toTile);
        
          if (Controller.queen.passedValidation) {
            System.out.println("Lands Queen Reach King");
            System.out.println(oppPlayer + " is moving " + piece + " to " + toTile);

            if (oppPlayer.equals("White")) {
              whiteReachKing.add(piece);
              whiteReachKingTile.add(toTile);
              blockThreat("Black", piece, toTile);
            } else {
              blackReachKing.add(piece);
              blackReachKingTile.add(toTile);
              blockThreat("White", piece, toTile);
            }

            return true;
          }
        
          Controller.queen.passedValidation = false;
        
        } else if (piece.contains("King")) {
          Controller.king.passedValidation = false;
          Controller.king.moveKing(oppPlayer, piece, toTile);
        
          if (Controller.king.passedValidation) {
            System.out.println("Lands King Reach King");
            System.out.println(oppPlayer + " is moving " + piece + " to " + toTile);

            if (oppPlayer.equals("White")) {
              whiteReachKing.add(piece);
              whiteReachKingTile.add(toTile);
            } else {
              blackReachKing.add(piece);
              blackReachKingTile.add(toTile);
            }
            
            return true;
          }
        
          Controller.king.passedValidation = false;
        }
      }
    }
    
    return false;
  }
  
  /**
   * This method is responsible for checking if a player's piece can be used to
   * block an opponent player's piece which is threatening the capture of their King.
   * @param player Takes the current player (either "White" or "Black").
   * @param piece Takes an ArrayList of threatening pieces.
   * @param tile Takes an ArrayList of tiles the threatening pieces can move to.
   */
  public static void blockThreat(String player, String piece, String tile)
      throws InvalidPieceException, InvalidPlayerException {
  	
    Board.board.map.valueLock = true;
    String oppPlayer = "";
    if (player.equals("White")) {
      oppPlayer = "Black";
    } else {
      oppPlayer = "White";
    }
    
    //threatenPieceTiles = new ArrayList<String>();
    String fromTile = Board.board.map.getTile(piece);
    String kingPos = Board.board.map.getTile(player + "King");
    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(kingPos.charAt(0)));
    char toColumn = kingPos.charAt(1);
    
    // Left
    if (fromRow == toRow && fromColumn > toColumn) {
      for (int i = fromColumn - 1; i > toColumn; i--) {
        threatenPieceTiles.add("" + toRow + String.valueOf((char)i));
      }
      
    // Right
    } else if (fromRow == toRow && toColumn > fromColumn) {
      for (int i = fromColumn + 1; i < toColumn; i++) {
        threatenPieceTiles.add("" + toRow + String.valueOf((char)i));
      }
      
    // Up
    } else if (fromColumn == toColumn && (fromRow < toRow)) {
      for (int i = fromRow + 1; i < toRow; i++) {
        threatenPieceTiles.add("" + i + fromColumn);
      }
     
    // Down
    } else if (fromColumn == toColumn && (fromRow > toRow)) {
      for (int i = fromRow - 1; i > toRow; i--) {
        threatenPieceTiles.add("" + i + fromColumn);
      }
      
    // North East
    } else if ((toColumn > fromColumn) && (toRow > fromRow)) {
      int colDifference = toColumn - fromColumn;
      int rowDifference = toRow - fromRow;
      int j = fromColumn;
      
      if (colDifference == rowDifference) {
      
        for (int i = fromRow + 1; i < toRow; i++) {
          ++j; // Also increments column to move East as part of moving diagonally North East
          threatenPieceTiles.add("" + i + (char)j);
        }
      }
     
    // South East
    } else if ((toColumn > fromColumn) && (toRow < fromRow)) {
      int colDifference = toColumn - fromColumn;
      int rowDifference = fromRow - toRow;
      int j = fromColumn;
       
      if (colDifference == rowDifference) {
        for (int i = fromRow - 1; i > toRow; i--) {
          ++j; // Also increments column to move East as part of moving diagonally South East
          threatenPieceTiles.add("" + i + (char)j);
        }
      }
     
    // South West
    } else if ((toColumn < fromColumn) && (toRow < fromRow)) {
      int colDifference = fromColumn - toColumn;
      int rowDifference = fromRow - toRow;
      int j = fromColumn;
      
      if (colDifference == rowDifference) {
        for (int i = fromRow - 1; i > toRow; i--) {
          --j; // Also decrements column to move West as part of moving diagonally South West.
          threatenPieceTiles.add("" + i + (char)j);
        }
      }
     
    // North West
    } else if ((toColumn < fromColumn) && (toRow > fromRow)) {
      int colDifference = fromColumn - toColumn;
      int rowDifference = toRow - fromRow;
      int j = fromColumn;
        
      if (colDifference == rowDifference) {
        for (int i = fromRow + 1; i < toRow; i++) {
          --j; // Also decrements column to move West as part of moving diagonally North West.
          threatenPieceTiles.add("" + i + (char)j);
        }
      }
          
    }
    
    for (int i = 0; i < threatenPieceTiles.size(); i++) {
      for (int j = 0; j < playerPieces.size(); j++) {
        if (playerPieces.get(j).contains("Pawn") && !legalMoves.contains(
              threatenPieceTiles.get(i) + ", " + playerPieces.get(j))) {
          Controller.pawn.passedValidation = false;
          Controller.pawn.movePawn(player, playerPieces.get(j), threatenPieceTiles.get(i));
          
          if (Controller.pawn.passedValidation) {
            legalMoves.add(threatenPieceTiles.get(i) + ", " + playerPieces.get(j));
            Controller.pawn.passedValidation = false;
          }
          
        } else if (playerPieces.get(j).contains("Rook") && !legalMoves.contains(
            threatenPieceTiles.get(i) + ", " + playerPieces.get(j))) {
          Controller.rook.passedValidation = false;
          Controller.rook.moveRook(player, playerPieces.get(j), threatenPieceTiles.get(i));
        
          if (Controller.rook.passedValidation) {
            legalMoves.add(threatenPieceTiles.get(i) + ", " + playerPieces.get(j));
            Controller.rook.passedValidation = false;
          }
        
        } else if (playerPieces.get(j).contains("Knight") && !legalMoves.contains(
            threatenPieceTiles.get(i) + ", " + playerPieces.get(j))) {
          Controller.knight.passedValidation = false;
          Controller.knight.moveKnight(player, playerPieces.get(j), threatenPieceTiles.get(i));
        
          if (Controller.knight.passedValidation) {
            legalMoves.add(threatenPieceTiles.get(i) + ", " + playerPieces.get(j));
            Controller.knight.passedValidation = false;
          }
        
        } else if (playerPieces.get(j).contains("Bishop") && !legalMoves.contains(
            threatenPieceTiles.get(i) + ", " + playerPieces.get(j))) {
          Controller.bishop.passedValidation = false;
          Controller.bishop.moveBishop(player, playerPieces.get(j), threatenPieceTiles.get(i));
        
          if (Controller.bishop.passedValidation) {
            legalMoves.add(threatenPieceTiles.get(i) + ", " + playerPieces.get(j));
            Controller.bishop.passedValidation = false;
          }
        
        } else if (playerPieces.get(j).contains("Queen") && !legalMoves.contains(
            threatenPieceTiles.get(i) + ", " + playerPieces.get(j))) {
          Controller.queen.passedValidation = false;
          Controller.queen.moveQueen(player, playerPieces.get(j), threatenPieceTiles.get(i));
      
          if (Controller.queen.passedValidation) {
            legalMoves.add(threatenPieceTiles.get(i) + ", " + playerPieces.get(j));
            Controller.queen.passedValidation = false;
          }
        }
      }
    }
    
    captureThreat(player, piece);
  }
  
  /**
   * This method is responsible for checking if an opponent's piece which can reach
   * the player's King can be captured by another one of the player's pieces.
   * @param player Takes the current player (either "White" or "Black").
   * @param piece Takes the piece to check.
   * @throws InvalidPieceException If an invalid piece is passed (not a piece type).
   * @throws InvalidPlayerException If an invalid player is passed (not "White" or "Black").
   */
  public static void captureThreat(String player, String piece) 
      throws InvalidPieceException, InvalidPlayerException {
    for (int i = 0; i < playerPieces.size(); i++) {
      if (playerPieces.get(i).contains("Pawn") && !legalMoves.contains(
            "Pawn " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i))) {
        Controller.pawn.passedValidation = false;
        Controller.pawn.attackMove = false;
        Controller.pawn.movePawn(player, playerPieces.get(i), Board.board.map.getTile(piece));
    
        if (Controller.pawn.passedValidation && Controller.pawn.attackMove) {
          legalMoves.add("Pawn " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i));
          Controller.pawn.attackMove = false;
          Controller.pawn.passedValidation = false;
        }
       
      } else if (playerPieces.get(i).contains("Rook") && !legalMoves.contains(
            "Rook " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i))) {
        Controller.rook.passedValidation = false;
        Controller.rook.moveRook(player, playerPieces.get(i), Board.board.map.getTile(piece));
    
        if (Controller.rook.passedValidation) {
          legalMoves.add("Rook " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i));
          Controller.rook.passedValidation = false;
        }
       
      } else if (playerPieces.get(i).contains("Knight") && !legalMoves.contains(
            "Knight " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i))) {
        Controller.knight.passedValidation = false;
        Controller.knight.moveKnight(player, playerPieces.get(i), Board.board.map.getTile(piece));
    
        if (Controller.knight.passedValidation) {
          legalMoves.add("Knight " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i));
          Controller.knight.passedValidation = false;
        }
       
      } else if (playerPieces.get(i).contains("Bishop") && !legalMoves.contains(
          "Bishop " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i))) {
        Controller.bishop.passedValidation = false;
        Controller.bishop.moveBishop(player, playerPieces.get(i), Board.board.map.getTile(piece));
        
        if (Controller.bishop.passedValidation) {
          legalMoves.add("Bishop " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i));
          Controller.bishop.passedValidation = false;
        }
      } else if (playerPieces.get(i).contains("Queen") && !legalMoves.contains(
            "Queen " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i))) {
        Controller.queen.passedValidation = false;
        Controller.queen.moveQueen(player, playerPieces.get(i), Board.board.map.getTile(piece));
    
        if (Controller.queen.passedValidation) {
          legalMoves.add("Queen " + Board.board.map.getTile(piece) + ", " + playerPieces.get(i));
          Controller.queen.passedValidation = false;
        }
      }
    }
  }
  
  
  
  /**
   * This method is responsible for iterating over the set of keys and values
   * in the legal moves TreeMap and printing this to the console.
   * @param list Takes a map of type String, ArrayList String.
   */
  public void printList(String list) {
  	
    if (list.equals("legalMoves")) {
      if (legalMoves.size() == 0) {
        System.out.println("There are no legal moves for this player");
      } else {
        for (int i = 0; i < legalMoves.size(); i++) {
          System.out.println(legalMoves.get(i));
        }
      }
    } else if (list.equals("illegalMoves")) {
      if (illegalMoves.size() == 0) {
        System.out.println("There are no illegal moves for this player");
      } else {
        for (int i = 0; i < illegalMoves.size(); i++) {
          System.out.println(illegalMoves.get(i));
        }
      }
    }
  }
}
