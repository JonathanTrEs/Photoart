package vision.photoart.ventanas;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import vision.photoart.herramientas.Rotar;

public class VentanaRotacion {
	private BufferedImage imagen;
	private int pixelFondo;
	
	public VentanaRotacion(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	public BufferedImage calcularBilineal() {
		int grados = Integer.parseInt((JOptionPane.showInputDialog("Introduce el ángulo de rotación en grados", 0)));
		Rotar rotar = new Rotar(imagen);
		BufferedImage imagenSalida = rotar.rotarAngulo(grados,2);
		pixelFondo = rotar.getPixelFondo();
		return imagenSalida;
	}
	
	public BufferedImage calcularVecinos() {
		int grados = Integer.parseInt((JOptionPane.showInputDialog("Introduce el ángulo de rotación en grados", 0)));
		Rotar rotar = new Rotar(imagen);
		BufferedImage imagenSalida = rotar.rotarAngulo(grados,1);
		pixelFondo = rotar.getPixelFondo();
		return imagenSalida;
	}
	
	public BufferedImage mapeoDirecto() {
		int grados = Integer.parseInt((JOptionPane.showInputDialog("Introduce el ángulo de rotación en grados", 0)));
		Rotar rotar = new Rotar(imagen);
		BufferedImage imagenSalida = rotar.rotarAngulo(grados,3);
		pixelFondo = rotar.getPixelFondo();
		return imagenSalida;
	}
	
	public int getPixelFondo() {
		return pixelFondo;
	}
}
