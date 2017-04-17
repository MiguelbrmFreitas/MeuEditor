package mvc.view;

import java.awt.Dimension;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

import mvc.controller.Controller;
import observer.Observer;

public class GUIView extends View implements Observer {

	private GUIWindow window;
	private JSlider slider;

	public GUIView(Controller controller) {
		window = new GUIWindow(controller);
		window.display();
	}

	@Override
	public void update() {
		window.updateFrame();
	}

	@Override
	public String prompt(String string) {
		return JOptionPane.showInputDialog(string);
	}

	@Override
	public void alert(String string) {
		JOptionPane.showMessageDialog(window, string);
	}

	@Override
	public File[] getFiles() {
		JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Abrir uma imagem");
        fc.setMultiSelectionEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new FileNameExtensionFilter("Arquivos de imagem", ImageIO.getReaderFileSuffixes()));
        fc.showOpenDialog(window);
        return fc.getSelectedFiles();
	}
	
	public File getFileSavingInformation() throws Exception {
		JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Salvar");
        if( fc.showSaveDialog(window) == JFileChooser.APPROVE_OPTION )
            return fc.getSelectedFile();
        else throw new Exception("Voce cancelou o salvamento.");
	}
	
	public void showError(String message) {
		JOptionPane.showMessageDialog(window, message, "Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
	}
	
	public void showInfo(String message, String title) {
		JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("info.png"));
	}

	@Override
	public boolean confirmExit() {
		int option = JOptionPane.showConfirmDialog(window, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("question.png"));
		return option == JOptionPane.YES_OPTION;
	}

	@Override
	public int getLimitedValue(int minValue, int maxValue, String title) throws Exception {
		int defaultValue = ((maxValue + minValue) / 100) * 5;
		JSlider slider = createSlider(minValue, maxValue, defaultValue);
		if ( approvedSlider(slider, title) ) {
			return slider.getValue();
		} else throw new Exception("You have canceled the slider.");
	}
	
	private JSlider createSlider(int minValue, int maxValue, int initialValue) {
		slider = new JSlider(JSlider.HORIZONTAL, minValue, maxValue, initialValue);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setVisible(true);
		slider.setSize(800, 70);
		slider.setPreferredSize(new Dimension(800, 70));
		return slider;
	}
	
	private boolean approvedSlider(JSlider slider, String title) {
		int messageDisplayed = JOptionPane.showConfirmDialog(null, slider, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		return messageDisplayed == JOptionPane.OK_OPTION;
	}

}
