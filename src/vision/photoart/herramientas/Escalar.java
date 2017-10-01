package vision.photoart.herramientas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Escalar {
	BufferedImage imagen;
	private int width;
	private int height;
	private double escalaX;
	private double escalaY;
	
	public Escalar(BufferedImage imagen){
		this.imagen = imagen;
	}
	
	public BufferedImage escalado(int porcentajeX, int porcentajeY, int tipo) {
		
		width = (int)Math.round((imagen.getWidth()*porcentajeX/100));
		height = (int)Math.round((imagen.getHeight()*porcentajeY/100));
		escalaX = (double)porcentajeX/(double)100;
		escalaY = (double)porcentajeY/(double)100;
		
		BufferedImage resultado = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		if(tipo == 0) { //si es vecino mas proximo
			resultado = vecinoMasProximo(resultado);
		}
		else if(tipo == 1) { // si es bilineal
			resultado = bilineal(resultado);
		}
		
		return resultado;
	}
	
	public BufferedImage vecinoMasProximo(BufferedImage resultado) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				resultado.setRGB(i, j, imagen.getRGB((int)(i/escalaX), (int)(j/escalaY)));
			}
		}
		return resultado;
	}
	
	public BufferedImage bilineal(BufferedImage resultado) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int x = (int)(i/escalaX);
				int y = (int)(j/escalaY);
				if(!detectarBorde(x, y)) {
					int media = calcularMedia(x,y);
					Color newColor = new Color(media, media, media);
					resultado.setRGB(i, j, newColor.getRGB());
				}
				else {
					resultado.setRGB(i, j, imagen.getRGB(x, y));
				}
				
			}
		}
		return resultado;
	}
	
	public boolean detectarBorde(int x, int y) {
		boolean resultado;
		if(x == imagen.getWidth()-1 || x == 0 || y == imagen.getHeight()-1 || y == 0) {
			resultado = true;
		}
		else {
			resultado = false;
		}
		return resultado;
	}
	
	public int calcularMedia(int x, int y) {		
		int media = ((new Color(imagen.getRGB(x -1,y)).getRed()) + (new Color(imagen.getRGB(x, y-1)).getRed()) + 
						(new Color (imagen.getRGB(x+1,y)).getRed()) + (new Color (imagen.getRGB(x, y+1)).getRed()))/4;
		return media;
	}
	
}