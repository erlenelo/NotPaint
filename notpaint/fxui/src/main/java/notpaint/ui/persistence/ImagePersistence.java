package notpaint.ui.persistence;

import java.io.IOException;
import javafx.scene.image.Image;

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
}
