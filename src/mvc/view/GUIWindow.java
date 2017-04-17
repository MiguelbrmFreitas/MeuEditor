package mvc.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import mvc.controller.Controller;
import mvc.model.Image;
import configurations.Configurations;

@SuppressWarnings("serial")
public class GUIWindow extends JFrame {

	private Controller controller;
	private ToolBar menuBar;
	private WorkingArea desktop;
	
	public GUIWindow(Controller controller) {
		super();
		initializeGUIWindow(controller);
		createMenuBar();
		setDesktopPane();
	}

	private void initializeGUIWindow(Controller controller) {
		this.controller = controller;
		setTitle(Configurations.APP_NAME);
		getContentPane().setBackground(Configurations.BACKGROUND_COLOR);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		Dimension preferredSize = new Dimension(dim.width - 150, dim.height - 150);
		setPreferredSize(preferredSize);
		
		// Set maximized window by default
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setDesktopPane() {
		desktop = new WorkingArea(); 
		add(desktop);
		
	}

	private void createMenuBar() {
		menuBar = new ToolBar(controller);
		setJMenuBar(menuBar);
	}

	public void updateFrame() {
		List<Image> openedImages = controller.getOpenedImages();
				
		for ( Image image : openedImages ) {
			if ( desktop.containsThis(image) )
				continue;
			InternalWindow imageWindow = buildInternalWindow(image);
			desktop.addInternalWindow(imageWindow);
			try {
				imageWindow.setSelected(true);
			} catch (PropertyVetoException e) {	}
		}
	}
	
	private InternalWindow buildInternalWindow(Image image) {
		InternalWindow imageWindow = new InternalWindow(controller, image);
		ImageIcon imageToDisplay = new ImageIcon(image.getBufferedImage());
		JLabel label = new JLabel(imageToDisplay);
		label.setVisible(true);
		imageWindow.add(label);
		setInternalFrameSize(imageToDisplay, imageWindow);
		imageWindow.setVisible(true);
		return imageWindow;
	}

	private void setInternalFrameSize(ImageIcon image, InternalWindow window) {
		Dimension maxSize = Toolkit.getDefaultToolkit().getScreenSize();
		if ( image.getIconWidth() > maxSize.width || image.getIconHeight() > maxSize.height ) 
			window.setSize(maxSize);
		else 
			window.setSize(image.getIconWidth(), image.getIconHeight());
	}

	public void display() {
		pack();
		setVisible(true);
	}
	
}