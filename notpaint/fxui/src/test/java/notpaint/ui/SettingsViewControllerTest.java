package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.ui.testutil.PersistenceTestConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Test class for {@link SettingsViewController}.
 */
public class SettingsViewControllerTest extends ApplicationTest {
    private SettingsViewController controller;

    @Override
    public void start(Stage stage) throws Exception {
        PersistenceTestConfig.setLocalPersistence(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(
                GameSelectController.class.getResource("SettingsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        scene.getStylesheets().add(getClass().getResource("fxui.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        PersistenceTestConfig.cleanUpLocalPersistence();
    }

    @Test
    public void testController() {
        assertNotNull(controller);
    }

    @Test
    public void testBackToMenu() {
        clickOn("#backToMenuButton");
        assertNotNull(findSceneRootWithId("gameSelectRoot"));
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

    @Test
    public void testRadioButtonsAreRightValue() {
        RadioButton checkboxYes = (RadioButton) lookup("#checkboxYes").query();
        RadioButton checkboxNo = (RadioButton) lookup("#checkboxNo").query();
        clickOn("#checkboxNo");
        assertTrue(checkboxNo.isSelected());
        assertFalse(checkboxYes.isSelected());
        clickOn("#checkboxYes");
        assertTrue(checkboxYes.isSelected());

    }

    @Test
    public void testCreateButton() {
        clickOn("#setTimeTextField");
        write("10");
        clickOn("#maxIterationsTextField");
        write("2");

        clickOn("#createButton");

        assertNotNull(findSceneRootWithId("paintRoot"));
    }

}
