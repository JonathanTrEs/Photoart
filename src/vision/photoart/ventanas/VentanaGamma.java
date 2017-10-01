package vision.photoart.ventanas;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import vision.photoart.herramientas.Gamma;

public class VentanaGamma {
	private BufferedImage imagen;
	private double gamma;
	
	public VentanaGamma(BufferedImage imagen) {
		this.imagen = imagen;
		gamma = Double.parseDouble(JOptionPane.showInputDialog("Introduce Gamma", 0));
	}
	
	public BufferedImage calcularGamma() {
		return new Gamma().calcularGamma(imagen, gamma);
	}
}
