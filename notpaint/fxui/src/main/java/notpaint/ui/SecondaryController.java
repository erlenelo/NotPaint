package notpaint.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import notpaint.core.GameInfo;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import notpaint.core.Persistence.GameInfoPersistence;

public class SecondaryController {

    List<GameInfo> allGameInfos;
    HashMap<GameInfo, ImageView> screenMap;

    GameInfo info;

    @FXML
    private ScrollPane activeProjectsScrollPane, completedProjectsScrollPane;

    @FXML
    private TilePane activeTilePane, completedTilePane;



    public void addImageToActiveTap() {
        ImageView imageView = new ImageView(info.getImagePath());
        activeTilePane.getChildren().add(imageView);
    }

    public void deleteImageActiveTab() {
        activeTilePane.getChildren().clear();
    }

    public void addImageToCompletedTap() {
        if (info.isFinished() == true) {
            ImageView imageView = new ImageView(info.getImagePath());
            completedTilePane.getChildren().add(imageView);
            deleteImageActiveTab();
        }
        
    }
    @FXML
    private Button secondaryButton; // Used to get reference to scene

    @FXML
    private void initialize() {
        // Because the scene is not set initially in initialize, we need to wait for the property to update
        secondaryButton.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(newScene != null) {
                // The window property is also initially not set.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    // Create a persistence instance and set it as the user data for the stage.
                    // This makes it accessible from all other scenes. 
                    Stage stage = (Stage)newWindow;                
                    if(stage.getUserData() == null) {
                        stage.setUserData(new GameInfoPersistence());
                    }
                });
            }
        });
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("SettingsView");
        
    }
}