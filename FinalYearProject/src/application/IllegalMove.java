package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import java.util.Map;

public class IllegalMove extends Piece {
  
  //private Piece piece = new Piece();
  private Pawn pawn = new Pawn();
  private Rook rook = new Rook();
  private Knight knight = new Knight();
  private Bishop bishop = new Bishop();
  private Queen queen = new Queen();
  private King king = new King();
  public int pieceCount = 0;
  public int stalemateWhiteCount = 0;
  public int stalemateBlackCount = 0;
  public boolean stalemate = false;
  public int whitePieceCount = 0;
  public int blackPieceCount = 0;

  /**
   * Responsible for returning true or false if the selected move is illegal.
   * This is now redundant to the LegalMoves class, which can both check legal and
   * illegal moves in the same class.
   * 
   * @param player The current player ("White" or "Black").
   * @param toTile The destination tile.
   * @return true if illegal, false is legal.
   * @throws InvalidPieceException If an invalid piece type is passed.
   */
  public boolean illegalMove(String player, String toTile)
      throws InvalidPieceException, InvalidPlayerException {
    Board.board.map.valueLock = true;
    
    /* Seeing if White player is putting their King in danger. Ensures value lock is true,
     * meaning when checking each piece's validation methods, which naturally calls their
     * corresponding set position methods, that the TreeMap is locked from actually setting their 
     * position in the map/on the board */
    if (player.equals("White") && Board.board.map.valueLock == true) {
      for (Map.Entry<String, String> entry : Board.board.map.piecePos.entrySet()) {
        String value = entry.getValue();
        
        // Checking I am getting the opponent player's piece from its piece ID
        if (!value.contains(player) && !value.equals("null") && value != null) {
          if (value.contains("Pawn")) {
            System.out.println("We have Pawn: " + value);
            //whitePieceCount++;
            pawn.movePawn("Black", value, toTile);
            king.moveKing("White", "WhiteKing", toTile);
            
            if (pawn.passedValidation == true && king.passedValidation == true) {
              pawn.passedValidation = false; // Reset values for future use/checking
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateWhiteCount++;
              return true;
            }
          } else if (value.contains("Rook")) {
            System.out.println("We have Rook: " + value);
            //whitePieceCount++;
            rook.moveRook("Black", value, toTile);
            king.moveKing("White", "WhiteKing", toTile);
            if (rook.passedValidation == true && king.passedValidation == true) {
              rook.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateWhiteCount++;
              return true;
            }
            
          } else if (value.contains("Knight")) {
            System.out.println("We have Knight: " + value);
            //whitePieceCount++;
            knight.moveKnight("Black", value, toTile);
            king.moveKing("White", "WhiteKing", toTile);
                
            if (knight.passedValidation == true && king.passedValidation == true) {
              knight.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateWhiteCount++;
              return true;
            }
            
          } else if (value.contains("Bishop")) {
            System.out.println("We have Bishop: " + value);
            //whitePieceCount++;
            bishop.moveBishop("Black", value, toTile);
            king.moveKing("White", "WhiteKing", toTile);
                
            // If the Bishop and King pass their validation checks and can both move into that
            // tile, then increase illegal move counter
            if (bishop.passedValidation == true && king.passedValidation == true) {
              bishop.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateWhiteCount++;
              return true;
            }
            
          } else if (value.contains("Queen")) {
            System.out.println("We have Queen: " + value);
            //whitePieceCount++;
            queen.moveQueen("Black", value, toTile);
            king.moveKing("White", "WhiteKing", toTile);
            
            if (queen.passedValidation == true && king.passedValidation == true) {
              queen.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              whitePieceCount++;
              stalemateWhiteCount++;
              return true;
            } else {
              whitePieceCount++;
            }
          } else if (value.contains("King")) {
            System.out.println("We have King: " + value);
            //whitePieceCount++;
            king.moveKing("White", "WhiteKing", toTile);
            
            if (king.passedValidation == true) {
              whitePieceCount++;
              king.passedValidation = false;
              king.moveKing("Black", value, toTile);
              
              if (king.passedValidation == true) {
                king.passedValidation = false;
                Board.board.map.valueLock = false;
                stalemateWhiteCount++;
                return true;
              }
            }
            
          } else {
            return true; /* If we don't have any valid piece type, return true for Illegal Move as
            the piece must be valid for a legal move to occur */
          }
        } else {
          ; // Do nothing in the instance that we don't have the opponent player's piece
        }
      }
      
      Board.board.map.valueLock = false; // Disable value lock
    
    
    /* Seeing if Black player is putting their King in danger. Ensures value lock is true,
     * meaning when checking each piece's validation methods, which naturally calls their
     * corresponding set position methods, that the TreeMap is locked from actually setting their 
     * position in the map/on the board */
    } else if (player.equals("Black") && Board.board.map.valueLock == true) {
      for (Map.Entry<String, String> entry : Board.board.map.piecePos.entrySet()) {
        String value = entry.getValue();
        
        // Checking I am getting the opponent player's piece from its piece ID
        if (!value.contains(player) && !value.equals("null") && value != null) {
          if (value.contains("Pawn")) {
            System.out.println("We have Pawn: " + value);
            //blackPieceCount++;
            pawn.movePawn("White", value, toTile);
            king.moveKing("Black", "BlackKing", toTile);
            
            if (pawn.passedValidation == true && king.passedValidation == true) {
              pawn.passedValidation = false; // Reset values for future use/checking
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateBlackCount++;
              return true;
            }
          } else if (value.contains("Rook")) {
            System.out.println("We have Rook: " + value);
            //blackPieceCount++;
            rook.moveRook("White", value, toTile);
            king.moveKing("Black", "BlackKing", toTile);
            if (rook.passedValidation == true && king.passedValidation == true) {
              rook.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateBlackCount++;
              return true;
            }
            
          } else if (value.contains("Knight")) {
            System.out.println("We have Knight: " + value);
            //blackPieceCount++;
            knight.moveKnight("White", value, toTile);
            king.moveKing("Black", "BlackKing", toTile);
                
            if (knight.passedValidation == true && king.passedValidation == true) {
              knight.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateBlackCount++;
              return true;
            }
            
          } else if (value.contains("Bishop")) {
            System.out.println("We have Bishop: " + value);
            //blackPieceCount++;
            bishop.moveBishop("White", value, toTile);
            king.moveKing("Black", "BlackKing", toTile);
                
            // If the Bishop and King pass their validation checks and can both move into that
            // tile, then increase illegal move counter
            if (bishop.passedValidation == true && king.passedValidation == true) {
              bishop.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateBlackCount++;
              return true;
            }
            
          } else if (value.contains("Queen")) {
            System.out.println("We have Queen: " + value);
            //blackPieceCount++;
            queen.moveQueen("White", value, toTile);
            king.moveKing("Black", "BlackKing", toTile);
            
            if (queen.passedValidation == true && king.passedValidation == true) {
              queen.passedValidation = false;
              king.passedValidation = false;
              Board.board.map.valueLock = false;
              stalemateBlackCount++;
              return true;
            }
          } else if (value.contains("King")) {
            System.out.println("We have King: " + value);
            //whitePieceCount++;
            king.moveKing("White", value, toTile);
              
            if (king.passedValidation == true) {
              whitePieceCount++;
              king.passedValidation = false;
              king.moveKing("Black", "BlackKing", toTile);
                
              if (king.passedValidation == true) {
                king.passedValidation = false;
                Board.board.map.valueLock = false;
                stalemateBlackCount++;
                return true;
              }
            }
          } else {
            return true; /* If we don't have any valid piece type, return true for Illegal Move as
            the piece must be valid for a legal move to occur */
          }
        } else {
          ; // Do nothing in the instance that we don't have the opponent player's piece
        }
      }
      
      Board.board.map.valueLock = false; // Disable value lock
    } else {
      throw new InvalidPlayerException();
    }
    return false;
  }
  
}
