package no.ntnu.idatg2003.chaosgame.frontend.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import no.ntnu.idatg2003.chaosgame.frontend.alert.ConfirmationAlert;
import org.springframework.stereotype.Indexed;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * The primary controller for the application.
 * This class is responsible for handling the primary view of the application.
 * It is also responsible for handling user input and updating the view.
 */
public class PrimaryController implements Initializable {

    @FXML private Button quitApplicationButton;
    @FXML private Button startApplicationButton;
    @FXML private Pane mainPane;
    @FXML
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void quitApplication(ActionEvent event) {
        ConfirmationAlert confirmationAlert =
                new ConfirmationAlert("Exit", "Are you sure you want to exit?");
        if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
        else {
            confirmationAlert.close();
        }
    }
    @FXML
    public void startApplication() {
        printToPane();
    }

    @FXML
    public void printToPane() {
        TextArea fa = new TextArea("Hello, World!");
        fa.setEditable(false);
        mainPane.getChildren().add(fa);

    }
}
