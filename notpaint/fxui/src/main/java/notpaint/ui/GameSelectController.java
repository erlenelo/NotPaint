package notpaint.ui;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import notpaint.core.GameInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import notpaint.core.Persistence.GameInfoPersistence;
import notpaint.ui.Util.AlertUtil;

import java.util.Comparator;

public class GameSelectController {

    @FXML
    private ScrollPane activeProjectsScrollPane, completedProjectsScrollPane;

    @FXML
    private TilePane activeTilePane, completedTilePane;

    @FXML
    private Text secondsPerRound, iterations, lastEdit, lastEditor;

    private GameInfoPersistence gameInfoPersistence;
    private GameInfo selectedGameInfo;

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
            setSelectedGameInfo(info);
        });
        pane.getChildren().add(imageView);
    }

    @FXML
    private void initialize() {
        // Get the scene from any Node object.
        // Because the scene is not set in initialize, we need to listen for the
        // property to update.
        secondsPerRound.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (newScene != null) {
                var stage = newScene.getWindow();
                // The window property is also initially not set the first time the app starts.
                // If it is null, listen for the property to update an then set it
                if (stage == null) {
                    newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {

                        // Create a persistence instance and set it as the user data for the stage.
                        // This makes it accessible from all other scenes.
                        if (newWindow != null)
                            onStageLoaded((Stage) newWindow);
                    });
                } else {
                    onStageLoaded((Stage) stage);
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

    private void onGameInfoPersistenceLoaded() {
        try {
            var allInfos = gameInfoPersistence.getAllGameInfos();
            displayGameInfos(allInfos);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.ErrorAlert("ERROR", "Error occured while loading games.");
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
        selectedGameInfo = info;
        secondsPerRound.setText(Integer.toString(info.getSecondsPerRound()));
        iterations.setText(String.format("%s / %s", info.getCurrentIterations(), info.getMaxIterations()));
        ;
        lastEdit.setText(info.getLastEditTime().toString());
        lastEditor.setText(info.getLastEditor());
    }

    @FXML
    private void handleNewProject() throws IOException {
        App.setRoot("SettingsView");
    }

    @FXML
    private void handleJoinProject() throws IOException {
        if (selectedGameInfo == null) {
            AlertUtil.WarningAlert("Warning", "You must select a project to join first.");
        }else if(selectedGameInfo.isFinished()) {
            AlertUtil.WarningAlert("Warning", "You cannot join a completed project.");
        } else {
            gameInfoPersistence.setActiveGameInfo(selectedGameInfo);
            App.setRoot("PaintView");
        }
    }
}