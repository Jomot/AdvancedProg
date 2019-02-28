import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class kNNCalculation {

	public void Calculation() throws IOException {
		
		MNISTReader mnist = new MNISTReader();
		List<MnistObject> mnistObjects = mnist.MnistRead();
		
		MainView display = new MainView();
		display.displayImage(img);
		
		BufferedImage org_image = display.displayImage(img);

		
	

		//ImageFileHandler img_handler = new ImageFileHandler();
		//BufferedImage org_image = img_handler.readFile("E:\\MNIST test images-20190226\\test_images\\tmp\\0_21.png");
		
		for (int i = 0; i < mnistObjects.size(); i++) {
            BufferedImage compressed_image = mnistObjects.get(i).getTemp();
            int label = mnistObjects.get(i).getLabel();
            //error checking
            if ((org_image.getWidth() != compressed_image.getWidth()) || (org_image.getHeight() != compressed_image.getHeight())) {
                try {
					throw new Exception("The two images have different dimensions");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

            double squared_sum = 0;
            for (int y = 0; y < org_image.getHeight(); y++) {
                for (int x = 0; x < org_image.getWidth(); x++) {

                    int rgbvalue_org = org_image.getRGB(x, y);

                    int alpha = (rgbvalue_org >> 24) & 0xff;
                    int red = (rgbvalue_org >> 16) & 0xff;
                    int green = (rgbvalue_org >> 8) & 0xff;
                    int blue = (rgbvalue_org) & 0xff;

                    int grayscale_org = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));

                    int rgbvalue_compressed = compressed_image.getRGB(x, y);

                    alpha = (rgbvalue_compressed >> 24) & 0xff;
                    red = (rgbvalue_compressed >> 16) & 0xff;
                    green = (rgbvalue_compressed >> 8) & 0xff;
                    blue = (rgbvalue_compressed) & 0xff;

                    int grayscale_compressed = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));

                    squared_sum += (Math.pow((grayscale_org - grayscale_compressed), 2));
                }
            }

            double mean_squared_error = squared_sum / (org_image.getHeight() * org_image.getWidth());
            double PSNR = 10 * Math.log10(Math.pow(255, 2) / mean_squared_error);

            System.out.printf("Computed PSNR  %.2f dB for label %d\n", PSNR, label);

		
		
		
	}
	}
}
