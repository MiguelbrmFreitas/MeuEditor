package mvc.controller;

import java.io.IOException;
import java.util.List;

import mvc.controller.commands.AboutDevelopers;
import mvc.controller.commands.CaptureWebcam;
import mvc.controller.commands.OpenImage;
import mvc.controller.commands.OverlapImages;
import mvc.controller.commands.ProgramInstructions;
import mvc.controller.commands.SaveImage;
import mvc.model.Engine;
import mvc.model.Image;
import mvc.model.filters.BlurImage;
import mvc.model.filters.ChangeColors;
import mvc.model.filters.DesaturateImage;
import mvc.model.filters.EqualizeImage;
import mvc.model.filters.InvertColors;
import mvc.model.filters.Brightness;
import mvc.view.View;


public class Controller {
	private Engine engine;
	private View view;
	
	public void openImage() {
		OpenImage open = new OpenImage(engine, view);
		open.execute();
	}
	
	public void saveImage() {
		SaveImage save = new SaveImage(engine, view);
		save.execute();
	}
	
	public void blurImage() {
		try {
			int mask = view.getLimitedValue(0, 100, "Selecione a intensidade");
			view.alert("Voce escolheu o valor " + mask);
			BlurImage blur = new BlurImage(engine, view, mask);
			blur.execute();
			
			Image filteredImage = blur.getImage();
			engine.loadImage(filteredImage);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	public void invertImage() {
		InvertColors invert = new InvertColors(engine, view);
		invert.execute();
		
		Image filteredImage = invert.getImage();
		engine.loadImage(filteredImage);
	}
	
	public void overlapImages() {
		OverlapImages overlap = new OverlapImages(engine, view);
		try {
			overlap.execute();
			
			Image resultImage = overlap.getImage();
			engine.loadImage(resultImage);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	public void desaturateImage() {
		DesaturateImage desaturate = new DesaturateImage(engine, view);
		desaturate.execute();
		
		Image filteredImage = desaturate.getImage();
		engine.loadImage(filteredImage);
	}
	
	public void equalizeImage() {
		EqualizeImage equalize = new EqualizeImage(engine, view);
		equalize.execute();
		
		Image filteredImage = equalize.getImage();
		engine.loadImage(filteredImage);
	}
	
	public void captureImage() {
		CaptureWebcam capture = new CaptureWebcam(engine, view);
		try {
			capture.execute();
		    engine.loadImage(capture.getImage());
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	public void changeColors() {
		try {
			int red = view.getLimitedValue(-255, 255, "Intensidade Vermelho");
			int green = view.getLimitedValue(-255, 255, "Intensidade Verde");
			int blue = view.getLimitedValue(-255, 255, "Intensidade Azul");
			
			view.alert("Voce escolheu os valores\nRed: " + red + "\nGreen: " + green + "\nBlue: " + blue);
			ChangeColors colors = new ChangeColors(engine, view, red, green, blue);
			colors.execute();
			
			Image filteredImage = colors.getImage();
			engine.loadImage(filteredImage);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	public void Brightness() {
		int value;
		try {
			value = view.getLimitedValue(-255, 255, "O quao voce quer dar brilho ou escurecer?");
			Brightness bright = new Brightness(engine, view, value);
			bright.execute();
			
			Image filteredImage = bright.getImage();
			engine.loadImage(filteredImage);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
		
	}
	
	public void aboutDevelopers() {
		AboutDevelopers about = new AboutDevelopers(engine, view);
		about.execute();
	}

	public void showInstructions() {
		ProgramInstructions instructions = new ProgramInstructions(engine, view);
		try {
			instructions.execute();
		} catch (IOException e) {
			view.showError("Ocorreu um erro ao abrir o arquivo.");
		}
	}
	
	public void exit() {
		if ( view.confirmExit() )
			System.exit(0);
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public List<Image> getOpenedImages() {
		return engine.getOpenedImages();
	}
	
	public void setSelectedImage(Image image) {
		engine.selectedImage = image;
	}

	public Image getSelectedImage() {
		return engine.selectedImage;
	}

	public void setPreviouslySelectedImage(Image image) {
		engine.previouslySelectedImage = image;
	}

	public void closeFile(Image image) {
		engine.removeImage(image);
	}
	
}