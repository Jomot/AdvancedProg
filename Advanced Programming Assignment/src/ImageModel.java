import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageModel {

	private File fp;
	private BufferedImage bufImg = null;
	private BufferedImage drawImg = null;
	
	public ImageModel() {
		// TODO Auto-generated constructor stub
	}

	public void setFilePointer(File fp) {
		this.fp = fp;
	}
	
	public void LoadImage(File fp) throws IOException {
			bufImg = ImageIO.read(fp);
	}
	
	public BufferedImage getRGBImage() {
		return bufImg;
	}
	
	public BufferedImage computeImageDraw(BufferedImage img) {
		return img;
	}
	
}
