package mvc.view;

import java.io.File;

import observer.Observer;

public abstract class View implements Observer {
	
	public abstract String prompt(String string);
	public abstract void alert(String string);
	public abstract File[] getFiles();
	public abstract File getFileSavingInformation() throws Exception;
	public abstract void showError(String message);
	public abstract void showInfo(String message, String title);
	public abstract boolean confirmExit();
	public abstract int getLimitedValue(int minValue, int maxValue, String title) throws Exception;
	
}