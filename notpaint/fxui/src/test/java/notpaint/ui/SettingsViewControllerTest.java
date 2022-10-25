package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.core.persistence.GameInfoPersistence;

public class SettingsViewControllerTest extends ApplicationTest {
    private SettingsViewController controller;

    static Path dataPath = Paths.get("testData_INALKN434NJN");

    @Override
    public void start(Stage stage) throws Exception {
        var gameInfoPersistence = new GameInfoPersistence(dataPath);
        stage.setUserData(gameInfoPersistence);

        FXMLLoader fxmlLoader = new FXMLLoader(GameSelectController.class.getResource("SettingsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        if (!Files.exists(dataPath)) {
            return;
        }

        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.delete(file);
        }
        Files.deleteIfExists(dataPath); // Delete datapath dir
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
            if (window instanceof Stage && window.isShowing()) {
                var root = window.getScene().getRoot();
                if (id.equals(root.getId())) {
                    return root;
                }
            }
        }
        return null;
    }

    // @Test
    // public void testTimeTextFieldIsEmpty() {
    // clickOn("#setTimeTextField");
    // write("");
    // clickOn("#createButton");
    // // assert if the error message is displayed
    // FxAssert.verifyThat("#timeErrorPopup", NodeMatchers.isVisible());
    // }

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

    // test if clicking on the create button opens paintview
    @Test
    public void testCreateButton() throws InterruptedException {
        clickOn("#setTimeTextField");
        write("10");
        clickOn("#maxIterationsTextField");
        write("2");
        clickOn("#createButton");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("paintRoot"));
    }

}
