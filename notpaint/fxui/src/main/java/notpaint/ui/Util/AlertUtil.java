package notpaint.ui.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
    public static void WarningAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(title);
        alert.show();
    }

    public static void ErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(title);
        alert.show();
    }
}
