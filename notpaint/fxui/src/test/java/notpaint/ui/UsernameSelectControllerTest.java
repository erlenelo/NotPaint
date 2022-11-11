package notpaint.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 * Test class for {@link UsernameSelectController}.
 */

public class UsernameSelectControllerTest extends ApplicationTest {

    private UsernameSelectController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
            UsernameSelectController.class.getResource("UsernameSelectView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        scene.getStylesheets().add(getClass().getResource("fxui.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    // Test that the controller is created.
    @Test
    public void testController() {
        assertNotNull(controller);
    }

    // Test if clicking on the Done-button opens GameSelectView.
    @Test
    public void testDoneButton() throws InterruptedException {
        clickOn("#setUsernameTextField");
        write("testAuthor");
        clickOn("#createUsername");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("gameSelectRoot"));
    }

    private Object findSceneRootWithId(String string) {
        return null;
    }

    // Test to see if radiobuttons have right values.
    @Test
    public void testRadioButtonsHaveRightValue() {
        RadioButton yesRadioButton = (RadioButton) lookup("#yesRadioButton").query();
        RadioButton noRadioButton = (RadioButton) lookup("#noRadioButton").query();
        clickOn("#checkboxNo");
        assertTrue(noRadioButton.isSelected());
        assertFalse(yesRadioButton.isSelected());
        clickOn("#checkboxYes");
        assertTrue(yesRadioButton.isSelected());
    }

    
}
