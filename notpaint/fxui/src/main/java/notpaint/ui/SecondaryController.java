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
        
        secondaryButton.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
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