package notpaint.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for saving and loading game info on local disk.
 */
public class GameInfoPersistence {

    private Path dataPath;

    private GameInfo activeGameInfo;

    public GameInfoPersistence(Path dataPath) {
        this.dataPath = dataPath;
    }

    public GameInfoPersistence() {
        this.dataPath = Paths.get("data");
    }

    /**
     * Get a list of all GameInfo classes stored as json in the dataPath.
     *
     * @return List of all gameInfos
     * @throws IOException If unable to read from dataPath
     */
    public List<GameInfo> getAllGameInfos() throws IOException {
        // File file = new File(dataPath);
        // If the file doesnt exist, create a directory at the path.

        if (!Files.exists(dataPath)) {
            Files.createDirectories(dataPath);
        }
        ArrayList<GameInfo> gameInfoList = new ArrayList<>();

        try (var allFilesStream = Files.list(dataPath)) {
            List<Path> allFiles = allFilesStream.toList();
            for (Path gameInfoPath : allFiles) {
                // Skip non-json files
                if (gameInfoPath.toString().endsWith(".json") == false) {
                    continue;
                }

                String jsonString = Files.readString(gameInfoPath);
                gameInfoList.add(parseFromJson(jsonString));
            }
        }

        return gameInfoList;
    }

    private static GameInfo parseFromJson(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Stop mapper from considering getXxx() and isXxx() methods for serialization
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);

        GameInfo parsedGameInfo = mapper.readValue(jsonString, GameInfo.class);
        return parsedGameInfo;
    }

    /**
     * Save a gameInfo to datapath. Its name will be its uuid + .json
     *
     * @throws IOException If unable to write to dataPath
     */
    public void saveGameInfo(GameInfo gameInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Stop mapper from considering getXxx() and isXxx() methods for serialization
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);

        // If the data folder doesn't exist, create it
        if (!Files.exists(dataPath)) {
            Files.createDirectories(dataPath);
        }

        // Serialize and write to json file
        mapper.writeValue(Paths.get(
            dataPath.toString(), gameInfo.getUuid().toString() + ".json").toFile(), gameInfo);
    }

    /**
     * Get the path to the corresponding image for the given GameInfo.
     *
     * @return String representing the path to image. Could be a file or web
     *         address.
     */
    public String getImagePath(GameInfo info) {
        String imageName = info.getUuid().toString() + ".png";
        return "file:" + Paths.get(dataPath.toString(), imageName).toString();
    }

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
