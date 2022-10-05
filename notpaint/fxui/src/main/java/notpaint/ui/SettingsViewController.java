package notpaint.ui;

import java.io.IOException;

import javafx.fxml.FXML;

public class SettingsViewController {
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToPaintView() throws IOException {
        App.setRoot("PaintView");
    }
}
