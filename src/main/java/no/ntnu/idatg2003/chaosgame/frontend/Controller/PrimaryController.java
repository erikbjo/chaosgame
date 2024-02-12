package no.ntnu.idatg2003.chaosgame.frontend.Controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import no.ntnu.idatg2003.chaosgame.backend.Vector2D;
import no.ntnu.idatg2003.chaosgame.frontend.alert.ConfirmationAlert;

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
    @FXML private Slider cImSlider;
    @FXML private Slider cReSlider;
    @FXML private Slider iterationSlider;
    // Assuming these properties for the JuliaTransform

    private AnimationTimer animationTimer;
    private double scaleFactor = 1.0;
    private double xMin = -1.5;
    private double xMax = 1.5;
    private double yMin = -1.5;
    private double yMax = 1.5;

    @FXML
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(mainCanvas != null) {
            animationTimer = new AnimationTimer() {
                long lastUpdate = 0;

                @Override
                public void handle(long now) {
                    if ((now - lastUpdate) >= 22_000_000) {
                        lastUpdate = now;
                        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
                        drawSierpinski(gc);
                    }
                }
            };
        }
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
        //drawJuliaSet(mainCanvas.getGraphicsContext2D(), ITERATIONS);
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        drawSierpinski(gc);
    }

    public void drawSierpinski(GraphicsContext gc) {
        int iterations = (int) iterationSlider.getValue() * 10; // Casting to int since iterations should be an integer.
        double scaleFactor = cImSlider.getValue();

        Vector2D canvasSize = new Vector2D(mainCanvas.getWidth(), mainCanvas.getHeight());
        gc.clearRect(0, 0, canvasSize.getX0(), canvasSize.getX1());

        Vector2D point1 = new Vector2D(0.0, 0.0);
        Vector2D point2 = new Vector2D(canvasSize.getX0() / scaleFactor, 0.0);
        Vector2D point3 = new Vector2D(canvasSize.getX0() / scaleFactor / 2, canvasSize.getX1() / scaleFactor);
        Vector2D[] points = new Vector2D[]{point1, point2, point3};

        Vector2D currentPoint = new Vector2D(canvasSize.getX0() / scaleFactor / 2, canvasSize.getX1() / scaleFactor / 2);

        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            Vector2D targetPoint = points[random.nextInt(3)];
            Vector2D midPoint = currentPoint.add(targetPoint).scalarMultiply(0.5);
            gc.setFill(Color.BLUE);
            gc.fillRect(midPoint.getX0() * scaleFactor, midPoint.getX1() * scaleFactor, 1, 1);
            currentPoint = midPoint;
        }
    }

    public void drawJuliaSet(GraphicsContext gc) {
        int iterations = (int) iterationSlider.getValue();
        if (gc == null || mainCanvas == null || scaleFactor <= 0 || iterations <= 0 || xMin == xMax || yMin == yMax) {
            System.out.println("Returning due to invalid parameters or null values...");
            return;
        }

        Vector2D canvasSize = new Vector2D(mainCanvas.getWidth(), mainCanvas.getHeight());

        if (canvasSize.getX0() <= 0 || canvasSize.getX1() <= 0) {
            System.out.println("Returning due to invalid canvas size...");
            return;
        }

        gc.clearRect(0, 0, canvasSize.getX0(), canvasSize.getX1());

        for (double pixelX = 0; pixelX < canvasSize.getX0(); pixelX++) {
            double zx = pixelX * (xMax - xMin) / canvasSize.getX0() + xMin;

            for (double pixelY = 0; pixelY < canvasSize.getX1(); pixelY++) {
                double zy = pixelY * (yMax - yMin) / canvasSize.getX1() + yMin;
                Vector2D complex = new Vector2D(zx, zy);

                int iteration;

                for (iteration = 0; iteration < iterations && Math.pow(complex.getX0(), 2) + Math.pow(complex.getX1(), 2) <= 4; iteration++) {
                    double newRe = complex.getX0() * complex.getX0() - complex.getX1() * complex.getX1() + cReSlider.getValue();
                    double newIm = 2.0 * complex.getX0() * complex.getX1() + cImSlider.getValue();
                    complex = new Vector2D(newRe, newIm);
                }

                gc.setFill(iteration == iterations ? Color.BLACK : Color.gray(1.0 - ((double)iteration / iterations)));
                gc.fillRect(pixelX, pixelY, scaleFactor, scaleFactor);
            }
        }
    }

    @FXML
    public void sliderChanged() {
        animationTimer.stop();
        animationTimer.start();
    }
}
