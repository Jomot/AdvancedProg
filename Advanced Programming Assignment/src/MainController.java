
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MainController implements ActionListener {
	
	MainView view;
	ImageModel imgModel;

	public MainController() {}
	
	public MainController(MainView view, ImageModel model) {
		this.view = view;
		this.imgModel = model;
	}
	
	public MainController(MainView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Button clicked: "  + e.getActionCommand());
		
	}

	
}
