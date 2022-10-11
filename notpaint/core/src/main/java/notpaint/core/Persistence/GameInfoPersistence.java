package notpaint.core.Persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;

import notpaint.core.GameInfo;

public class GameInfoPersistence {
    
    private String dataPath = "data";

    private GameInfo activeGameInfo;


    public GameInfoPersistence(String dataPath) {
        this.dataPath = dataPath;
    }

    public GameInfoPersistence() {}

    /**
     * Get a list of all GameInfo classes stored as json in the dataPath
     * @return List of all gameInfos
     * @throws IOException
     */
    public List<GameInfo> getAllGameInfos() throws IOException {
        File file = new File(dataPath);
        if(!file.exists())
            file.mkdirs();

        if(!file.isDirectory())
            throw new IllegalStateException("dataPath not set to a directory");
        
        var allFiles = file.listFiles();

        ArrayList<GameInfo> gameInfoList = new ArrayList<>();

        for(var gameInfoJson : allFiles) {
            if(gameInfoJson.toString().endsWith(".json") == false) {
                continue;
            }

            String jsonString = Files.readString(gameInfoJson.toPath());
            gameInfoList.add(parseFromJson(jsonString));            
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
     * @param gameInfo The gameInfo to save
     * @throws IOException 
     */
    public void saveGameInfo(GameInfo gameInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Stop mapper from considering getXxx() and isXxx() methods for serialization
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
        
        
        mapper.writeValue(new File(dataPath + "\\" + gameInfo.getUuid().toString() + ".json"), gameInfo);        
    }

    /**
     * Returns the active gameinfo, which is the one that should be used by PaintController to draw
     * @return
     */
    public GameInfo getActiveGameInfo() {
        return activeGameInfo;
    }

    /**
     * Sets the active gameinfo, which is the one that should be used by PaintController to draw
     * @param activeGameInfo The gameinfo to set as active
     */
    public void setActiveGameInfo(GameInfo activeGameInfo) {
        this.activeGameInfo = activeGameInfo;
    }

}
