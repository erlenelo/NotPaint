package notpaint.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.persistence.GameInfo;
import notpaint.persistence.GameInfoPersistence;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

/**
 * Test class for {@link GameSelectController}.
 */
public class GameSelectControllerTest extends ApplicationTest {
    private GameSelectController controller;

    GameInfoPersistence persistence;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
            GameSelectController.class.getResource("GameSelectView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        scene.getStylesheets().add(getClass().getResource("fxui.css").toExternalForm());
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
        assertEquals("12", secondsPerRoundText.getText());
        
        Text iterationsText = lookup("#iterations").query();
        assertEquals("1 / 44", iterationsText.getText());

        Text lastEditText = lookup("#lastEdit").query();
        assertEquals(info.getLastEditTime().toString(), lastEditText.getText());

        Text lastEditorText = lookup("#lastEditor").query();
        assertEquals("testAuthor", lastEditorText.getText());
    }

    // @Test
    // public void testJoinGameButton() {
        
    //     info.addIteration("testAuthor");
    //     controller.setSelectedGameInfo(info);
        
    //     persistence.setActiveGameInfo(info);
    //     controller.addImageToActiveTap(info);
    //     WaitForAsyncUtils.waitForFxEvents();
    //     clickOn("#joinProjectButton");
    //     assertNotNull(findSceneRootWithId("paintRoot"));
        
    // }

    // private void testAddImagetoActiveTab() {
    //     GameInfo info = new GameInfo(9, 99, false);
    //     ImageView imageView = new ImageView(
    //         new Image(persistence.getImagePath(info), 200, 140, true, true));
    //     imageView.maxHeight(150);
    //     imageView.maxWidth(200);

    //     imageView.
    // }





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

    @Test
    public void testHandleChangeUsernameButton() {
        clickOn("#changeUsernameButton");
        assertNotNull(findSceneRootWithId("usernameSelectRoot"));

    }


   

}
