import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MNISTReader {
	
	public List<MnistObject> MnistRead() {
		
		String workingDir = System.getProperty("user.dir");
        String train_label_filename = workingDir + "\\Images\\t10k-labels.idx1-ubyte";
        String train_image_filename = workingDir + "\\Images\\t10k-images.idx3-ubyte";

//        String train_label_filename = "E:\\t10k-labels.idx1-ubyte";
//        String train_image_filename = "E:\\t10k-images.idx3-ubyte";

        DataInputStream label_data_stream = null;
        DataInputStream image_data_stream = null;
        List<MnistObject> mnist_database_list = null;
        
        try {
        	System.out.println("\n--------------------------");
        	System.out.println("Loading Mnist Dataset...");
        	System.out.println("--------------------------");
        	
            label_data_stream = new DataInputStream(new FileInputStream(train_label_filename));
            image_data_stream = new DataInputStream(new FileInputStream(train_image_filename));

            int startcode_label = label_data_stream.readInt();
            int startcode_img = image_data_stream.readInt();
            System.out.println("Label start code  = " + startcode_label);
            System.out.println("Image start code = " + startcode_img);

            int number_of_labels = label_data_stream.readInt();
            int number_of_images = image_data_stream.readInt();
            System.out.println("Numbers of labels: " + number_of_labels);
            System.out.println("Number of images: " + number_of_images);

            int image_height = image_data_stream.readInt();
            int image_width = image_data_stream.readInt();
            System.out.println("Image height: " + image_height);
            System.out.println("Image width: " + image_width);

            //byte array for labels
            byte[] label_data = new byte[number_of_labels];

            //byte array for images
            int image_size = image_height * image_width;
            byte[] image_data = new byte[image_size * number_of_images];

            label_data_stream.read(label_data);

            image_data_stream.read(image_data);

            int[][] image;

            //Creates an ArrayList that holds MnistObjects.
            mnist_database_list = new ArrayList<MnistObject>();

            for (int i = 0; i < number_of_labels; i++) {
                int label = label_data[i];
                image = new int[image_width][image_height];

                BufferedImage temp = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
                for (int row = 0; row < image_height; row++) {
                    for (int col = 0; col < image_width; col++) {
                        image[row][col] = image_data[(i * image_size) + ((row * image_width) + col)];

                        int gray = 255 - image[row][col];
                        int rgb_value = 0xFF000000 | (gray << 16) |
                                					 (gray << 8) |
                                					 (gray);

                        temp.setRGB(col, row, rgb_value);
                    }
                }

                //Populates the list
                MnistObject obj = new MnistObject(temp, label);
                mnist_database_list.add(obj);
            }
            System.out.println("--------------------------------");
            System.out.println("Mnist dataset loaded. Total size: " + mnist_database_list.size());
            System.out.println("--------------------------------");


            BufferedImage unknown;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mnist_database_list;
        
    }

	
}