package vision.photoart.herramientas;

import java.awt.image.BufferedImage;


public class Traspuesta {
	
	private BufferedImage imagen;
	
	public Traspuesta(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	public BufferedImage trasponer() {
		BufferedImage imagenSalida = new BufferedImage(imagen.getHeight(), imagen.getWidth(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < imagen.getWidth(); i++) {
			for (int j = 0; j < imagen.getHeight(); j++) {
				imagenSalida.setRGB(j, i, imagen.getRGB(i, j));
			}
		}
		return imagenSalida;
	}
}
