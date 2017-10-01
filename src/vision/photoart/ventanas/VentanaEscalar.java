package vision.photoart.ventanas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vision.photoart.herramientas.Escalar;

public class VentanaEscalar extends JDialog{
	  private JButton aceptar = new JButton("Aceptar");
	  private JTextField textX, textY;
	  private JComboBox<String> opcionesAncho;
	  private JComboBox<String> opcionesAlto;
	  private BufferedImage imagen;
	  private BufferedImage imagenSalida;
	  private int tipo;
	  
	  public VentanaEscalar(BufferedImage imagen, int tipo) {
		  this.imagen = imagen;
		  this.tipo = tipo;
		  textX = new JTextField(3);
		  textY = new JTextField(3);
		  setSize(250, 140);
		  setTitle("Porcentaje de la Imagen");
		  setLocationRelativeTo(null);
		  setModal(true);
		  crearElementos();
		  crearEventos();
		  setVisible(true);
	  }
	  
	  private void crearElementos() {
		  JPanel panelControles = new JPanel(new GridLayout(3, 1));
		  JPanel controlX = new JPanel(new FlowLayout());
		  JPanel controlY = new JPanel(new FlowLayout());
		  JPanel controlAceptar = new JPanel(new FlowLayout());
		  textX.setText(imagen.getWidth() + "");
		  textY.setText(imagen.getHeight() + "");
		  
		  opcionesAncho = new JComboBox<String>();
		  opcionesAncho.addItem("Píxeles");
		  opcionesAncho.addItem("Porcentaje");
		  opcionesAlto = new JComboBox<String>();
		  opcionesAlto.addItem("Píxeles");
		  opcionesAlto.addItem("Porcentaje");
		  
		  controlAceptar.add(aceptar);
		  
		  controlX.add(new JLabel("Ancho "));
		  controlX.add(textX);
		  controlX.add(opcionesAncho);
		  controlY.add(new JLabel("Alto    "));
		  controlY.add(textY);
		  controlY.add(opcionesAlto);
		  
		  panelControles.add(controlX);
		  panelControles.add(controlY);
		  panelControles.add(controlAceptar);
		  
		  this.add(panelControles);
	  }
	  
	  private void crearEventos() {
		  
		  aceptar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      int valorX = Integer.parseInt(textX.getText().replace(" ", ""));
		      int valorY = Integer.parseInt(textY.getText().replace(" ", ""));
		      //Controlamos si metemos un valor erroneo en los campos de texto
		      if (valorX <= 0)
		    	  textX.setText("1");
		      if (valorY <= 0)
		    	  textY.setText("1");
		      if (opcionesAlto.getSelectedIndex() == 0)
		    	  calcularPorcentaje(Integer.parseInt(textX.getText()), Integer.parseInt(textY.getText()));
		      crearImagen();
		      dispose();
		    }
		  });
		  
		  textX.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  int valor = Integer.parseInt(textX.getText().replace(" ", ""));
				  if (valor <= 0)
					  textX.setText("1");
	      		}
		  });
		  
		  textY.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  int valor = Integer.parseInt(textY.getText().replace(" ", ""));
				  if (valor <= 0)
					  textY.setText("1");
	      	}
		  });
		  
		  opcionesAncho.addActionListener(new ActionListener() {		
			  public void actionPerformed(ActionEvent e) {
				  opcionesAlto.setSelectedIndex(opcionesAncho.getSelectedIndex());
				  if (opcionesAncho.getSelectedIndex() == 0) {
					  textX.setText(imagen.getWidth() + "");
					  textY.setText(imagen.getHeight() + "");
				  }
				  else if (opcionesAncho.getSelectedIndex() == 1) {
					  textX.setText("100");
					  textY.setText("100");
				  }
			  }
		  });
		  
		  opcionesAlto.addActionListener(new ActionListener() {		
			  public void actionPerformed(ActionEvent e) {
				  opcionesAncho.setSelectedIndex(opcionesAlto.getSelectedIndex());
				  if (opcionesAlto.getSelectedIndex() == 0) {
					  textX.setText(imagen.getWidth() + "");
					  textY.setText(imagen.getHeight() + "");
				  }
				  else if (opcionesAlto.getSelectedIndex() == 1) {
					  textX.setText("100");
					  textY.setText("100");
				  }
			  }
		  });
	  }
	  
	  private void calcularPorcentaje(int ancho, int alto) {
		  int porcentajeAncho = (int)Math.round((double)(ancho * 100) / (double)imagen.getWidth());
		  int porcentajeAlto = (int)Math.round((double)(alto * 100) / (double)imagen.getHeight());
		  textX.setText(porcentajeAncho + "");
		  textY.setText(porcentajeAlto + "");
	  }
	  
	  private void crearImagen() {
		  imagenSalida = new Escalar(imagen).escalado(Integer.parseInt(textX.getText()), Integer.parseInt(textY.getText()), tipo);
	  }

	  public BufferedImage getImagenSalida() {
		  return imagenSalida;
	  }

	  public void setImagenSalida(BufferedImage imagenSalida) {
		  this.imagenSalida = imagenSalida;
	  }
}
