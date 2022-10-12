package notpaint.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import notpaint.core.GameInfo;

public class SecondaryController {

    List<GameInfo> allGameInfos;
    HashMap<GameInfo, ImageView> screenMap;

    GameInfo info;

    @FXML
    private ScrollPane activeProjectsScrollPane, completedProjectsScrollPane;

    @FXML
    private TilePane activeTilePane, completedTilePane;



    public void addImageToActiveTap() {
        ImageView imageView = new ImageView(info.getImagePath());
        activeTilePane.getChildren().add(imageView);
    }

    public void deleteImageActiveTab() {
        activeTilePane.getChildren().clear();
    }

    public void addImageToCompletedTap() {
        if (info.isFinished() == true) {
            ImageView imageView = new ImageView(info.getImagePath());
            completedTilePane.getChildren().add(imageView);
            deleteImageActiveTab();
        }
        
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("SettingsView");
    }
}