package notpaint.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import notpaint.persistence.GameInfo;
import notpaint.persistence.GameInfoPersistence;
import notpaint.ui.util.AlertUtil;
import notpaint.ui.util.StageUtil;

/**
 * Controller for the view that handles the game selection.
 */
public class GameSelectController {

    @FXML
    private ScrollPane completedProjectsScrollPane;

    @FXML
    private ScrollPane activeProjectsScrollPane;

    @FXML
    private Text secondsPerRound;

    @FXML
    private Text iterations;

    @FXML
    private Text lastEdit;

    @FXML
    private Text lastEditor;

    @FXML
    private Text usernameText;

    @FXML
    private TilePane completedTilePane;

    @FXML
    private TilePane activeTilePane;

    private GameInfoPersistence gameInfoPersistence;
    private GameInfo selectedGameInfo;

    private static ScheduledService<Void> refreshService;

    HBox addImage(GameInfo info) {
        if (info.isFinished()) {
            return addImageToTab(info, completedTilePane);
        } else {
            return addImageToTab(info, activeTilePane);
        }
    }

    private HBox addImageToTab(GameInfo info, TilePane pane) {

        ImageView imageView = new ImageView(
                new Image(gameInfoPersistence.getImagePath(info), 200, 140, true, true));
        imageView.maxHeight(150);
        imageView.maxWidth(200);

        // Dark border for each project
        HBox imageHbox = new HBox();
        imageHbox.setId("projectBorder");
        imageHbox.getChildren().add(imageView);

        // Blank border to add margins to projects, and highlight selected project.
        HBox imageSpace = new HBox();
        imageSpace.setId("unselected");
        imageSpace.getChildren().add(imageHbox);

        imageView.setOnMouseClicked(event -> {
            try {
                pane.lookupAll("#selected").forEach(node -> {
                    node.setId("unselected");
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            setSelectedGameInfo(info);
            imageSpace.setId("selected");

        });

        pane.getChildren().add(imageSpace);
        return imageSpace;
    }

    @FXML
    private void initialize() {
        StageUtil.onGameInfoPersistenceLoaded(
            secondsPerRound, this::onGameInfoPersistenceLoaded);        
    }

    private void onGameInfoPersistenceLoaded(GameInfoPersistence persistence) {
        this.gameInfoPersistence = persistence;
        usernameText.setText(gameInfoPersistence.getUsername());
        try {
            var allInfos = gameInfoPersistence.getAllGameInfos();
            displayGameInfos(allInfos);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error occured while loading games.");
        }

        // Stop existing service if it exists
        if(refreshService != null) {
            refreshService.cancel();
        }

        // Start service to refresh automatically periodically        
        refreshService = new ScheduledService<Void> () {
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    protected Void call() {
                        try {
                            Platform.runLater(() -> handleRefresh());                            
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        refreshService.setPeriod(Duration.seconds(2));
        refreshService.start();
    }

    private void displayGameInfos(List<GameInfo> infos) {
        infos.sort(new Comparator<GameInfo>() {
            @Override
            public int compare(GameInfo o1, GameInfo o2) {
                return o1.getLastEditTime().compareTo(o2.getLastEditTime());
            }
        });
        for (var info : infos) {
            HBox image = addImage(info);
            // If a game is already selected, select it again after refreshing.
            if(selectedGameInfo != null && info.getUuid().equals(selectedGameInfo.getUuid())) {
                setSelectedGameInfo(info);
                image.setId("selected");
            }
            boolean isLocked = gameInfoPersistence.isGameInfoLocked(info);
            if(isLocked) {
                image.getStyleClass().add("locked-project");
            }
            
        }
    }

    void setSelectedGameInfo(GameInfo info) {
        selectedGameInfo = info;
        secondsPerRound.setText(Integer.toString(info.getSecondsPerRound()));
        iterations.setText(String.format(
                "%s / %s", info.getCurrentIterations(), info.getMaxIterations()));
        lastEdit.setText(info.getLastEditTime().toString());
        lastEditor.setText(info.getLastEditor());
    }

    @FXML
    private void handleChangeUsername() throws IOException {
        deleteUsername();
        App.setRoot("UsernameSelectView");
    }

    /**
     * Empties the username file, so the user is no longer remembered.
     *
     * @throws IOException if the file cannot be emptied.
     */
    @FXML
    public void deleteUsername() throws IOException {
        PrintWriter writer = new PrintWriter("usernameFile.txt", Charset.forName("UTF-16"));
        writer.print("");
        writer.close();
    }

    @FXML
    private void handleRefresh() {
        activeTilePane.getChildren().clear();
        completedTilePane.getChildren().clear();
        try {
            var allInfos = gameInfoPersistence.getAllGameInfos();
            displayGameInfos(allInfos);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error occured while loading games.");
        }
    }

    @FXML
    private void handleNewProject() throws IOException {
        App.setRoot("SettingsView");
    }

    @FXML
    private void handleJoinProject() throws IOException {
        if (selectedGameInfo == null) {
            AlertUtil.warningAlert("Warning", "You must select a project to join first.");
        } else if (selectedGameInfo.isFinished()) {
            AlertUtil.warningAlert("Warning", "You cannot join a completed project.");
        } else if (gameInfoPersistence.getUsername().equals(selectedGameInfo.getLastEditor())) {
            AlertUtil.warningAlert("Warning",
                    "You cannot draw on the same prosject two times in a row.");
        } else {
            // Check if the game is locked (currently being edited by someone else)
            if (gameInfoPersistence.tryLockGameInfo(selectedGameInfo)) {
                gameInfoPersistence.setActiveGameInfo(selectedGameInfo);
                App.setRoot("PaintView");
            } else {
                AlertUtil.warningAlert(
                    "Warning", "This project is currently being edited by someone else.");
            }
            
        }
    }
}