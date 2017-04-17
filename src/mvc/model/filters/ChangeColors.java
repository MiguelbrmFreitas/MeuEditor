package mvc.model.filters;

import mvc.model.Engine;
import mvc.model.Filter;
import mvc.model.Image;
import mvc.view.View;

/*
 * Classe com um filtro para mudar a intesidade
 * das cores da imagem
 */
public class ChangeColors extends Filter {

	private int red, green, blue;
	
	public ChangeColors(Engine engine, View view, int red, int green, int blue) {
		super(engine, view);
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public void execute() {
		int a, r, g, b;
		int newPixel;
		
		filteredImage = new Image(image.getBufferedImage());
		
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				a =  image.getAlpha(i, j);
				r =  normaliza(image.getRed(i, j) + red);
				g =  normaliza(image.getGreen(i, j) + green);
				b =  normaliza(image.getBlue(i, j) + blue);
				
				newPixel = (a << 24) + (r << 16) + (g << 8) + b;
				filteredImage.setPixel(i, j, newPixel);
			}
		}
	}
	
	private int normaliza(int x){
		if(x > 255)
			return 255;
		else if(x < 0)
			return 0;
		else
			return x;
	}

}