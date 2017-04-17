package mvc.controller.commands;

import java.io.File;

import javax.swing.ImageIcon;

import mvc.controller.Command;
import mvc.model.Engine;
import mvc.model.Image;
import mvc.view.View;


public class OpenImage extends Command {
	
	public OpenImage(Engine engine, View view) {
		super(engine, view);
	}
	
	@Override
	public void execute() {
		File[] files = view.getFiles();
		for ( File file : files ) {
            Image image = new Image(new ImageIcon(file.getAbsolutePath()));
            engine.loadImage(image);
		}
	}

}
