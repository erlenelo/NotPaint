package notpaint.restserver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import notpaint.persistence.GameInfo;
import notpaint.persistence.LocalGameInfoPersistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

/**
 * Test class for {@link GameInfoController}.
 */
public class GameInfoControllerTest {

    static GameInfoController controller;
    static Path dataPath; 

    /**
     * Setup the test class by creating a new controller and setting a test path.
     */
    @BeforeAll
    public static void setup() {
        controller = new GameInfoController();
        dataPath = Paths.get("testData_" + UUID.randomUUID().toString());
        LocalGameInfoPersistence gameInfoPersistence = new LocalGameInfoPersistence(dataPath);
        
        controller.setGameInfoPersistence(gameInfoPersistence);
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.deleteIfExists(file);
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
        // Assert that the list contains the game infos we saved, by checking the UUIDs
        assertTrue(allGameInfos.stream().map(x -> x.getUuid())
            .anyMatch(x -> x.equals(gameInfo1.getUuid())));
        assertTrue(allGameInfos.stream().map(x -> x.getUuid())
            .anyMatch(x -> x.equals(gameInfo2.getUuid())));
    }

    @Test
    public void testGameInfoLocking() {
        var gameInfo = new GameInfo(5, 100, false);
        controller.putSaveGameInfo(gameInfo);

        // Check if the new GameInfo is locked, it should not be.
        Boolean isLocked = controller.getLockStatus(gameInfo.getUuid()).getBody();
        if (isLocked == null) {
            throw new AssertionError("Response body is null");
        }
        assertFalse(isLocked);

        // Lock the GameInfo. Assert that locking was successfull
        Boolean lockSuccessful = controller.postRequestLock(gameInfo.getUuid()).getBody();
        if (lockSuccessful == null) {
            throw new AssertionError("Response body is null");
        }
        assertTrue(lockSuccessful);

        // Now it should be locked
        isLocked = controller.getLockStatus(gameInfo.getUuid()).getBody();

        if (isLocked == null) {
            throw new AssertionError("Response body is null");
        }
        assertTrue(isLocked);

        // Try to lock again, it should fail because it is already locked
        lockSuccessful = controller.postRequestLock(gameInfo.getUuid()).getBody();
        if (lockSuccessful == null) {
            throw new AssertionError("Response body is null");
        }
        assertFalse(lockSuccessful);

        // Unlock the GameInfo, and make sure it is unlocked after
        controller.deleteReleaseLock(gameInfo.getUuid());
        isLocked = controller.getLockStatus(gameInfo.getUuid()).getBody();
        if (isLocked == null) {
            throw new AssertionError("Response body is null");
        }
        assertFalse(isLocked);
    }

}
