package vision.photoart.ventanas;

import java.awt.image.BufferedImage;

import vision.photoart.herramientas.EspecificacionHistograma;
import vision.photoart.imagen.Histograma;

public class VentanaEspecHistograma {
	private BufferedImage imagen1;
	private BufferedImage imagen2;
	
	public VentanaEspecHistograma(BufferedImage imagen1, BufferedImage imagen2) {
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
	}
	
	public BufferedImage calcularHistograma() {
		int[] histograma1 = obtenerHistograma(imagen1);
		int[] histograma2 = obtenerHistograma(imagen2);
		EspecificacionHistograma especificacionHistograma = new EspecificacionHistograma(imagen1, imagen2, histograma1, histograma2);
		BufferedImage imagenSalida = especificacionHistograma.calcularImagen();
		return imagenSalida;
	}
	
	private int[] obtenerHistograma(BufferedImage imagen) {
		Histograma histograma = new Histograma();
	    int[][] canalesHistograma = histograma.histograma(imagen, false);
	    int[] canal=new int[256];
	    System.arraycopy(canalesHistograma[0], 0, canal, 0, canalesHistograma[0].length);
	    return canal;
	}
}
