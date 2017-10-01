package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AjusteBrilloContraste {
	
	public BufferedImage modificarBrilloContraste(BufferedImage imagen, double brilloin, double brillo, double contrastein, double contraste) {
			double a;
			double b;
			int aux;
			
			a = contrastein / contraste;
			b = brilloin- a*brillo;
			
			ArrayList<Integer> vout = new ArrayList<Integer>();
			
			for (int i=0; i<256; i++){
				aux = (int)(a*i+b);
				if (aux>254)
					aux=254;
				if (aux<0)
					aux=0;
				vout.add(aux);
			}

			BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(), imagen.getHeight(),BufferedImage.TYPE_INT_RGB);
			
			for(int i = 0; i < imagen.getWidth(); i++) {
				for(int j = 0; j < imagen.getHeight(); j++) {
					Color color = new Color(imagen.getRGB(i, j));
					Color newColor = new Color(vout.get(color.getRed()), vout.get(color.getRed()), vout.get(color.getRed()));
					imagenSalida.setRGB(i, j, newColor.getRGB());
				}
			}

			return imagenSalida;
	}
}
