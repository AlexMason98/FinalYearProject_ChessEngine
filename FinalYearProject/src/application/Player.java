package application;

import application.exceptions.InvalidPlayerException;

public class Player {

  private String player;

  /**
   * This is my constructor for Player. It takes a string and sets as the current player.
   * @param player Takes a player as a string and sets the player.
   */
  public Player(String player) throws InvalidPlayerException {
    if (player.equals("White") || player.equals("Black")) {
      this.player = player;
    } else {
      throw new InvalidPlayerException();
    }
  }

  public String getPlayer() {
    return player.toString();
  }
  
  /**
   * This is a boolean method which checks to see which player's turn it is.
   * 
   * @return true if White Player's turn, false if Black Player's turn
   */
  public boolean isWhiteTurn() {
    if (player == "White") {
      return true;
    } else if (player == "Black") {
      return false;
    }
    
    return false;
  }
}
