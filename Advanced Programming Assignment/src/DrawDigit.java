import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DrawDigit extends JFrame implements MouseListener, MouseMotionListener {
	
	BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
    Graphics g = img.getGraphics();
	
	public DrawDigit() {
		addMouseMotionListener(this);
        addMouseListener(this);
        
        //Button interface
        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");
        JButton resizeButton = new JButton("Resize");
        saveButton.setBounds(15, 10, 100, 30);
        resetButton.setBounds(142,10,100,30);
        resizeButton.setBounds(265, 10, 100, 30);
        add(resetButton);
        add(saveButton);
        add(resizeButton);

        g.setColor(Color.white);
        //getContentPane().setBackground(Color.black);
        g.fillRect(0,0, img.getWidth(), img.getHeight());
        setSize(400,400);
        setLayout(null);
        setVisible(true);
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
                try {
                    ImageIO.write(img, "PNG", new File("E:\\DrawDigit\\DrawDigit.png"));
                    System.out.println("Image saved");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo closes window and instead of disposing of drawing
                dispose();
            }
        });
        
        resizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	ResizeDrawnDigit resize = new ResizeDrawnDigit();
            	resize.resizeConstructor();
            	System.out.println("Digit Resized");        	
            }
        });
        
        
    }
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//I dont know?
		g.setColor(Color.black);
		g.fillOval(e.getX(), e.getY(), 10, 10);
		
		//Colour of drawing
        Graphics g2 = getGraphics();
        g2.setColor(Color.black);
        g2.fillOval(e.getX(), e.getY(), 40, 40);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void resizeConstructor() {
		
		File digitOriginal = new File("E:\\DrawDigit\\DrawDigit.png");
		File digitResized = new File("E:\\DrawDigit\\DigitResized.png");
		resizeImage(digitOriginal, digitResized, 28, 28, "png");
		}
		
	public void resizeImage(File originalImage, File resizedImage, int width, int height, String format) {
			
		ImageFileHandler img_handler = new ImageFileHandler();
			
		try {
			BufferedImage original = img_handler.readFile("E:\\DrawDigit\\DrawDigit.png");
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
	
	

