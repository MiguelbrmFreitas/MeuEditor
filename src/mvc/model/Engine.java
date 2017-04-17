package mvc.model;

import java.util.ArrayList;
import java.util.List;

import observer.Observer;
import observer.Subject;

public class Engine implements Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	private List<Image> openedImages = new ArrayList<Image>();
	public Image selectedImage, previouslySelectedImage; // There's no need for setters/getters here
	
	public void loadImage(Image image) {
		openedImages.add(image);
		previouslySelectedImage = selectedImage;
		selectedImage = image;
		notifyObservers();
	}

	public void closeImage() {
		openedImages.remove(selectedImage);
		notifyObservers();
	}
	
	@Override
	public void register(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for ( Observer observer : observers ) {
			observer.update();
		}
	}

	public List<Image> getOpenedImages() {
		return openedImages;
	}

	public void removeImage(Image image) {
		openedImages.remove(image);
	}
}
