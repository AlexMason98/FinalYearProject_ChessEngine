package application;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {

  public Main main = new Main();
  @FXML private static ImageView blackRook1;
  @FXML private static ImageView blackKnight1;
  @FXML private static ImageView blackBishop1;
  @FXML private static ImageView blackQueen;
  @FXML private static ImageView blackKing;
  @FXML private static ImageView blackBishop2;
  @FXML private static ImageView blackKnight2;
  @FXML private static ImageView blackRook2;
  @FXML private static ImageView blackPawn1;
  @FXML private static ImageView blackPawn2;
  @FXML private static ImageView blackPawn3;
  @FXML private static ImageView blackPawn4;
  @FXML private static ImageView blackPawn5;
  @FXML private static ImageView blackPawn6;
  @FXML private static ImageView blackPawn7;
  @FXML private static ImageView blackPawn8;
  @FXML private static ImageView whiteRook1;
  @FXML private static ImageView whiteKnight1;
  @FXML private static ImageView whiteBishop1;
  @FXML private static ImageView whiteQueen;
  @FXML private static ImageView whiteKing;
  @FXML private static ImageView whiteBishop2;
  @FXML private static ImageView whiteKnight2;
  @FXML private static ImageView whiteRook2;
  @FXML private static ImageView whitePawn1;
  @FXML private static ImageView whitePawn2;
  @FXML private static ImageView whitePawn3;
  @FXML private static ImageView whitePawn4;
  @FXML private static ImageView whitePawn5;
  @FXML private static ImageView whitePawn6;
  @FXML private static ImageView whitePawn7;
  @FXML private static ImageView whitePawn8;
  
  public static ArrayList<String> movementPath = new ArrayList<String>();
  public static Pawn pawn = new Pawn();
  public static Rook rook = new Rook();
  public static Knight knight = new Knight();
  public static Bishop bishop = new Bishop();
  public static Queen queen = new Queen();
  public static King king = new King();
  
 
  /**
   * This method checks to see what piece and tile is clicked based on X and Y co-ords.
   * 
   * @param event The current MouseEvent event handler.
   * @throws InvalidPieceException If the piece is not a valid piece type.
   * @throws InvalidPlayerException If the player is not "White" or "Black".
   */
  
  @FXML
  public void pieceClicked(@SuppressWarnings("exports") MouseEvent event) 
       throws InvalidPieceException, InvalidPlayerException {
    String id = event.getPickResult().getIntersectedNode().getId();
    
    if (id == null) {

      System.out.println("LANDS LINE 91");
      String tile = findTile(event.getX(), event.getY());
      String piece = Board.board.map.getPieceOrOccupation("piecePos", tile);
      
      if (piece == null || piece.equals("null")) {
        System.out.println("Lands Line 80");
        System.out.println(tile);
        movementPath.add(tile);
        
        if (movementPath.size() == 2) {
          validate(movementPath);
        }
      } else if (!piece.equals("null")) {
        System.out.println("LANDS LINE 95, PIECE IS: " + piece);
        movementPath.add(piece);
          
        if (movementPath.size() == 2) {
          validate(movementPath);
        }
      } else {
        System.out.println("Lands in Line 89");
        movementPath.add(tile);
      }

    } else if (id.contains("white") || id.contains("black")) {
      System.out.println("Piece is: " + id);
      
      /* This checks to see if the player is trying to de-select a piece.
       * It is assumed that if a player clicks the same piece again that they are trying to
       * unselect the piece they originally selected to make a different move.
       */
      if (movementPath.contains(idToUpper(id))) {
        movementPath.clear();
        System.out.println("Deselected Piece");
      } else {
        movementPath.add(idToUpper(id));
        
        if (movementPath.size() == 2) {
          validate(movementPath);
        }
      }
    }
    
    System.out.println("ID: " + id);
    
  }
  
  
  /**
   * This method is responsible for taking two co-ordinates and
   * matching them to a tile on the Chessboard GUI, based on what range
   * these co-ordinates fall into.
   * @param x A co-ordinate from mouse click event.
   * @param y A co-ordinate from mouse click event.
   * @return tile corresponding to co-ordinates.
   */
  public static String findTile(double x, double y) { 
    if (x >= 0 && x <= 112.5 && y >= 0 && y <= 112.5) {
      return "8A";
    } else if (x >= 112.5 && x <= 225 && y >= 0 && y <= 112.5) {
      return "8B";
    } else if (x >= 225 && x <= 337.5 && y >= 0 && y <= 112.5) {
      return "8C";
    } else if (x >= 337.5 && x <= 450 && y >= 0 && y <= 112.5) {
      return "8D";
    } else if (x >= 450 && x <= 562.5 && y >= 0 && y <= 112.5) {
      return "8E";
    } else if (x >= 562.5 && x <= 675 && y >= 0 && y <= 112.5) {
      return "8F";
    } else if (x >= 675 && x <= 787.5 && y >= 0 && y <= 112.5) {
      return "8G";
    } else if (x >= 787.5 && x <= 900 && y >= 0 && y <= 112.5) {
      return "8H";
    } else if (x >= 0 && x <= 112.5 && y >= 112.5 && y <= 225) {
      return "7A";
    } else if (x >= 112.5 && x <= 225 && y >= 112.5 && y <= 225) {
      return "7B";
    } else if (x >= 225 && x <= 337.5 && y >= 112.5 && y <= 225) {
      return "7C";
    } else if (x >= 337.5 && x <= 450 && y >= 112.5 && y <= 225) {
      return "7D";
    } else if (x >= 450 && x <= 562.5 && y >= 112.5 && y <= 225) {
      return "7E";
    } else if (x >= 562.5 && x <= 675 && y >= 112.5 && y <= 225) {
      return "7F";
    } else if (x >= 675 && x <= 787.5 && y >= 112.5 && y <= 225) {
      return "7G";
    } else if (x >= 787.5 && x <= 900 && y >= 112.5 && y <= 225) {
      return "7H";
    } else if (x >= 0 && x <= 112.5 && y >= 225 && y <= 337.5) {
      return "6A";
    } else if (x >= 112.5 && x <= 225 && y >= 225 && y <= 337.5) {
      return "6B";
    } else if (x >= 225 && x <= 337.5 && y >= 225 && y <= 337.5) {
      return "6C";
    } else if (x >= 337.5 && x <= 450 && y >= 225 && y <= 337.5) {
      return "6D";
    } else if (x >= 450 && x <= 562.5 && y >= 225 && y <= 337.5) {
      return "6E";
    } else if (x >= 562.5 && x <= 675 && y >= 225 && y <= 337.5) {
      return "6F";
    } else if (x >= 675 && x <= 787.5 && y >= 225 && y <= 337.5) {
      return "6G";
    } else if (x >= 787.5 && x <= 900 && y >= 225 && y <= 337.5) {
      return "6H";
    } else if (x >= 0 && x <= 112.5 && y >= 337.5 && y <= 450) {
      return "5A";
    } else if (x >= 112.5 && x <= 225 && y >= 337.5 && y <= 450) {
      return "5B";
    } else if (x >= 225 && x <= 337.5 && y >= 337.5 && y <= 450) {
      return "5C";
    } else if (x >= 337.5 && x <= 450 && y >= 337.5 && y <= 450) {
      return "5D";
    } else if (x >= 450 && x <= 562.5 && y >= 337.5 && y <= 450) {
      return "5E";
    } else if (x >= 562.5 && x <= 675 && y >= 337.5 && y <= 450) {
      return "5F";
    } else if (x >= 675 && x <= 787.5 && y >= 337.5 && y <= 450) {
      return "5G";
    } else if (x >= 787.5 && x <= 900 && y >= 337.5 && y <= 450) {
      return "5H";
    } else if (x >= 0 && x <= 112.5 && y >= 450 && y <= 562.5) {
      return "4A";
    } else if (x >= 112.5 && x <= 225 && y >= 450 && y <= 562.5) {
      return "4B";
    } else if (x >= 225 && x <= 337.5 && y >= 450 && y <= 562.5) {
      return "4C";
    } else if (x >= 337.5 && x <= 450 && y >= 450 && y <= 562.5) {
      return "4D";
    } else if (x >= 450 && x <= 562.5 && y >= 450 && y <= 562.5) {
      return "4E";
    } else if (x >= 562.5 && x <= 675 && y >= 450 && y <= 562.5) {
      return "4F";
    } else if (x >= 675 && x <= 787.5 && y >= 450 && y <= 562.5) {
      return "4G";
    } else if (x >= 787.5 && x <= 900 && y >= 450 && y <= 562.5) {
      return "4H";
    } else if (x >= 0 && x <= 112.5 && y >= 562.5 && y <= 675) {
      return "3A";
    } else if (x >= 112.5 && x <= 225 && y >= 562.5 && y <= 675) {
      return "3B";
    } else if (x >= 225 && x <= 337.5 && y >= 562.5 && y <= 675) {
      return "3C";
    } else if (x >= 337.5 && x <= 450 && y >= 562.5 && y <= 675) {
      return "3D";
    } else if (x >= 450 && x <= 562.5 && y >= 562.5 && y <= 675) {
      return "3E";
    } else if (x >= 562.5 && x <= 675 && y >= 562.5 && y <= 675) {
      return "3F";
    } else if (x >= 675 && x <= 787.5 && y >= 562.5 && y <= 675) {
      return "3G";
    } else if (x >= 787.5 && x <= 900 && y >= 562.5 && y <= 675) {
      return "3H";
    } else if (x >= 0 && x <= 112.5 && y >= 675 && y <= 787.5) {
      return "2A";
    } else if (x >= 112.5 && x <= 225 && y >= 675 && y <= 787.5) {
      return "2B";
    } else if (x >= 225 && x <= 337.5 && y >= 675 && y <= 787.5) {
      return "2C";
    } else if (x >= 337.5 && x <= 450 && y >= 675 && y <= 787.5) {
      return "2D";
    } else if (x >= 450 && x <= 562.5 && y >= 675 && y <= 787.5) {
      return "2E";
    } else if (x >= 562.5 && x <= 675 && y >= 675 && y <= 787.5) {
      return "2F";
    } else if (x >= 675 && x <= 787.5 && y >= 675 && y <= 787.5) {
      return "2G";
    } else if (x >= 787.5 && x <= 900 && y >= 675 && y <= 787.5) {
      return "2H";
    } else if (x >= 0 && x <= 112.5 && y >= 787.5 && y <= 900) {
      return "1A";
    } else if (x >= 112.5 && x <= 225 && y >= 787.5 && y <= 900) {
      return "1B";
    } else if (x >= 225 && x <= 337.5 && y >= 787.5 && y <= 900) {
      return "1C";
    } else if (x >= 337.5 && x <= 450 && y >= 787.5 && y <= 900) {
      return "1D";
    } else if (x >= 450 && x <= 562.5 && y >= 787.5 && y <= 900) {
      return "1E";
    } else if (x >= 562.5 && x <= 675 && y >= 787.5 && y <= 900) {
      return "1F";
    } else if (x >= 675 && x <= 787.5 && y >= 787.5 && y <= 900) {
      return "1G";
    } else if (x >= 787.5 && x <= 900 && y >= 787.5 && y <= 900) {
      return "1H";
    } else {
      return "Not a Valid Tile. Please select a tile within the Chess board.";
    }
  }
  
  /**
   * This method validates a pair of moves, whether this a piece to piece, piece to tile, 
   * or tile to tile move, depending on where the user clicked on the board.
   * @param path The path/route the user is intending to take.
   * @throws InvalidPlayerException If the player is not "White" or "Black".
   * @throws InvalidPieceException If an invalid piece type is received.
   */
  public static void validate(ArrayList<String> path) throws InvalidPieceException, 
       InvalidPlayerException {
    if (path.size() == 2) {
      String idOrTile1 = path.get(0);
      String idOrTile2 = path.get(1);
      // Checks for White player piece moving to Black player piece
      if (idOrTile1.contains("White") && idOrTile2.contains("Black")) {
        System.out.println("Detected White player move to Black player piece");
        Controller.guiPieceToClass(idOrTile1, idOrTile2);
        movementPath.clear();
        
      // Checks for Black player piece moving to White player piece
      } else if (idOrTile1.contains("Black") && idOrTile2.contains("White")) {
        System.out.println("Detected Black player move to White player piece");
        Controller.guiPieceToClass(idOrTile1, idOrTile2);
        movementPath.clear();
      
      // Checks for White player move to another tile
      } else if (idOrTile1.contains("White") && idOrTile2.length() == 2) {
        System.out.println("Detected White player move to another tile");
        Controller.guiPieceToClass(idOrTile1, idOrTile2);
        movementPath.clear();
      
      // Checks for Black player move to another tile
      } else if (idOrTile1.contains("Black") && idOrTile2.length() == 2) {
        System.out.println("Detected Black player move to another tile");
        Controller.guiPieceToClass(idOrTile1, idOrTile2);
        movementPath.clear();
      
      /*
       * ERROR CHECKING/PREVENTION STARTS HERE
       */
      } else if (idOrTile1.contains("Black") && idOrTile2.contains("Black")) {
        System.out.println("You cannot move your own piece to another tile containing your own "
            + "piece");
        movementPath.clear();
        
      } else if (idOrTile1.contains("White") && idOrTile2.contains("White")) {
        System.out.println("You cannot move your own piece to another tile containing your own "
            + "piece");
        movementPath.clear();
      
      } else if (idOrTile1.length() == 2 && idOrTile2.length() == 2) {
        System.out.println("Please select a piece and a destination tile. If you did select a"
            + " piece, please ensure you click directly on the piece");
        movementPath.clear();
      } else if (idOrTile1.equals(idOrTile2)) {
        System.out.println("Deselected Piece");
        movementPath.clear();
        
      } else {
        System.out.println("Please select a piece and a destination tile. If you did select a"
            + " piece, please ensure you click directly on the piece");
        movementPath.clear();
      }
    
    } else if (path.size() > 2) {
      System.out.println("Error: Movement Path array contains 3 or more selections");
    } else {
      ; // Do Nothing
    }
  }
  
  /**
   * This method is responsible for taking pieces/tiles from the GUI and linking
   * them to the rest of the game logic, such as the corresponding piece validation classes.
   * @param piece Takes a piece ID.
   * @param value Takes either a second piece ID or tile co-ord for the first piece to move to.
   * @throws InvalidPlayerException If the player in the piece ID is not White or Black.
   * @throws InvalidPieceException If the piece ID does not contain a valid piece type.
   */
  public static void guiPieceToClass(String piece, String value) 
      throws InvalidPieceException, InvalidPlayerException {
  
    Board.board.map.valueLock = false;
    String player = "";
    String oppPlayer = "";
    String toTile = "";
    String kingPos = "";
    boolean tile = false;
    ImageView img = (ImageView) Main.scene.lookup(idToLower(piece));
    ImageView img2 = new ImageView();
  
    if (piece.contains("White")) {
      player = "White";
      oppPlayer = "Black";
    } else if (piece.contains("Black")) {
      player = "Black";
      oppPlayer = "White";
    }
    
    // If true, the second value is a tile co-ord. If false, it is a piece.
    if (value.length() == 2) {
      tile = true;
      toTile = value;
    } else {
      tile = false;
      toTile = Board.board.map.getTile(value);
      img2 = (ImageView) Main.scene.lookup(idToLower(value));
    }
    
    kingPos = Board.board.map.getTile(oppPlayer + "King");
    LegalMoves.legalMoves(player);
    
    if (piece.contains("Pawn")) {
      if (tile) {
        //pawn.movePawn(player, piece, value);
        
        if (LegalMoves.legalMoves.contains(value + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            pawn.movePawn(player, piece, value);
            setImagePos(img, value);
            pawn.passedValidation = false;
          }
        }
        
      } else {
        //pawn.movePawn(player, piece, toTile);
        
        if (LegalMoves.legalMoves.contains(toTile + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            pawn.movePawn(player, piece, toTile);
            setImagePos(img, toTile);
            img2.setImage(null);
            pawn.passedValidation = false;
          }
        }
      }
      
    } else if (piece.contains("Rook")) {
      if (tile) {
         
        if (LegalMoves.legalMoves.contains(value + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            rook.moveRook(player, piece, value);
            setImagePos(img, value);
            rook.passedValidation = false;
          }
        }
        
      } else {
        
        if (LegalMoves.legalMoves.contains(toTile + ", " + piece)) {
          if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else {
            rook.moveRook(player, piece, toTile);
            setImagePos(img, toTile);
            img2.setImage(null);
            rook.passedValidation = false;
          }
        }
      }
       
    } else if (piece.contains("Knight")) {
      if (tile) {
        
        if (LegalMoves.legalMoves.contains(value + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            knight.moveKnight(player, piece, value);
            setImagePos(img, value);
            knight.passedValidation = false;
          }
        }
        
      } else {
        
        if (LegalMoves.legalMoves.contains(toTile + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            knight.moveKnight(player, piece, toTile);
            setImagePos(img, toTile);
            img2.setImage(null);
            knight.passedValidation = false;
          }
        }
      }
       
    } else if (piece.contains("Bishop")) {
      if (tile) {
        
        if (LegalMoves.legalMoves.contains(value + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            bishop.moveBishop(player, piece, value);
            setImagePos(img, value);
            bishop.passedValidation = false;
          }
        }
        
      } else {
      
        if (LegalMoves.legalMoves.contains(toTile + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            bishop.moveBishop(player, piece, toTile);
            setImagePos(img, toTile);
            img2.setImage(null);
            bishop.passedValidation = false;
          }
        }
      }
       
    } else if (piece.contains("Queen")) {
      if (tile) {
        
        if (LegalMoves.legalMoves.contains(value + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            queen.moveQueen(player, piece, value);
            setImagePos(img, value);
            queen.passedValidation = false;
          }
        }
        
      } else {
        
        if (LegalMoves.legalMoves.contains(toTile + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            queen.moveQueen(player, piece, toTile);
            setImagePos(img, toTile);
            img2.setImage(null);
            queen.passedValidation = false;
          }
        }
      }
       
    } else if (piece.contains("King")) {
      if (tile) {
      
        if (LegalMoves.legalMoves.contains(value + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            king.moveKing(player, piece, value);
            setImagePos(img, value);
            king.passedValidation = false;
          }
        } else {
          System.out.println("You cannot put your King in danger at tile " + value);
        }
        
      } else {

        if (LegalMoves.legalMoves.contains(toTile + ", " + piece)) {
          if (LegalMoves.stalemate) {
            System.out.println("Stalemate Detected");
          } else if (toTile.equals(kingPos)) {
            System.out.println("Checkmate Detected");
          } else {
            king.moveKing(player, piece, toTile);
            setImagePos(img, toTile);
            img2.setImage(null);
            king.passedValidation = false;
          }
        } else {
          System.out.println("You cannot put your King in danger at tile " + toTile);
        }
      }
       
    }
    LegalMoves.legalMoves(player);
    System.out.println("After Move");
  }
  
  
  /**
   * After taking a piece in the GUI and moving it (after validation in the corresponding piece 
   * class), the method below updates the image to the new tile the piece has moved to.
   * @param img Takes the piece as an ImageView object to set new X and Y positions.
   * @param tile Takes the tile to specify the X and Y positions the image should move to.
   */
  public static void setImagePos(@SuppressWarnings("exports") ImageView img, String tile) {
  
    if (tile.equals("8A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(0.25);
    } else if (tile.equals("8B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(0.25);
    } else if (tile.equals("8C")) {
      img.setLayoutX(225.25);
      img.setLayoutY(0.25);
    } else if (tile.equals("8D")) {
      img.setLayoutX(337.75);
      img.setLayoutY(0.25);
    } else if (tile.equals("8E")) {
      img.setLayoutX(450.25);
      img.setLayoutY(0.25);
    } else if (tile.equals("8F")) {
      img.setLayoutX(562.75);
      img.setLayoutY(0.25);
    } else if (tile.equals("8G")) {
      img.setLayoutX(675.25);
      img.setLayoutY(0.25);
    } else if (tile.equals("8H")) {
      img.setLayoutX(787.75);
      img.setLayoutY(0.25);
    } else if (tile.equals("7A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(112.75);
    } else if (tile.equals("7H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(112.75);
    } else if (tile.equals("6A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(225.25);
    } else if (tile.equals("6H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(225.25);
    } else if (tile.equals("5A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(337.75);
    } else if (tile.equals("5H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(337.75);
    } else if (tile.equals("4A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(450.25);
    } else if (tile.equals("4H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(450.25);
    } else if (tile.equals("3A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(562.75);
    } else if (tile.equals("3H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(562.75);
    } else if (tile.equals("2A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(675.25);
    } else if (tile.equals("2H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(675.25);
    } else if (tile.equals("1A")) {
      img.setLayoutX(0.25); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1B")) {
      img.setLayoutX(112.75); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1C")) {
      img.setLayoutX(225.25); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1D")) {
      img.setLayoutX(337.75); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1E")) {
      img.setLayoutX(450.25); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1F")) {
      img.setLayoutX(562.75); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1G")) {
      img.setLayoutX(675.25); 
      img.setLayoutY(787.75);
    } else if (tile.equals("1H")) {
      img.setLayoutX(787.75); 
      img.setLayoutY(787.75);
    } else {
      System.out.println("Not a Valid Tile.");
    }
  }
  
  /**
   * The purpose of this method is to alter a piece ID so that the beginning
   * letter is capitalised so it is uniform/standard with ID validation throughout
   * the rest of the program. 
   * @param id Takes a piece ID.
   * @return Returns a string with the beginning letter of the ID capitalised.
   */
  public String idToUpper(String id) {
    char firstLetter = id.charAt(0);
    char upperCase = Character.toUpperCase(firstLetter);
    
    String alteredID = "";
    
    for (int i = 1; i < id.length(); i++) {
      alteredID += id.charAt(i);
    }
    
    return "" + upperCase + alteredID;
  }
  
  /**
   * The purpose of this method is to alter a piece ID so that the beginning
   * letter is lower-case so that it can be used as an FX ID to reference
   * (update position or delete) the piece on the GUI view.
   * @param id Takes a piece ID.
   * @return Returns a string with the beginning letter of the ID in lower case.
   */
  public static String idToLower(String id) {
    char firstLetter = id.charAt(0);
    char lowerCase = Character.toLowerCase(firstLetter);
    String convertToID = "#" + lowerCase;
  
    for (int i = 1; i < id.length(); i++) {
      convertToID += id.charAt(i);
    }
    
    System.out.println("Convert to ID is: " + convertToID);
    return convertToID;
  }
  
  public static void main(String[] args) throws InvalidPlayerException, InvalidPieceException {
    new Controller();
    
  }
}
