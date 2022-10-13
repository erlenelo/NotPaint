package notpaint.ui;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.channels.SeekableByteChannel;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SettingsViewControllerTest extends ApplicationTest {
    private SettingsViewController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameSelectController.class.getResource("SettingsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    // Test that the controller is created
    @Test
    public void testController() {
        assertNotNull(controller);
    }

    // Test that the "back to menu" button works
    @Test
    public void testBackToMenu() {
        clickOn("#backToMenuButton");
        assertNotNull(findSceneRootWithId("gameSelectRoot"));
    }

    private Parent findSceneRootWithId(String id) {
        for (Window window : Window.getWindows()) {
            System.out.println("Window: " + window.toString());
            if (window instanceof Stage && window.isShowing()) {
                var root = window.getScene().getRoot();
                System.out.println("root.getId(): " + root.getId());
                if (id.equals(root.getId())) {
                    return root;
                }
            }
        }
        return null;
    }

    @Test
    public void testTimeTextFieldIsEmpty() {
        clickOn("#setTimeTextField");
        write("");
        clickOn("#createButton");
        // assert if the error message is displayed
        FxAssert.verifyThat("#timeErrorLabel", NodeMatchers.isVisible());
    }

}
