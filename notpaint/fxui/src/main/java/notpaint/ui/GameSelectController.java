package notpaint.ui;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import notpaint.core.GameInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import notpaint.core.Persistence.GameInfoPersistence;
import java.util.Comparator;

public class GameSelectController {

    @FXML
    private ScrollPane activeProjectsScrollPane, completedProjectsScrollPane;

    @FXML
    private TilePane activeTilePane, completedTilePane;

    @FXML
    private Button secondaryButton; // Used to get reference to scene

    private GameInfoPersistence gameInfoPersistence;

    public void addImageToActiveTap(GameInfo info) {

    }

    void addImage(GameInfo info) {
        if (info.isFinished())
            addImageToTab(info, completedTilePane);
        else
            addImageToTab(info, activeTilePane);
    }

    private void addImageToTab(GameInfo info, TilePane pane) {
        System.out.println("Loading Image path: " + info.getImagePath());
        ImageView imageView = new ImageView(new Image(info.getImagePath(), 200, 140, true, true));
        imageView.maxHeight(150);
        imageView.maxWidth(200);
        imageView.setOnMouseClicked(event -> {
            updateSidePanelInfo(info);
        });
        pane.getChildren().add(imageView);
    }

    @FXML
    private void initialize() {
        // Because the scene is not set initially in initialize, we need to wait for the
        // property to update

        secondaryButton.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (newScene != null) {
                var stage = newScene.getWindow();
                if(stage == null) {
                // The window property is also initially not set.
                    newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                        // Create a persistence instance and set it as the user data for the stage.
                        // This makes it accessible from all other scenes.
                        onStageLoaded((Stage) newWindow);
                    });
                } else {
                    onStageLoaded((Stage)stage);
                }
            }
        });
    }

    private void onStageLoaded(Stage stage) {
        if (stage.getUserData() == null) {
            stage.setUserData(new GameInfoPersistence());
        }
        gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
        onGameInfoPersistenceLoaded();
    }

    private void updateSidePanelInfo() {

    }

    private void onGameInfoPersistenceLoaded() {
        try {
            var allInfos = gameInfoPersistence.getAllGameInfos();
            displayGameInfos(allInfos);
        } catch (IOException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Error occured while loading games.");
            alert.setHeaderText("ERROR");
            alert.show();
        }
    }

    private void displayGameInfos(List<GameInfo> infos) {
        infos.sort(new Comparator<GameInfo>() {
            @Override
            public int compare(GameInfo o1, GameInfo o2) {
                return o1.getLastEditTime().compareTo(o2.getLastEditTime());
            }
        });
        for (var info : infos) {
            addImage(info);
        }
    }

    private void setSelectedGameInfo(GameInfo info) {

    }

    @FXML
    private void handleNewProject() throws IOException {
        App.setRoot("SettingsView");

    }

    @FXML
    private void handleJoinProject() {

    }
}