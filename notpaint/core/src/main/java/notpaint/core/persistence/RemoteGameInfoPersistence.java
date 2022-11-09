package notpaint.core.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import notpaint.core.GameInfo;

public class RemoteGameInfoPersistence implements GameInfoPersistence {

    private URI serverURI;

    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT_HEADER = "Accept";

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
            ObjectMapper mapper = new ObjectMapper();
            // Stop mapper from considering getXxx() and isXxx() methods for serialization
            mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);

            return Arrays.asList(mapper.readValue(response.body(), GameInfo[].class));
        } catch (IOException | InterruptedException exception) {
            throw new IOException(exception);
        }


    }

    @Override
    public void saveGameInfo(GameInfo gameInfo) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getImagePath(GameInfo info) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameInfo getActiveGameInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setActiveGameInfo(GameInfo activeGameInfo) {
        // TODO Auto-generated method stub
        
    }
    
}
