package notpaint.ui;

import java.io.File;
import java.io.IOException;

import notpaint.core.Brushes.CircleBrush;
import notpaint.core.Brushes.SquareBrush;
import notpaint.ui.PaintTools.Tool;
import notpaint.ui.PaintTools.EraserTool;
import notpaint.ui.PaintTools.PenTool;
import notpaint.core.PaintSettings;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import notpaint.core.Persistence.*;

/**
 * Controller for the view that handles the painting.
 * Supports drawing on a canvas with different tools and brushes.
 */
public class PaintController {

    @FXML
    Circle circleSmall, circleMedium, circleBig;

    @FXML
    Rectangle squareSmall, squareMedium, squareBig;

    @FXML
    Canvas drawingCanvas;

    PaintSettings settings;

    Tool selectedTool;

    private Persistence persistence;
    private FileChooser chooser;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void initialize() {
        // Set the default settings and tools
        settings = new PaintSettings();

        settings.setColor(Color.BLACK);
        selectedTool = new PenTool(settings);
        setCircleBrush(10);

        // Create event handlers for brush changes
        circleSmall.setOnMouseClicked(e -> setCircleBrush(5));
        circleMedium.setOnMouseClicked(e -> setCircleBrush(10));
        circleBig.setOnMouseClicked(e -> setCircleBrush(17));

        squareSmall.setOnMouseClicked(e -> setSquareBrush(5));
        squareMedium.setOnMouseClicked(e -> setSquareBrush(10));
        squareBig.setOnMouseClicked(e -> setSquareBrush(17));

        // Init file chooser settings. TODO: Remove when moving to REST API
        persistence = new LocalPersistence();

        chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PNG Image", "*.png"));
    }

    /**
     * Set the brush to be a circle
     * 
     * @param size Radius of the brush
     */
    private void setCircleBrush(int size) {
        settings.setBrush(new CircleBrush(size));
    }

    /**
     * Set the brush to be a square
     * 
     * @param size 'Radius' of the brush (half of the square side length)
     */
    private void setSquareBrush(int size) {
        settings.setBrush(new SquareBrush(size));
    }

    @FXML
    private void handleCavasClick(MouseEvent event) {
        selectedTool.Paint(drawingCanvas, (int) event.getX(), (int) event.getY());
    }

    @FXML
    private void setToolEraser() {
        selectedTool = new EraserTool(settings);
    }

    @FXML
    private void setToolPen() {
        selectedTool = new PenTool(settings);
    }

    @FXML
    private void clearCanvas() {
        drawingCanvas.getGraphicsContext2D().clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        initialize();
    }

    @FXML
    private void save() {
        File file = chooser.showSaveDialog(null);
        if (file == null)
            return;

        WritableImage image = new WritableImage((int) drawingCanvas.getWidth(), (int) drawingCanvas.getHeight());
        drawingCanvas.snapshot(new Callback<SnapshotResult, Void>() {
            @Override
            public Void call(SnapshotResult arg0) {
                System.out.println("Saving to path: " + file.toString());
                try {
                    persistence.Save(image, file.toString());
                } catch (IOException e) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setTitle("Failed to save image!");
                    error.setContentText(e.getMessage());
                    error.showAndWait();
                }
                return null;
            }
        }, new SnapshotParameters(), image);
    }

    @FXML
    private void load() {
        File file = chooser.showOpenDialog(null);
        if (file == null)
            return;

        System.out.println("Loading image at path: " + file.toURI().toString());
        Image loadedImage = persistence.Load(file.toURI().toString());
        drawingCanvas.getGraphicsContext2D().drawImage(loadedImage, 0, 0);
    }

}
