package notpaint.restserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for images.
 */
@RestController
public class ImageController {
    
    /**
     * HTTP GET method for getting an image.
     *
     * @param uuid the UUID of the image to get
     * @return byte array of the image in PNG format
     * @throws IOException if the image could not be read
     */
    @GetMapping(
        value = "/image",
        produces = MediaType.IMAGE_PNG_VALUE
        )
    public @ResponseBody ResponseEntity<byte[]> getImage(
        @RequestParam(value = "uuid") String uuid) throws IOException {
        File imageFile = new File("data/" + uuid + ".png");
        if (!imageFile.exists()) {
            return ResponseEntity.notFound().build();
        }
        try (InputStream in = new FileInputStream(imageFile)) {
            return ResponseEntity.ok(in.readAllBytes());
        }
        
    }
    
    /**
     * HTTP PUT method for saving an image. Overwrites any existing image with the same UUID.
     *
     * @param uuid the UUID of the image to save
     * @param imageData the image data to save, as byte array in PNG format-
     * @return 200 OK if the image was saved successfully, 500 Internal Server Error otherwise
     */
    @PutMapping("/image")
    public ResponseEntity<String> putImage(
        @RequestParam(value = "uuid") String uuid, @RequestBody() byte[] imageData) {
        Path path = Paths.get("data", uuid + ".png");
        try {
            Files.write(path, imageData);
        } catch (IOException ioe) {
            return ResponseEntity.internalServerError().body("Failed to save image");
        }
        return ResponseEntity.ok().build();
    }
}
