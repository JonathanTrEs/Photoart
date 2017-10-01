package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Negativo {
  private BufferedImage imagen;
  private ArrayList<Integer> vout = new ArrayList<Integer>();
  
  public Negativo(BufferedImage imagen) {
	  this.imagen = imagen;
	  for (int i = 255; i >= 0; i--)
		  vout.add(i);
  }
  
  public BufferedImage calcularNegativo() {
	  BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
	  for (int i = 0; i < imagen.getWidth(); i++) {
		  for (int j = 0; j < imagen.getHeight(); j++) {
			  Color color = new Color(imagen.getRGB(i, j));
			  Color newColor = new Color(vout.get(color.getRed()), vout.get(color.getGreen()), vout.get(color.getBlue()));
			  imagenSalida.setRGB(i, j, newColor.getRGB());
		  }
	  }
	  return imagenSalida;
  }
}
