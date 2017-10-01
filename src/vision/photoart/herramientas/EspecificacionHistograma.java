package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EspecificacionHistograma {
	private BufferedImage imagen1;
	private BufferedImage imagen2;
	private int histograma1[];
	private int histograma2[];
	private ArrayList<Integer> vout = new ArrayList<Integer>();
	
	public EspecificacionHistograma(BufferedImage imagen1, BufferedImage imagen2, int[] histograma1, int[] histograma2) {
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.histograma1 = histograma1;
		this.histograma2 = histograma2;
		for (int i = 0; i < 256; i++)
			vout.add(i);
	}
	
	public BufferedImage calcularImagen() {
		BufferedImage imagenSalida = new BufferedImage(imagen1.getWidth(), imagen1.getHeight(), BufferedImage.TYPE_INT_RGB);
		double[] histogramaN1 = normalizarHistograma(histograma1, imagen1.getWidth() * imagen1.getHeight());
		double[] histogramaN2 = normalizarHistograma(histograma2, imagen2.getWidth() * imagen2.getHeight());
		for (int i = 0; i < 256; i++) {
			int j = 255;
			while((j >= 0) && (histogramaN1[i] <= histogramaN2[j])) {
				vout.set(i, j);
				j--;
			}
		}
		for (int i = 0; i < imagen1.getWidth(); i++) {
			for (int j = 0; j < imagen1.getHeight(); j++) {
				Color color = new Color(imagen1.getRGB(i, j));
				Color newColor = new Color(vout.get(color.getRed()), vout.get(color.getRed()), vout.get(color.getRed()));
				imagenSalida.setRGB(i, j, newColor.getRGB());
			}
		}
		return imagenSalida;
	}
	
	private double[] normalizarHistograma(int[] histograma, int size) {
		double histogramaN[] = new double[256];
		for (int i = 0; i < histograma.length; i++) {
			histogramaN[i] = (double)histograma[i]/(double)size;
		}
		return histogramaN;
	}
}
