package mvc.model.filters;

import mvc.model.Engine;
import mvc.model.Filter;
import mvc.model.Image;
import mvc.view.View;
/*
 * Classe com um filtro para borrar a imagem atrav�s de uma
 * m�scara com um filtro da mediana. O quanto a imagem ser�
 * suavizada/borrada depende do tamanho da m�scara.
 */
public class BlurImage extends Filter {
	
	private int mask;
	
	public BlurImage(Engine engine, View view, int mask) {
		super(engine, view);
		this.mask = mask;
	}

	private int normalize(int x){
		if(x < 3)
			return 3;
		else if(x % 2 != 0)
			return x + 1;
		else
			return x;
	}
	
	@Override
	public void execute() {
		int r, g, b, a;
		int size, h, w, divisor;
		
		mask = normalize(mask);
		size = (mask-1)/2;
		
		h = image.getHeight();
		w = image.getWidth();
		
		filteredImage = new Image(image.getBufferedImage());
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				r = g = b = 0;
				divisor = 1;
				for(int x = -size; x <= size; x++){
					for(int y = -size; y <= size; y++){
						if((i+x) > 0 && (j+y) > 0 && (i+x) < w && (j+y) < h){
							r += image.getRed(i + x, j + y);
							g += image.getGreen(i + x, j + y);
							b += image.getBlue(i + x, j + y);
							divisor++;
						}
					}
				}
				r /= divisor;
				g /= divisor;
				b /= divisor;
				a = image.getAlpha(i, j);
				
				int novoPixel = (a << 24) + (r << 16) + (g << 8) + b;
				filteredImage.setPixel(i, j, novoPixel);
			}
		}
	}
}