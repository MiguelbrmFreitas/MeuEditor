package mvc.controller.commands;

import mvc.controller.Command;
import mvc.model.Engine;
import mvc.model.Image;
import mvc.view.View;

public class OverlapImages extends Command {

	private Image firstImage;
	private Image secondImage;
	private Image resultImage;
	
	public OverlapImages(Engine engine, View view) {
		super(engine, view);
		firstImage = engine.selectedImage;
		secondImage = engine.previouslySelectedImage;
	}

	@Override
	public void execute() throws Exception {
		if ( secondImage == null ) throw new Exception("Eh necessario que duas imagens estejam abertas!");
		int height = largest(firstImage.getHeight(), secondImage.getHeight());
		int width = largest(firstImage.getWidth(), secondImage.getWidth());
		
		int a1, r1, g1, b1, a2, r2, g2, b2, newPixel;
		
		resultImage = new Image(width, height);
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				a1 = checkPixel(firstImage, i, j, 24);
				a2 = checkPixel(secondImage, i, j, 24); 
				r1 = checkPixel(firstImage, i, j, 16);
				r2 = checkPixel(secondImage, i, j, 16); 
				g1 = checkPixel(firstImage, i, j, 8);
				g2 = checkPixel(secondImage, i, j, 8); 
				b1 = checkPixel(firstImage, i, j, 0);
				b2 = checkPixel(secondImage, i, j, 0); 
				
				newPixel = (addComponents(a1,a2) << 24) + (addComponents(r1,r2) << 16) +
							(addComponents(g1, g2) << 8) + addComponents(b1, b2);
				
				resultImage.setPixel(i, j, newPixel);
			}
		}
	}
	
	private int largest(int x1, int x2){
		if(x1 > x2)
			return x1;
		else
			return x2;
	}
	
	private int checkPixel(Image image, int i, int j, int param){
		int component;
		
		try{
			component = image.getPixel(i, j) >> param & 0xFF;
		}
		catch(Exception e){
			component = -1;
		}
		
		return component;
	}
	
	private int addComponents(int firstComponent, int secondComponent){
		if(firstComponent == -1){
			return secondComponent;
		}
		else if(secondComponent == -1){
			return firstComponent;
		}
		else{
			return (int) (firstComponent+secondComponent)/2;
		}
	}
	
	public Image getImage() {
		return resultImage;
	}
	
}
