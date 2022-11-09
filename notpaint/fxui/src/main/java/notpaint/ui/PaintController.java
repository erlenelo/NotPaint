package notpaint.ui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import notpaint.core.brushes.CircleBrush;
import notpaint.core.brushes.SquareBrush;
import notpaint.persistence.GameInfo;
import notpaint.persistence.GameInfoPersistence;
import notpaint.ui.painttools.EraserTool;
import notpaint.ui.painttools.PenTool;
import notpaint.ui.painttools.Tool;
import notpaint.ui.persistence.ImagePersistence;
import notpaint.ui.persistence.LocalImagePersistence;
import notpaint.ui.util.AlertUtil;
import notpaint.ui.util.LineUtil;
import notpaint.ui.util.StageUtil;

/**
 * Controller for the view that handles the painting.
 * Supports drawing on a canvas with different tools and brushes.
 */
public class PaintController {

    @FXML
    Circle circleSmall;

    @FXML
    Circle circleMedium;

    @FXML
    Circle circleBig;

    @FXML
    Rectangle squareSmall;

    @FXML
    Rectangle squareMedium;

    @FXML
    Rectangle squareBig;

    @FXML
    Canvas drawingCanvas;

    @FXML
    Text countDown;

    @FXML
    Text wordToDrawText;

    @FXML
    ColorPicker colorPicker;

    PaintSettings settings;

    Tool selectedTool;
    @FXML
    StackPane pencilPane;
    @FXML
    StackPane eraserPane;

