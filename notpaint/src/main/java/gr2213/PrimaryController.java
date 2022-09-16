package gr2213;

import java.io.IOException;

import gr2213.Brushes.CircleBrush;
import gr2213.Brushes.SquareBrush;
import gr2213.PaintTools.EraserTool;
import gr2213.PaintTools.PenTool;
import gr2213.PaintTools.Tool;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    }

    /**
     * Set the brush to be a circle
     * @param size Radius of the brush
     */
    private void setCircleBrush(int size) {
        settings.setBrush(new  CircleBrush(size));
    }

    /**
     * Set the brush to be a square
     * @param size 'Radius' of the brush  (half of the square side length)
     */
    private void setSquareBrush(int size) {
        settings.setBrush(new  SquareBrush(size));
    }

    @FXML
    private void handleCavasClick(MouseEvent event) {   
        selectedTool.Paint(drawingCanvas, (int)event.getX(), (int)event.getY());
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

    
}
