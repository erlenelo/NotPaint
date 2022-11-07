package notpaint.core.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import notpaint.core.GameInfo;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LocalGameInfoPersistence}.
 */
public class GameInfoPersistenceTest {

    /*
     * Helper method that deletes the folder at the given path.
     */
    private static void cleanUp(Path dataPath) throws IOException {
        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.delete(file);
        }
        Files.deleteIfExists(dataPath); // Delete datapath dir
    }

    @Test
    public void testGetActiveGameInfo() {
        LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        GameInfo testGameInfo = gameInfoPersistence.getActiveGameInfo();
        assertNotNull(testGameInfo);

    }

    @Test
    public void testGetAllGameInfos() throws IOException {
        LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();
        gameInfoPersistence.getAllGameInfos();
        assertNotNull(gameInfoPersistence);

    }

    @Test
    public void testSaveGameInfo() throws IOException {
        Path dataPath = Paths.get("testData_" + UUID.randomUUID().toString()); // Random path

        LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence(dataPath);
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        gameInfoPersistence.saveGameInfo(gameInfo);

        var allGameInfos = gameInfoPersistence.getAllGameInfos();
        var deserializedGameInfo = allGameInfos.get(0);

        cleanUp(dataPath);
        assertEquals(gameInfo.getUuid(), deserializedGameInfo.getUuid());
    }

    @Test
    public void testSetActiveGameInfo() {
        LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        assertNotNull(gameInfoPersistence.getActiveGameInfo());

    }
}
