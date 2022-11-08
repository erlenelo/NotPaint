package notpaint.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    public void addImageToActiveTap(GameInfo info) {

    }

    void addImage(GameInfo info) {
        if (info.isFinished()) {
            addImageToTab(info, completedTilePane);
        } else {
            addImageToTab(info, activeTilePane);
        } 
    }

    private void addImageToTab(GameInfo info, TilePane pane) {
        ImageView imageView = new ImageView(
            new Image(gameInfoPersistence.getImagePath(info), 200, 140, true, true));
        imageView.maxHeight(150);
        imageView.maxWidth(200);
        imageView.setOnMouseClicked(event -> {
            setSelectedGameInfo(info);
        });
        pane.getChildren().add(imageView);
    }

    
       

    @FXML
    private void initialize() {
        StageUtil.onStageLoaded(secondsPerRound, this::onStageLoaded);        
    }

    private void onStageLoaded(Stage stage) {
        if (stage.getUserData() == null) {
            stage.setUserData(new GameInfoPersistence());
        }
        gameInfoPersistence = (GameInfoPersistence) stage.getUserData();
        onGameInfoPersistenceLoaded();
    }

    private void onGameInfoPersistenceLoaded() {
        usernameText.setText(gameInfoPersistence.getUsername());
        try {
            var allInfos = gameInfoPersistence.getAllGameInfos();
            displayGameInfos(allInfos);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertUtil.errorAlert("ERROR", "Error occured while loading games.");
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

    
    private void deleteUsername() throws IOException {
        PrintWriter writer = new PrintWriter("usernameFile.txt", Charset.forName("UTF-16"));
        writer.print("");
        writer.close();
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
            gameInfoPersistence.setActiveGameInfo(selectedGameInfo);
            App.setRoot("PaintView");
        }
    }
}