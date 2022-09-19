package gr2213.Persistence;

import javafx.scene.image.Image;

public abstract class Persistence {
    
    public abstract void Save(Image image, String imageName);

    public abstract Image Load(String imageName);
}
