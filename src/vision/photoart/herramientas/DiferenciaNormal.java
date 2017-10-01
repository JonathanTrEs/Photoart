package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DiferenciaNormal{
	public BufferedImage calcularDiferencia(BufferedImage imagen, BufferedImage imagenDos) {
		BufferedImage resultado = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < imagen.getWidth(); i++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				int colorCalculado = Math.abs(new Color(imagen.getRGB(i, j)).getRed() - new Color(imagenDos.getRGB(i, j)).getRed());
				Color color = new Color(colorCalculado, colorCalculado, colorCalculado);
				resultado.setRGB(i, j, color.getRGB());
			}
		}
		return resultado;
	}
}