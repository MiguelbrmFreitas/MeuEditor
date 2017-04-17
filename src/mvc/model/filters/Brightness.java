package mvc.model.filters;

import mvc.model.Engine;
import mvc.model.Filter;
import mvc.model.Image;
import mvc.view.View;
/*
 * Classe com um filtro para gerar o negativo da imagem
 */
public class Brightness extends Filter {
	private int value;
	
	public Brightness(Engine engine, View view, int value) {
		super(engine, view);
		this.value = value;
	}

	@Override
	public void execute() {
		filteredImage = new Image(image.getBufferedImage());

		ChangeColors color = new ChangeColors(engine, view, value, value, value);
		color.execute();
		filteredImage = color.getImage();
	}

}