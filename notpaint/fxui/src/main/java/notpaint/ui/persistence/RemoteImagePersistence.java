package notpaint.ui.persistence;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * Implementation of ImagePersistence that saves images to a remote server.
 */
public class RemoteImagePersistence extends ImagePersistence {

    @Override
    public void save(Image image, String imagePath) throws IOException {
        BufferedImage bufferedImage = javaFxImageToBufferedImage(image);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteStream);
        
        byte[] imageBytes = byteStream.toByteArray();
        HttpRequest request = HttpRequest.newBuilder(URI.create(imagePath))
            .PUT(BodyPublishers.ofByteArray(imageBytes))
            .build();

        try {
            HttpClient.newBuilder().build().send(request, BodyHandlers.discarding());
        } catch (InterruptedException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
