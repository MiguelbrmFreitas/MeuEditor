package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mvc.controller.Controller;
import mvc.controller.commands.CaptureWebcam;
import mvc.controller.commands.OpenImage;
import mvc.controller.commands.OverlapImages;
import mvc.controller.commands.SaveImage;
import mvc.model.Engine;
import mvc.model.Image;
import mvc.model.filters.BlurImage;
import mvc.model.filters.ChangeColors;
import mvc.model.filters.DesaturateImage;
import mvc.model.filters.EqualizeImage;
import mvc.model.filters.InvertColors;
import mvc.view.View;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;

public class TestEditor {

	private BufferedImage imagem;
	
	private View view = new TestView();
	private Engine engine = new Engine();
	
	@Before
	public void setUp() throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		imagem = ImageIO.read(new File("tests/lena.png"));
		engine.selectedImage = null;
		engine.previouslySelectedImage = null;
		for ( Image i : engine.getOpenedImages() ) {
			engine.removeImage(i);
		}
	}

	@Test
	public void testOpenImage() {
		OpenImage open = new OpenImage(engine, view);
		assertNull(engine.selectedImage);
		open.execute();
		assertNotNull(engine.selectedImage);
	}
	
	@Test
	public void testCaptureWebcam() {
		CaptureWebcam webcam = new CaptureWebcam(engine, view);
		try {
			webcam.execute();
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
		assertNotNull(webcam.getImage());
	}
	
	@Test
	public void testSaveImage() {
		OpenImage open = new OpenImage(engine, view);
		open.execute(); // Now the selected image is set
		SaveImage save = new SaveImage(engine, view);
		save.execute();
		File f = new File(System.getProperty("user.home"), "Desktop/" + "lena.jpg");
		assertTrue(f.exists());
		if ( f.exists() )
			f.delete();
	}
	
	@Test
	public void testOverlapImages() {
		try {
			OpenImage open = new OpenImage(engine, view);
			open.execute();
			
			BufferedImage bi = ImageIO.read(new File("tests/somebody.jpg"));
			Image image = new Image(bi);
			
			engine.loadImage(image);
			
			OverlapImages overlap = new OverlapImages(engine, view);
			overlap.execute();
			
			Image filteredImage = overlap.getImage();
			boolean equalToAtLeastOne = false;
			for (Image i : engine.getOpenedImages()) {
				if (filteredImage.equals(i) && !equalToAtLeastOne)
					equalToAtLeastOne = true;
			}
			assertTrue(!equalToAtLeastOne);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	@Test
	public void testBlurImage() {
		try {
			BufferedImage bi = ImageIO.read(new File("tests/lena.png"));
			Image image = new Image(bi);
			
			engine.loadImage(image);
			
			BlurImage blur = new BlurImage(engine, view, 20);
			blur.execute();
			
			Image filteredImage = blur.getImage();
			assertNotEquals(filteredImage, image);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	@Test
	public void testDesaturateImage() {
		try {
			BufferedImage bi = ImageIO.read(new File("tests/lena.png"));
			Image image = new Image(bi);
			
			engine.loadImage(image);
			
			DesaturateImage desaturate = new DesaturateImage(engine, view);
			desaturate.execute();
			
			Image filteredImage = desaturate.getImage();
			assertNotEquals(filteredImage, image);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	@Test
	public void testChangeColors() {
		try {
			BufferedImage bi = ImageIO.read(new File("tests/lena.png"));
			Image image = new Image(bi);
			
			engine.loadImage(image);
			
			ChangeColors colors = new ChangeColors(engine, view, 30, 30, 30);
			colors.execute();
			
			Image filteredImage = colors.getImage();
			assertNotEquals(filteredImage, image);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	@Test
	public void testInvertColors() {
		try {
			BufferedImage bi = ImageIO.read(new File("tests/lena.png"));
			Image image = new Image(bi);
			
			engine.loadImage(image);
			
			InvertColors invert = new InvertColors(engine, view);
			invert.execute();
			
			Image filteredImage = invert.getImage();
			assertNotEquals(filteredImage, image);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}
	
	@Test
	public void testEqualizeImage() {
		try {
			BufferedImage bi = ImageIO.read(new File("tests/lena.png"));
			Image image = new Image(bi);
			
			engine.loadImage(image);
			
			EqualizeImage equalize = new EqualizeImage(engine, view);
			equalize.execute();
			
			Image filteredImage = equalize.getImage();
			assertNotEquals(filteredImage, image);
		} catch (Exception e) {
			view.showError(e.getMessage());
		}
	}

}
