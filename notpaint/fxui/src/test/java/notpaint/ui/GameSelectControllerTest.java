package notpaint.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.core.GameInfo;

public class GameSelectControllerTest extends ApplicationTest {
    private GameSelectController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameSelectController.class.getResource("GameSelectView.fxml"));
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

    @Test
    public void testSetSelectedGameInfo() {
        GameInfo info = new GameInfo(44, 12, false);
        info.addIteration("testAuthor");
        controller.setSelectedGameInfo(info);
        Text secondsPerRoundText = lookup("#secondsPerRound").query();
        Text iterationsText = lookup("#iterations").query();
        Text lastEditText = lookup("#lastEdit").query();
        Text lastEditorText = lookup("#lastEditor").query();
        assertEquals("12", secondsPerRoundText.getText());
        assertEquals("1 / 44", iterationsText.getText());
        assertEquals("testAuthor", lastEditorText.getText());
        assertEquals(info.getLastEditTime().toString(), lastEditText.getText());
    }


    @Test
    public void testOpenNewProject() throws InterruptedException {
        System.out.println("testOpenNewProject");
        clickOn("#newProjectButton");
        WaitForAsyncUtils.waitForFxEvents();
        // Thread.sleep(1000);
        // assertTrue(true);
        assertNotNull(findSceneRootWithId("settingsRoot"));
        // Thread.sleep(1000);
    }

    @Test
    public void testJoinGameAlert() {
        clickOn("#joinProjectButton");
        FxAssert.verifyThat("Warning", NodeMatchers.isVisible());
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
    public void testClickOnCompletedTab() {
        clickOn("#completedTab");
        WaitForAsyncUtils.waitForFxEvents();
    }

}
