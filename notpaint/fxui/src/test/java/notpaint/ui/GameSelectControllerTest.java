package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameSelectControllerTest extends ApplicationTest {
    private GameSelectController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameSelectController.class.getResource("GameSelectView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    // Test that the controller is created
    @Test
    public void testController() {
        assertNotNull(controller);
    }

    @Test
    public void testTodoSettings() {
        clickOn("#joinProjectButton");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("settingsRoot"));
    }

    private Parent findSceneRootWithId(String id) {
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage && window.isShowing()) {
                var root = window.getScene().getRoot();
                if (id.equals(root.getId())) {
                    return root;
                }
            }
        }
        return null;
    }

}
