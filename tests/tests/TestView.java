package tests;

import java.io.File;

import mvc.view.View;

public class TestView extends View {

	@Override
	public void update() {}

	@Override
	public String prompt(String string) {
		System.out.println("Tried to prompt: " + string);
		return "Test";
	}

	@Override
	public void alert(String string) {
		System.out.println("[!] " + string);
	}

	@Override
	public File[] getFiles() {
		File[] files = { new File("tests/lena.png") };
		return files;
	}

	@Override
	public File getFileSavingInformation() throws Exception {
		return new File(System.getProperty("user.home"), "Desktop/" + "lena.jpg");
	}

	@Override
	public void showError(String message) {
		System.out.println("ERROR: " + message);
	}

	@Override
	public void showInfo(String message, String title) {
		System.out.println("Info: (" + title + ") " + message);
	}

	@Override
	public boolean confirmExit() {
		System.out.println("Exiting...");
		return true;
	}

	@Override
	public int getLimitedValue(int minValue, int maxValue, String title)
			throws Exception {
		int randomNumber = (int) (Math.random() * (maxValue - minValue) + minValue);
		System.out.println("Generated number: " + randomNumber);
		return randomNumber;
	}

}
