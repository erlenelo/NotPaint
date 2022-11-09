package notpaint.core.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import notpaint.core.GameInfo;

public interface GameInfoPersistence {
     /**
     * Get a list of all GameInfo classes stored as json in the dataPath.
     *
     * @return List of all gameInfos
     * @throws IOException If unable to read from dataPath
     */
    public List<GameInfo> getAllGameInfos() throws IOException;

    /**
     * Save a gameInfo to datapath. Its name will be its uuid + .json
     *
     * @throws IOException If unable to write to dataPath
     */
    public void saveGameInfo(GameInfo gameInfo) throws IOException;

    /**
     * Get the path to the corresponding image for the given GameInfo.
     *
     * @return String representing the path to image. Could be a file or web
     *         address.
     */
    public String getImagePath(GameInfo info);

    /**
     * Returns the active gameinfo, which is the one that should be used by
     * PaintController to draw.
     *
     * @return The active gameinfo
     */
    public GameInfo getActiveGameInfo();

    /**
     * Sets the active gameinfo, which is the one that should be used by
     * PaintController to draw.
     *
     * @param activeGameInfo The gameinfo to set as active
     */
    public void setActiveGameInfo(GameInfo activeGameInfo);

    static GameInfo parseFromJson(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Stop mapper from considering getXxx() and isXxx() methods for serialization
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);

        GameInfo parsedGameInfo = mapper.readValue(jsonString, GameInfo.class);
        return parsedGameInfo;
    }
}
