// package notpaint.ui.util;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.stage.Stage;
// import notpaint.ui.App;
// import notpaint.ui.GameSelectController;

// public class AlertUtilTest extends ApplicationTest {
// private GameSelectController controller;

// @Override
// public void start(Stage stage) throws Exception {

// FXMLLoader fxmlLoader = new FXMLLoader(
// GameSelectController.class.getResource("GameSelectView.fxml"));
// Scene scene = new Scene(fxmlLoader.load());
// App.scene = scene;
// scene.getStylesheets().add(getClass().getResource("fxui.css").toExternalForm());
// stage.setScene(scene);
// stage.show();
// controller = fxmlLoader.getController();
// }

// @Test
// public void testWarningAlert() {
// Alert alert = new Alert(AlertType.WARNING);
// alert.setContentText("testAlert123");
// alert.setHeaderText("testHeader123");
// alert.show();
// assertEquals(alert.contentTextProperty(), "testAlert123");
// assertEquals(alert.headerTextProperty(), "testHeader123");
// }

// @Test
// public void testErrorAlert() {
// Alert alert = new Alert(AlertType.ERROR);
// alert.setContentText("testErrorAlert123");
// alert.setHeaderText("testErrorHeader123");
// alert.show();
// assertEquals(alert.contentTextProperty(), "testErrorAlert123");
// assertEquals(alert.headerTextProperty(), "testErrorHeader123");
// }

// }
