package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;

public class GameLoop {

  private boolean whitePlayerTurn = true;
  
  public Board board = new Board();
  private Checkmate checkmate = new Checkmate();
  private Stalemate stalemate = new Stalemate();
  //public TreeMaps map = new TreeMaps();
  
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
   * @throws InvalidPlayerException .
   * @throws InvalidPieceException .
   */
  public void run() throws InvalidPieceException, InvalidPlayerException {

    while (!checkmate.checkmate() && !stalemate.stalemate()) {

      if (whitePlayerTurn == true) {
        //Move.whitePieceMove();
      }

    }

  }
  
}
