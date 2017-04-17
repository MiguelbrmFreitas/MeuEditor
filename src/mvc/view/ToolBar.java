package mvc.view;

import javax.swing.JMenuBar;

import mvc.controller.Controller;

@SuppressWarnings("serial")
public class ToolBar extends JMenuBar {

	private Menu arquivo;
	private Menu ferramentas;
	private Menu ajuda;
	
	public ToolBar(Controller controller) {
		super();
		setToolsMenu(controller);
		setFiltersMenu(controller);
		setHelpMenu(controller);
	}

	private void setHelpMenu(Controller controller) {
		ajuda = new HelpMenu(controller, "Ajuda", 'j', "Informacoes de uso, sobre o programa e sobre os desenvolvedores");
		add(ajuda);
	}

	private void setFiltersMenu(Controller controller) {
		ferramentas = new FiltersMenu(controller, "Filtros", 'f', "Aplique filtros e outras funcoes sobre a tela de pintura atual.");
		add(ferramentas);
	}

	private void setToolsMenu(Controller controller) {
		arquivo = new ToolsMenu(controller, "Ferramentas", 'a', "Opcoes de abertura/salvamento de imagens.");
		add(arquivo);
	}
	
}
