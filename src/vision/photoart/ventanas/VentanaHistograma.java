package vision.photoart.ventanas;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import vision.photoart.imagen.DibujarHistograma;
import vision.photoart.imagen.Histograma;

public class VentanaHistograma extends JInternalFrame {
  private BufferedImage imagen;
  private JPanel contenedor;
  
  public VentanaHistograma(BufferedImage imagen, boolean gris, boolean absoluto, String titulo) {
    this.imagen = imagen;
    contenedor = new JPanel();
    add(contenedor);
    Histograma histograma = new Histograma();
    DibujarHistograma dibujarHis = new DibujarHistograma();
    int[][] canalesHistograma = histograma.histograma(imagen, absoluto);
    int[] canal=new int[256];
    int imagenSize = this.imagen.getWidth() * this.imagen.getHeight();
    if (gris)
      System.arraycopy(canalesHistograma[0], 0, canal, 0, canalesHistograma[0].length);
    else
      System.arraycopy(canalesHistograma[4], 0, canal, 0, canalesHistograma[4].length);
    dibujarHis.crearHistograma(canal, contenedor, Color.blue, absoluto, imagenSize);
    setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    setSize(600, 400 + 40);
    setTitle(titulo);
    setVisible(true);
  }
  
  public VentanaHistograma(BufferedImage imagen, boolean gris, boolean absoluto, String titulo, int pixelFondo, boolean directo) {
	    this.imagen = imagen;
	    contenedor = new JPanel();
	    add(contenedor);
	    Histograma histograma = new Histograma();
	    DibujarHistograma dibujarHis = new DibujarHistograma();
	    int[][] canalesHistograma = histograma.histograma(imagen, absoluto);
	    int[] canal=new int[256];
	    int imagenSize = this.imagen.getWidth() * this.imagen.getHeight();
	    if (gris)
	      System.arraycopy(canalesHistograma[0], 0, canal, 0, canalesHistograma[0].length);
	    else
	      System.arraycopy(canalesHistograma[4], 0, canal, 0, canalesHistograma[4].length);
	    if (!directo)
	    	canal[0] = canal[0] - pixelFondo;
	    else
	    	canal[0] = pixelFondo;
	    dibujarHis.crearHistograma(canal, contenedor, Color.blue, absoluto, imagenSize);
	    setClosable(true);
	    setIconifiable(true);
	    setMaximizable(true);
	    setResizable(true);
	    setSize(600, 400 + 40);
	    setTitle(titulo);
	    setVisible(true);
	  }
}
