package notpaint.ui.Persistence;

import java.io.IOException;

import javafx.scene.image.Image;

public abstract class ImagePersistence {

    public abstract void save(Image image, String imageName) throws IOException;

    
    /**
     * @param imagePath Starts with "file:" to load from disk, start with "http:" to load from web
     * @return Image found at imagePath
     */
    public Image load(String imagePath) {
        Image image = new Image(imagePath);
        return image;
    }
}
