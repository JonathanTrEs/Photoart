package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Gamma {
	
	public BufferedImage calcularGamma (BufferedImage imagen, double gamma) {
		double a;
		double b;
		int aux;
		ArrayList<Integer> vout = new ArrayList<Integer>();
		for(int i = 0; i < 256; i++)
			vout.add(0);
		BufferedImage resultado = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i = 1; i <= 254; i++) {
			a = (double) i/255.0;
			b = Math.pow(a, gamma);
			aux = (int) Math.round(b*255);
			vout.set(i, aux);
		}
		vout.set(0, vout.get(1));
		vout.set(255, vout.get(254));
		for(int i = 0; i < imagen.getWidth(); i++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				Color color = new Color(imagen.getRGB(i, j));
				Color newColor = new Color(vout.get(color.getRed()), vout.get(color.getRed()), vout.get(color.getRed()));
				resultado.setRGB(i, j, newColor.getRGB());
			}
		}
		return resultado;
	}
}
