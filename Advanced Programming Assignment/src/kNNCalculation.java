import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class kNNCalculation {
	
	public int outputDigit;
	
	//OutputDigit getter
	public int getOutputDigit() {
		return outputDigit;
	}	

	public void CalculateDistance() throws Exception {
		
		System.out.println("\nProcessing...\n");
		
		//Input image
		kNNSelector fileSelect = new kNNSelector();
        fileSelect.SelectFile();
        BufferedImage input_image = fileSelect.createBufferedImage();
		
		//Creates ArrayList to store MnistObjects.
		MNISTReader mnist = new MNISTReader();
		List<MnistObject> mnistList = mnist.MnistRead();
		
		//Comparing the distance between images.
		for (int i = 0; i < mnistList.size(); i++) {
			
			//Mnist dataset
			MnistObject distance = mnistList.get(i);
            BufferedImage comparison_image = mnistList.get(i).getTemp();
            
            //Error checking for different size images.
            if ((input_image.getWidth() != comparison_image.getWidth()) || (input_image.getHeight() != comparison_image.getHeight())) {
                try {
					throw new Exception("The two images have different dimensions");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            double squared_sum = 0;
            
            //Calculate distance.
            for (int y = 0; y < input_image.getHeight(); y++) {
                for (int x = 0; x < input_image.getWidth(); x++) {

                	//System.out.println("Processing...");
                	
                    int rgbvalue_input = input_image.getRGB(x, y);

                    int red = (rgbvalue_input >> 16) & 0xff;
                    int green = (rgbvalue_input >> 8) & 0xff;
                    int blue = (rgbvalue_input) & 0xff;

                    int grayscale_input = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));

                    int rgbvalue_comparison = comparison_image.getRGB(x, y);

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
            distance.setEuclideanDistance(euc);
	}

		Collections.sort(mnistList, new EuclideanComparator());

        Iterator<MnistObject> itr = mnistList.iterator();
        while (itr.hasNext()) {
            MnistObject element = itr.next();
        }
        
        //This creates a sublist of k=10. Need to calculate the mode of the sublist.
        List<MnistObject> subItems = new ArrayList<MnistObject>(mnistList.subList(0, 10));
        System.out.println("\nk = 1\n");
        for(int i = 0; i < subItems.size(); i++){
            System.out.println("Label: " + subItems.get(i).getLabel() + " Distance: " + subItems.get(i).getEuclideanDistance());
           // System.out.println(mostCommon(subItems).get(i).getLabel());
        }
        
        //Output = first element of the list. k=1. Ideally the below code would be implemented in future work.
        this.outputDigit = mnistList.get(0).getLabel();
        
        System.out.println("\nOutput Digit: " + outputDigit);    
        System.out.println("\nFinished processing.");
              	
	}
	
	
	//The below code was an attempt to implement K = >1 with confidence. 
	//Using a SubList created above, this code would calculate the Mode of the sublist. 
	//This would be used to calculate the output with confidence.
	
	public static <T> T mostCommon(List<T> subItems) {
		 Map<T, Integer> map = new HashMap<>();
		 
		 for (T t: subItems) {
			 Integer val = map.get(t);
			 map.put(t,  val == null ? 1 : val + 1);
		 }
		 
		 Entry<T, Integer> max = null;
		 
		 for (Entry<T, Integer> e : map.entrySet()) {
			 if (max == null || e.getValue() > max.getValue())
				 max = e;
		 }
		
		return max.getKey();
		
	}
	
}
	



