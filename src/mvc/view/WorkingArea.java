package mvc.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;

import mvc.model.Image;
import configurations.Configurations;

@SuppressWarnings("serial")
public class WorkingArea extends JDesktopPane {

	private List<InternalWindow> windows = new ArrayList<InternalWindow>();
	
	public WorkingArea() {
		super();
		setBackground(Configurations.BACKGROUND_COLOR);
		setVisible(true);
	}
	
	public boolean containsThis(Image image) {
		for (InternalWindow window : windows) {
			if ( window.getImage() == image )
				return true;
		}
		return false;
	}
	
	public void addInternalWindow(InternalWindow window) {
		add(window);
		windows.add(window);
	}
	
}
