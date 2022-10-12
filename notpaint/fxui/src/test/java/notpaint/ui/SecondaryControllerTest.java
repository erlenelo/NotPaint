package notpaint.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecondaryControllerTest extends ApplicationTest {
    private SecondaryController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SecondaryController.class.getResource("secondary.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        controller = fxmlLoader.getController();
    }

    @Test
    public void testAuthorTextField() {
        clickOn("#authorTextField");
        write("test");
    }

}
