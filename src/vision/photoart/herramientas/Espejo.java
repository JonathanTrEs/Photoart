package vision.photoart.herramientas;

import java.awt.image.BufferedImage;

public class Espejo {
	private BufferedImage imagen;
	
	public Espejo(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	public BufferedImage espejoHorizontal() {
		BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0, inEspejo = imagen.getWidth() - 1; i < imagen.getWidth(); i++, inEspejo--) {
			for (int j = 0; j < imagen.getHeight(); j++) {
				imagenSalida.setRGB(i, j, imagen.getRGB(inEspejo, j));
			}
		}
		return imagenSalida;
	}
	
	public BufferedImage espejoVertical() {
		BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < imagen.getWidth(); i++) {
			for (int j = 0, inEspejo = imagen.getHeight() - 1; j < imagen.getHeight(); j++, inEspejo--) {
				imagenSalida.setRGB(i, j, imagen.getRGB(i, inEspejo));
			}
		}
		return imagenSalida;
	}
}
