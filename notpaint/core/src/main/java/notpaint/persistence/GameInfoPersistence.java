package notpaint.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

/**
 * Class for reading and writing game info.
 */
public abstract class GameInfoPersistence {

    private GameInfo activeGameInfo;

    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get a list of all GameInfo classes stored.
     *
     * @return List of all gameInfos
     * @throws IOException If unable to read
     */
    public abstract List<GameInfo> getAllGameInfos() throws IOException;

    /**
     * Save a gameInfo to datapath. Its name will be its uuid + .json
     *
     * @throws IOException If unable to write to dataPath
     */
    public abstract void saveGameInfo(GameInfo gameInfo) throws IOException;

    /**
     * Get the path to the corresponding image for the given GameInfo.
     *
     * @return String representing the path to image. Could be a file or web
     *         address.
     */
    public abstract String getImagePath(GameInfo info);

    static GameInfo parseFromJson(String jsonString) throws IOException {
        ObjectMapper mapper = JacksonObjectMapperBuilder.getConfiguredObjectMapper();

        GameInfo parsedGameInfo = mapper.readValue(jsonString, GameInfo.class);
        return parsedGameInfo;
    }

    public abstract boolean isGameInfoLocked(GameInfo info);

    public abstract boolean tryLockGameInfo(GameInfo info);

    public abstract void releaseGameInfoLock(GameInfo info);

    /**
     * Returns the active gameinfo, which is the one that should be used by
     * PaintController to draw.
     *
     * @return The active gameinfo
     */
    public GameInfo getActiveGameInfo() {
        return activeGameInfo;
    }

    /**
     * Sets the active gameinfo, which is the one that should be used by
     * PaintController to draw.
     *
     * @param activeGameInfo The gameinfo to set as active
     */
    public void setActiveGameInfo(GameInfo activeGameInfo) {
        this.activeGameInfo = activeGameInfo;
    }
}
