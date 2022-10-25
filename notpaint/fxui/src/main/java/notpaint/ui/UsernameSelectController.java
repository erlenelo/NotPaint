package notpaint.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import notpaint.core.GameInfo;

public class UsernameSelectController {
    
    @FXML
    private TextField setUsernameTextField;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private Button doneButton;

    private GameInfo lastEditor;

    public void initialize() {}

   
    
    @FXML
    public void createUsername(ActionEvent event) {
        String username = setUsernameTextField.getText();

        try {
            stage.close();
            GameInfoPersistence gameInfoPersistence = (GameInfoPersistence)stage.getUserData();
            gameInfoPersistence.setActiveGameInfo(newGameInfo);
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
