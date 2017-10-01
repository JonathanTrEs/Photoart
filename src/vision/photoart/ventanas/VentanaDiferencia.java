package vision.photoart.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vision.photoart.herramientas.DiferenciaImagenes;
import vision.photoart.herramientas.Recta;
import vision.photoart.herramientas.Seleccion;
import vision.photoart.imagen.Imagen;
import vision.photoart.imagen.PintarDiferencia;
import vision.photoart.layaout.SpringUtilities;

public class VentanaDiferencia extends JInternalFrame implements MouseListener, MouseMotionListener{
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
	private JSlider slider = new JSlider(0, 255);
	private JTextField umbral = new JTextField(3);
	private int valorUmbral = 0;
	private BufferedImage resultado;
  
  public VentanaDiferencia(BufferedImage imagen, BufferedImage imagenDif, String titulo, int version) {
    this.titulo = titulo; 
    this.imagen = new Imagen();
    try {
      this.imagen = new Imagen(imagen, version);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.imagen.setPintarDiferencia(new PintarDiferencia(imagen, imagenDif));
    add(this.imagen);
    resultado = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
    this.imagen.setSeleccion(seleccion);
    this.imagen.setRecta(recta);
    gris = this.imagen.isGris();
    addMouseListener(this);
    addMouseMotionListener(this);
    panelInferior = new JPanel(new SpringLayout());
    JPanel panelEtiqueta = new JPanel(new FlowLayout(0));
    JPanel panelSlider = new JPanel(new FlowLayout());
    umbral.setText("255");
    valorUmbral = 255;
    slider.setValue(255);
    slider.setMajorTickSpacing(50);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    panelSlider.add(umbral);
    panelSlider.add(slider);
    panelEtiqueta.add(pixel);
    panelEtiqueta.add(color); 

    panelInferior.add(panelSlider);
    panelInferior.add(panelEtiqueta);
    SpringUtilities.makeCompactGrid(panelInferior, 2, 1, 0, 0, 5, 5);
    add(panelInferior, BorderLayout.SOUTH);
    crearEventos();
    setTitle(this.titulo + " (" + version + ") " + " (" + this.imagen.getWidth() + "x" + this.imagen.getHeight() + ")");
    setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    setSize(imagen.getWidth() + 15, imagen.getHeight() + 122);
    setVisible(true);
    repaint();
  }
  
  private void crearEventos() {
	  slider.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	    	  valorUmbral = slider.getValue();
	    	  umbral.setText(slider.getValue() + "");
	    	  imagen.setUmbralPintar(valorUmbral);
		   	  imagen.repaint();
	      }
	  });
	  
	  umbral.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  int valor = Integer.parseInt(umbral.getText().replace(" ", ""));
	    	  if (valor >= 0 && valor <= 255)
	    		  slider.setValue(valor);
	    	  else
	    		  umbral.setText(slider.getValue() + "");
	      }
	  });
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
