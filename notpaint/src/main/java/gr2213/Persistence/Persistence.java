package gr2213.Persistence;

import java.io.IOError;
import java.io.IOException;

import javafx.scene.image.Image;

public abstract class Persistence {
    
    public abstract void Save(Image image, String imageName) throws IOException;

    public abstract Image Load(String imageName);
}
