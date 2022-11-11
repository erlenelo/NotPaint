package notpaint.core.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import notpaint.core.GameInfo;

/**
 * Class for saving and loading game info on local disk.
 */
public class LocalGameInfoPersistence extends GameInfoPersistence {

    private Path dataPath;

    public LocalGameInfoPersistence(Path dataPath) {
        this.dataPath = dataPath;
    }

    public LocalGameInfoPersistence() {
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
                gameInfoList.add(GameInfoPersistence.parseFromJson(jsonString));
            }
        }

        return gameInfoList;
    }

    

    /**
     * Save a gameInfo to datapath. Its name will be its uuid + .json
     *
     * @throws IOException If unable to write to dataPath
     */
    public void saveGameInfo(GameInfo gameInfo) throws IOException {
        var mapper = JacksonObjectMapperBuilder.getConfiguredObjectMapper();

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



}
