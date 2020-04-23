package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import java.util.ArrayList;


public class Minimax {

  static ArrayList<String> moves = new ArrayList<String>();
  
  /**
   * This method is responsible for deciding what move to make from a set of possible/
   * legal moves, with the best move selected as the one that can capture an opponent
   * player's piece with the highest importance. If no opponent piece can be captured,
   * the best move will be to an empty tile.
   * @throws InvalidPieceException If an invalid piece is detected (not a piece type).
   * @throws InvalidPlayerException If anything other than "White" or "Black" is used.
   */
  public static void algo() throws InvalidPieceException, InvalidPlayerException {
    moves = LegalMoves.legalMoves("Black");
    
    for (int i = 0; i < moves.size(); i++) {
      String[] move = moves.get(i).split(", ", 2);
      
      if (move[1].contains("Pawn")) {
        System.out.println("Best is: " + bestMove(move[0]));
        
        Controller.pawn.movePawn("Black", move[1], bestMove(move[0]));
        
      } else if (move[1].contains("Rook")) {
        System.out.println("Best is: " + bestMove(move[0]));
        
        Controller.rook.moveRook("Black", move[1], bestMove(move[0]));
       
      } else if (move[1].contains("Knight")) {
        System.out.println("Best is: " + bestMove(move[0]));
        
        Controller.knight.moveKnight("Black", move[1], bestMove(move[0]));
       
      } else if (move[1].contains("Bishop")) {
        System.out.println("Best is: " + bestMove(move[0]));
        
        Controller.bishop.moveBishop("Black", move[1], bestMove(move[0]));
       
      } else if (move[1].contains("Queen")) {
        System.out.println("Best is: " + bestMove(move[0]));
        
        Controller.pawn.movePawn("Black", move[1], bestMove(move[0]));
      } else if (move[1].contains("King")) {
        System.out.println("Best is: " + bestMove(move[0]));
        
        Controller.king.moveKing("Black", move[1], bestMove(move[0]));
      }
    }  
  }
  
  /**
   * This method takes the legal moves from the method above and executes the if/else if
   * statements from highest priority (of the piece to capture) to lowest. It returns 
   * the best move it can do first. 
   * @param toTile Takes a destination tile to check if a piece is in that tile.
   * @return a string containing the best move thus far.
   */
  public static String bestMove(String toTile) {
    
    String piece = "";
    piece = Board.board.map.getPieceOrOccupation("piecePos", toTile);
  	
    // Find best move starting with most valuable piece to capture to the lowest
    if (piece != null && piece.contains("King")) {
      return toTile;
      
    } else if (piece != null && piece.contains("Queen")) {
      return toTile;
      
    } else if (piece != null && piece.contains("Rook")) {
      return toTile;
      
    } else if (piece != null && piece.contains("Bishop")) {
      return toTile;
     
    } else if (piece != null && piece.contains("Knight")) {
      return toTile;
     
    } else if (piece != null && piece.contains("Pawn")) {
      return toTile;
     
    } else if (piece == null || piece == "null") {
      return toTile;
    }
  	
    return toTile;
  }
}
