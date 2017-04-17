package mvc.controller.commands;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import mvc.controller.Command;
import mvc.model.Engine;
import mvc.model.Image;
import mvc.view.View;

public class CaptureWebcam extends Command {
	
	private Image resultImage;
	
	public CaptureWebcam(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() throws Exception {
		VideoCapture camera = new VideoCapture(0);
    	
    	if (!camera.isOpened()) { // Verifica se tem uma webcam configurada
    		throw new Exception("Configure uma webcam"); // Lanca uma excecao se nao tiver uma camera configurada
    	} else {
    		Mat frame = new Mat(); // Cria uma nova matriz de imagem do openCV
    	    while(true) {
    	    	if (camera.read(frame)){ // Sai do loop quando a foto tiver sido tirada
    	    		view.showInfo("Frame obtido: Largura = " + frame.width() + " Altura = " + frame.height(), "Imagem capturada");
    	    		break;
    	    	}
    	    }
    	    
    	    resultImage = new Image(frame);
    	}
	    camera.release();
	}
	
	public Image getImage() {
		return resultImage;
	}
	
}
