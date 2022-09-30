package notpaint.ui;

import java.io.IOException;

import javafx.fxml.FXML;

public class SettingsViewController {
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("secondary");
    }
}
