package notpaint.core.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import notpaint.core.GameInfo;

public class GameInfoPersistenceTest {

    public static void cleanUp(Path dataPath) throws IOException {
        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.delete(file);
        }
        Files.deleteIfExists(dataPath); // Delete datapath dir
    }

    @Test
    public void testGetActiveGameInfo() {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        GameInfo testGameInfo = gameInfoPersistence.getActiveGameInfo();
        assertNotNull(testGameInfo);

    }

    @Test
    public void testGetAllGameInfos() throws IOException {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        gameInfoPersistence.getAllGameInfos();
        assertNotNull(gameInfoPersistence);

    }

    @Test
    public void testSaveGameInfo() throws IOException {
        Path dataPath = Paths.get("testData_" + UUID.randomUUID().toString()); // Random path

        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence(dataPath);
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
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        assertNotNull(gameInfoPersistence.getActiveGameInfo());

    }
}
