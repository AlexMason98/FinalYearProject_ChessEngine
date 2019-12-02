package application;

public class Knight {

  public Board board = new Board();
  
  /**
   * This method is responsible for validating and then moving a Knight
   * chess piece, in accordance to the game's rules.
   * 
   * @param player Takes the current player (Either "White" or "Black").
   * @param selectedKnight Takes the selected Knight piece.
   * @param toTile The destination tile that the Knight intends to move to.
   */
  public void moveKnight(String player, String selectedKnight, String toTile) {
    
    String fromTile = board.map.getTile(selectedKnight);
    
    int fromRow = Integer.parseInt(String.valueOf(fromTile.charAt(0)));
    char fromColumn = fromTile.charAt(1);
    int toRow = Integer.parseInt(String.valueOf(toTile.charAt(0)));
    char toColumn = toTile.charAt(1);
    
    // MOVING UP 2 SPACES AND 1 SPACE LEFT
    if ((toRow == fromRow + 2) && (toColumn == fromColumn - 1)) {
      System.out.println("----- Moving 2 Spaces Up and 1 Space Left -----");
    
    // MOVING UP 2 SPACES AND 1 SPACE RIGHT
    } else if ((toRow == fromRow + 2) && (toColumn == fromColumn + 1)) {
      System.out.println("----- Moving 2 Spaces Up and 1 Space Right -----");
    
    // MOVING RIGHT 2 SPACES AND 1 SPACE UP
    } else if ((toColumn == fromColumn + 2) && (toRow == fromRow + 1)) {
      System.out.println("----- Moving 2 Spaces Right and 1 Space Up -----");
    
    // MOVING RIGHT 2 SPACES AND 1 SPACE DOWN
    } else if ((toColumn == fromColumn + 2) && (toRow == fromRow - 1)) {
      System.out.println("----- Moving 2 Spaces Right and 1 Space Down -----");
      
    // MOVING DOWN 2 SPACES AND 1 SPACE LEFT
    } else if ((toRow == fromRow - 2) && (toColumn == fromColumn - 1)) {
      System.out.println("----- Moving 2 Spaces Down and 1 Space Left -----");
    
    // MOVING DOWN 2 SPACES AND 1 SPACE RIGHT
    } else if ((toRow == fromRow - 2) && (toColumn == fromColumn + 1)) {
      System.out.println("----- Moving 2 Spaces Down and 1 Space Right -----");
    
    // MOVING LEFT 2 SPACES AND 1 SPACE UP
    } else if ((toColumn == fromColumn - 2) && (toRow == fromRow + 1)) {
      System.out.println("----- Moving 2 Spaces Left and 1 Space Up -----");
    
    // MOVING LEFT 2 SPACES AND 1 SPACE DOWN
    } else if ((toColumn == fromColumn - 2) && (toRow == fromRow - 1)) {
      System.out.println("----- Moving 2 Spaces Left and 1 Space Down -----");
    
    /* If we have none of the above combinations which are valid moves for a Knight, print "Illegal
     * Move". */
    } else {
      System.out.println("Illegal Move. Please move your Knight in accordance to the game's rules");
    }
  }
  
  
}
