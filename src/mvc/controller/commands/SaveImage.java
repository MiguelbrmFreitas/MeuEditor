package mvc.controller.commands;

import java.io.File;

import javax.imageio.ImageIO;

import mvc.controller.Command;
import mvc.model.Engine;
import mvc.model.Image;
import mvc.view.View;
import utils.FileUtilities;

public class SaveImage extends Command {

	public SaveImage(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() {
		try {
			File file = view.getFileSavingInformation();

			Image image = engine.selectedImage;
			
			/* Fixes the filename if it is wrong */
			if (!FileUtilities.validFilename(file.getName())){
				file = FileUtilities.fixFilePath(file);
			}
			
			ImageIO.write(image.getBufferedImage(), "jpg", file);
        	view.showInfo("Caminho: " + file.getPath() + " Nome: " + file.getName(), "Caminho de salvamento");
        }catch(Exception e){
            view.showError(e.getMessage());
        }
	}

}
