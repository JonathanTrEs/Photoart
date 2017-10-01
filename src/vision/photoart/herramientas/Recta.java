package vision.photoart.herramientas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Recta extends JComponent implements MouseListener, MouseMotionListener {
	private int posX1 = 0, posY1 = 0;
	private int posX2 = 0, posY2 = 0;
	
	public void paintComponent(Graphics2D g) {
	      super.paintComponent(g);
	      g.setColor(Color.RED);
	      float[] style = {1,0};
	      g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, style, 0));
	      g.drawLine(posX1, posY1, posX2, posY2);
	}
	
	public void limpiar() {
	  posX1 = 0;
	  posX2 = 0;
	  posY1 = 0;
	  posY2 = 0;
	}
	
	public void mouseDragged(MouseEvent e) {
		posX2 = e.getX() - 5;
		posY2 = e.getY() - 25;
	}

	public void mouseMoved(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		posX1 = e.getX() - 5;
		posY1 = e.getY() - 25;
	}

	public void mouseReleased(MouseEvent e) {
		posX2 = e.getX() - 5;
		posY2 = e.getY() - 25;	
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {	
	}

	public int getPosX1() {
		return posX1;
	}

	public int getPosY1() {
		return posY1;
	}

	public int getPosX2() {
		return posX2;
	}

	public int getPosY2() {
		return posY2;
	}
}
