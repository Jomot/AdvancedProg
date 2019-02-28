import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class MainView {

    File fp;
    BufferedImage selectedFile;
	
	
	// Declaring main view components
		private JFrame mainWindow;
		
		private JButton drawCanvasButton;
		private JButton loadImageButton;
		private JButton compareButton;
		private JButton openFileBtn;
		private JTextField fileNameTxt;
		private JLabel fileNameLbl;
		

		private JPanel displayPanel;
		private JLabel imageLabel;
		
		private JFileChooser fileChooser;
		
		MainController controller;
		ImageModel imgModel;
		
		// Default constructor
		public MainView() {
	        
			//controller = new MainController();
			//controller = new MainController(this);
			imgModel = new ImageModel();
			controller = new MainController(this, imgModel);
			
			// Create and set up the window.
			mainWindow = new JFrame("Main window title");
			mainWindow.setLayout(new BorderLayout());
		
			// Creating file selection panel
	        JPanel fileSelectPanel = new JPanel();
	        
	        fileNameLbl = new JLabel ("File name: ");
	        fileNameTxt = new JTextField(50);
	        fileNameTxt.setEnabled(false); 
	        openFileBtn = new JButton("Open File");
		
	        // Add components to the JPanel
	        fileSelectPanel.add(fileNameLbl);
	        fileSelectPanel.add(fileNameTxt);
	        fileSelectPanel.add(openFileBtn);
		
	        // Add JPanel to the JFrame's north position
	        // mainWindow.add(fileSelectPanel, BorderLayout.PAGE_START);
			
	        // Creating main operation button panel
	        JPanel opButtonPanel = new JPanel();
			
	        loadImageButton = new JButton("Load Digit");
	        loadImageButton.setPreferredSize(new Dimension(150, 50));
	        drawCanvasButton = new JButton("Draw Digit");
	        drawCanvasButton.setPreferredSize(new Dimension(150, 50));
	        
	        opButtonPanel.add(loadImageButton);
	        opButtonPanel.add(drawCanvasButton);
	        
	     // A JPanel with box layout
	        JPanel northLayout = new JPanel();
	        northLayout.setLayout(new BoxLayout(northLayout,
	        		BoxLayout.Y_AXIS));
	        
	        // add both flow layouts to northLayout
	        northLayout.add(fileSelectPanel);
	        northLayout.add(opButtonPanel);
	        
	        // add northLayout to mainWindow's north
	        mainWindow.add(northLayout, BorderLayout.NORTH);
	        
	     // instantiate a new JPanel to hold image labels
	        displayPanel = new JPanel();
	        displayPanel.setPreferredSize(new Dimension(250, 150));
	        displayPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	        imageLabel = new JLabel();
	        displayPanel.add(imageLabel);
	        
	        // add image panel to the centre of the main window
	        mainWindow.add(displayPanel, BorderLayout.CENTER);
	        
			// Make the program quit, as the window is closed
			mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// Set the width and height of mainWindow
			// mainWindow.setSize(new Dimension(200, 100));;
			mainWindow.pack();
	        // Display the window.
			mainWindow.setVisible(true);
			
			//////////////////////////////
			
			// Creating main operation button panel
	        JPanel subButtonPanel = new JPanel();
			
	        compareButton = new JButton("Compare Digit");
	        compareButton.setPreferredSize(new Dimension(150, 25));
	       
	        
	        subButtonPanel.add(compareButton);

	        
	     // A JPanel with box layout
	        JPanel southLayout = new JPanel();
	        southLayout.setLayout(new BoxLayout(southLayout,
	        		BoxLayout.Y_AXIS));
	        
	        // add both flow layouts to northLayout
	        southLayout.add(subButtonPanel);
	        
	        // add northLayout to mainWindow's north
	        mainWindow.add(southLayout, BorderLayout.SOUTH);
					
			// Using annonymous inner class
	        
	        //Open File Button
			openFileBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//FileSelect(e);
					
					File selectedFile = showFileChooserDialog();
					
					if(selectedFile != null) {
						fileNameTxt.setText(selectedFile.getAbsolutePath());
						try {
							imgModel.LoadImage(selectedFile);
						} catch (IOException e1) {
							
							System.out.println("IO Exception in "
									+ "imageLoad(); " + e.toString());
							
							JOptionPane.showMessageDialog(null, 
									"Error in loading the selected image!", 
									"Image Error", 
									JOptionPane.ERROR_MESSAGE);
							fileNameTxt.setText("");
						}
						

					}
					else
						fileNameTxt.setText("No file selected");	
				}
			});	
			
			//Load Image Button
			loadImageButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
										
					BufferedImage img = imgModel.getRGBImage();
					if(img != null) {
						displayImage(img);
					}
					else {
						JOptionPane.showMessageDialog(null, 
								"No image file is choosen", 
								"Image error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			//Open File Button
			drawCanvasButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});	
			
			//kNN Button
			compareButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					kNNCalculation kNN = new kNNCalculation();
					
					try {
						kNN.Calculation();
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
				}
			});
			
		}
		
		public void displayImage(BufferedImage img) {
			imageLabel.setIcon(new ImageIcon(img));
		}
		
		
		private File showFileChooserDialog() {
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new 
					File(System.getProperty("user.home")));
		    int status = fileChooser.showOpenDialog(this.mainWindow);
		    File selected_file = null;
		    if (status == JFileChooser.APPROVE_OPTION) {
		        selected_file = fileChooser.getSelectedFile();
		    }
		    return selected_file;
		}
	
}
