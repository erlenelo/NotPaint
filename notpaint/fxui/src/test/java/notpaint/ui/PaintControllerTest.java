package notpaint.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.core.GameInfo;
import notpaint.core.Brushes.Brush;
import notpaint.core.Brushes.CircleBrush;
import notpaint.core.Brushes.SquareBrush;
import notpaint.core.Persistence.GameInfoPersistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class PaintControllerTest extends ApplicationTest {
    private PaintController controller;

    // weird name because this folder will be removed after tests, make sure it's not a folder anyone will use
    static Path dataPath = Paths.get("testData_INALKN434NJN");
    GameInfoPersistence gameInfoPersistence;
    GameInfo gameInfo;

    @Override
    public void start(Stage stage) throws Exception {
        gameInfoPersistence = new GameInfoPersistence(dataPath);
        gameInfo = new GameInfo(5, 10, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);        
        stage.setUserData(gameInfoPersistence);
         
        FXMLLoader fxmlLoader = new FXMLLoader(PaintController.class.getResource("PaintView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        App.scene = scene;
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.delete(file);
        }
        Files.deleteIfExists(dataPath); // Delete datapath dir
    }

    @Test
    public void testController() {
        assertNotNull(controller);
    }

    
    @Test
    public void testBrushButtons() {
        assertButtonSetsBrush("#circleSmall", CircleBrush.class);
        assertButtonSetsBrush("#squareSmall", SquareBrush.class);
        assertButtonSetsBrush("#circleMedium", CircleBrush.class);
        assertButtonSetsBrush("#squareMedium", SquareBrush.class);
        assertButtonSetsBrush("#circleBig", CircleBrush.class);
        assertButtonSetsBrush("#squareBig", SquareBrush.class);
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
                assertTrue(image.getPixelReader().getColor(x, y).equals(javafx.scene.paint.Color.WHITE));
            }
        }
    }

    @Test
    public void testDone() {
        clickOn("#doneButton");
        assertNotNull(findSceneRootWithId("gameSelectRoot"), "GameSelectView should be visible");
        boolean jsonExists = Files.exists(Paths.get(dataPath.toString(), gameInfo.getUuid().toString() + ".json"));
        System.out.println(Paths.get(dataPath.toString(), gameInfo.getUuid().toString() + ".json").toAbsolutePath());
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


}

