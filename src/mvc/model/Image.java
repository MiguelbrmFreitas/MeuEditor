package mvc.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.opencv.core.Mat;
/**
 * Classe para representa��o da imagem. A imagem tem altura, largura
 * e uma matrix de pixels. Cada pixel � um int de 4 bytes, aonde cada byte
 * representa um dos componentes: alpha (opacidade), Red, Green e Blue.
 * @author Miguel
 *
 */
public class Image {
	private int width;
	private int height;
	private int pixels [][];
	
	/*
	 * Os construtores podem tanto receber altura e largura (fazendo que se inicie uma matriz de zeros),
	 * tanto altura, largura e uma matriz de pixels, tanto uma BufferedImage, tanto outra Image, tanto
	 * uma ImageIcon e tanto uma Mat. Essas s�o as v�rias formas de se construir uma imagem nesta representa��o.
	 */
	
	public Image(int w, int h){
		width = w;
		height = h;
		
		pixels = new int[w][h];
	}
	
	public Image(int w, int h, int matriz[][]){
		setImage(w, h, matriz);
	}
	
	public Image(BufferedImage img){
		setImage(img);
	}
	
	public Image(ImageIcon img){
		setImage(img);
	}
	
	public Image(Mat img){
		setImage(img);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isColorful(){
		int r = pixels[0][0] >> 16 & 0xFF;
		int g = pixels[0][0] >> 8 & 0xFF;
		int b = pixels[0][0]  & 0xFF;
		
		return !(r == g && r == b && g == b);
	}
	
	public boolean equals(Image img){
		if (this.getWidth() == img.getWidth() && this.getHeight() == img.getHeight()
				&& this.getPixels() == img.getPixels()){
			return true;
		}
		else{
			return false;
		}
	}

	public int getPixel(int i, int j){
		return pixels[i][j];
	}
	
	public int getAlpha(int i, int j){
		return (pixels[i][j] >> 24 & 0xFF);
	}
	
	public int getRed(int i, int j){
		return (pixels[i][j] >> 16 & 0xFF);
	}
	
	public int getGreen(int i, int j){
		return (pixels[i][j] >> 8 & 0xFF);
	}
	
	public int getBlue(int i, int j){
		return (pixels[i][j] & 0xFF);
	}

	
	public int[][] getPixels(){
		return pixels;
	}
	
	public void setPixel(int i, int j, int valorPixel){
		pixels[i][j] = valorPixel;
	}
	
	public void setPixels(int [][] novosPixels){
		pixels = novosPixels;
	}
	
	public BufferedImage getBufferedImage(){
		BufferedImage novaImagem = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				novaImagem.setRGB(i, j, pixels[i][j]);
			}
		}
		
		return novaImagem;
	}
	
	public void setImage(int w, int h, int matriz[][]){
		width = w;
		height = h;
				
		pixels = matriz;
	}

	public void setImage(BufferedImage img){
		width = img.getWidth();
		height = img.getHeight();
		
		pixels = new int[width][height];
		
		for(int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				pixels[i][j] = img.getRGB(i, j);
			}
		}
	}
	
	public void setImage(ImageIcon img){
		BufferedImage novaImagem = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_RGB);
	
		Graphics g = novaImagem.createGraphics();
		img.paintIcon(null, g, 0,0);
		g.dispose();
		
		setImage(novaImagem);
	}
	
	public void setImage(Mat img){
		byte[] newpixels = new byte[img.rows()*img.cols()*(int)(img.elemSize())];
		img.get(0, 0, newpixels);
		if (img.channels() >= 3) { // Verifica se a imagem é colorida
			for (int i = 0; i < newpixels.length; i += 3) {
				byte temp = newpixels[i];
			  	newpixels[i] = newpixels[i + 2];
			  	newpixels[i + 2] = temp;
			}
		}
		BufferedImage novaImagem = new BufferedImage(img.cols(), img.rows(), BufferedImage.TYPE_3BYTE_BGR);
		novaImagem.getRaster().setDataElements(0, 0, img.cols(), img.rows(), newpixels);
		
		setImage(novaImagem);
	}

}