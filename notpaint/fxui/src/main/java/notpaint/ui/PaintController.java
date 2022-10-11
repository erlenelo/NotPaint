package notpaint.ui;

import java.io.File;
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
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
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

    @FXML Text countDown, wordToDrawText;

    @FXML
    ColorPicker colorPicker;

    PaintSettings settings;

    Tool selectedTool;

    private Persistence persistence;
    private FileChooser chooser;
    private GameInfoPersistence gameInfoPersistence;
    private GameInfo gameInfo;
    private Integer countDownSecondsLeft;
    private Timer countDownTimer;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
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
        persistence = new LocalPersistence();
        
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
                    throw new IllegalArgumentException("Loaded PaintController but gameInfo was not set in stage");

                System.out.println("GameInfo loaded: " + gameInfo.toString());

                wordToDrawText.setText(gameInfo.getWord());

                countDownSecondsLeft = gameInfo.getSecondsPerRound();

                countDownTimer = new Timer();
                TimerTask countDownTask = new TimerTask() {
                    @Override
                    public void run() {
                        countDownSecondsLeft -= 1;
                        if(countDownSecondsLeft < 0) {
                            finishDrawing();
                        }else {
                            countDown.setText(countDownSecondsLeft.toString());
                        }
                        
                    }
                };

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

        try {
            gameInfoPersistence.saveGameInfo(gameInfo);  
            App.setRoot("secondary");
        } catch(IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Error occured while saving drawing.");
            alert.setHeaderText("ERROR");
            alert.show();
        }
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
    private void updatePaintColor() {
        settings.setColor(colorPicker.getValue());
    }
    // @FXML
    // private void save() {
    //     File file = chooser.showSaveDialog(null);
    //     if (file == null)
    //         return;

    //     WritableImage image = new WritableImage((int) drawingCanvas.getWidth(), (int) drawingCanvas.getHeight());
    //     drawingCanvas.snapshot(new Callback<SnapshotResult, Void>() {
    //         @Override
    //         public Void call(SnapshotResult arg0) {
    //             System.out.println("Saving to path: " + file.toString());
    //             try {
    //                 persistence.Save(image, file.toString());
    //             } catch (IOException e) {
    //                 Alert error = new Alert(AlertType.ERROR);
    //                 error.setTitle("Failed to save image!");
    //                 error.setContentText(e.getMessage());
    //                 error.showAndWait();
    //             }
    //             return null;
    //         }
    //     }, new SnapshotParameters(), image);
    // }

    // @FXML
    // private void load() {
    //     File file = chooser.showOpenDialog(null);
    //     if (file == null)
    //         return;

    //     System.out.println("Loading image at path: " + file.toURI().toString());
    //     Image loadedImage = persistence.Load(file.toURI().toString());
    //     drawingCanvas.getGraphicsContext2D().drawImage(loadedImage, 0, 0);
    // }

}
