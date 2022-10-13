package notpaint.ui;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.channels.SeekableByteChannel;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
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
