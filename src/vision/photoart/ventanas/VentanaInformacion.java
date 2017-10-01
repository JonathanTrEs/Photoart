package vision.photoart.ventanas;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import vision.photoart.herramientas.Brillo;
import vision.photoart.herramientas.Contraste;
import vision.photoart.herramientas.Entropia;
import vision.photoart.herramientas.Rango;

public class VentanaInformacion {
	private double brilloCalculado = 0;
	private double contrasteCalculado = 0;
	private double entropiaCalculada = 0;
	private DecimalFormat df = new DecimalFormat("0.00");
	private BufferedImage imagen;
	boolean gris = false;
	
	public VentanaInformacion(BufferedImage imagen, boolean gris){
		this.imagen = imagen;
		this.gris = gris;
	}
	
	public void mostrarVentanaInformacion() {
		Brillo brillo = new Brillo();
		brilloCalculado = brillo.calcularBrillo(imagen, gris);
    	
		Contraste contraste = new Contraste();
		contrasteCalculado = contraste.calcularContraste(imagen, brilloCalculado, gris);
    	
		Entropia entropia = new Entropia();
		entropiaCalculada = entropia.calcularEntropia(imagen, gris);
    	
		Rango rango = new Rango(imagen, gris);
		rango.calcularLimites();
    	
		JOptionPane.showMessageDialog(null, 
    			"Brillo: " + df.format(brilloCalculado) + "\n"
				+ "Contraste: " + df.format(contrasteCalculado) + "\n"
				+ "Entropia: " + df.format(entropiaCalculada) + "\n"
				+ "Rango: [" + rango.getMenor() + ", " + rango.getMayor() +"]\n",
				"Informacion Imagen",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
