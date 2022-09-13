package gr2213;

import java.io.IOException;

import gr2213.Brushes.*;
import gr2213.PaintTools.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PrimaryController {

    @FXML Canvas drawingCanvas;

    private PaintSettings settings;
    private PaintTool tool;
    @FXML
    void initialize(){

        System.out.println("Init");
        // Make canvas completely white
        var gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight() );
        
        // Create settings and tool
        settings = new PaintSettings();
        settings.setBrush(new SquareBrush(20));
        settings.setColor(Color.BLUE); 
        tool = new PenTool(settings);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML void handleCanvasClick(MouseEvent evt) {
        tool.Paint(drawingCanvas, (int)evt.getX(), (int)evt.getY());
    }
}
