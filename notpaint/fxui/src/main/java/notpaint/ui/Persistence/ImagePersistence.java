package notpaint.ui.Persistence;

import java.io.IOException;

import javafx.scene.image.Image;

public abstract class ImagePersistence {

    public abstract void save(Image image, String imageName) throws IOException;

    public abstract Image load(String imageName);
}
