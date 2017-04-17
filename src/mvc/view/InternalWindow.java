package mvc.view;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import mvc.controller.Controller;
import mvc.model.Image;

@SuppressWarnings("serial")
public class InternalWindow extends JInternalFrame implements InternalFrameListener {

	private Image image;
	private Controller controller;
	
	public InternalWindow(Controller controller, Image image) {
		super("Image", true, true, true, true);
		
		this.controller = controller;
		
		ImageIcon imageIcon = new ImageIcon(image.getBufferedImage());
		add(new JLabel(imageIcon));
		this.image = image;
		addInternalFrameListener(this);
	}
	
	public Image getImage() {
		return image;
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		Image selectedImage = controller.getSelectedImage();
		controller.setPreviouslySelectedImage(selectedImage);
		controller.setSelectedImage(image);
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		controller.closeFile(image);
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		Image selectedImage = controller.getSelectedImage();
		controller.setPreviouslySelectedImage(selectedImage);
		controller.setSelectedImage(image);
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		Image selectedImage = controller.getSelectedImage();
		controller.setPreviouslySelectedImage(selectedImage);
		controller.setSelectedImage(image);
	}
	
}