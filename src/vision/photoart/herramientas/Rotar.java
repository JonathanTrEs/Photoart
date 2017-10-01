package vision.photoart.herramientas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//2 rotar bilineal 1 rotar vecinos
public class Rotar {
	BufferedImage imagen;
	int difX = 0;
	int difY = 0;
	int pixelFondo = 0;
	
	public Rotar(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	public BufferedImage rotarDerecha() {
		BufferedImage resultado = new BufferedImage(imagen.getHeight(), imagen.getWidth(), BufferedImage.TYPE_INT_RGB);
		
		for(int i = 0; i < imagen.getWidth(); i++) {
			for(int j = imagen.getHeight()-1, k = 0; j >= 0; j--, k++) {
				resultado.setRGB(k, i, imagen.getRGB(i, j));
			}
		}
		
		return resultado;
	}
	
	public BufferedImage rotarIzquierda() {
		BufferedImage resultado = new BufferedImage(imagen.getHeight(), imagen.getWidth(), BufferedImage.TYPE_INT_RGB);
		
		for(int i = imagen.getWidth()-1, k = 0; i >= 0; i--, k++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				resultado.setRGB(j, k, imagen.getRGB(i, j));
			}
		}
		
		return resultado;
	}
	
	public BufferedImage rotarAngulo(int ang, int tipo) {
		Double angulo = Math.toRadians(ang);
		ArrayList<Point> puntos = new ArrayList<Point>();
		ArrayList<Point> puntosCalculados = new ArrayList<Point>();
		puntos.add(new Point(0,0));
		puntos.add(new Point(imagen.getWidth()-1, 0));
		puntos.add(new Point(0,imagen.getHeight()-1));
		puntos.add(new Point(imagen.getWidth()-1, imagen.getHeight()-1));
		for(int i = 0; i < 4; i ++) {
			puntosCalculados.add(calcularEsquina(puntos.get(i), angulo));
		}
		Point tamano = calcularTamano(puntosCalculados);
		BufferedImage resultado = new BufferedImage(tamano.x + 1, tamano.y + 1, BufferedImage.TYPE_INT_ARGB);
		
		if(tipo == 1) {
			resultado = vmc(resultado, angulo);
		}
		else if (tipo == 2) {
			resultado = bilineal(resultado, angulo);
		}
		else if (tipo == 3) {
			resultado = mapeoDirecto(resultado, angulo);
		}
		
		//Recortar lo transparente para que no quede enorme
		Point min = new Point();
		Point max = new Point();
		min.x = resultado.getWidth();
		min.y = resultado.getHeight();
		max.x = resultado.getRGB(resultado.getWidth()/2, resultado.getHeight()/2);
		max.y = resultado.getRGB(resultado.getWidth()/2, resultado.getHeight()/2);
		
		for(int i = 0; i < resultado.getWidth(); i++) {
			for(int j = 0; j < resultado.getHeight(); j++) {
				if((new Color (resultado.getRGB(i,j)).getRed()) != 0 || 
						(new Color (resultado.getRGB(i,j)).getGreen()) != 0 ||
						(new Color (resultado.getRGB(i,j)).getBlue()) != 1) {
					if(min.x > i) {
						min.x = i;
					}
					if(min.y > j) {
						min.y = j;
					}
					if(max.x < i) {
						max.x = i;
					}
					if(max.y < j) {
						max.y = j;
					}
				}
			}
		}
		Point medida = new Point();
		medida.x = max.x - min.x;
		medida.y = max.y - min.y;
		
		BufferedImage resultado2 = new BufferedImage(medida.x+1, medida.y+1, BufferedImage.TYPE_4BYTE_ABGR);
		
		for(int i = min.x, i2 = 0; i <= max.x; i++, i2++) {
			for(int j = min.y, j2 = 0; j <= max.y; j++, j2++) {
				if((new Color (resultado.getRGB(i,j)).getRed()) == 0 && 
						(new Color (resultado.getRGB(i,j)).getGreen()) == 0 &&
						(new Color (resultado.getRGB(i,j)).getBlue()) == 1) {
					resultado2.setRGB(i2, j2, AlphaComposite.CLEAR);
					//pixelFondo++;
				}
				else {
					resultado2.setRGB(i2, j2, resultado.getRGB(i, j));
				}
			}
		}
		
		return resultado2;
	}
	
	public Point calcularEsquina(Point punto, double angulo) {
		Point resultado = new Point();
		
		resultado.x = (int) Math.round(((double)(punto.getX()) * Math.cos(angulo)) - (double)(punto.getY()) * Math.sin(angulo));
		resultado.y = (int) Math.round(((double)(punto.getX()) * Math.sin(angulo)) + (double)(punto.getY()) * Math.cos(angulo));
		
		return resultado;
	}
	
