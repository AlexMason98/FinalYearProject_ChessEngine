package application;

public class GameLoop {

  private boolean whitePlayerTurn = true;
  private boolean isKingCaptured = false;
  
  public Board board = new Board();
  
  /**
   * These are the pre-conditions needed to set up the game.
   * This initialises the board (which then initialises my TreeMaps), followed
   * by setting up the tiles and pieces to start the game. 
   */
  //public void preConditions() {
  //board = new Board();
  //}

  /**
   * This is the game loop, it constantly runs until one of the two kings
   * are captured. It also checks for player turns and if a move is valid.
   */
  public void run() {

    while (!isCheckmate()) {

      if (whitePlayerTurn == true) {
        //Move.whitePieceMove();
      }

    }

  }
  
  /**
   * This method is responsible for checking if the game is over, which occurs when a player
   * has captured their opponent's king (when a king is added to the capturedPieces array).
   * 
   * @return true if a King has been captured (is checkmate), false if a King hasn't been captured.
   */
  public boolean isCheckmate() {
    if (board.map.capturedPieces.contains("BlackKing")) {
      // TODO: When GUI added, call a method to display "White Player Wins" on game board
      return true;
    } else if (board.map.capturedPieces.contains("WhiteKing")) {
      // TODO: When GUI added, call a method to display "Black Player Wins" on game board
      return true;
    } else {
      return false;
    }
  }
}
