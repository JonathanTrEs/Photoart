package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Binarizar {
	public BufferedImage binarizarImagen(BufferedImage imagen, int valor) {
		BufferedImage resultado = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < imagen.getWidth(); i++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				if(new Color(imagen.getRGB(i, j)).getRed() < valor) {
					Color color = (Color.BLACK);
					resultado.setRGB(i, j, color.getRGB());
				}
				else {
					Color color = (Color.WHITE);
					resultado.setRGB(i, j, color.getRGB());
				}
			}
		}
		return resultado;
	}
}
