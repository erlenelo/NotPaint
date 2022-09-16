package gr2213;

import java.io.IOException;

import gr2213.Brushes.CircleBrush;
import gr2213.Brushes.SquareBrush;
import gr2213.PaintTools.PenTool;
import gr2213.PaintTools.Tool;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PrimaryController {

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
        settings = new PaintSettings();
        settings.setColor(Color.BLACK);
        selectedTool = new PenTool(settings);

        circleSmall.setOnMouseClicked(e -> setCircleBrush(5));
        circleMedium.setOnMouseClicked(e -> setCircleBrush(10));
        circleBig.setOnMouseClicked(e -> setCircleBrush(17));
        
        squareSmall.setOnMouseClicked(e -> setSquareBrush(5));
        squareMedium.setOnMouseClicked(e -> setSquareBrush(10));
        squareBig.setOnMouseClicked(e -> setSquareBrush(17));
    }

    private void setCircleBrush(int size) {
        settings.setBrush(new  CircleBrush(size));
    }

    private void setSquareBrush(int size) {
        settings.setBrush(new  SquareBrush(size));
    }

    @FXML
    private void handleCavasClick(MouseEvent event) {
        selectedTool.Paint(drawingCanvas, (int)event.getX(), (int)event.getY());
    }
}