    private GameInfoPersistence gameInfoPersistence;
    private ImagePersistence imagePersistence;
    private GameInfo gameInfo;
    private Integer countDownSecondsLeft;
    private Timer countDownTimer;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("GameSelectView");
    }

    
    

    /**
     * Set the default settings and tools.
     */
    @FXML public void initialize() {
        
        StageUtil.onStageLoaded(drawingCanvas, this::onStageLoaded);

        settings = new PaintSettings();

        settings.setColor(Color.BLACK);
        selectedTool = new PenTool(settings);
        setCircleBrush(10);

        // Create event handlers for brush changes and highlight the selected brush/tool
        circleSmall.setOnMouseClicked(e -> {
            setCircleBrush(5);
            handleHighlightCircle(circleSmall);
        });
        circleMedium.setOnMouseClicked(e -> {
            setCircleBrush(10);
            handleHighlightCircle(circleMedium);
        });;
        circleBig.setOnMouseClicked(e -> {
            setCircleBrush(17);
            handleHighlightCircle(circleBig);
        });

        squareSmall.setOnMouseClicked(e -> {
            setSquareBrush(5);
            handleHighlightSquare(squareSmall);
            
        });
        squareMedium.setOnMouseClicked(e -> {
            setSquareBrush(10);
            handleHighlightSquare(squareMedium);
            
        });
        squareBig.setOnMouseClicked(e -> {
            setSquareBrush(17);
            handleHighlightSquare(squareBig);
        });
        eraserPane.setOnMouseClicked(e -> {
            handleEraserClick();
        });
        pencilPane.setOnMouseClicked(e -> {
            handlePencilClick();
        });
    
        

        imagePersistence = new LocalImagePersistence();

        colorPicker.setValue(Color.BLACK);
    }

    private void onStageLoaded(Stage stage) {
        gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
        if (gameInfoPersistence == null) {
            throw new IllegalStateException("Stage has no gameInfoPersistence set");
        }
        gameInfo = gameInfoPersistence.getActiveGameInfo();
        //
        if (gameInfo == null) {
            throw new IllegalArgumentException(
                "Loaded PaintController but activeGameInfo was not set in stage");
        }
        // Load the current image into the canvas, unless this is the first iteration.
        // In that case there is no image.
        if (gameInfo.getCurrentIterations() > 0) {
            loadImage(gameInfoPersistence.getImagePath(gameInfo));
        }

        wordToDrawText.setText(gameInfo.getWord());

        countDownSecondsLeft = gameInfo.getSecondsPerRound();

        countDownTimer = new Timer();

        // Create a timer task that decreases and keeps track of the remaining time.
        TimerTask countDownTask = new TimerTask() {
            @Override
            public void run() {
                countDownSecondsLeft -= 1;
                if (countDownSecondsLeft < 0) {
                    // finishDrawing() is not safe to run from a separate thread.
                    // Platform.runLater schedules the function to be run on the main UI thread.
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            finishDrawing();
                        }
                    });
                } else {
                    countDown.setText(countDownSecondsLeft.toString());
                }
            }
        };
        // Run timer once every second.
        countDownTimer.schedule(countDownTask, 1000, 1000);

    }

    @FXML
    private void finishDrawing() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        gameInfo.addIteration(gameInfoPersistence.getUsername());
        saveImageToPath(gameInfoPersistence.getImagePath(gameInfo));

        try {
            gameInfoPersistence.saveGameInfo(gameInfo);
            App.setRoot("GameSelectView");
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error occured while saving drawing.");
        }
    }

    void saveImageToPath(String path) {
        WritableImage image = new WritableImage(
            (int) drawingCanvas.getWidth(), (int) drawingCanvas.getHeight());
        image = drawingCanvas.snapshot(new SnapshotParameters(), image);

        try {
            imagePersistence.save(image, path);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Failed to save image!");
        }
    }

    private void loadImage(String path) {
        Image loadedImage = imagePersistence.load(path);
        drawingCanvas.getGraphicsContext2D().drawImage(loadedImage, 0, 0);
    }

    /**
     * Set the brush to be a circle.
     *
     * @param size Radius of the brush
     */
    private void setCircleBrush(int size) {
        settings.setBrush(new CircleBrush(size));
    }

    /**
     * Set the brush to be a square.
     *
     * @param size 'Radius' of the brush (half of the square side length)
     */
    private void setSquareBrush(int size) {
        settings.setBrush(new SquareBrush(size));
    }

    private int lastX;
    private int lastY;

    @FXML
    private void handleCavasClick(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        
        // If we are dragging the mouse, we might have moved over some pixels since the last event.
        // Paint on all pixels between the last event and this one.
        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            var pixels = LineUtil.getAllPixelsBetween(lastX, lastY, x, y);
            for (var pixel : pixels) {
                selectedTool.paint(drawingCanvas, pixel.getKey(), pixel.getValue());
            }
        } else {
            selectedTool.paint(drawingCanvas, x, y);
        }

        lastX = x;
        lastY = y;
    }
    
    @FXML // Highlight the selected circle brush, clear other highlights
    private void handleHighlightCircle(Circle circle) {
            circle.setId("highlightedBrush");
            if (circle == circleSmall) {
                circleMedium.setId("clear");
                circleBig.setId("clear");
                squareBig.setId("clear");
                squareMedium.setId("clear");
                squareSmall.setId("clear");
            } else if (circle == circleMedium) {
                circleSmall.setId("clear");
                circleBig.setId("clear");
                squareBig.setId("clear");
                squareMedium.setId("clear");
                squareSmall.setId("clear");
            } else if (circle == circleBig) {
                circleSmall.setId("clear");
                circleMedium.setId("clear");
                squareBig.setId("clear");
                squareMedium.setId("clear");
                squareSmall.setId("clear");
            }
        }
    @FXML // Highlight the selected square brush, clear other highlights
    private void handleHighlightSquare(Rectangle rectangle) {
            rectangle.setId("highlightedBrush");
            if (rectangle == squareSmall) {
                squareMedium.setId("clear");
                squareBig.setId("clear");
                circleBig.setId("clear");
                circleMedium.setId("clear");
                circleSmall.setId("clear");
            } else if (rectangle == squareMedium) {
                squareSmall.setId("clear");
                squareBig.setId("clear");
                circleBig.setId("clear");
                circleMedium.setId("clear");
                circleSmall.setId("clear");
            } else if (rectangle == squareBig) {
                squareSmall.setId("clear");
                squareMedium.setId("clear");
                circleBig.setId("clear");
                circleMedium.setId("clear");
                circleSmall.setId("clear");
            }
            
        }
        // Tool selection highlights
    @FXML
    private void handleEraserClick() {
        eraserPane.setId("toolPaneHighlight");
        pencilPane.setId("toolPane");
        }
    
    @FXML
    private void handlePencilClick() {
        pencilPane.setId("toolPaneHighlight");
        eraserPane.setId("toolPane");
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
    private void resetCanvas() {
        drawingCanvas.getGraphicsContext2D().clearRect(
            0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        loadImage(gameInfoPersistence.getImagePath(gameInfo));
    }

    @FXML
    private void updatePaintColor() {
        settings.setColor(colorPicker.getValue());
    }

}
