package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RectaHistograma {
	private Recta recta;
	private ArrayList<Integer> pixeles = new ArrayList<Integer>();
	private BufferedImage imagen;
	private int[] canal = new int[256];
	
	public RectaHistograma(Recta recta, BufferedImage imagen) {
		this.recta = recta;
		this.imagen = imagen;
		for (int i = 0; i < 256; i++)
		  canal[i] = 0;
		calcularPixeles();
	}
	
	private void calcularPixeles() {
		int posX1 = recta.getPosX1();
		int posX2 = recta.getPosX2();
		int posY1 = recta.getPosY1();
		int posY2 = recta.getPosY2();
		double pendiente = ((double)(posY2 - posY1))/((double)(posX2 - posX1));
		double intersect = ((double) posY1) - (pendiente * (double) posX1);
		if (posX1 < posX2) {
		  for (int i = posX1; i <= posX2; i++) {
		    int auxY = (int) Math.round((pendiente * (double)i) + intersect);
		    Color color = new Color(imagen.getRGB(i, auxY));
		    pixeles.add(color.getRed());
		  }
		}
		else {
		  for (int i = posX1; i >= posX2; i--) {
        int auxY = (int) Math.round((pendiente * (double)i) + intersect);
        Color color = new Color(imagen.getRGB(i, auxY));
        pixeles.add(color.getRed());
      }
		}
	}
	
	public int[] getPixeles() {
	  int[] canal = new int[pixeles.size()];
		for (int i = 0; i < pixeles.size(); i++)
			canal[i] = pixeles.get(i);
		return canal;
	}
	
	public int getSize() {
	  return pixeles.size();
	}
}
