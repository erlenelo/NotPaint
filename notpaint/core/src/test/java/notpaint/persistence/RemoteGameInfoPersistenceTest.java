package notpaint.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;

import org.junit.Test;

public class RemoteGameInfoPersistenceTest {

    private URI serverURI;

    @Test
    public void testGetAllGameInfos() {
        RemoteGameInfoPersistence persistence = new RemoteGameInfoPersistence(serverURI);

        try {
            assertNotNull(persistence.getAllGameInfos());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
