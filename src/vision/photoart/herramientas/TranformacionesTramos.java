package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TranformacionesTramos {
	private ArrayList<Integer> vout = new ArrayList<Integer>();
	BufferedImage imagen;
	
	public TranformacionesTramos (BufferedImage imagen) {
		this.imagen = imagen;
		for(int i = 0; i < 256; i++)
			vout.add(i);
	}
	
	public BufferedImage calcularTranformacionTramos(int tramos, ArrayList<Point> puntos) {
		BufferedImage resultado = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < tramos; i++) {
			calcularTramo(puntos.get(i), puntos.get(i+1));
		}
		
		for(int i = 0; i < imagen.getWidth(); i++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				Color color = new Color(imagen.getRGB(i, j));
				Color newColor = new Color(vout.get(color.getRed()), vout.get(color.getRed()), vout.get(color.getRed()));
				resultado.setRGB(i, j, newColor.getRGB());
			}
		}
		return resultado;
	}
	
	private void calcularTramo(Point first, Point second) {
		double a = (double)(second.getY() - first.getY())/(double)(second.getX() - first.getX());
		double b = (double)first.getY() - a * (double) first.getX();
		for(int i = (int)first.getX(); i <= (int)second.getX(); i++) {
			/*if((int)(a * i + b) < 0)
				vout.set(i, 0);
			else if ((int)(a * i + b)> 255) 
				vout.set(i, 255);
			else*/
				vout.set(i, (int)(a * i + b));
		}
	}
}
