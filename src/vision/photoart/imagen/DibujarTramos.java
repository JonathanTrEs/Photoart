package vision.photoart.imagen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;

public class DibujarTramos extends JComponent {
  public static final int SIZE = 255;
  private ArrayList<Point> puntos = new ArrayList<Point>();
  
  public DibujarTramos() {
    
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, SIZE, SIZE);
    g.setColor(Color.BLACK);
    for (int i = 0; i < puntos.size()- 1; i++) {
      g.drawLine((int)puntos.get(i).getX(), SIZE - (int)puntos.get(i).getY(), (int)puntos.get(i + 1).getX(), SIZE - (int)puntos.get(i + 1).getY());
    }
  }

  public void setPuntos(ArrayList<Point> puntos) {
    this.puntos = puntos;
  }
}
