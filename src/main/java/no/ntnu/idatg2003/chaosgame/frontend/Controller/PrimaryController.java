package no.ntnu.idatg2003.chaosgame.frontend.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import no.ntnu.idatg2003.chaosgame.backend.Vector2D;
import no.ntnu.idatg2003.chaosgame.frontend.alert.ConfirmationAlert;
import org.springframework.stereotype.Indexed;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * The primary controller for the application.
 * This class is responsible for handling the primary view of the application.
 * It is also responsible for handling user input and updating the view.
 */
public class PrimaryController implements Initializable {

    @FXML private Button quitApplicationButton;
    @FXML private Button startApplicationButton;
    @FXML private Canvas mainCanvas;
    private Random randomX;
    private Random randomY;
    private Vector2D vector;
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
    public void startApplication(ActionEvent event) {
        drawToCanvas(event);
    }

    @FXML
    public void drawToCanvas(ActionEvent event) {
        randomX = new Random();
        randomY = new Random();
        System.out.println(randomX.nextInt() + randomY.nextInt());
        vector = new Vector2D(randomX.nextInt(1000)+1,randomY.nextInt(1000)+1);
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        gc.beginPath();
        gc.moveTo(vector.getX0(),vector.getX1());
        gc.lineTo(vector.getLength(),vector.getLength());
        gc.stroke();
    }
}
