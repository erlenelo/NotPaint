package notpaint.core.Persistence;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class LocalPersistence extends Persistence {

    @Override
    public void Save(Image image, String imagePath) throws IOException {
        // TODO Auto-generated method stub
        File output = new File(imagePath);
        BufferedImage bufferedImage = javaFXImageToBufferedImage(image);
        
        ImageIO.write(bufferedImage, "png", output);               
    }

    @Override
    public Image Load(String imagePath) {
        // TODO Auto-generated method stub
        File file = new File(imagePath);
        Image image = new Image(file.toString());
        return image;
    }

    private BufferedImage javaFXImageToBufferedImage(Image image) {
		BufferedImage img = new BufferedImage((int)image.getWidth(), (int)image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		PixelReader fxImageReader =  image.getPixelReader();
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				int color = fxImageReader.getArgb(x, y);
				img.setRGB(x, y, color);
			}
		}
		return img;
	}
    
}
