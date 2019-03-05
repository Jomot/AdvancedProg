import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResizeDrawnDigit {
	
	public void resizeConstructor() {
	
	String workingDir = System.getProperty("user.dir");
	File digitOriginal = new File(workingDir + "\\Images\\DrawDigit\\DrawDigit.png");
	File digitResized = new File(workingDir + "\\Images\\DrawDigit\\DigitResized.png");
	resizeImage(digitOriginal, digitResized, 28, 28, "png");
	}
	
	public void resizeImage(File originalImage, File resizedImage, int width, int height, String format) {
		
		ImageFileHandler img_handler = new ImageFileHandler();
		
		try {
			String workingDir = System.getProperty("user.dir");
			BufferedImage original = img_handler.readFile(workingDir + "\\Images\\DrawDigit\\DrawDigit.png");
			BufferedImage resized = new BufferedImage(width, height, original.getType());
			Graphics2D g2 = resized.createGraphics();
			g2.drawImage(original,  0 , 0, width, height, null);
			g2.dispose();
			ImageIO.write(resized, format, resizedImage);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
