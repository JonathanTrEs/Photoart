package vision.photoart.ventanas;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import vision.photoart.herramientas.Recta;
import vision.photoart.herramientas.RectaHistograma;
import vision.photoart.imagen.DibujarHistograma;

public class VentanaRectaHistograma extends JInternalFrame{
  private BufferedImage imagen;
  
  public VentanaRectaHistograma(BufferedImage imagen, Recta recta, String titulo) {
    JPanel contenedor = new JPanel();
    add(contenedor);
    RectaHistograma rectaHistograma = new RectaHistograma(recta, imagen);
    int[] canal = rectaHistograma.getPixeles();
    int size = rectaHistograma.getSize();
    DibujarHistograma dibujarHis = new DibujarHistograma();
    dibujarHis.crearHistograma(canal, contenedor, Color.DARK_GRAY, true, size);
    setClosable(true);
    setIconifiable(true);
    setMaximizable(true);
    setResizable(true);
    setSize(600, 400 + 40);
    setTitle(titulo);
    setVisible(true);
  }
}
