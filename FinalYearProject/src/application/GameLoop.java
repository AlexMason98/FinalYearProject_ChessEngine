package application;

public class GameLoop {

  private boolean whitePlayerTurn = true;
  private boolean isKingCaptured = false;
  
  public TreeMaps map;
  public Board board;
  
  /**
   * These are the pre-conditions needed to set up the game.
   * This initialises the board (which then initialises my TreeMaps), followed
   * by setting up the tiles and pieces to start the game. 
   */
  public void preConditions() {
  	map = new TreeMaps();
    board = new Board();
  }

  /**
   * This is the game loop, it constantly runs until one of the two kings
   * are captured. It also checks for player turns and if a move is valid.
   */
  public void run() {

    while (isKingCaptured == false) {

      if (whitePlayerTurn == true) {
        //Move.whitePieceMove();
      }

    }

  }
}
