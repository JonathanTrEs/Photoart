package vision.photoart.herramientas;

import java.awt.image.BufferedImage;

import vision.photoart.imagen.Histograma;

public class Contraste {
	private double contraste;
	
	public Contraste(){}
	
	public double calcularContraste(BufferedImage imagen, double brillo, boolean gris) {
		double pixel = 0;
		int[][] canalesHistograma = new Histograma().histograma(imagen, true);
		if (gris) {
	    	for(int i = 0; i < canalesHistograma[0].length; i++) {
	    		for(int j = 0; j < canalesHistograma[0][i]; j++) {
	    			pixel = pixel + ((i-brillo)*(i-brillo));
	    		}
	    	}
	    	contraste = Math.sqrt(pixel/((imagen.getHeight()*imagen.getWidth()) - 1));
	    }
	    else {
	    	for(int i = 0; i < canalesHistograma[4].length; i++) {
	    		for(int j = 0; j < canalesHistograma[4][i]; j++) {
	    			pixel = pixel + ((i-brillo)*(i-brillo));
	    		}
	    	}
	    	contraste = Math.sqrt(pixel/((imagen.getHeight()*imagen.getWidth()) - 1));
	    }
		return contraste;
	}
}
