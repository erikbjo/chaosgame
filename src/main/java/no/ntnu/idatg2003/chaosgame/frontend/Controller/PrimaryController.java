package no.ntnu.idatg2003.chaosgame.frontend.Controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.ntnu.idatg2003.chaosgame.backend.Complex;
import no.ntnu.idatg2003.chaosgame.backend.JuliaTransform;
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
    // Assuming these properties for the JuliaTransform
    private Vector2D original = new Vector2D(0.0, 0.0);
    private final Complex point = new Complex(1.0, 1.0);
    private final JuliaTransform juliaTransform = new JuliaTransform(point, 1);
    private final double FACTOR = 0.01;
    private final double SCALE = 100;
    private AnimationTimer animationTimer;
    @FXML
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    // Store the old transformed vector
                    Vector2D oldTransformed = new Vector2D(original.getX0(), original.getX1());

                    // Transform original Vector2D
                    Vector2D transformed = juliaTransform.transform(original);

                    double centerX = mainCanvas.getWidth() / 2;
                    double centerY = mainCanvas.getHeight() / 2;

                    double oldDrawX = centerX + SCALE * FACTOR * oldTransformed.getX0();
                    double oldDrawY = centerY + SCALE * FACTOR * oldTransformed.getX1();

                    double newDrawX = centerX + SCALE * transformed.getX0();
                    double newDrawY = centerY + SCALE * transformed.getX1();

                    // Verify that we are within the bounds of the canvas
                    if(newDrawX >= 0 && newDrawX <= mainCanvas.getWidth()
                            && newDrawY >= 0 && newDrawY <= mainCanvas.getHeight()) {
                        // Draw the transformed vector
                        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
                        gc.setStroke(Color.BLUE);
                        gc.setLineWidth(2.0);
                        gc.strokeLine(oldDrawX, oldDrawY, newDrawX, newDrawY);
                        System.out.printf("Drawing line from (%f, %f) to (%f, %f)\n", oldDrawX, oldDrawY, newDrawX, newDrawY);
                    }

                    // Change original
                    original = transformed;
                }
        };
    }

    @FXML
    public void quitApplication(ActionEvent event) {
        ConfirmationAlert confirmationAlert =
                new ConfirmationAlert("Exit", "Are you sure you want to exit?");
        if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        } else {
            confirmationAlert.close();
        }
    }

    @FXML
    public void startApplication(ActionEvent event) {
        animationTimer.start();
        System.out.println("Starting application");
        System.out.printf(animationTimer.toString());
    }

    @FXML
    public void drawToCanvas(ActionEvent event) {

    }
}
