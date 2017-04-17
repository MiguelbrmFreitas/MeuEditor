package mvc.model.filters;

import mvc.model.Engine;
import mvc.model.Filter;
import mvc.model.Image;
import mvc.view.View;
/*
 * Classe com um filtro para gerar o negativo da imagem
 */
public class InvertColors extends Filter {

	public InvertColors(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() {
		filteredImage = new Image(image.getBufferedImage());

		int r, g, b, alpha, value;
		
		for(int i = 0; i < image.getWidth(); i++){
			for (int j = 0; j < image.getHeight(); j++){
              	alpha = image.getAlpha(i, j);
              	r = 255 - image.getRed(i, j);
              	g = 255 - image.getGreen(i, j);
              	b = 255 - image.getBlue(i, j);
               
              	value = (alpha << 24) + (r << 16) + (g << 8) + b << 0;
           	
              	filteredImage.setPixel(i, j, value);
			}
		}
	}

}