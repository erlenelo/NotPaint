package notpaint.restserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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

@RestController
public class ImageController {
    
    @GetMapping(
        value = "/image",
        produces = MediaType.IMAGE_PNG_VALUE
        )
    public @ResponseBody ResponseEntity<byte[]> getImage(@RequestParam(value = "uuid") String uuid) throws IOException {
        File imageFile = new File("data/" + uuid + ".png");
        if(!imageFile.exists())
            return ResponseEntity.notFound().build();
        InputStream in = new FileInputStream(imageFile);
        return ResponseEntity.ok(in.readAllBytes());
    }
    
    @PutMapping("/image")
    public ResponseEntity putImage(
        @RequestParam(value = "uuid") String uuid, @RequestBody() byte[] imageData) {
        Path path = Paths.get("data", uuid + ".png");
        try {
            Files.write(path, imageData);
        } catch(IOException ioe) {
            return ResponseEntity.internalServerError().body("Failed to save image");
        }
        return ResponseEntity.ok().build();
    }
}
