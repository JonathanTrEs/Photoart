package vision.photoart.herramientas;

import java.awt.image.BufferedImage;

import vision.photoart.imagen.Histograma;

public class Brillo {
	private double brillo;
	
	public Brillo() {}
	
	public double calcularBrillo(BufferedImage imagen, boolean gris) {
		int[][] canalesHistograma = new Histograma().histograma(imagen, true);
	    if (gris) {
	    	for(int i = 0; i < canalesHistograma[0].length; i++) {
	    		brillo = brillo + canalesHistograma[0][i] * i;
	    	}
	    	brillo = brillo/(imagen.getHeight()*imagen.getWidth());
	    }
	    else {
	    	for(int i = 0; i < canalesHistograma[4].length; i++) {
	    		brillo = brillo + (canalesHistograma[4][i] * i);
	    	}
	    	brillo = brillo/(imagen.getHeight()*imagen.getWidth());
	    }
	    return brillo;
	}
}
