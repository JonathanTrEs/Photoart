package vision.photoart.herramientas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import vision.photoart.imagen.Histograma;

public class Ecualizar {
  private BufferedImage imagen;
  private ArrayList<Integer> vout = new ArrayList<Integer>();
  
  public Ecualizar(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  public BufferedImage ecualizarImagen() {
    calcularVout();
    return calcularImagen();
  }
  
  private void calcularVout() {
    Histograma histograma = new Histograma();
    int[][] canalesHistograma = histograma.histograma(imagen, false);
    int[] canal=new int[256];
    System.arraycopy(canalesHistograma[0], 0, canal, 0, canalesHistograma[0].length);
    int imagenSize = imagen.getWidth() * imagen.getHeight();
    for (int i = 0; i < 256; i++) {
      vout.add((int) Math.max(0, Math.round((255.0 / (double)imagenSize) * (double)canal[i]) - 1));
    }
  } 
  
  private BufferedImage calcularImagen () {
    BufferedImage imagenSalida = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < imagen.getWidth(); i++) {
      for (int j = 0; j < imagen.getHeight(); j++) {
        Color color = new Color(imagen.getRGB(i, j));
        Color newColor = new Color(vout.get(color.getRed()), vout.get(color.getRed()), vout.get(color.getRed()));
        imagenSalida.setRGB(i, j, newColor.getRGB());
      }
    }
    return imagenSalida;
  }
}
