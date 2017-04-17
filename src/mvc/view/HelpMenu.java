package mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.KeyStroke;

import mvc.controller.Controller;

@SuppressWarnings("serial")
public class HelpMenu extends Menu implements ActionListener {

	private ItemMenu sobre;
	private ItemMenu instrucoes;
	
	public HelpMenu(Controller controller, String nome, char hotkey, String descricao) {
		super(controller, nome, hotkey, descricao);
		setItemInstrucoes();
        setItemSobre();
	}

	private void setItemSobre() {
		sobre = new ItemMenu("Sobre", 's', "Informacoes sobre os desenvolvedores e a aplicacao", "about.png");
        sobre.setAccelerator(KeyStroke.getKeyStroke("F2"));
        sobre.addActionListener(this);
		add(sobre);
	}

	private void setItemInstrucoes() {
		instrucoes = new ItemMenu("Instrucoes de uso", 'i', "Instrucoes de uso da aplicacao", "manual.png");
        instrucoes.setAccelerator(KeyStroke.getKeyStroke("F1"));
        instrucoes.addActionListener(this);
		add(instrucoes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if ( source == sobre ) {
			showAboutDialog();
		} else if ( source == instrucoes ) {
			showInstructionsDialog();
		}
	}

	private void showInstructionsDialog() {
		controller.showInstructions();
	}

	private void showAboutDialog() {
		controller.aboutDevelopers();
	}

}
