package notpaint.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;


public class RemoteGameInfoPersistence extends GameInfoPersistence {

    private URI serverURI;

    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";


    public RemoteGameInfoPersistence(URI serverURI) {
        this.serverURI = serverURI;
        
    }

    @Override
    public List<GameInfo> getAllGameInfos() throws IOException {
        HttpRequest request = HttpRequest.newBuilder(serverURI.resolve("allGameInfos"))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .GET()
            .build();
        try {
            var response = HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
            var mapper = JacksonObjectMapperBuilder.getConfiguredObjectMapper();

            var infoArray = mapper.readValue(response.body(), GameInfo[].class);
            return Arrays.asList(infoArray);
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }


    }

    @Override
    public void saveGameInfo(GameInfo gameInfo) throws IOException {
        var mapper = JacksonObjectMapperBuilder.getConfiguredObjectMapper();
        String infoJson = mapper.writeValueAsString(gameInfo);
        
        HttpRequest request = HttpRequest.newBuilder(serverURI.resolve("saveGameInfo"))
            .PUT(HttpRequest.BodyPublishers.ofString(infoJson))
            .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .build();
        try {
            HttpClient.newBuilder().build().send(request, BodyHandlers.discarding());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String getImagePath(GameInfo info) {
        // Server takes following URL format:
        // {baseUri}/image?uuid={uuid}
        return serverURI.resolve("image").toString() + "?uuid=" + info.getUuid().toString();
    }

    @Override
    public boolean isGameInfoLocked(GameInfo info) {
        HttpRequest request = HttpRequest.newBuilder(serverURI.resolve("lock?uuid=" + info.getUuid().toString()))
            .GET()
            .build();
        try {
            var response = HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
            return Boolean.parseBoolean(response.body());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean tryLockGameInfo(GameInfo info) {
        HttpRequest request = HttpRequest.newBuilder(serverURI.resolve("lock?uuid=" + info.getUuid().toString()))
            .POST(BodyPublishers.noBody())
            .build();
        try {
            var response = HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
            return Boolean.parseBoolean(response.body());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void releaseGameInfoLock(GameInfo info) {
        HttpRequest request = HttpRequest.newBuilder(serverURI.resolve("lock?uuid=" + info.getUuid().toString()))
            .DELETE()
            .build();
        try {
            HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        
    }
}
