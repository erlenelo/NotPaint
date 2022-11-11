package notpaint.ui.util;

import java.net.URI;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.stage.Stage;
import notpaint.core.persistence.GameInfoPersistence;
import notpaint.core.persistence.RemoteGameInfoPersistence;

/**
 * Utility class for stage loading.
 */
public class StageUtil {

    /**
     * Will call the callback with stage as soon as it is set for Node.
     *
     * @param node Node to get stage reference from
     * @param callback Method to call when stage is set
     */
    public static void onGameInfoPersistenceLoaded(Node node, Consumer<GameInfoPersistence> callback) {
        // Get the scene from any Node object.
        // Because the scene is not set in initialize, we need to listen for the
        // property to update.
        node.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (newScene != null) {
                var stage = newScene.getWindow();
                // The window property is also initially not set the first time the app starts.
                // If it is null, listen for the property to update an then set it
                if (stage == null) {
                    newScene.windowProperty().addListener((
                        observableWindow, oldWindow, newWindow) -> {
                        // Create a persistence instance and set it as the user data for the stage.
                        // This makes it accessible from all other scenes.
                        if (newWindow != null) {
                            setGameInfoPersistanceOnStage(
                                (Stage) newWindow, callback);                           
                        }
                    });
                } else {
                    setGameInfoPersistanceOnStage((Stage) stage, callback);
                }
            }
        });
    }

    private static void setGameInfoPersistanceOnStage(
        Stage stage, Consumer<GameInfoPersistence> callback) {
        if (stage.getUserData() == null) {
            
            stage.setUserData(new RemoteGameInfoPersistence(URI.create("http://localhost:8080/")));
        }
        var gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
        callback.accept(gameInfoPersistence);
    }
}
