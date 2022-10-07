package notpaint.ui;

import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;
import notpaint.core.GameInfo;

public class SettingsViewController {


    @FXML
    TextField setTimeTextField, maxIterationsTextField;

    @FXML
    RadioButton checkboxYes, checkboxNo;

    @FXML
    ToggleGroup newWordToggleGroup;

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
        App.setRoot("secondary");
    }

    @FXML
    private void createGame(ActionEvent event) {
        
        int maxIterations = Integer.parseInt(maxIterationsTextField.getText());
        int secondsPerRound = Integer.parseInt(setTimeTextField.getText());
        
        boolean newWordEachRound = checkboxYes.isSelected();        
    
        GameInfo newGameInfo = new GameInfo(maxIterations, secondsPerRound, newWordEachRound);
        // TODO: SAVE GAMEINFO TO JSON!
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        
        
        try {
            stage.close();
            stage.setUserData(newGameInfo);
            App.setRoot("PaintView");            
            stage.show();
        } catch(IOException IOException) {
            IOException.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Error opening PaintView");
            alert.setHeaderText("ERROR");
            alert.show();
        }
    }
}
