package notpaint.ui.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.stage.Stage;
import notpaint.persistence.GameInfoPersistence;
import notpaint.persistence.LocalGameInfoPersistence;

/**
 * Configuration for persistence tests.
 */
public class PersistenceTestConfig {

    // weird name because this folder will be removed after tests,
    // make sure it's not a folder anyone will use
    public static final Path dataPath = Paths.get("testData_INALKN434NJN");

    /**
     * Creates a new persistence object and sets it as the persistence object for
     * the
     * stage.
     *
     * @param stage the stage of the application
     * @return the new persistence object
     * @throws IOException if an error occurs while creating the persistence object
     */
    public static GameInfoPersistence setLocalPersistence(Stage stage) {
        var gameInfoPersistence = new LocalGameInfoPersistence(dataPath);
        stage.setUserData(gameInfoPersistence);
        return gameInfoPersistence;
    }

    /**
     * Deletes the data folder for setLocalPersistence.
     *
     * @throws IOException if an error occurs while deleting the folder
     */
    public static void cleanUpLocalPersistence() {
        try {
            var files = Files.list(dataPath).toList();
            for (var file : files) { // Delete every file in datapath dir
                Files.deleteIfExists(file);
            }
            Files.deleteIfExists(dataPath); // Delete datapath dir
        } catch (IOException ioe) {
            throw new RuntimeException("Failed to cleanup local persistence files", ioe);
        }
    }
}
