package notpaint.persistence;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Test class for {@link RemoteGameInfoPersistence}.
 */
public class RemoteGameInfoPersistenceTest {
    private WireMockConfiguration config;
    private WireMockServer wireMockServer;
    private RemoteGameInfoPersistence remoteGameInfoPersistence;

    /**
     * Setup method for Mock Server before each test class.
     *
     * @throws URISyntaxException if URI is invalid
     */
    
    @BeforeEach
    public void startWireMockServerAndSetup() throws URISyntaxException {
        config = WireMockConfiguration.wireMockConfig().port(8089);
        wireMockServer = new WireMockServer(config.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", config.portNumber());
        remoteGameInfoPersistence = new RemoteGameInfoPersistence(new URI("http://localhost:" + wireMockServer.port() + "/allGameInfos"));
    }


    @Test
    public void testGetAllGameInfos() throws IOException {
        stubFor(get(urlEqualTo("/allGameInfos"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("[{\"words\": [ \"test1\", \"test2\", \"test3\"]}]")
        ));
              
        List<GameInfo> allGameInfos = remoteGameInfoPersistence.getAllGameInfos();
        assertEquals(1, allGameInfos.size());
        assertEquals("test3", allGameInfos.get(0).getWord());
        

    }

    @Test
    public void testSaveGameInfo() throws IOException {
        stubFor(put(urlEqualTo("/saveGameInfo"))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"words\": [ \"test1\", \"test2\", \"test3\"]}")
        ));
        GameInfo gameInfo = new GameInfo();
        remoteGameInfoPersistence.saveGameInfo(gameInfo);
        verify(putRequestedFor(urlEqualTo("/saveGameInfo")));
    }

    @Test
    public void isGameInfoLocked() throws IOException {
        GameInfo newGameInfo = new GameInfo(5, 5, false);
        String testuuid = newGameInfo.getUuid().toString();
      
        stubFor(get(urlEqualTo("/lock?uuid=" + testuuid))
            .willReturn(aResponse()
            .withBody("true")
        ));
        assertTrue(remoteGameInfoPersistence.isGameInfoLocked(newGameInfo));
    
    }


    @AfterEach
    public void stopWireMockServer() {
        wireMockServer.stop();
    } 
}

