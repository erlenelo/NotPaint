package notpaint.ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import notpaint.core.Persistence.GameInfoPersistence;
import notpaint.core.Persistence.IUsername;
import notpaint.core.Persistence.Username;
import notpaint.ui.Util.AlertUtil;



public class UsernameSelectController {
    
    @FXML
    private TextField setUsernameTextField;

    @FXML
    private RadioButton yesRadioButton, NoRadioButton;

    @FXML
    private Button doneButton;

    @FXML
    ToggleGroup newWordToggleGroup;

    GameInfoPersistence gameInfoPersistence;

    private IUsername file = new Username();


    @FXML
    private void initialize() {
        // Get the scene from any Node object.
        // Because the scene is not set in initialize, we need to listen for the
        // property to update.
        setUsernameTextField.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (newScene != null) {
                var stage = newScene.getWindow();
                // The window property is also initially not set the first time the app starts.
                // If it is null, listen for the property to update an then set it
                if (stage == null) {
                    newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {

                        // Create a persistence instance and set it as the user data for the stage.
                        // This makes it accessible from all other scenes.
                        if (newWindow != null)
                            onStageLoaded((Stage) newWindow);
                    });
                } else {
                    onStageLoaded((Stage) stage);
                }
            }
        });
    }

    private void onStageLoaded(Stage stage) {
        if (stage.getUserData() == null) {
            stage.setUserData(new GameInfoPersistence());
        }
        gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
        
        if (yesRadioButton.isSelected()) {
            try {
                file.readUsername("usernameFile");
                App.setRoot("GameSelectView");
            } catch (IOException e) {
                e.printStackTrace();
                AlertUtil.errorAlert("ERROR", "Error opening GameSelectView");
            }
        }

    }
   
    
    @FXML
    public void createUsername(ActionEvent event) throws IOException {
        // File usernameFile = new File("username.txt");
        
        String username = setUsernameTextField.getText();
        
        if (username.isEmpty()) {
            AlertUtil.errorAlert("Username Required!", "Please enter a username");
            return;
        }

        // gameInfoPersistence.createFolder();
        // gameInfoPersistence.readUsernameFile();
        // gameInfoPersistence.addUsername(username);

        try {
            file.writeUsername("usernameFile", username);
        } catch (IllegalArgumentException e) {
            AlertUtil.errorAlert("ERROR!", "Something went wrong");
        } catch (IOException i) {
            AlertUtil.errorAlert("ERROR", "Could noe save file!");
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();


        try {
            stage.close();
            GameInfoPersistence gameInfoPersistence = (GameInfoPersistence)stage.getUserData();
            gameInfoPersistence.setUsername(username);
            App.setRoot("GameSelectView");            
            stage.show();
        } catch(IOException IOException) {
            IOException.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error opening GameSelectView");
        }

    }

    

    
   

    





}
