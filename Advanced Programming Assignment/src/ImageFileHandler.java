import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageFileHandler extends FileHandler{

	
	int width;
	int height;
	String image_type;
	BufferedImage img;
	
	public ImageFileHandler() {
		// TODO Auto-generated constructor stub
	}
	public ImageFileHandler(String file_name){
		super(file_name);
		img = null;
		
	}
	
	@Override
	public void readFile() throws IOException {
		// TODO Auto-generated method stub
		if(fp.isFile() && fp.exists()) {
			img = ImageIO.read(fp);
		}
	}

	@Override
	public void writeFile(String file_name) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public BufferedImage readFile(String file_name) {
		
		try {
			img = ImageIO.read(new File(file_name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			img = null;
		}
		return img;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	

	
}
