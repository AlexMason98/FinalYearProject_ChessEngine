package application;

import java.io.IOException;

import application.exceptions.InvalidPieceException;
import application.exceptions.InvalidPlayerException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
 
  public static Scene scene;
  
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Chessboard.fxml"));
    Pane pane = loader.load();
    
    scene = new Scene(pane);
    stage.setTitle("Chess - By Alex Mason - Alpha v1.0");
    stage.setScene(scene);
    stage.show();
    
  }

  public static void main(String[] args) throws InvalidPieceException, InvalidPlayerException {
    launch(args);
  }
}
