package notpaint.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;


public class RemoteGameInfoPersistenceTest {
    private WireMockConfiguration config;
    private WireMockServer wireMockServer;

    private RemoteGameInfoPersistence remoteGameInfoPersistence;


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
            .withBody("{\"words\": [ \"test1\"\"test2\" \"test3\" \"test4\"]}")
        ));
        List<GameInfo> allGameInfos = remoteGameInfoPersistence.getAllGameInfos();
        assertEquals(4, allGameInfos.size());
        assertEquals("test1", allGameInfos.get(0).getWord());
    }
    
    @AfterEach
    public void stopWireMockServer() {
      wireMockServer.stop();
    }
  }

