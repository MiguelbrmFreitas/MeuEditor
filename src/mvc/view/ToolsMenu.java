package mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import mvc.controller.Controller;


@SuppressWarnings("serial")
public class ToolsMenu extends Menu implements ActionListener {

	private ItemMenu openImage;
	private ItemMenu saveImage;
	private ItemMenu overlapImages;
	private ItemMenu captureWebcam;
	private ItemMenu exit;
	
	public ToolsMenu(Controller controller, String nome, char hotkey, String descricao) {
		super(controller, nome, hotkey, descricao);		
		setOpenImageMenu();
        setSaveImageMenu();
        setCaptureWebcamMenu();
        setOverlapImagesMenu();
        setExitMenu();
	}

	private void setExitMenu() {
		exit = new ItemMenu("Sair", 'x', "Encerrar a aplicacao.", "exit.png");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        exit.addActionListener(this);
        add(exit);
	}

	private void setSaveImageMenu() {
		saveImage = new ItemMenu("Salvar a imagem", 's', "Salvar a imagem em um formato coerente no disco rigido.", "save.png");
        saveImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveImage.addActionListener(this);
        add(saveImage);
	}

	private void setOpenImageMenu() {
        openImage = new ItemMenu("Abrir arquivo", 'a', "Carregar um arquivo de imagem no programa.", "open.png");
        openImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openImage.addActionListener(this);
		add(openImage);
	}

	private void setCaptureWebcamMenu() {
		captureWebcam = new ItemMenu("Foto de WebCam", 'c', "Tira foto em tempo real pela webcam e gera nova imagem", "webcam.png");
        captureWebcam.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        captureWebcam.addActionListener(this);
        add(captureWebcam);
	}

	private void setOverlapImagesMenu() {
		overlapImages = new ItemMenu("Sobrepor duas imagens", 's', "Sobrepoe as duas ultimas imagens selecionadas", "sharpen.png");
        overlapImages.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
        overlapImages.addActionListener(this);
		add(overlapImages);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if ( source == exit ) {
			controller.exit();
		} else if ( source == saveImage ) {
			controller.saveImage();
		} else if ( source == openImage ) {
			controller.openImage();
		} else if ( source == overlapImages ) {
			controller.overlapImages();
		} else if ( source == captureWebcam ) {
			controller.captureImage();
		}
	}

}
