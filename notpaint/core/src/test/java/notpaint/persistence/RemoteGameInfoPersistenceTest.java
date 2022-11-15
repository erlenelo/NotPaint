package notpaint.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.net.URI;
import java.net.URISyntaxException;

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

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080

    @BeforeEach
    public void startWireMockServerAndSetup() throws URISyntaxException {
      config = WireMockConfiguration.wireMockConfig().port(8089);
      wireMockServer = new WireMockServer(config.portNumber());
      wireMockServer.start();
      WireMock.configureFor("localhost", config.portNumber());
      remoteGameInfoPersistence = new RemoteGameInfoPersistence(new URI("http://localhost:" + wireMockServer.port() + "/notpaint"));
    }


    @Test
    public void testGetAllGameInfos() {
        stubFor(get(urlEqualTo("/notpaint"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            
            
        )
    );


    }
    
    @AfterEach
    public void stopWireMockServer() {
      wireMockServer.stop();
    }
  }

