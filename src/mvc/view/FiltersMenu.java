package mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import mvc.controller.Controller;

@SuppressWarnings("serial")
public class FiltersMenu extends Menu implements ActionListener {

	private ItemMenu changeColors;
	private ItemMenu blackWhite;
	private ItemMenu invertColors;
	private ItemMenu equalizeImage;
	private ItemMenu blurImage;
	private ItemMenu bright;
	
	public FiltersMenu(Controller controller, String nome, char hotkey, String descricao) {
		super(controller, nome, hotkey, descricao);
		setBlackWhiteItem();
        setBlurItem();
        setEqualizeImageItem();
        setInvertColorsItem();
        setChangeColorsItem();
        setBrightnessItem();
	}

	private void setChangeColorsItem() {
		changeColors = new ItemMenu("Alterar Cores", 'l', "Permite alterar os niveis de vermelho, verde e azul da imagem", "rgb.jpg");
		changeColors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		changeColors.addActionListener(this);
		add(changeColors);
	}

	private void setInvertColorsItem() {
		invertColors = new ItemMenu("Negativar", 'i', "Faz o negativo da imagem selecionada" , "polarity.png");
        invertColors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        invertColors.addActionListener(this);
		add(invertColors);
	}

	private void setEqualizeImageItem() {
		equalizeImage = new ItemMenu("Contraste", 'n', "Melhora o contraste de uma imagem preto e branca. Se usar em uma colorida, ficara preto e branca", "sharpen.png");
        equalizeImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        equalizeImage.addActionListener(this);
		add(equalizeImage);
	}

	private void setBlurItem() {
		blurImage = new ItemMenu("Suavizar", 'd', "Suaviza/borra a imagem selecionada", "sharpen.png");
        blurImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        blurImage.addActionListener(this);
		add(blurImage);
	}

	private void setBlackWhiteItem() {
		blackWhite = new ItemMenu("Preto e branco", 'p', "Torna a imagem selecionada preta e branca", "black_white.png");
        blackWhite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        blackWhite.addActionListener(this);
        add(blackWhite);
	}
	
	private void setBrightnessItem() {
		bright = new ItemMenu("Brilho", 'b', "Aumenta ou diminui o brilho da imagem selecionada", "black_white.png");
        bright.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        bright.addActionListener(this);
        add(bright);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if ( source == blackWhite ) {
			controller.desaturateImage();
		} else if ( source == invertColors ) {
			controller.invertImage();
		} else if ( source == equalizeImage ) {
			controller.equalizeImage();
		} else if ( source == blurImage ) {
			controller.blurImage();
		} else if ( source == changeColors ) {
			controller.changeColors();
		} else if( source == bright){
			controller.Brightness();
		}
	}

}
