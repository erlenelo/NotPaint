package notpaint.ui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import notpaint.core.Brushes.CircleBrush;
import notpaint.core.Brushes.SquareBrush;
import notpaint.ui.PaintTools.Tool;
import notpaint.ui.PaintTools.EraserTool;
import notpaint.ui.PaintTools.PenTool;
import notpaint.core.GameInfo;
import notpaint.core.PaintSettings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import notpaint.core.Persistence.GameInfoPersistence;
import notpaint.ui.Persistence.*;
import notpaint.ui.Util.AlertUtil;

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

    @FXML Text countDown, wordToDrawText;

    @FXML
    ColorPicker colorPicker;

    PaintSettings settings;

    private Tool selectedTool;

   
    private FileChooser chooser;
    private GameInfoPersistence gameInfoPersistence;
    private ImagePersistence imagePersistence;
    private GameInfo gameInfo;
    private Integer countDownSecondsLeft;
    private Timer countDownTimer;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("GameSelectView");
    }

    @FXML
    public void initialize() {
        // Set the default settings and tools

        loadGameInfo();
        
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
        imagePersistence = new LocalImagePersistence();
        
        colorPicker.setValue(Color.BLACK);

        chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PNG Image", "*.png"));
    }


    private void loadGameInfo() {        
        drawingCanvas.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(newScene != null) {
                Stage stage = (Stage)newScene.getWindow();
                gameInfoPersistence = (GameInfoPersistence)stage.getUserData();
                if(gameInfoPersistence == null)
                    throw new IllegalStateException("Stage has no gameInfoPersistence set");

                gameInfo = gameInfoPersistence.getActiveGameInfo();

                if(gameInfo == null) 
                    throw new IllegalArgumentException("Loaded PaintController but activeGameInfo was not set in stage");

                System.out.println("GameInfo loaded: " + gameInfo.toString());

                // Load the current image into the canvas, unless this is the first iteration. In that case there is no image.
                if(gameInfo.getCurrentIterations() > 0) {
                    loadImage(gameInfo.getImagePath());
                }

                wordToDrawText.setText(gameInfo.getWord());

                countDownSecondsLeft = gameInfo.getSecondsPerRound();

                countDownTimer = new Timer();

                // Create a timer task that decreases and keeps track of the remaining time.
                TimerTask countDownTask = new TimerTask() {
                    @Override
                    public void run() {
                        countDownSecondsLeft -= 1;
                        if(countDownSecondsLeft < 0) {     
                            // finishDrawing() is not safe to run from a separate thread.
                            // Platform.runLater schedules the function to be run on the main UI thread.
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    finishDrawing();
                                }
                            });                            
                        }else {
                            countDown.setText(countDownSecondsLeft.toString());
                        }                        
                    }
                };
                // Run timer once every second.
                countDownTimer.schedule(countDownTask, 1000, 1000);
            }
        });
    }
    @FXML
    private void finishDrawing()  {
        if(countDownTimer != null)
            countDownTimer.cancel();
            
        gameInfo.addIteration("UnknownEditor");
        //TODO: Save gameinfo and image to json and png respectively
        saveImageToPath(gameInfo.getImagePath());

        try {
            gameInfoPersistence.saveGameInfo(gameInfo);  
            App.setRoot("GameSelectView");
        } catch(IOException ex) {
            ex.printStackTrace();
            AlertUtil.ErrorAlert("ERROR", "Error occured while saving drawing.");
        }
    }
    private void saveImageToPath(String path) {
        WritableImage image = new WritableImage((int) drawingCanvas.getWidth(), (int) drawingCanvas.getHeight());
        image = drawingCanvas.snapshot(new SnapshotParameters(), image);    
        System.out.println("Saving to path: " + path);
        try {
            imagePersistence.save(image, path);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.ErrorAlert("ERROR","Failed to save image!");
        }
    }

    private void loadImage(String path) {
        Image loadedImage = imagePersistence.load(path);
        drawingCanvas.getGraphicsContext2D().drawImage(loadedImage, 0, 0);
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
        selectedTool.paint(drawingCanvas, (int) event.getX(), (int) event.getY());
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
        loadImage(gameInfo.getImagePath());
    }
    @FXML
    private void updatePaintColor() {
        settings.setColor(colorPicker.getValue());
    }

}
