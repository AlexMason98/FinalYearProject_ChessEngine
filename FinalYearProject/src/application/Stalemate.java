package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Stalemate {
  //private IllegalMove illegalMove = new IllegalMove();
  public static King king = new King();
  public static Queen queen = new Queen();
  public static Rook rook = new Rook();
  private static Knight knight = new Knight();
  private static Bishop bishop = new Bishop();
  public static Pawn pawn = new Pawn();
  private static int whiteStalemate = 0;
  private static int blackStalemate = 0;
  private static int whiteLegalMove = 0;
  private static int blackLegalMove = 0;
  private static int whitePieceCount = 0;
  private static int blackPieceCount = 0;
  static TreeMap<String, String> stalemateAt = new TreeMap<String, String>();
  static ArrayList<String> legalMoveAt = new ArrayList<String>();
  
  /**
   * NA.
   * @return .
   */
  public static boolean stalemate() throws InvalidPlayerException, InvalidPieceException {
    for (Map.Entry<String, String> entry : Board.board.map.piecePos.entrySet()) {
      for (int i = 1; i <= 8; i++) {
    
        for (int j = 65; j <= 72; j++) {
      
          String value = entry.getValue();

          // For each piece found in the first iteration of all loops, increment piece counter.
          if (value.contains("White") && i == 1 && j == 65) {
            whitePieceCount++;
          } else if (value.contains("Black") && i == 1 && j == 65) {
            blackPieceCount++;
          }

          move(value, "" + i + (char)j);
        }

      }
    }
    
    Board.board.map.valueLock = false;
    //System.out.println("WHITE KING MOVES = " + whiteKingMove);
    System.out.println("WHITE PIECES = " + whitePieceCount);
    System.out.println("WHITE LEGAL MOVES = " + whiteLegalMove);
    System.out.println("WHITE STALEMATE COUNT = " + whiteStalemate);
    //System.out.println("WHITE KING LEGAL MOVES = " + whiteKingLegalMove);
    //System.out.println("BLACK KING MOVES = " + blackKingMove);
    System.out.println("BLACK PIECES = " + blackPieceCount);
    System.out.println("BLACK LEGAL MOVES = " + blackLegalMove);
    System.out.println("BLACK STALEMATE COUNT = " + blackStalemate);
    //Board.board.map.printStatus();
    //for (int i = 0; i < stalemateAt.size(); i++) {
    for (Map.Entry<String, String> entry : stalemateAt.entrySet()) {
      System.out.println("Stalemate At: " + entry.getKey() + ", "
          + entry.getValue());
    }
    for (int i = 0; i < legalMoveAt.size(); i++) {
      System.out.println("Legal Move At: " + legalMoveAt.get(i));
    }
    
    if (whiteLegalMove == 0) {
      System.out.println("White Stalemate");
      return true;
    } else if (blackLegalMove == 0) {
      System.out.println("Black Stalemate");
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Check if player is putting their piece in danger.
   * @param piece .
   * @param toTile .
   * @throws InvalidPlayerException .
   * @throws InvalidPieceException .
   */
  public static void move(String piece, String toTile) 
      throws InvalidPieceException, InvalidPlayerException {
    
    String player = "";
    String oppPlayer = "";
    if (piece.contains("White")) {
      oppPlayer = "White";
      player = "Black";
    } else if (piece.contains("Black")) {
      oppPlayer = "Black";
      player = "White";
    }
    Board.board.map.valueLock = true;
    // CHECKING IF PLAYER IS PUTTING THEIR KING IN DANGER TO THE OPPONENT
    if (piece.contains("Pawn")) {
      // Checks for Moves when pawn and King Don't Belong to Same Player
      king.moveKing(player, player + "King", toTile);
      pawn.movePawn(oppPlayer, piece, toTile);
      
      if (!oppPlayer.equals(player)) {
        if (pawn.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "PAWN 1" + piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("stalemateAt", player + "King" + ", " + toTile, player + "King", toTile);
          pawn.movedPawns.remove(piece);
        } else if (king.passedValidation == true) {
          /*ignoreDuplicates(legalMoveAt, "PAWN 1 " + player + "King" + ", " + toTile, player + 
          "King");*/
        } else if (pawn.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "PAWN 2" + piece + ", " + toTile, piece, toTile);
          pawn.movedPawns.remove(piece);
        }
      } else {
        // Checks for Moves when pawn and King Belong to Same Player
        king.moveKing(player, player + "King", toTile);
        pawn.movePawn(player, piece, toTile);
      
        if (pawn.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "PAWN 3" + piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("legalMoveAt", player + "King" + ", " + toTile, player + "King", toTile);
          pawn.movedPawns.remove(piece);
        } else if (king.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, player + "King" + ", " + toTile, player + "King");
        } else if (pawn.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "PAWN 4 " + piece + ", " + toTile, piece, toTile);
          pawn.movedPawns.remove(piece);
        }
      }
      
      pawn.passedValidation = false;
      king.passedValidation = false;
      
      
    } else if (piece.contains("Rook")) {
      // Checks for Moves when the Knight and King Don't Belong to Same Player
      rook.moveRook(oppPlayer, piece, toTile);
      king.moveKing(player, player + "King", toTile);
      
      if (!oppPlayer.equals(player)) {
        if (rook.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "ROOK 1 " + piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("stalemateAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          /*ignoreDuplicates(legalMoveAt, "ROOK 1 " + player + "King" + ", " + toTile, player + 
          "King");*/
        } else if (rook.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "ROOK 2 " + piece + ", " + toTile, piece, toTile);
        }
      
        rook.passedValidation = false;
        king.passedValidation = false;
      } else {
        // Checks for Moves when the Knight and King Belong to Same Player
        rook.moveRook(player, piece, toTile);
        king.moveKing(player, player + "King", toTile);
      
        if (rook.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", "ROOK 3 " + piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("legalMoveAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, player + "King" + ", " + toTile, player + "King");
        } else if (rook.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, piece + ", " + toTile, piece);
        }
      }
      
      rook.passedValidation = false;
      king.passedValidation = false;
      
        
    } else if (piece.contains("Knight")) {
      // Checks for Moves when the Knight and King Don't Belong to Same Player
      knight.moveKnight(oppPlayer, piece, toTile);
      king.moveKing(player, player + "King", toTile);
      
      if (!oppPlayer.equals(player)) {
        if (knight.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("stalemateAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          /*ignoreDuplicates(legalMoveAt, "KNIGHT 1 " + player + "King" + ", " + toTile, player + 
          "King");*/
        } else if (knight.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
        }
      
        knight.passedValidation = false;
        king.passedValidation = false;
      } else {
        // Checks for Moves when the Knight and King Belong to Same Player
        knight.moveKnight(player, piece, toTile);
        king.moveKing(player, player + "King", toTile);
      
        if (knight.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("legalMoveAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, player + "King" + ", " + toTile, player + "King");
        } else if (knight.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, piece + ", " + toTile, piece);
        }
      }
      
      knight.passedValidation = false;
      king.passedValidation = false;
        
    } else if (piece.contains("Bishop")) {
      // Checks for Moves when bishop and King Don't Belong to Same Player
      bishop.moveBishop(oppPlayer, piece, toTile);
      king.moveKing(player, player + "King", toTile);
      
      if (!oppPlayer.equals(player)) {
        if (bishop.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("stalemateAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          /*ignoreDuplicates(legalMoveAt, "bishop 1 " + player + "King" + ", " + toTile, player + 
          "King");*/
        } else if (bishop.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
        }
      
        bishop.passedValidation = false;
        king.passedValidation = false;
      } else {
        // Checks for Moves when bishop and King Belong to Same Player
        bishop.moveBishop(player, piece, toTile);
        king.moveKing(player, player + "King", toTile);
      
        if (bishop.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("legalMoveAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, player + "King" + ", " + toTile, player + "King");
        } else if (bishop.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, piece + ", " + toTile, piece);
        }
      }
      
      bishop.passedValidation = false;
      king.passedValidation = false;
      
    } else if (piece.contains("Queen")) {
      // Checks for Moves when Queen and King Don't Belong to Same Player
      queen.moveQueen(oppPlayer, piece, toTile);
      king.moveKing(player, player + "King", toTile);
      
      if (!oppPlayer.equals(player)) {
        if (queen.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("stalemateAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          /*ignoreDuplicates(legalMoveAt, "QUEEN 1 " + player + "King" + ", " + toTile, player + 
          "King");*/
        } else if (queen.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
        }
      
        queen.passedValidation = false;
        king.passedValidation = false;
      } else {
        // Checks for Moves when Queen and King Belong to Same Player
        queen.moveQueen(player, piece, toTile);
        king.moveKing(player, player + "King", toTile);
      
        if (queen.passedValidation == true && king.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
          ignoreDuplicates("legalMoveAt", player + "King" + ", " + toTile, player + "King", toTile);
        } else if (king.passedValidation == true) {
          //ignoreDuplicates(legalMoveAt, player + "King" + ", " + toTile, player + "King");
        } else if (queen.passedValidation == true) {
          ignoreDuplicates("legalMoveAt", piece + ", " + toTile, piece, toTile);
        }
      }
      
      queen.passedValidation = false;
      king.passedValidation = false;
    } else if (piece.contains("King")) {
      king.moveKing(oppPlayer, piece, toTile);
      
      if (king.passedValidation == true) {
        king.passedValidation = false;
        king.moveKing(player, player + "King", toTile);
        if (king.passedValidation == true) {
          king.passedValidation = false;
          
          ignoreDuplicates("stalemateAt", piece + ", " + toTile, piece, toTile);
        } else { /* If opponent's King cannot reach current player's King, check to see if other 
        pieces can reach the current player's King. */

          // CHECK FOR PUTTING KING IN CHECK
          System.out.println("LANDS IN ELSE");
          for (Map.Entry<String, String> entry : Board.board.map.piecePos.entrySet()) {
            String value = entry.getValue();
            if (value.contains("Pawn")) {
              if (value.contains("White") && piece.contains("White") || value.contains("Black") 
                  && piece.contains("Black")) {
                // Checks for Moves when pawn and King Belong to Same Player
                pawn.movePawn(oppPlayer, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
                          
                if (pawn.passedValidation == true && king.passedValidation == true) {
                  ignoreDuplicates("legalMoveAt", "ELSE 1 " + value + ", " + toTile, value, toTile);
                  ignoreDuplicates("legalMoveAt", "ELSE 2 " + piece + ", " + toTile, piece, toTile);
                } else if (king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 3 " + piece + ", " + toTile, piece);
                } else if (pawn.passedValidation == true) {
                  ignoreDuplicates("legalMoveAt", "ELSE 4 " + value + ", " + toTile, value, toTile);
                }
              } else {
                // Checks for Moves when pawn and King Don't Belong to Same Player
                king.moveKing(oppPlayer, piece, toTile);
                pawn.movePawn(player, value, toTile);
                
                if (oppPlayer.equals("White") && !oppPlayer.equals("White") 
                    || oppPlayer.equals("Black") && !oppPlayer.equals("Black") 
                    || player.equals("White") && !player.equals("White") || player.equals("Black") 
                    && !player.equals("Black")) {
                  if (pawn.passedValidation == true && king.passedValidation == true) {
                    ignoreDuplicates("stalemateAt", "ELSE 5" + piece + ", " + toTile, piece, 
                        toTile);
                    ignoreDuplicates("legalMoveAt", "ELSE 6" + value + ", " + toTile, value, 
                        toTile);
                  } else if (king.passedValidation == true) {
                    //ignoreDuplicates(legalMoveAt, "ELSE 7 " + piece + ", " + toTile, piece);
                  } else if (pawn.passedValidation == true) {
                    ignoreDuplicates("legalMoveAt", "ELSE 8 " + value + ", " + toTile, value, 
                        toTile);
                  }
                
                  pawn.passedValidation = false;
                  king.passedValidation = false;
                }
              }

              
              king.passedValidation = false;
              pawn.passedValidation = false;
              
            } else if (value.contains("Rook")) {
              if (value.contains("White") && piece.contains("White") || value.contains("Black") 
                  && piece.contains("Black")) {
                // Checks for Moves when rook and King Belong to Same Player
                rook.moveRook(oppPlayer, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
              
                if (rook.passedValidation == true && king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 4 " + value + ", " + toTile, value);
                  ignoreDuplicates("legalMoveAt", "ELSE 9 " + piece + ", " + toTile, piece, toTile);
                } else if (king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 10 " + piece + ", " + toTile, piece);
                } else if (rook.passedValidation == true) {
                  ignoreDuplicates("legalMoveAt", "ELSE 11 " + value + ", " + toTile, value, 
                      toTile);
                }
              } else {
                // Checks for Moves when rook and King Don't Belong to Same Player
                rook.moveRook(player, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
                
                if (oppPlayer.equals("White") && !oppPlayer.equals("White") 
                    || oppPlayer.equals("Black") && !oppPlayer.equals("Black") 
                    || player.equals("White") && !player.equals("White") || player.equals("Black") 
                    && !player.equals("Black")) {
                  if (rook.passedValidation == true && king.passedValidation == true) {
                    ignoreDuplicates("stalemateAt", "ELSE 12" + piece + ", " + toTile, piece, 
                        toTile);
                    ignoreDuplicates("legalMoveAt", "ELSE 13" + value + ", " + toTile, value, 
                        toTile);
                  } else if (king.passedValidation == true) {
                    //ignoreDuplicates(legalMoveAt, "ELSE 14 " + piece + ", " + toTile, piece);
                  } else if (rook.passedValidation == true) {
                    ignoreDuplicates("legalMoveAt", "ELSE 3 " + value + ", " + toTile, value, 
                        toTile);
                  }
                
                  rook.passedValidation = false;
                  king.passedValidation = false;
                }
              }

              
              king.passedValidation = false;
              rook.passedValidation = false;
              
            } else if (value.contains("Knight")) {
              if (value.contains("White") && piece.contains("White") || value.contains("Black") 
                  && piece.contains("Black")) {
                // Checks for Moves when knight and King Belong to Same Player
                knight.moveKnight(oppPlayer, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
              
                if (knight.passedValidation == true && king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 4 " + value + ", " + toTile, value);
                  ignoreDuplicates("legalMoveAt", "ELSE 15 " + piece + ", " + toTile, piece, 
                      toTile);
                } else if (king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 16 " + piece + ", " + toTile, piece);
                } else if (knight.passedValidation == true) {
                  ignoreDuplicates("legalMoveAt", "ELSE 17 " + value + ", " + toTile, value, 
                      toTile);
                }
              } else {
                // Checks for Moves when knight and King Don't Belong to Same Player
                knight.moveKnight(player, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
                
                if (oppPlayer.equals("White") && !oppPlayer.equals("White") 
                    || oppPlayer.equals("Black") && !oppPlayer.equals("Black") 
                    || player.equals("White") && !player.equals("White") || player.equals("Black") 
                    && !player.equals("Black")) {
                  if (knight.passedValidation == true && king.passedValidation == true) {
                    ignoreDuplicates("stalemateAt", "ELSE 18" + piece + ", " + toTile, piece, 
                        toTile);
                    ignoreDuplicates("legalMoveAt", "ELSE 19" + value + ", " + toTile, value, 
                        toTile);
                  } else if (king.passedValidation == true) {
                    //ignoreDuplicates(legalMoveAt, "ELSE 20 " + piece + ", " + toTile, piece);
                  } else if (knight.passedValidation == true) {
                    ignoreDuplicates("legalMoveAt", "ELSE 21 " + value + ", " + toTile, value, 
                        toTile);
                  }
                
                  knight.passedValidation = false;
                  king.passedValidation = false;
                }
              }

              
              king.passedValidation = false;
              knight.passedValidation = false;
              
            } else if (value.contains("Bishop")) {
              if (value.contains("White") && piece.contains("White") || value.contains("Black") 
                  && piece.contains("Black")) {
                // Checks for Moves when bishop and King Belong to Same Player
                bishop.moveBishop(oppPlayer, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
              
                if (bishop.passedValidation == true && king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 4 " + value + ", " + toTile, value);
                  ignoreDuplicates("legalMoveAt", "ELSE 22 " + value + ", " + toTile, value, 
                      toTile);
                } else if (king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 23 " + piece + ", " + toTile, piece);
                } else if (bishop.passedValidation == true) {
                  ignoreDuplicates("legalMoveAt", "ELSE 24 " + value + ", " + toTile, value, 
                      toTile);
                }
              } else {
                // Checks for Moves when bishop and King Don't Belong to Same Player
                bishop.moveBishop(player, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
                
                if (oppPlayer.equals("White") && !oppPlayer.equals("White") 
                    || oppPlayer.equals("Black") && !oppPlayer.equals("Black") 
                    || player.equals("White") && !player.equals("White") || player.equals("Black") 
                    && !player.equals("Black")) {
                  if (bishop.passedValidation == true && king.passedValidation == true) {
                    ignoreDuplicates("stalemateAt", "ELSE 25" + piece + ", " + toTile, piece, 
                        toTile);
                    ignoreDuplicates("legalMoveAt", "ELSE 26" + value + ", " + toTile, value, 
                        toTile);
                  } else if (king.passedValidation == true) {
                    ignoreDuplicates("legalMoveAt", "ELSE 27 " + piece + ", " + toTile, piece, 
                        toTile);
                  } else if (bishop.passedValidation == true) {
                    ignoreDuplicates("legalMoveAt", "ELSE 28 " + value + ", " + toTile, value, 
                        toTile);
                  }
                
                  bishop.passedValidation = false;
                  king.passedValidation = false;
                }
              }

              
              king.passedValidation = false;
              bishop.passedValidation = false;
              
            } else if (value.contains("Queen")) {
             
              
              if (value.contains("White") && piece.contains("White") 
                  || value.contains("Black") && piece.contains("Black")) {
                // Checks for Moves when Queen and King Belong to Same Player
                queen.moveQueen(oppPlayer, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
              
                if (queen.passedValidation == true && king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 4 " + value + ", " + toTile, value);
                  ignoreDuplicates("legalMoveAt", "ELSE 29 " + value + ", " + toTile, value, 
                      toTile);
                } else if (king.passedValidation == true) {
                  //ignoreDuplicates(legalMoveAt, "ELSE 30 " + piece + ", " + toTile, piece);
                } else if (queen.passedValidation == true) {
                  ignoreDuplicates("legalMoveAt", "ELSE 31 " + value + ", " + toTile, value, 
                      toTile);
                }
              } else {
                // Checks for Moves when Queen and King Don't Belong to Same Player
                queen.moveQueen(player, value, toTile);
                king.moveKing(oppPlayer, piece, toTile);
                
                if (oppPlayer.equals("White") && !oppPlayer.equals("White") 
                    || oppPlayer.equals("Black") && !oppPlayer.equals("Black") 
                    || player.equals("White") 
                    && !player.equals("White") || player.equals("Black") 
                    && !player.equals("Black")) {
                  if (queen.passedValidation == true && king.passedValidation == true) {
                    ignoreDuplicates("stalemateAt", "ELSE 32" + piece + ", " + toTile, piece, 
                        toTile);
                    ignoreDuplicates("legalMoveAt", "ELSE 33" + value + ", " + toTile, value, 
                        toTile);
                  } else if (king.passedValidation == true) {
                    //ignoreDuplicates(legalMoveAt, "ELSE 34 " + piece + ", " + toTile, piece);
                  } else if (queen.passedValidation == true) {
                    ignoreDuplicates("legalMoveAt", "ELSE 35 " + value + ", " + toTile, value, 
                        toTile);
                  }
                
                  queen.passedValidation = false;
                  king.passedValidation = false;
                }
              }

              
              king.passedValidation = false;
              queen.passedValidation = false;
            
            } else {
              ; // Do Nothing
            }
            
          }
          
        }
      }
              
    } else {
        ; // Not a Piece
    }
  }
  
  /**
   * Method increments the stalemate counter if a player is putting their King in danger or in
   * check. For example, if the Black player is trying to move their King into a tile where a White
   * player's piece can move into.
   * @param piece Takes a piece containing a player's colour
   */
  public static void stalemateCount(String piece) {
    /* If piece contains White, this means Black player is checking to see if they are putting
     * their King into danger (If the tile the King intends to move to can also be moved into
     * by the White player's piece, we have a Stalemate for that particular move).
     */
    if (piece.contains("White")) {
      whiteStalemate++;
      
    /* If piece contains Black, this means White player is checking to see if they are putting
     * their King into danger (If the tile the King intends to move to can also be moved into
     * by the Black player's piece, we have a Stalemate for that particular move).
     * 
     */
    } else if (piece.contains("Black")) {
      blackStalemate++;
    }
  }
  
  /**
   * If a piece can move into a tile successfully (also without a player putting their own King in
   * danger), then the legal move counter is incremented.
   * @param piece Takes a piece containing a player's colour.
   */
  public static void legalMoveCount(String piece) {
    if (piece.contains("White")) {
      whiteLegalMove++;
    } else if (piece.contains("Black")) {
      blackLegalMove++;
    }
  }
  
  /**
   * Ignores duplicate Array entries of the same player, piece and destination tile
   * which can happen in some cases during numerous iterations of legal and illegal moves.
   */
  public static void ignoreDuplicates(String dataStructure, String string, String piece, 
      String toTile) {
    if (dataStructure.equals("stalemateAt")) {
      stalemateAt.put(toTile, piece);
      stalemateCount(piece);
    } else if (dataStructure.equals("legalMoveAt")) {
      if (stalemateAt.containsKey(toTile)) {
        ; // If there is already a Stalemate in this tile, do nothing.
      } else if (legalMoveAt.contains(string)) {
        ; // Do Nothing
      } else {
        legalMoveAt.add(string);
        legalMoveCount(piece);
      }
      
    }

  }
}
