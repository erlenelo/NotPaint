package notpaint.core.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import notpaint.core.GameInfo;

public class GameInfoPersistenceTest {
    @Test
    public void testGetActiveGameInfo() {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        GameInfo gameInfo = new GameInfo();
        gameInfoPersistence.setActiveGameInfo(gameInfo);
        assertEquals(gameInfo, gameInfoPersistence.getActiveGameInfo());

        
    }

    @Test
    public void testGetAllGameInfos() throws IOException {
        GameInfo gameInfo = new GameInfo(2,5,true);
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        gameInfoPersistence.getAllGameInfos();
        assertEquals(gameInfo, gameInfoPersistence.getAllGameInfos());



    }

    @Test
    public void testSaveGameInfo() throws IOException {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        gameInfoPersistence.saveGameInfo();
        assertEquals(gameInfoPersistence, gameInfoPersistence.saveGameInfo());  


    }

    @Test
    public void testSetActiveGameInfo() {
        GameInfoPersistence gameInfoPersistence = new GameInfoPersistence();
        gameInfoPersistence.setActiveGameInfo(null);
        assertEquals(gameInfoPersistence, gameInfoPersistence.setActiveGameInfo(null));
        


    }
}
