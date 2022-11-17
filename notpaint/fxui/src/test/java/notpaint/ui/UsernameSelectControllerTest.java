package notpaint.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.ui.testutil.PersistenceTestConfig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

/**
 * Test class for {@link UsernameSelectController}.
 */

public class UsernameSelectControllerTest extends ApplicationTest {

    private UsernameSelectController controller;

    @Override
    public void start(Stage stage) throws Exception {
        PersistenceTestConfig.setLocalPersistence(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(
                UsernameSelectController.class.getResource("UsernameSelectView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        scene.getStylesheets().add(getClass().getResource("fxui.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();

    }

    @Test
    public void testController() {
        assertNotNull(controller);
    }

    /**
     * Delete username before testing.
     */
    @BeforeAll
    public static void deleteFile() {
        GameSelectController ctrl = new GameSelectController();
        try {
            ctrl.deleteUsername();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoneButton() throws InterruptedException {
        clickOn("#setUsernameTextField");
        write("testAuthor");
        clickOn("#doneButton");
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
    public void testRadioButtonsHaveRightValue() {
        RadioButton yesRadioButton = (RadioButton) lookup("#yesRadioButton").query();
        RadioButton noRadioButton = (RadioButton) lookup("#noRadioButton").query();
        clickOn("#noRadioButton");
        assertTrue(noRadioButton.isSelected());
        assertFalse(yesRadioButton.isSelected());
        clickOn("#yesRadioButton");
        assertTrue(yesRadioButton.isSelected());
        assertFalse(noRadioButton.isSelected());
    }

    @Test
    public void testUsernameText() throws IOException {
        clickOn("#setUsernameTextField");
        write("testAuthor");
        clickOn("#yesRadioButton");
        clickOn("#doneButton");

        Path file = Path.of("usernameFile.txt");
        String content = Files.readString(file);
        String usernameText = "testAuthor";
        assertEquals(content, usernameText);
    }

    @Test
    public void testSetUserNameAlert() {
        clickOn("#doneButton");
        FxAssert.verifyThat("Warning", NodeMatchers.isVisible());
    }

    @AfterAll
    public static void deleteUsername() throws IOException {
        PrintWriter writer = new PrintWriter("usernameFile.txt", Charset.forName("UTF-16"));
        writer.print("");
        writer.close();
    }

}
