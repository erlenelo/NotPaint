package notpaint.restserver;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
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


    private Path basePath = Paths.get("data");

    /**
     * HTTP GET method for getting an image.
     *
     * @param uuid the UUID of the image to get
     * @return byte array of the image in PNG format
     * @throws IOException if the image could not be read
     */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getImage(
            @RequestParam(value = "uuid") UUID uuid) throws IOException {
        
        Path imageFile = basePath.resolve(uuid + ".png");
    
        if (!Files.exists(imageFile)) {
            return ResponseEntity.notFound().build();
        }
        
        try (var in = Files.newInputStream(imageFile)) {
            return ResponseEntity.ok(in.readAllBytes());
        }

    }

    /**
     * HTTP PUT method for saving an image. Overwrites any existing image with the
     * same UUID.
     *
     * @param uuid      the UUID of the image to save
     * @param imageData the image data to save, as byte array in PNG format-
     * @return 200 OK if the image was saved successfully, 500 Internal Server Error
     *         otherwise
     */
    @PutMapping("/image")
    public ResponseEntity<String> putImage(
            @RequestParam(value = "uuid") UUID uuid, @RequestBody() byte[] imageData) {
        Path path = basePath.resolve(uuid + ".png");
        try {
            Path parentPath = path.getParent();
            if (parentPath != null) {
                Files.createDirectories(parentPath);
            }
            Files.write(path, imageData);
        } catch (IOException ioe) {
            return ResponseEntity.internalServerError().body("Failed to save image");
        }
        return ResponseEntity.ok().build();
    }

    void setBasePath(Path basePath) {
        this.basePath = basePath;
    }
}
