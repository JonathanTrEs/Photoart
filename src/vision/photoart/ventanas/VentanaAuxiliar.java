package vision.photoart.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vision.photoart.herramientas.Recta;
import vision.photoart.herramientas.Seleccion;
import vision.photoart.imagen.Imagen;

public class VentanaAuxiliar extends JInternalFrame implements MouseListener, MouseMotionListener{
	private Imagen imagen;
	private String titulo;
	private Seleccion seleccion = new Seleccion();
	private Recta recta = new Recta();
	private boolean gris;
	private boolean seleccionar = false;
	private boolean dibujarRecta = false;
	private JPanel panelInferior;
	private JLabel pixel = new JLabel("0 0");
	private JLabel color = new JLabel("RGB: (0, 0, 0)");
  
  public VentanaAuxiliar(BufferedImage imagen, String titulo, int version) {
	this.titulo = titulo; 
	this.imagen = new Imagen();
    try {
      this.imagen = new Imagen(imagen, version);
    } catch (IOException e) {
      e.printStackTrace();
    }
    add(this.imagen);
    this.imagen.setSeleccion(seleccion);
    this.imagen.setRecta(recta);
    gris = this.imagen.isGris();
    addMouseListener(this);
    addMouseMotionListener(this);
    panelInferior = new JPanel(new FlowLayout(0));
    panelInferior.add(pixel);
    panelInferior.add(color);
    add(panelInferior, BorderLayout.SOUTH);
    setTitle(this.titulo + " (" + version + ") " + " (" + this.imagen.getWidth() + "x" + this.imagen.getHeight() + ")");
    setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    setSize(imagen.getWidth() + 15, imagen.getHeight() + 60);
    setVisible(true);
  }

  public Imagen getImagen() {
    return imagen;
  }

  public void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

  public boolean isGris() {
    return gris;
  }

  public void setGris(boolean gris) {
    this.gris = gris;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
	
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (seleccionar) {
      seleccion.mousePressed(e);
      imagen.repaint();
    }
    if (dibujarRecta) {
    	recta.mousePressed(e);
    	//imagen.repaint();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (seleccionar) {
      seleccion.mouseReleased(e);
      imagen.repaint();
    }
    if (dibujarRecta) {
    	recta.mouseReleased(e);
    	imagen.repaint();
    	dibujarRecta = false;
    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
	
  }

  @Override
  public void mouseExited(MouseEvent e) {
  
  }

  @Override
  public void mouseDragged(MouseEvent e) {
	int posX = e.getX() - 3 - 6;
	int posY = e.getY() - 3 - 29;
	pixel.setText((posX + 1) + " " + (posY + 1));
	
	if (posX >= 0 && posY >= 0 && posX < imagen.getImagen().getWidth() && posY < imagen.getImagen().getHeight()) {
    	Color rgb = imagen.getRGB(posX, posY);
    	color.setText("RGB: (" + rgb.getRed() + ", " + rgb.getGreen() + ", " + rgb.getBlue() + ")");
    }
    else 
    	color.setText("RGB: (0, 0, 0)");
	
    if (seleccionar) {
      seleccion.mouseDragged(e);
      imagen.repaint();
    }
    if (dibujarRecta) {
    	recta.mouseReleased(e);
    	imagen.repaint();
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {

	int posX = e.getX() - 3 - 6;
	int posY = e.getY() - 3 - 29;
    pixel.setText((posX + 1) + " " + (posY + 1));
    
    if (posX >= 0 && posY >= 0 && posX < imagen.getImagen().getWidth() && posY < imagen.getImagen().getHeight()) {
    	Color rgb = imagen.getRGB(posX, posY);
    	color.setText("RGB: (" + rgb.getRed() + ", " + rgb.getGreen() + ", " + rgb.getBlue() + ")");
    }
    else 
    	color.setText("RGB: (0, 0, 0)");

  }
  
  public void setPixelFondo(int pixelFondo) {
	  imagen.setPixelFondo(pixelFondo);
  }
  
  public boolean isSeleccionar() {
    return seleccionar;
  }

  public void setSeleccionar(boolean seleccionar) {
    this.seleccionar = seleccionar;
  }

  public String getTitulo() {
	  return titulo;
  }

  public void setTitulo(String titulo) {
	  this.titulo = titulo;
  }

  public void setDibujarRecta(boolean dibujarRecta) {
	  this.dibujarRecta = dibujarRecta;
  }

  public Recta getRecta() {
    return recta;
  }
}
