package notpaint.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import notpaint.core.Persistence.GameInfoPersistence;

public class SecondaryController {

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