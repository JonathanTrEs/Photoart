package vision.photoart.herramientas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Seleccion extends JComponent implements MouseListener, MouseMotionListener{
	private int posX = 0;
	private int posY = 0;
	private int selectWidth = 0;
	private int selectHeight = 0;
	private int posXActual = 0;
	private int posYActual = 0;
	
	public Seleccion() {
		//addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		posX = e.getX() - 5;
		posY = e.getY() - 25;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if((e.getY()-25) < posY && (e.getX()-5) > posX) {
			selectWidth = e.getX() - posX -5;
		    selectHeight = Math.abs(e.getY() - posY -25);
		    posXActual = posX;
		    posYActual = e.getY() -25;
		}
		else if((e.getY()-25) < posY && (e.getX()-5) < posX){
			selectWidth = Math.abs(e.getX() - posX -5);
		    selectHeight = Math.abs(e.getY() - posY -25);
		    posXActual = e.getX() - 5;
		    posYActual = e.getY() -25;
		}
		else if((e.getY()-25) > posY && (e.getX()-5) < posX) {
			selectWidth = Math.abs(e.getX() - posX -5);
		    selectHeight = e.getY() - posY - 25;
		    posXActual = e.getX() - 5;
		    posYActual = posY;
		}
		else {
			selectWidth = e.getX() - posX - 5;
		    selectHeight = e.getY() - posY - 25;
		    posYActual = posY;
		    posXActual = posX;
		}
		posX = posXActual;
		posY = posYActual;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	@Override
  public void mouseDragged(MouseEvent e) {
	if((e.getY()-25) < posY && (e.getX()-5) > posX) {
		selectWidth = e.getX() - posX -5;
	    selectHeight = Math.abs(e.getY() - posY -25);
	    posXActual = posX;
	    posYActual = e.getY() -25;
	}
	else if((e.getY()-25) < posY && (e.getX()-5) < posX){
		selectWidth = Math.abs(e.getX() - posX -5);
	    selectHeight = Math.abs(e.getY() - posY -25);
	    posXActual = e.getX() - 5;
	    posYActual = e.getY() -25;
	}
	else if((e.getY()-25) > posY && (e.getX()-5) < posX) {
		selectWidth = Math.abs(e.getX() - posX -5);
	    selectHeight = e.getY() - posY - 25;
	    posXActual = e.getX() - 5;
	    posYActual = posY;
	}
	else {
		selectWidth = e.getX() - posX - 5;
	    selectHeight = e.getY() - posY - 25;
	    posYActual = posY;
	    posXActual = posX;
	}
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
	
	public void paintComponent(Graphics2D g) {
	      super.paintComponent(g);
	      g.setColor(Color.RED);
	      float[] style = {10,5};
	      g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, style, 0));
	      g.drawRect(posXActual, posYActual, selectWidth, selectHeight);
	}

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public int getSelectWidth() {
    return selectWidth;
  }

  public void setSelectWidth(int selectWidth) {
    this.selectWidth = selectWidth;
  }

  public int getSelectHeight() {
    return selectHeight;
  }

  public void setSelectHeight(int selectHeight) {
    this.selectHeight = selectHeight;
  }
}
