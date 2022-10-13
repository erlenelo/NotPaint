package notpaint.core.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.shape.Path;
import notpaint.core.GameInfo;

public class GameInfoPersistenceTest {
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

     /* @Test
    public void testSaveGameInfo() throws IOException {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        gameInfoPersistence.saveGameInfo();
        assertNotNull(gameInfoPersistence);
        
    } */

    @Test
    public void testSetActiveGameInfo() {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        GameInfo gameInfo = new GameInfo(2, 5, true);
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        assertNotNull(gameInfoPersistence);



    }
}
