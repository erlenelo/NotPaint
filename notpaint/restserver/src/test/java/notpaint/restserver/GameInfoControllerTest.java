package notpaint.restserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



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
    public void testGetAllGameInfos() {

    }

}
