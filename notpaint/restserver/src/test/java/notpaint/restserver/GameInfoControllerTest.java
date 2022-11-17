package notpaint.restserver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import notpaint.persistence.GameInfo;
import notpaint.persistence.LocalGameInfoPersistence;

/**
 * Test class for {@link GameInfoController}.
 */
public class GameInfoControllerTest {

    static GameInfoController controller;
    static Path dataPath; 

    @BeforeAll
    public static void setup() {
        controller = new GameInfoController();
        dataPath = Paths.get("testData_" + UUID.randomUUID().toString());
        LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence();
        
        controller.setGameInfoPersistence(gameInfoPersistence);
    }

    @AfterAll
    public static void cleanUp(Path dataPath) throws IOException {
        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.delete(file);
        }
        Files.deleteIfExists(dataPath); // Delete datapath dir
    }

    @Test
    public void testSaveAndGetGameInfos() {
        var gameInfo1 = new GameInfo(5, 5, false);
        var gameInfo2 = new GameInfo(2, 5, true);
        controller.putSaveGameInfo(gameInfo1);
        controller.putSaveGameInfo(gameInfo2);
        
        ResponseEntity<List<GameInfo>> allGameInfosResponse = controller.getAllGameInfos();
        assertTrue(allGameInfosResponse.hasBody());
        
        var allGameInfos = allGameInfosResponse.getBody();
        if (allGameInfos == null) {
            throw new AssertionError("Response body is null");
        }

        assertTrue(allGameInfos.contains(gameInfo1));
        assertTrue(allGameInfos.contains(gameInfo2));
    }

    @Test
    public void testGameInfoLocking() {
        var gameInfo = new GameInfo(5, 100, false);
        controller.putSaveGameInfo(gameInfo);

        Boolean isLocked = controller.getLockStatus(gameInfo.getUuid()).getBody();

        if (isLocked == null) {
            throw new AssertionError("Response body is null");
        }
        assertFalse(isLocked);

        Boolean lockSucessfull = controller.postRequestLock(gameInfo.getUuid()).getBody();

        if (lockSucessfull == null) {
            throw new AssertionError("Response body is null");
        }
        




    }

}
