package no.ntnu.idatg2003.chaosgame.frontend.Controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import no.ntnu.idatg2003.chaosgame.backend.AffineTransform2D;
import no.ntnu.idatg2003.chaosgame.backend.Matrix2x2;
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
    @FXML private ScrollPane scrollPane;
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
    // Instance variables
    private double startX;
    private double startY;
    private AffineTransform2D f1;
    private AffineTransform2D f2;
    private AffineTransform2D f3;
    private AffineTransform2D f4;

    @FXML
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeMatrix();

        animationTimer = new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                mainCanvas.setOnMousePressed(event -> {
                    startX = event.getX();
                    startY = event.getY();
                });

                mainCanvas.setOnMouseDragged(event -> {
                    double endX = event.getX();
                    double endY = event.getY();

                    double dx = startX - endX;
                    double dy = startY - endY;

                    double xRange = xMax - xMin;
                    double yRange = yMax - yMin;

                    xMin += dx / mainCanvas.getWidth() * xRange;
                    xMax += dx / mainCanvas.getWidth() * xRange;

                    yMin += dy / mainCanvas.getHeight() * yRange;
                    yMax += dy / mainCanvas.getHeight() * yRange;

                    // Save the current position for the next drag event
                    startX = endX;
                    startY = endY;

                    // Refresh the drawing
                    drawFern(mainCanvas.getGraphicsContext2D());
                });


                mainCanvas.setOnScroll(event -> {
                    double scaleFactor = event.getDeltaY() > 0 ? 1.1 : 1 / 1.1; // zoom in if scroll up, else zoom out
                    double mouseX = Math.max(0, Math.min(event.getX(), mainCanvas.getWidth() - 1));
                    double mouseY = Math.max(0, Math.min(event.getY(), mainCanvas.getHeight() - 1));

                    double centerX = xMin + mouseX / mainCanvas.getWidth() * (xMax - xMin);
                    double centerY = yMin + mouseY / mainCanvas.getHeight() * (yMax - yMin);

                    zoom(scaleFactor, centerX, centerY);
                });
            }
        };
        iterationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // the slider value has changed, redraw the fern
            drawFern(mainCanvas.getGraphicsContext2D());
        });

        cImSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // the slider value has changed, redraw the fern
            drawFern(mainCanvas.getGraphicsContext2D());
        });

        cReSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // the slider value has changed, redraw the fern
            drawFern(mainCanvas.getGraphicsContext2D());
        });
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
        drawJuliaSet(gc);
    }

    public void drawSierpinski(GraphicsContext gc) {
        int iterations = (int) iterationSlider.getValue() * 100; // Casting to int since iterations should be an integer.
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
        double iterations = iterationSlider.getValue();
        double cRe = cReSlider.getValue();
        double cIm = cImSlider.getValue();
        double xRange = xMax - xMin;
        double yRange = yMax - yMin;
        double canvasWidth = mainCanvas.getWidth();
        double canvasHeight = mainCanvas.getHeight();

        if (gc == null || mainCanvas == null || scaleFactor <= 0 || iterations <= 1) {
            System.out.println("Returning due to invalid parameters or null values...");
            return;
        }

        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        WritableImage image = new WritableImage((int)canvasWidth, (int)canvasHeight);
        PixelWriter pixelWriter = image.getPixelWriter();

        for (double pixelX = 0; pixelX < canvasWidth; pixelX++) {
            for (double pixelY = 0; pixelY < canvasHeight; pixelY++) {
                double zx = (pixelX / canvasWidth) * xRange + xMin;
                double zy = (pixelY / canvasHeight) * yRange + yMin;
                int iter = 0;

                while (zx*zx + zy*zy < 4 && iter < iterations) {
                    double tmp = zx*zx - zy*zy + cRe; // calculation of the real part

                    zy = 2 * zx * zy + cIm; // calculation of the imaginary part
                    zx = tmp;

                    iter++;
                }

                if (iter < iterations) {
                    pixelWriter.setColor((int)pixelX, (int)pixelY, Color.gray((double)iter/iterations));
                } else {
                    pixelWriter.setColor((int)pixelX, (int)pixelY, Color.BLACK);
                }
            }
        }

        gc.drawImage(image, 0, 0, canvasWidth, canvasHeight);
    }

    @FXML
    public void sliderChanged() {
        animationTimer.stop();
        animationTimer.start();
    }


    // method to stop and start animation
    public void restartAnimation() {
        animationTimer.stop();
        animationTimer.start();
    }

    public void zoom(double scaleFactor, double centerX, double centerY) {
        double scaleUpperBound = 100.0; // the maximum zoom-in level
        double scaleLowerBound = 0.01; // the maximum zoom-out level

        scaleFactor = Math.max(scaleFactor, scaleLowerBound);
        scaleFactor = Math.min(scaleFactor, scaleUpperBound);

        double halfWidth = (xMax - xMin) / 2.0;
        double halfHeight = (yMax - yMin) / 2.0;

        xMin = centerX - halfWidth / scaleFactor;
        xMax = centerX + halfWidth / scaleFactor;

        yMin = centerY - halfHeight / scaleFactor;
        yMax = centerY + halfHeight / scaleFactor;
    }

    public void makeMatrix() {
        f1 = new AffineTransform2D(new Matrix2x2(0.00, 0.00, 0.00, 0.16), new Vector2D(0.00, 0.00)); // match exact constants with your case
        f2 = new AffineTransform2D(new Matrix2x2(0.85, 0.04, -0.04, 0.85), new Vector2D(0.00, 1.60));
        f3 = new AffineTransform2D(new Matrix2x2(0.20, -0.26, 0.23, 0.22), new Vector2D(0.00, 1.60));
        f4 = new AffineTransform2D(new Matrix2x2(-0.15, 0.28, 0.26, 0.24), new Vector2D(0.00, 0.44));
    }

    public void drawFern(GraphicsContext gc) {
        int iterations = (int) iterationSlider.getValue() * 1000;
        double scaleFactor = cImSlider.getValue() * 1000;

        Vector2D canvasSize = new Vector2D(mainCanvas.getWidth(), mainCanvas.getHeight());
        gc.clearRect(0, 0, canvasSize.getX0(), canvasSize.getX1());

        Vector2D currentPoint = new Vector2D(canvasSize.getX0() / scaleFactor / 2, canvasSize.getX1() / scaleFactor / 2);

        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            int randomInt = random.nextInt(100);
            if (randomInt < 1) {
                currentPoint = f1.transform(currentPoint);
            } else if (randomInt < 86) {
                currentPoint = f2.transform(currentPoint);
            } else if (randomInt < 93) {
                currentPoint = f3.transform(currentPoint);
            } else {
                currentPoint = f4.transform(currentPoint);
            }
            Color color = Color.color(Math.random(), Math.random(), Math.random());
            gc.setFill(color);
            double drawX = (currentPoint.getX0() * scaleFactor) + (canvasSize.getX0() / 2);
            double drawY = (canvasSize.getX1()) - (currentPoint.getX1() * scaleFactor);
            gc.fillRect(drawX, drawY, 1, 1);
        }
    }
}
