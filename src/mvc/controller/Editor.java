package mvc.controller;

import org.opencv.core.Core;

import mvc.model.Engine;
import mvc.view.GUIView;
import mvc.view.View;

public class Editor {

	public static void main(String[] args) {
		System.out.println(Core.NATIVE_LIBRARY_NAME);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Controller controller = new Controller();
		View view = new GUIView(controller);
		Engine engine = new Engine();
		engine.register(view);
		controller.setEngine(engine);
		controller.setView(view);
	}
	
}
