package mvc.model;

import mvc.controller.Command;
import mvc.view.View;
/*
 * Classe abstrata para representar um filtro.
 * Um filtro também é um comando.
 */
public abstract class Filter extends Command {	
	protected Image image;
	protected Image filteredImage;
	
	public Filter(Engine engine, View view) {
		super(engine, view);
		image = engine.selectedImage;
	}
	
	public Image getImage() {
		return filteredImage;
	}
}
