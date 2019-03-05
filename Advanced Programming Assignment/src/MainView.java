import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

public class MainView {

	    // Declaring main view components
		private JFrame mainWindow;
		
		private JButton drawCanvasButton;
		private JButton loadImageButton;
		private JButton compareButton;
		private JButton openFileBtn;
		private JTextField fileNameTxt;
		private JLabel fileNameLbl;
		private JLabel digitLbl;
		private JTextField digitTxt;
		

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
			mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
	        
	        //Instantiate a new JPanel to hold image labels
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
			
			// Creating sub operation button panel
	        JPanel subButtonPanel = new JPanel();
			
	        compareButton = new JButton("k-NN Calculation");
	        compareButton.setPreferredSize(new Dimension(150, 25));
	        digitLbl = new JLabel ("Number: ");
	        digitTxt = new JTextField(5);
	        digitTxt.setEnabled(true);
	       
	        //Add components to panel
	        subButtonPanel.add(compareButton);
	        subButtonPanel.add(digitLbl);
	        subButtonPanel.add(digitTxt);

	        
	        // A JPanel with box layout
	        JPanel southLayout = new JPanel();
	        southLayout.setLayout(new BoxLayout(southLayout,
	        		BoxLayout.Y_AXIS));
	        
	        // add both flow layouts to northLayout
	        southLayout.add(subButtonPanel);
	        
	        // add northLayout to mainWindow's north
	        mainWindow.add(southLayout, BorderLayout.SOUTH);
	        
	     
			
			// Event handling
			// Register action listener with openFileBtn
			//openFileBtn.addActionListener(controller);
					
			// Using annonymous inner class
			openFileBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Choosing file to display...");
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
			
			loadImageButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Loading Image...");
					BufferedImage img = imgModel.getRGBImage();
					if(img != null) {
						displayImage(img);
						System.out.println("Image Loaded");
					}
					else {
						JOptionPane.showMessageDialog(null, 
								"No image file is choosen", 
								"Image error", 
								JOptionPane.ERROR_MESSAGE);
						System.out.println("Image could not be loaded!");
					}
				}
			});
			
			compareButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                kNNCalculation KNN = new kNNCalculation();
	                System.out.println("Choosing file for k-NN calculation...");
	                try {
	                	KNN.CalculateDistance();
	                	KNN.getOutputDigit();
	                    int digit = KNN.getOutputDigit();
	                    digitTxt.setText(String.valueOf(digit));
	                } catch (Exception e1) {
	                	digitTxt.setText("NA");
	                }
	            }
	        });
			
			drawCanvasButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					DrawDigit DrawDigit = new DrawDigit();
					
				}
			});
		}
		
		public void displayImage(BufferedImage img) {
			imageLabel.setIcon(new ImageIcon(img));
		}
		
		
		public File showFileChooserDialog() {
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
