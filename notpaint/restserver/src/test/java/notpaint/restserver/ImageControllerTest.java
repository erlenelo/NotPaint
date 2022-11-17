package notpaint.restserver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

/**
 * Test class for {@link GameInfoController}.
 */
public class ImageControllerTest {
    
    static ImageController controller;
    static Path dataPath;
    
    /**
     * Setup the test class by creating a new controller and setting a test path.
     */
    @BeforeAll
    public static void setup() {
        controller = new ImageController();
        dataPath = Paths.get("testData_" + UUID.randomUUID().toString());
        controller.setBasePath(dataPath);
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        var files = Files.list(dataPath).toList();
        for (var file : files) { // Delete every file in datapath dir
            Files.deleteIfExists(file);
        }
        Files.deleteIfExists(dataPath); // Delete datapath dir
    }

    @Test
    public void testSaveAndGetImage() throws IOException {
        byte[] fakeImage = new byte[500];
        fakeImage[420] = 69;
        UUID fakeUuid = UUID.randomUUID();

        controller.putImage(fakeUuid, fakeImage);

        ResponseEntity<byte[]> imageResponse = controller.getImage(fakeUuid);
        
        assertTrue(imageResponse.hasBody());
        byte[] recievedImage = imageResponse.getBody();
        if (recievedImage == null) {
            throw new AssertionError("Response body is null");
        }
        assertTrue(recievedImage.length == 500);
        assertTrue(recievedImage[420] == 69);
        assertTrue(recievedImage[0] == 0);
        assertTrue(recievedImage[499] == 0);
    }

    @Test
    public void testNotFoundImage() throws IOException {
        // This random UUID should not be in the database
        UUID fakeUuid = UUID.randomUUID();
        ResponseEntity<byte[]> imageResponse = controller.getImage(fakeUuid);
        assertTrue(imageResponse.getStatusCodeValue() == 404);
    }

}
