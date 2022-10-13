package notpaint.ui.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
    public static void warningAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(title);
        alert.show();
    }

    public static void errorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(title);
        alert.show();
    }
}
