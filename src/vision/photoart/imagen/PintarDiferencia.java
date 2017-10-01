package vision.photoart.imagen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class PintarDiferencia extends JComponent{
	private BufferedImage imagenDiferencia = null;
	private BufferedImage imagen = null;
	private int umbral = 255;
	
	public PintarDiferencia() {};
	
	public PintarDiferencia(BufferedImage imagen, BufferedImage imagenDif) {
		this.imagen = imagen;
		imagenDiferencia = imagenDif;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagenDiferencia != null) {
			g.setColor(Color.RED);
			for (int i = 0; i < imagenDiferencia.getWidth(); i++) {
				for (int j = 0; j < imagenDiferencia.getHeight(); j++) {
					if (new Color(imagenDiferencia.getRGB(i, j)).getRed() >= umbral)
						g.fillRect(3 + i, 3 + j, 1, 1);
				}
			}
		}
	}

	public void setImagenDiferencia(BufferedImage imagenDiferencia) {
		this.imagenDiferencia = imagenDiferencia;
	}

	public void setUmbral(int umbral) {
		this.umbral = umbral;
	}
}
