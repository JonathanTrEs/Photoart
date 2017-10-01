package vision.photoart.ventanas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vision.photoart.herramientas.TranformacionesTramos;
import vision.photoart.imagen.DibujarTramos;

public class VentanaTramos extends JDialog {
  private BufferedImage imagen;
  private BufferedImage imagenSalida;
	private int nTramos;
	private ArrayList<Point> puntos = new ArrayList<Point>();
	private ArrayList<ArrayList<JTextField>> entradas = new ArrayList<ArrayList<JTextField>>();
	private JButton aceptar = new JButton("Aceptar");
	private DibujarTramos dTramos;
	
	public VentanaTramos(BufferedImage imagen) {
	  this.imagen = imagen;
		nTramos = Integer.parseInt(JOptionPane.showInputDialog(this, "Inroduce el número de tramos"));
		setSize(550, 280 + (nTramos * 10));
		setTitle("Ajustar brillo y contraste");
		setLocationRelativeTo(null);
		setModal(true);
		JPanel general = new JPanel(new GridLayout(1,2));
		general.add(crearInputs());
		dTramos = new DibujarTramos();
		general.add(dTramos);
		add(general);
		crearEventos();
		setVisible(true);
	}
	
	private JPanel crearInputs() {
		JPanel panelInputs = new JPanel(new GridLayout(nTramos + 2, 1));
		for (int i = 0; i <= nTramos; i++) {
			JPanel linea = new JPanel(new FlowLayout());
			entradas.add(new ArrayList<JTextField>());
			entradas.get(i).add(new JTextField(3));
			entradas.get(i).add(new JTextField(3));
			linea.add(entradas.get(i).get(0));
			linea.add(entradas.get(i).get(1));
			panelInputs.add(linea);
		}
		JPanel linea = new JPanel(new FlowLayout());
		linea.add(aceptar);
		panelInputs.add(linea);
		return panelInputs;
	}
	
	private void crearEventos() {
	  aceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        for (int i = 0; i < entradas.size(); i++) {
          Point punto = new Point(Integer.parseInt(entradas.get(i).get(0).getText()), Integer.parseInt(entradas.get(i).get(1).getText()));
          puntos.add(punto);
        }
        TranformacionesTramos tramos = new TranformacionesTramos(imagen);
        imagenSalida = tramos.calcularTranformacionTramos(nTramos, puntos);
        dispose();
      }
    });
	  
	  for (int i = 0; i < entradas.size(); i++) {
	    final int actual = i;
	    entradas.get(i).get(1).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          ArrayList<Point> dPuntos = new ArrayList<Point>();
          for (int j = 0; j <= actual; j++) {
            Point punto = new Point(Integer.parseInt(entradas.get(j).get(0).getText()), Integer.parseInt(entradas.get(j).get(1).getText()));
            dPuntos.add(punto);
          }
          dTramos.setPuntos(dPuntos);
          dTramos.repaint();
        }
      });
	  }
	}

  public BufferedImage getImagenSalida() {
    return imagenSalida;
  }
}
