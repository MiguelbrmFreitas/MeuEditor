package mvc.controller;

import mvc.model.Engine;
import mvc.view.View;

public abstract class Command {
	protected Engine engine;
	protected View view;
	
	public Command(Engine engine, View view) {
		this.engine = engine;
		this.view = view;
	}
	
	public abstract void execute() throws Exception;
}
