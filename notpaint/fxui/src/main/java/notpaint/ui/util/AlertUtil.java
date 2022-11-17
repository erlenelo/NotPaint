package notpaint.ui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class for creating alerts easily.
 */
public class AlertUtil {

    /**
     * Creates and shows a warning alert with the given title and message.
     *
     * @param title   Title of the alert
     * @param content Message of the alert
     */
    public static void warningAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(title);
        alert.show();
    }

    /**
     * Creates and shows an error alert with the given title and message.
     *
     * @param title   Title of the alert
     * @param content Message of the alert
     */
    public static void errorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(content);
        alert.setHeaderText(title);
        alert.show();
    }
}
