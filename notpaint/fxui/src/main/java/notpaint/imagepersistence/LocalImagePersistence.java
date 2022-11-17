package notpaint.imagepersistence;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * Implementation of ImagePersistence that saves images to disk.
 */
public class LocalImagePersistence extends ImagePersistence {

    @Override
    public void save(Image image, String imagePath) throws IOException {
        if (imagePath.startsWith("file:")) {
            imagePath = imagePath.substring(5); // Remove first 5 characters
        }

        File output = new File(imagePath);
        output.getParentFile().mkdirs(); // If directory to save img does not exist, create it.
        BufferedImage bufferedImage = javaFxImageToBufferedImage(image);
        System.out.println("[LOG] Saving image to: " + imagePath);
        ImageIO.write(bufferedImage, "png", output);
    }
}
