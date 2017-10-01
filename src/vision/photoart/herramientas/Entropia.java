package vision.photoart.herramientas;

import java.awt.image.BufferedImage;

import vision.photoart.imagen.Histograma;

public class Entropia {
	private double entropia = 0;
	
	public double calcularEntropia(BufferedImage imagen, boolean gris) {
		int[][] canalesHistograma = new Histograma().histograma(imagen, true);
		double proba = 0;
		double size = (imagen.getHeight()*imagen.getWidth());
	    if (gris) {
	    	for(int i = 0; i < canalesHistograma[0].length; i++) {
	    		proba = (canalesHistograma[0][i])/size;
	    		if(proba != 0)
	    			entropia = entropia -  (proba * (Math.log(proba)/Math.log(2.0)));
	    	}
	    }
	    else {
	    	for(int i = 0; i < canalesHistograma[4].length; i++) {
	    		proba = (canalesHistograma[4][i])/size;
	    		if(proba != 0)
	    			entropia = entropia -  (proba * (Math.log(proba)/Math.log(2.0)));
	    	}
	    }
		return entropia;
	}
}
