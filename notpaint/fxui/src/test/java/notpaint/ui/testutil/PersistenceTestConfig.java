package notpaint.ui.testutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.stage.Stage;
import notpaint.persistence.GameInfoPersistence;
import notpaint.persistence.LocalGameInfoPersistence;

public class PersistenceTestConfig {

    // weird name because this folder will be removed after tests,
    // make sure it's not a folder anyone will use
    public static final Path dataPath = Paths.get("testData_INALKN434NJN");

    public static GameInfoPersistence setLocalPersistence(Stage stage) {
        var gameInfoPersistence = new LocalGameInfoPersistence(dataPath);
        stage.setUserData(gameInfoPersistence);
        return gameInfoPersistence;
    }

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
