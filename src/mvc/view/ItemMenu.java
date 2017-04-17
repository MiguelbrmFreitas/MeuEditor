package mvc.view;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

//Classe para agrupar elementos importantes de um JMenuItem na nossa GUI
//Herda de JMenuItem e cria o objeto ja dando set em 3 atributos
@SuppressWarnings("serial")
public class ItemMenu extends JMenuItem{
	public ItemMenu(String nome, char hotkey, String descricao){
		super(nome);
		setMnemonic(hotkey);
		setToolTipText(descricao);
	}

	public ItemMenu(String nome, char hotkey, String descricao, String iconFileName) {
		super(nome);
		setMnemonic(hotkey);
		setToolTipText(descricao);
		setIcon(new ImageIcon(iconFileName));
	}
}

