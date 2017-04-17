package mvc.model.filters;

import mvc.model.Engine;
import mvc.model.Filter;
import mvc.model.Image;
import mvc.view.View;
/*
 * Classe para equalizar o histograma uma imagem em escala de cinza,
 * melhorando o contraste da mesma
 */
public class EqualizeImage extends Filter {

	public EqualizeImage(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() {
		
		DesaturateImage desaturate = new DesaturateImage(engine, view);
		desaturate.execute();
		image = desaturate.getImage();
		
		filteredImage = new Image(image.getBufferedImage());
			
		double [] normalizedHistogram = normalizeHistogram();
		double sum = 0;
		short [] arraySum = new short [256];
		int pixelValue, component;
		
		for(int i = 0; i < 256; i++){
			sum += normalizedHistogram[i];
			arraySum[i] = (short) (255*sum + 0.5); // o + 0.5 é pra arredondar pra cima
		}
		
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				component = arraySum[image.getRed(i, j)];
				pixelValue = (image.getAlpha(i, j) << 24) + (component << 16) + (component << 8) + component;
				filteredImage.setPixel(i, j, pixelValue);
			}
		}
	}
	
    private int [] getHistogram() {
        int [] histogram;
        
        histogram = new int[256];
        
        for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				histogram[image.getRed(i, j)] += 1;
			}
		}
        
        return histogram;
    }
    
    private double [] normalizeHistogram() {
        int [] histogram = getHistogram();
        double [] normHistogram;
        
        int nPixels = (image.getHeight() * image.getWidth());
        normHistogram = new double[256];
        
        //Normaliza o histograma dividindo cada posição pelo número de pixels
        for( int n = 0; n < 256; n++ )
            normHistogram[n] = (double) histogram[n]/nPixels;
              
        return normHistogram;
    }
   

}