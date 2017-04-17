package mvc.model.filters;

import java.awt.Color;

import mvc.model.Engine;
import mvc.model.Filter;
import mvc.model.Image;
import mvc.view.View;
/*
 * Classe com um filtro para deixar a imagem em escala de cinza (preto e branco)
 */
public class DesaturateImage extends Filter {

	public DesaturateImage(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() {
		int r, g, b, average;
		filteredImage = new Image(image.getBufferedImage());
		
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				r = image.getRed(i, j);
				g = image.getGreen(i, j);
				b = image.getBlue(i, j);
				
				average = (r + g + b)/3;
				
				Color valor = new Color(average, average, average);
				filteredImage.setPixel(i, j, valor.getRGB());
			}
		}
	}

}
