package notpaint.core.Persistence;

import java.io.IOException;

import javafx.scene.image.Image;

public abstract class Persistence {

    public abstract void save(Image image, String imageName) throws IOException;

    public abstract Image load(String imageName);
}
