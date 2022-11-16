package notpaint.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.github.tomakehurst.wiremock.WireMockServer;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;



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
            .withBody("[{\"words\": [ \"test1\", \"test2\", \"test3\"]}]")
        ));
        List<GameInfo> allGameInfos = remoteGameInfoPersistence.getAllGameInfos();
        assertEquals(1, allGameInfos.size());
        assertEquals("test3", allGameInfos.get(0).getWord());
    }

    @Test
    public void testSaveGameInfo() throws IOException{
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

    // @Test
    // public void isGameInfoLocked(){
    //   stubFor(get(urlEqualTo("/lock?uuid={uuid}"))
    //   .withHeader("Accept", equalTo("application/json"))
    //   .willReturn((ResponseDefinitionBuilder) ((MappingBuilder) aResponse()
    //       .withStatus(200))
    //       .withQueryParam("uuid", equalTo("123"))
    //   ));
    //   List<GameInfo> gameinfos = remoteGameInfoPersistence.getAllGameInfos();

    //   boolean isLocked = remoteGameInfoPersistence.isGameInfoLocked("123");
      


      


    
        
        
  
      
    

    
    @AfterEach
    public void stopWireMockServer() {
      wireMockServer.stop();
    }
  
  }

