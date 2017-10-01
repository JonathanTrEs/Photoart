package vision.photoart.imagen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import vision.photoart.herramientas.Recta;
import vision.photoart.herramientas.Seleccion;

public class Imagen extends JPanel {
  private BufferedImage imagen;
  private ImageIcon icon;
  private ArrayList<ArrayList<Color>> pixeles = new ArrayList<ArrayList<Color>>();
  private Seleccion seleccion;
  private Recta recta;
  private int version;
  private int versionOriginal;
  private PintarDiferencia pintarDiferencia = new PintarDiferencia();
  private int pixelFondo = 0;
  private boolean directo = false;
  
  public int getVersion() {
	return version;
  }

  public void setVersion(int version) {
	this.version = version;
  }

  public int getVersionOriginal() {
	return versionOriginal;
  }

  public void setVersionOriginal(int versionOriginal) {
	this.versionOriginal = versionOriginal;
  }

//Si la ventana sale azul es que algo sali� mal
  public Imagen() {
    setBackground(Color.BLUE);
  }
  
  public Imagen(BufferedImage imagen, int version) throws IOException {	
    this.imagen = imagen;
    this.version = version;
    this.versionOriginal = version;
    icon = new ImageIcon(this.imagen);
    setSize(this.imagen.getWidth(), this.imagen.getHeight());
    obtenerMatriz();
  }
  
  public void obtenerMatriz() {
    for (int i = 0; i < imagen.getWidth(); i++) {
      pixeles.add(new ArrayList<Color>());
      for (int j = 0; j < imagen.getHeight(); j++) 
        pixeles.get(i).add(new Color(imagen.getRGB(i, j)));
    }
  }
  
  public BufferedImage escalaGris() {
    BufferedImage nuevaimagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
    for (int i = 0; i < nuevaimagen.getWidth(); i++) {
      for (int j = 0 ; j < nuevaimagen.getHeight(); j++) {
        Color pixel = new Color(imagen.getRGB(i, j));
        int grisCode = (int) ((int) (0.222 * pixel.getRed()) + (0.707 * pixel.getGreen()) + (0.071 * pixel.getBlue()));
        Color gris = new Color(grisCode, grisCode, grisCode);
        nuevaimagen.setRGB(i, j, gris.getRGB());
      }
    }
    return nuevaimagen;
  }
  
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      icon.paintIcon(this, g, 3, 3);
      seleccion.paintComponent((Graphics2D) g);
      //System.out.println(recta.getPosX1());
      recta.paintComponent((Graphics2D) g);
      pintarDiferencia.paintComponent(g);
  }
  
  public BufferedImage getSubImagen() {
    BufferedImage subImagen = imagen.getSubimage(seleccion.getPosX(), seleccion.getPosY(), seleccion.getSelectWidth(), seleccion.getSelectHeight());
    return subImagen;
  }
  
  public void limpiarSeleccion() {
    seleccion.setPosX(0);
    seleccion.setPosY(0);
    seleccion.setSelectWidth(0);
    seleccion.setSelectHeight(0);
    recta.limpiar();
    repaint();
  }
  
  public Color getRGB(int x, int y) {
	  Color rgb = new Color(imagen.getRGB(x,y));
	  return rgb;
  }
  
  public boolean isGris() {
    boolean gris = true;
    for (int i = 0; i < imagen.getWidth(); i++) {
      for (int j = 0 ; j < imagen.getHeight(); j++) {
        Color pixel = new Color(imagen.getRGB(i, j));
        if (!isIgual(pixel.getRed(), pixel.getBlue()) && !isIgual(pixel.getRed(), pixel.getGreen())) {
          gris = false;
        }
      }
    }
    return gris;
  }
  
  private boolean isIgual(int color1, int color2) {
	  boolean iguales = true;
	  int margenError = 1;
	  if ((Math.abs(color1 - color2)) > margenError)
		  iguales = false;
	  return iguales;
  }
  
  public void setDirecto(boolean directo) {
	  this.directo = directo;
  }
  
  public boolean isDirecto() {
	  return directo;
  }
  
  public void setPixelFondo(int pixelFondo) {
	  this.pixelFondo = pixelFondo; 
  }
  
  public int getPixelFondo() {
	  return pixelFondo;
  }
  
  public Seleccion getSeleccion() {
	return seleccion;
  }

  public void setSeleccion(Seleccion seleccion) {
	this.seleccion = seleccion;
  }

  public BufferedImage getImagen() {
    return imagen;
  }

  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }

  public void setRecta(Recta recta) {
	  this.recta = recta;
  }
  
  public Recta getRecta() {
    return recta;
  }
  
  public void setUmbralPintar(int umbral) {
	  pintarDiferencia.setUmbral(umbral);
  }

  public void setPintarDiferencia(PintarDiferencia pintarDiferencia) {
	  this.pintarDiferencia = pintarDiferencia;
  }
}
