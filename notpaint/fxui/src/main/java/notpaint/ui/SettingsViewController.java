package notpaint.ui;

import java.io.IOException;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import notpaint.persistence.GameInfo;
import notpaint.persistence.GameInfoPersistence;
import notpaint.ui.util.AlertUtil;

/**
 * Controller for the view that handles the settings when configuring a new game.
 */
public class SettingsViewController {

    @FXML
    TextField setTimeTextField;

    @FXML
    TextField maxIterationsTextField;

    @FXML
    RadioButton checkboxYes;

    @FXML
    RadioButton checkboxNo;

    @FXML
    ToggleGroup newWordToggleGroup;

    /**
     * Configure the controller to have integer filters for the text fields.
     */
    @FXML
    public void initialize() {

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        setTimeTextField.setTextFormatter(new TextFormatter<Integer>(integerFilter));
        maxIterationsTextField.setTextFormatter(new TextFormatter<Integer>(integerFilter));
    }


    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("GameSelectView");
    }

    @FXML
    private void createGame(ActionEvent event) {
        
        int maxIterations = Integer.parseInt(maxIterationsTextField.getText());
        int secondsPerRound = Integer.parseInt(setTimeTextField.getText());
        
        boolean newWordEachRound = checkboxYes.isSelected();        
    
        GameInfo newGameInfo = new GameInfo(maxIterations, secondsPerRound, newWordEachRound);
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            stage.close();
            GameInfoPersistence gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
            gameInfoPersistence.setActiveGameInfo(newGameInfo);
            App.setRoot("PaintView");            
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error opening paintview");
        }
    }
}
