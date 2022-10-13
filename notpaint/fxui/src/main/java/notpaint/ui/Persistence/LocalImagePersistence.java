package notpaint.ui.Persistence;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class LocalImagePersistence extends ImagePersistence {

    
    @Override
    public void save(Image image, String imagePath) throws IOException {
        if(imagePath.startsWith("file:"))
            imagePath = imagePath.substring(5); // Remove first 5 characters

        File output = new File(imagePath);
        output.getParentFile().mkdirs(); // If directory to save img does not exist, create it.
        BufferedImage bufferedImage = javaFXImageToBufferedImage(image);
        System.out.println("Saving image to: " + imagePath);
        ImageIO.write(bufferedImage, "png", output);
    }


    private BufferedImage javaFXImageToBufferedImage(Image image) {
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
