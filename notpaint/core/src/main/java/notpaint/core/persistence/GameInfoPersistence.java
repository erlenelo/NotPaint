package notpaint.core.persistence;

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
}
