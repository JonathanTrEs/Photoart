package vision.photoart.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vision.photoart.herramientas.AjusteBrilloContraste;

public class VentanaAjusteByC extends JDialog {
  private JSlider sBrillo = new JSlider(0, 255);
  private JSlider sContraste = new JSlider(0, 255);
  private JButton aceptar = new JButton("Aceptar");
  private JTextField brilloActual, contrasteActual;
  private BufferedImage imagen;
  private BufferedImage imagenSalida;
  private int brillo = 0;
  private int contraste = 0;
  private int nuevoBrillo = 0;
  private int nuevoContraste = 0;
  
  public VentanaAjusteByC(double brillo, double contraste, BufferedImage imagen) {
	  this.imagen = imagen;
	  sBrillo.setValue((int) brillo);
	  sContraste.setValue((int) contraste);
	  brilloActual = new JTextField(((int) brillo) + "   ");
	  brilloActual.setSize(30, 5);
	  contrasteActual = new JTextField(((int) contraste) + "   ");
	  this.brillo = (int) brillo;
	  this.contraste = (int) contraste;
	  nuevoBrillo = (int)brillo;
	  nuevoContraste = (int)contraste;
	  setSize(330, 180);
	  setTitle("Ajustar brillo y contraste");
	  setLocationRelativeTo(null);
	  setModal(true);
	  crearElementos();
	  crearEventos();
	  setVisible(true);
  }
  
  private void crearElementos() {
	  JPanel panelControles = new JPanel(new GridLayout(3, 1));
	  JPanel controlBrillo = new JPanel(new FlowLayout());
	  JPanel controlContraste = new JPanel(new FlowLayout());
	  JPanel controlAceptar = new JPanel(new FlowLayout());
	  controlAceptar.add(aceptar);
	  sBrillo.setMajorTickSpacing(50);
	  sBrillo.setPaintTicks(true);
	  sBrillo.setPaintLabels(true);
	  sContraste.setMajorTickSpacing(50);
	  sContraste.setPaintTicks(true);
	  sContraste.setPaintLabels(true);
	  controlBrillo.add(new JLabel("Brillo         "));
	  controlBrillo.add(brilloActual);
	  controlBrillo.add(sBrillo);
	  controlContraste.add(new JLabel("Contraste"));
	  controlContraste.add(contrasteActual);
	  controlContraste.add(sContraste);
	  panelControles.add(controlBrillo);
	  panelControles.add(controlContraste);
	  panelControles.add(controlAceptar);
	  
	  this.add(panelControles);
  }
  
  private void crearEventos() {
	  sBrillo.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	    	  nuevoBrillo = sBrillo.getValue();
	    	  brilloActual.setText(sBrillo.getValue() + "");
	      }
	  });
	  
	  sContraste.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent e) {
	    	  nuevoContraste = sContraste.getValue();
	    	  contrasteActual.setText(sContraste.getValue() + "");
	      }
	  });
	  
	  aceptar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	      int valorBrillo = Integer.parseInt(brilloActual.getText().replace(" ", ""));
	      int valorContraste = Integer.parseInt(contrasteActual.getText().replace(" ", ""));
	      //Controlamos si metemos un valor erroneo en los campos de texto
	      if (valorBrillo >= 0 && valorBrillo <= 255)
	        sBrillo.setValue(valorBrillo);
	      else
	        brilloActual.setText(sBrillo.getValue() + "");
	      
	      if (valorContraste >= 0 && valorContraste <= 255)
	        sContraste.setValue(valorContraste);
	      else
	        contrasteActual.setText(sContraste.getValue() + "");
	      
	      crearImagen();
	      dispose();
	    }
	  });
	  
	  brilloActual.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int valor = Integer.parseInt(brilloActual.getText().replace(" ", ""));
        if (valor >= 0 && valor <= 255)
          sBrillo.setValue(valor);
        else
          brilloActual.setText(sBrillo.getValue() + "");
      }
    });
	  
	  contrasteActual.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int valor = Integer.parseInt(contrasteActual.getText().replace(" ", ""));
        if (valor >= 0 && valor <= 255)
          sContraste.setValue(valor);
        else
          contrasteActual.setText(sContraste.getValue() + "");
      }
    });
  }
  
  private void crearImagen() {
	  imagenSalida = new AjusteBrilloContraste().modificarBrilloContraste(imagen, nuevoBrillo, brillo, nuevoContraste, contraste);
  }

  public BufferedImage getImagenSalida() {
	  return imagenSalida;
  }

  public void setImagenSalida(BufferedImage imagenSalida) {
	  this.imagenSalida = imagenSalida;
  }
}
