package notpaint.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LocalGameInfoPersistenceTest {
    @Test
    public void testGetAllGameInfos() throws IOException {
        LocalGameInfoPersistence localGameInfoPersistence = new LocalGameInfoPersistence();
        GameInfo gameInfo = new GameInfo(5,5,false);
        localGameInfoPersistence.saveGameInfo(gameInfo);
        List<GameInfo> allGameInfos = localGameInfoPersistence.getAllGameInfos();
        assertEquals(1, allGameInfos.size());
    }
}

