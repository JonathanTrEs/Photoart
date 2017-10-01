package vision.photoart.herramientas;

import java.awt.image.BufferedImage;

import vision.photoart.imagen.Histograma;

public class Rango {
  private int menor;
  private int mayor;
  private BufferedImage imagen;
  private boolean gris;
  
  public Rango(BufferedImage imagen , boolean gris) {
    this.imagen = imagen;
    this.gris = gris;
  }
  
  public void calcularLimites () {
    Histograma histograma = new Histograma();
    int[][] canalesHistograma = histograma.histograma(imagen, true);
    int[] canal=new int[256];
    if (gris)
      System.arraycopy(canalesHistograma[0], 0, canal, 0, canalesHistograma[0].length);
    else
      System.arraycopy(canalesHistograma[4], 0, canal, 0, canalesHistograma[4].length);
    int i = 0;
    while(canal[i] == 0)
      i++;
    menor = i;
    i = 255;
    while(canal[i] == 0)
      i--;
    mayor = i;
  }

  public int getMenor() {
    return menor;
  }

  public int getMayor() {
    return mayor;
  }
}