	public Point calcularTamano(ArrayList<Point> puntos){
		Point resultadoMin = new Point();
		Point resultadoMax = new Point();
		Point resultado = new Point();
		
		resultadoMin.x = (int)puntos.get(0).getX();
		resultadoMin.y = (int)puntos.get(0).getY();
		resultadoMax.x = (int)puntos.get(0).getX();
		resultadoMax.y = (int)puntos.get(0).getY();
		for(int i = 1; i < puntos.size(); i++) {
			if(puntos.get(i).getX() < resultadoMin.x) {
				resultadoMin.x = (int) puntos.get(i).getX();
			}
			if(puntos.get(i).getY() < resultadoMin.y) {
				resultadoMin.y = (int) puntos.get(i).getY();
			}
			if(puntos.get(i).getX() > resultadoMax.x) {
				resultadoMax.x = (int) puntos.get(i).getX();
			}
			if(puntos.get(i).getY() > resultadoMax.y) {
				resultadoMax.y = (int) puntos.get(i).getY();
			}
		}
		difX = Math.abs(resultadoMin.x);
		difY = Math.abs(resultadoMin.y);
		resultado.x = resultadoMax.x - resultadoMin.x;
		resultado.y = resultadoMax.y - resultadoMin.y;
		return resultado;
	}
	
	public BufferedImage vmc(BufferedImage resultado, double angulo) {
		for(int i = 0; i < resultado.getWidth(); i ++) {
			for(int j = 0; j < resultado.getHeight(); j++) {
				int x = (int) Math.round((((double)(i-difX) * Math.cos(angulo)) + (double)(j-difY) * Math.sin(angulo)));
				int y = (int) Math.round((((double)(j-difY) * Math.cos(angulo)) - (double)(i-difX) * Math.sin(angulo)));
				if(x < imagen.getWidth() && x > -1 && y < imagen.getHeight() && y > -1) {
					resultado.setRGB(i, j, imagen.getRGB(x, y));
				}
				else {
					resultado.setRGB(i, j, AlphaComposite.CLEAR);
					pixelFondo++;
				}
			}
		}
		return resultado;
	}
	
	public BufferedImage bilineal(BufferedImage resultado, double angulo) {
		for(int i = 0; i < resultado.getWidth(); i ++) {
			for(int j = 0; j < resultado.getHeight(); j++) {
				int x = (int) (((double)(i-difX) * Math.cos(angulo)) + (double)(j-difY) * Math.sin(angulo));
				int y = (int) (((double)(j-difY) * Math.cos(angulo)) - (double)(i-difX) * Math.sin(angulo));
				if(x < imagen.getWidth() && x > -1 && y < imagen.getHeight() && y > -1) {
					if(!detectarBorde(x, y)){
						if(calcularMedia(x,y) == -1) {
							resultado.setRGB(i, j, AlphaComposite.CLEAR);
						}
						else {
							int media = calcularMedia(x,y);
							Color newColor = new Color(media, media, media);
							resultado.setRGB(i, j, newColor.getRGB());
						}
					}
					else {
						resultado.setRGB(i, j, imagen.getRGB(x, y));
					}
				}
				else {
					resultado.setRGB(i, j, AlphaComposite.CLEAR);
					pixelFondo++;
				}
			} 
		}
		return resultado;
	}
	
	public boolean detectarBorde(int x, int y) {
		boolean resultado;
		if(x == imagen.getWidth()-1 || x == 0 || y == imagen.getHeight()-1 || y == 0) {
			resultado = true;
		}
		else {
			resultado = false;
		}
		return resultado;
	}
	
	public int calcularMedia(int x, int y) {		
		if((new Color (imagen.getRGB(x,y)).getRed()) == 0 && 
				(new Color (imagen.getRGB(x,y)).getGreen()) == 0 &&
				(new Color (imagen.getRGB(x,y)).getBlue()) == 1) {
			return -1;
		}
		else {
			int media = ((new Color(imagen.getRGB(x -1,y)).getRed()) + (new Color(imagen.getRGB(x, y-1)).getRed()) + 
							(new Color (imagen.getRGB(x+1,y)).getRed()) + (new Color (imagen.getRGB(x, y+1)).getRed()))/4;
			return media;
		}
	}
	
	public BufferedImage mapeoDirecto(BufferedImage resultado, double angulo) {
		pixelFondo = 0;
		for(int i = 0; i < resultado.getWidth(); i ++) {
			for(int j = 0; j < resultado.getHeight(); j++) {
				resultado.setRGB(i, j, AlphaComposite.CLEAR);
			}
		}
		for(int i = 0; i < imagen.getWidth(); i ++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				int x = (int)((i)*Math.cos(angulo)) + (int)((j)*(-Math.sin(angulo)));
				int y = (int)((i)*Math.sin(angulo)) + (int)((j)*(Math.cos(angulo)));
				resultado.setRGB(x+difX,y+difY, imagen.getRGB(i, j));
			}
		}
		for(int i = 0; i < imagen.getWidth(); i ++) {
			for(int j = 0; j < imagen.getHeight(); j++) {
				if((new Color (imagen.getRGB(i,j)).getRed()) == 0) {
					pixelFondo++;
				}
			}
		}
		return resultado;
	}
	
	public int getPixelFondo() {
		return pixelFondo;
	}
}

