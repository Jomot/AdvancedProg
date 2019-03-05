import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		try {
			
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch(Exception e) {
			System.out.println("Error occured while setting "
					+ "up the look and feel" + e.toString());
		}
		
		
		/*
		 * Initiate the UI, and place it in the Event Dispatch Thread
		 */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					
                	new MainView();
                	
				} catch (Exception e) {
					
					System.out.println("Error occured while initiating "
							+ "the Swing thread. Please try again later..." + e.toString());
				}
            }
        });
        
        System.out.println("Choose File to display.");
        
//		MNISTReader reader = new MNISTReader();
//		reader.MnistRead();
//		
//		MnistObject distance = new MnistObject();
//		distance.getEuclideanDistance();
		
	}
		
}


