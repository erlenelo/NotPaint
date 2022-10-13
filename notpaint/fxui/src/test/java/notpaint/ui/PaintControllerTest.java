package notpaint.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import notpaint.core.GameInfo;
import notpaint.core.Persistence.GameInfoPersistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.testfx.framework.junit5.ApplicationTest;

public class PaintControllerTest extends ApplicationTest {
    private PaintController controller;

    // weird name because this folder will be removed after tests, make sure it's not a folder anyone will use
    Path dataPath = Paths.get("testData_INALKN434NJN"); 
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
    }

    @Test
    public void testController() {
        assertNotNull(controller);
    }

    @AfterAll
    public void cleanup() {
        try(var filesStream = Files.list(dataPath)) {
            var filesList = filesStream.toList();
            for(var file: filesList) {
                Files.delete(file);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    
    @Test
    public void testDone() {
        clickOn("#doneButton");
        assertNotNull(findSceneRootWithId("paintRoot"));
        boolean jsonExists = Files.exists(Paths.get(dataPath.toString(), gameInfo.getUuid().toString() + ".json"));
        assertTrue(jsonExists);
        boolean imageExists = Files.exists(Paths.get(gameInfo.getImagePath()));
        assertTrue(imageExists);
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

