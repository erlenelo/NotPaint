package notpaint.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import notpaint.persistence.GameInfoPersistence;
import notpaint.ui.util.AlertUtil;

/**
 * Controller for making an username.
 * Saves the username if the user choose to be remembered.
 */
public class UsernameSelectController {

    @FXML
    private TextField setUsernameTextField;

    @FXML
    private RadioButton yesRadioButton;

    @FXML
    private Button doneButton;

    @FXML
    ToggleGroup newWordToggleGroup;

    GameInfoPersistence gameInfoPersistence;

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
                    newScene.windowProperty().addListener((observableWindow,
                            oldWindow, newWindow) -> {

                        // Create a persistence instance and set it as the user data for the stage.
                        // This makes it accessible from all other scenes.
                        if (newWindow != null) {
                            onStageLoaded((Stage) newWindow);
                        }
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

        try {
            if (readUsernameFile() == true) {
                rememberMeSelected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void rememberMeSelected() {
        handleRememberMe();
        try {
            readUsernameFile();
            App.setRoot("GameSelectView");
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error opening GameSelectView");
        }
    }

    private void handleRememberMe() {
        if (yesRadioButton.isSelected()) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter("usernameFile.txt", Charset.forName("UTF-8"), true)))) {
                out.print(setUsernameTextField.getText());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private boolean readUsernameFile() throws IOException {

        File file = new File("usernameFile.txt");
        String usernameFileString = "";
        StringBuilder sb = new StringBuilder();

        if (file.length() == 0) {
            return false;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file,
                    Charset.forName("UTF-8")))) {

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            usernameFileString = sb.toString();
            gameInfoPersistence.setUsername(usernameFileString);
        }
        return true;
    }

    @FXML
    private void createUsername(ActionEvent event) throws IOException {

        String username = setUsernameTextField.getText();

        if (username.isEmpty()) {
            AlertUtil.warningAlert("Warning", "Please enter a username");
            return;
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            stage.close();
            GameInfoPersistence gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
            gameInfoPersistence.setUsername(username);
            handleRememberMe();
            App.setRoot("GameSelectView");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error opening GameSelectView");
        }

    }

}
