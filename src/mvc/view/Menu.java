package mvc.view;

import javax.swing.JMenu;

import mvc.controller.Controller;

//Classe para agrupar elementos importantes de um JMenu na nossa GUI
//Herda de JMenu e cria o objeto ja dando set em 3 atributos
@SuppressWarnings("serial")
public abstract class Menu extends JMenu {

	protected Controller controller;
	
	public Menu(Controller controller, String nome, char hotkey, String descricao) {
		super(nome);
		this.controller = controller;
		setMnemonic(hotkey);
		setToolTipText(descricao);
	}
	
}
