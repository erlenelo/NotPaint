package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.core.brushes.Brush;
import notpaint.core.brushes.CircleBrush;
import notpaint.core.brushes.SquareBrush;
import notpaint.persistence.GameInfo;
import notpaint.persistence.GameInfoPersistence;
import notpaint.persistence.LocalGameInfoPersistence;
import notpaint.ui.testutil.PersistenceTestConfig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Test class for {@link GameSelectController}.
 */
public class PaintControllerTest extends ApplicationTest {
    private PaintController controller;

    
    GameInfoPersistence gameInfoPersistence;
    GameInfo gameInfo;

    @Override
    public void start(Stage stage) throws Exception {
        gameInfoPersistence = PersistenceTestConfig.setLocalPersistence(stage);
        gameInfo = new GameInfo(5, 100, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);       
         
        FXMLLoader fxmlLoader = new FXMLLoader(PaintController.class.getResource("PaintView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("fxui.css").toExternalForm());
        App.scene = scene;
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
    public void testBrushButtons() {
        assertButtonSetsBrush(".smallCircle", CircleBrush.class);
        assertButtonSetsBrush(".smallSquare", SquareBrush.class);
        assertButtonSetsBrush(".mediumCircle", CircleBrush.class);
        assertButtonSetsBrush(".mediumSquare", SquareBrush.class);
        assertButtonSetsBrush(".bigCircle", CircleBrush.class);
        assertButtonSetsBrush(".bigSquare", SquareBrush.class);
    }

    private void assertButtonSetsBrush(String button, Class<? extends Brush> brushClass) {
        clickOn(button);
        assertEquals(brushClass, controller.settings.getBrush().getClass());

    }


    @Test
    public void testResetCanvas() {
        clickOn("#drawingCanvas");
        clickOn("#resetCanvasButton");
        clickOn("#doneButton");
    
        Image image = new Image(gameInfoPersistence.getImagePath(gameInfo));
        

        // assert that the image is completely white
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                assertTrue(image.getPixelReader()
                    .getColor(x, y).equals(javafx.scene.paint.Color.WHITE));
            }
        }
    }

    @Test
    public void testHighlightCircle() {

        assertButtonIsHighlighted(".smallCircle");
        assertButtonIsHighlighted(".mediumCircle");
        assertButtonIsHighlighted(".bigCircle");

    }

    @Test
    public void testHighlightSquare() {

        assertButtonIsHighlighted(".smallSquare");
        assertButtonIsHighlighted(".mediumSquare");
        assertButtonIsHighlighted(".bigSquare");

    }
    private void assertButtonIsHighlighted(String button) {
        clickOn(button);
        assertTrue(lookup(button).query().getId().contains("highlightedBrush"));
    }

    

    @Test
    public void testEraserClick() {
        clickOn("#toolPane");
        assertTrue(lookup("#toolPaneHighlight").query().getId().contains("toolPaneHighlight"));
  
    }

    @Test
    public void testPencilClick() {
        clickOn("#pencil");
        assertTrue(lookup("#toolPaneHighlight").query().getId().contains("toolPaneHighlight"));
    }

    @Test
    public void testDone() {
        clickOn("#doneButton");
        assertNotNull(findSceneRootWithId("gameSelectRoot"), "GameSelectView should be visible");
        boolean jsonExists = Files.exists(Paths.get(
            PersistenceTestConfig.dataPath.toString(), gameInfo.getUuid().toString() + ".json"));
        assertTrue(jsonExists, "GameInfo json should exist after clicking done on PaintView");
        Image image = new Image(gameInfoPersistence.getImagePath(gameInfo));
        assertNotNull(image, "Image should exist after clicking done on PaintView");
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
    public void testUndoRedo(){

        clickOn("#drawingCanvas");
        
        clickOn("#undoArrow");
        assertTrue(controller.undoStack.isEmpty());
        
        clickOn("#redoArrow");
        assertTrue(controller.redoStack.isEmpty());



    }

    



    @Test
    public void testKeyUndoRedo() {
        clickOn("#drawingCanvas");
        press(KeyCode.CONTROL).press(KeyCode.Z);
        assertTrue(lookup(".undoPane").query().getId().contains("undoredopane2"));
        press(KeyCode.CONTROL).press(KeyCode.Y).release(KeyCode.Y).release(KeyCode.CONTROL);


    }


}



