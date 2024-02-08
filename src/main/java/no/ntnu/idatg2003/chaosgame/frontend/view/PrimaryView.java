package no.ntnu.idatg2003.chaosgame.frontend.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class PrimaryView extends Application {

      /**
    * Main method that runs the application and JavaFx.
    *
    * @param args The command line arguments.
    */
      public static void mainApp(String[] args) {
     launch(args);
      }

      @Override
      public void start(Stage primaryStage) throws Exception {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Chaos Game");
            primaryStage.setScene(scene);
            primaryStage.show();
     throw new UnsupportedOperationException("Not supported yet.");
      }
}
