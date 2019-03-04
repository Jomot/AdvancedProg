import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class kNNCalculation {
	
	ImageModel imgModel;
	
	private BufferedImage BufferedImage;
	
	public void CalculateDistance() throws Exception {
		
		//Original image - cannot figure out how to use the opened file so this will do for now. Ask in seminar monday.
		kNNSelector fileSelect = new kNNSelector();
        fileSelect.SelectFile();
        BufferedImage input_image = fileSelect.createBufferedImage();

        
		//List<Double> euclideanDistance = new ArrayList<Double>();
		
		//"Compressed image" to compare the original image against. Mnist dataset.
		MNISTReader mnist = new MNISTReader();
		
		//Creates ArrayList to store MnistObjects
		List<MnistObject> mnistList = mnist.MnistRead();
		
		//Comparing the distance between images
		for (int i = 0; i < mnistList.size(); i++) {
			
			//Mnist dataset
			MnistObject distance = mnistList.get(i);
            BufferedImage comparison_image = mnistList.get(i).getTemp();
            int label = mnistList.get(i).getLabel();
            
            //error checking
            if ((input_image.getWidth() != comparison_image.getWidth()) || (input_image.getHeight() != comparison_image.getHeight())) {
                try {
					throw new Exception("The two images have different dimensions");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

            
            double squared_sum = 0;
            for (int y = 0; y < input_image.getHeight(); y++) {
                for (int x = 0; x < input_image.getWidth(); x++) {

                    int rgbvalue_input = input_image.getRGB(x, y);

                    int alpha = (rgbvalue_input >> 24) & 0xff;
                    int red = (rgbvalue_input >> 16) & 0xff;
                    int green = (rgbvalue_input >> 8) & 0xff;
                    int blue = (rgbvalue_input) & 0xff;

                    int grayscale_input = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));

                    int rgbvalue_comparison = comparison_image.getRGB(x, y);

                    alpha = (rgbvalue_comparison >> 24) & 0xff;
                    red = (rgbvalue_comparison >> 16) & 0xff;
                    green = (rgbvalue_comparison >> 8) & 0xff;
                    blue = (rgbvalue_comparison) & 0xff;

                    int grayscale_comparison = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));

                    //This is Eculidean Distance
                    squared_sum += (Math.pow((grayscale_input - grayscale_comparison), 2));
                }
            }
            
            //Set Euclidean Distance in Mnist Object class.
            double euc = Math.sqrt(squared_sum);
            System.out.println("Euclidean Disctance: " + euc);
            distance.setEuclideanDistance(euc);
	}

		Collections.sort(mnistList, new EuclideanComparator());

        Iterator<MnistObject> itr = mnistList.iterator();
        while (itr.hasNext()) {
            MnistObject element = itr.next();
            System.out.println("Label: " + element.getLabel() + " Euclidean Distance: " + element.getEuclideanDistance());
        }
        // dSystem.out.println(distance.getEuclideanDistance());
        System.out.println("Finished processing");
		
	}
	
}
	



