package notpaint.ui.util;

import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.stage.Stage;

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
    public static void onStageLoaded(Node node, Consumer<Stage> callback) {
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
                            callback.accept((Stage) newWindow);
                            
                        }
                    });
                } else {
                    callback.accept((Stage) stage);
                }
            }
        });
    }
}
