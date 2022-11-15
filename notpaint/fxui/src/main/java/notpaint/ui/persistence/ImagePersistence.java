package notpaint.ui.persistence;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * Interface for saving and loading images.
 * Should be implemented by classes that save and load images from different sources.
 */
public abstract class ImagePersistence {

    public abstract void save(Image image, String imageName) throws IOException;

    
    /**
     * Loads an image from the given path.
     *
     * @param imagePath Starts with "file:" to load from disk, start with "http:" to load from web
     * @return Image found at imagePath
     */
    public Image load(String imagePath) {
        Image image = new Image(imagePath);
        return image;
    }

    static BufferedImage javaFxImageToBufferedImage(Image image) {
        BufferedImage img = new BufferedImage((int) image.getWidth(), (int) image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        PixelReader fxImageReader = image.getPixelReader();
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int color = fxImageReader.getArgb(x, y);
                img.setRGB(x, y, color);
            }
        }
        return img;
    }
}
