package vision.photoart.ventanas;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import vision.photoart.herramientas.Binarizar;
import vision.photoart.herramientas.Brillo;
import vision.photoart.herramientas.Contraste;
import vision.photoart.herramientas.DiferenciaNormal;
import vision.photoart.herramientas.Ecualizar;
import vision.photoart.herramientas.Escalar;
import vision.photoart.herramientas.Espejo;
import vision.photoart.herramientas.Negativo;
import vision.photoart.herramientas.Recta;
import vision.photoart.herramientas.Rotar;
import vision.photoart.herramientas.Traspuesta;
import vision.photoart.imagen.SplashPhotoArt;

public class VentanaPrincipal extends JFrame implements ActionListener{
  JMenuBar barra;
  JMenu archivo, ver, opciones, transformaciones, espejo, rotar, escalar;
  JMenuItem abrir, guardar, salir;
  JMenuItem histoAbsoluto, histoAcumulado, infoImagen;
  JMenu transformacionesLineales, transformacionesNoLineales;
  JMenuItem escalaGrises, seleccion, recortar, recta, rectaHistograma;
  JMenuItem ajusteBrilloContraste, ajusteTramos;
  JMenuItem gamma, ecualizar, diferenciaImagenes, diferenciaUmbral, especHistograma;
  JMenuItem binarizar, negativo;
  JMenuItem espejoVertical, espejoHorizontal, traspuesta;
  JMenu rotarDerecha, rotarIzquierda;
  JMenuItem d90, d180, d270;
  JMenuItem i90, i180, i270;
  JMenu indirecto;
  JMenuItem directo;
  JMenuItem bilineal, vecinosProximos;
  JMenuItem escalarBilineal, escalarVecinos;
  JDesktopPane desktop = new JDesktopPane();
  private String ruta;
  
  public VentanaPrincipal() {
	try {
		ruta = new java.io.File(".").getCanonicalPath();
	} catch (IOException e) {
		e.printStackTrace();
	}
    archivo = new JMenu("Archivo");
    ver = new JMenu("Ver");
    opciones = new JMenu("Opciones");
    transformaciones = new JMenu("Transformaciones");
    espejo = new JMenu("Espejo");
    rotar = new JMenu("Rotar");
    escalar = new JMenu("Escalar");
    barra = new JMenuBar();
    
    transformacionesLineales = new JMenu("Transformaciones Lineales");
    transformacionesNoLineales = new JMenu("Transformaciones No Lineales");
    
    rotarDerecha = new JMenu("Derecha");
    rotarIzquierda = new JMenu("Izquierda");
    
    directo = new JMenuItem("Mapeo Directo");
    indirecto = new JMenu("Mapeo Indirecto");
    
    abrir = new JMenuItem("Abrir imagen");
    guardar = new JMenuItem("Guardar");
    salir = new JMenuItem("Salir");
    histoAbsoluto = new JMenuItem("Histograma absoluto");
    histoAcumulado = new JMenuItem("Histograma acumulado");
    infoImagen = new JMenuItem("Informacion Imagen");
    escalaGrises = new JMenuItem("Escala de grises");
    seleccion = new JMenuItem("Selección");
    recortar = new JMenuItem("Recortar");
    ajusteBrilloContraste = new JMenuItem("Cambiar Brillo y Contraste");
    ajusteTramos = new JMenuItem("Transformacion lineal por tramos");
    gamma = new JMenuItem("Correcion de Gamma");
    ecualizar = new JMenuItem("Ecualizar Histograma");
    diferenciaImagenes = new JMenuItem("Diferencia de imagenes");
    binarizar = new JMenuItem("Binarizar");
    diferenciaUmbral = new JMenuItem("Diferencia de imagenes con umbral");
    negativo = new JMenuItem("Negativo");
    especHistograma = new JMenuItem("Especificación del histograma");
    recta = new JMenuItem("Dibujar recta");
    rectaHistograma = new JMenuItem("Ver perfil de la recta");
    espejoVertical = new JMenuItem("Espejo vertical");
    espejoHorizontal = new JMenuItem("Espejo horizontal");
    traspuesta = new JMenuItem("Traspuesta");
    d90 = new JMenuItem("90º");
    d180 = new JMenuItem("180º");
    d270 = new JMenuItem("270º");
    i90 = new JMenuItem("90º");
    i180 = new JMenuItem("180º");
    i270 = new JMenuItem("270º");
    bilineal = new JMenuItem("Interpolación bilineal");
    vecinosProximos =  new JMenuItem("Interpolación vecinos más próximos");
    escalarBilineal = new JMenuItem("Interpolación vecinos más próximos");
    escalarVecinos = new JMenuItem("Interpolación bilineal");
    setContentPane(desktop);
    inicializarMenu();
  }
  
  public void inicializarMenu() {
    //Añadiendo menus a la barra
	setJMenuBar(barra);
    barra.add(archivo);
    barra.add(ver);
    barra.add(opciones);
    barra.add(transformaciones);
    barra.add(espejo);
    barra.add(rotar);
    barra.add(escalar);
    
    //Menu Archivo
    archivo.add(abrir);
    abrir.addActionListener(this);
    archivo.add(guardar);
    guardar.addActionListener(this);
    archivo.addSeparator();
    archivo.add(salir);
    salir.addActionListener(this);
    
    //Menu Ver
    ver.add(histoAbsoluto);
    histoAbsoluto.addActionListener(this);
    ver.add(histoAcumulado);
    histoAcumulado.addActionListener(this);
    ver.add(infoImagen);
    infoImagen.addActionListener(this);
    
    //Menu Opciones
    opciones.add(escalaGrises);
    escalaGrises.addActionListener(this);
    opciones.add(seleccion);
    seleccion.addActionListener(this);
    opciones.add(recortar);
    recortar.addActionListener(this);
    opciones.add(recta);
    recta.addActionListener(this);
    opciones.add(rectaHistograma);
    rectaHistograma.addActionListener(this);
    
    //Transformaciones lineales
    transformaciones.add(transformacionesLineales);
    transformacionesLineales.add(ajusteTramos);
    ajusteTramos.addActionListener(this);
    transformacionesLineales.add(ajusteBrilloContraste);
    ajusteBrilloContraste.addActionListener(this);
    
    //Transformaciones No lineales
    transformaciones.add(transformacionesNoLineales);
    transformacionesNoLineales.add(ecualizar);
    ecualizar.addActionListener(this);
    transformacionesNoLineales.add(gamma);
    gamma.addActionListener(this);
    //transformacionesNoLineales.add(diferenciaImagenes);
    //diferenciaImagenes.addActionListener(this);
    transformacionesNoLineales.add(diferenciaUmbral);
    diferenciaUmbral.addActionListener(this);
    transformacionesNoLineales.add(especHistograma);
    especHistograma.addActionListener(this);
    
    //Espejo
    espejo.add(espejoVertical);
    espejoVertical.addActionListener(this);
    espejo.add(espejoHorizontal);
    espejoHorizontal.addActionListener(this);
    espejo.add(traspuesta);
    traspuesta.addActionListener(this);
    
    //Menu rotar
    rotar.add(rotarDerecha);
    rotar.add(rotarIzquierda);
    rotar.add(directo);
    directo.addActionListener(this);
    rotar.add(indirecto);
    
    //menu rotar derecha
    rotarDerecha.add(d90);
    d90.addActionListener(this);
    rotarDerecha.add(d180);
    d180.addActionListener(this);
    rotarDerecha.add(d270);
    d270.addActionListener(this);
    
    //menu rotar izquierda
    rotarIzquierda.add(i90);
    i90.addActionListener(this);
    rotarIzquierda.add(i180);
    i180.addActionListener(this);
    rotarIzquierda.add(i270);
    i270.addActionListener(this);
    
    //Menu indirecto
    indirecto.add(bilineal);
    bilineal.addActionListener(this);
    indirecto.add(vecinosProximos);
    vecinosProximos.addActionListener(this);
    
    //Menu Transformaciones
    transformaciones.add(binarizar);
    binarizar.addActionListener(this);
    transformaciones.add(negativo);
    negativo.addActionListener(this);
    
    //Menu escalar
    escalar.add(escalarBilineal);
    escalarBilineal.addActionListener(this);
    escalar.add(escalarVecinos);
    escalarVecinos.addActionListener(this);
  }
  
  public void calcularVersiones() {
	  JInternalFrame[] aux = desktop.getAllFrames();
      
      for(int i = 0; i < aux.length; i++) {
    	  if(((VentanaAuxiliar)aux[i]).getTitulo().equals( ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo() )) {
    		  ((VentanaAuxiliar)aux[i]).getImagen().setVersion(((VentanaAuxiliar) aux[i]).getImagen().getVersion()+1);
    	  }
      }
  }
  
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == salir)
      System.exit(0);
    //Menu archivo evento boton abrir
    else if (event.getSource() == abrir) {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & JPEG & GIF & BMP & PNG Images", "jpg", "jpeg", "gif", "bmp", "tif", "png");
      chooser.setFileFilter(filter);
      chooser.setCurrentDirectory(new File(ruta));
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
        VentanaAuxiliar ventanaAuxiliar;
        try {
          ventanaAuxiliar = new VentanaAuxiliar((BufferedImage)ImageIO.read(chooser.getSelectedFile()), chooser.getSelectedFile().getName(), 1);
          desktop.add(ventanaAuxiliar);
          ventanaAuxiliar.moveToFront(); 
          ventanaAuxiliar.setSelected(true);
        } catch (IOException e) {
          e.printStackTrace();
        } catch (PropertyVetoException e) {
          e.printStackTrace();
        }  
      }
    }
    //Menu ver, boton histograma absoluto
    else if (event.getSource() == histoAbsoluto) {
      BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
      VentanaHistograma histograma = new VentanaHistograma(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).isGris(), true, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(), ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getPixelFondo(),((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().isDirecto());
      desktop.add(histograma);
      histograma.moveToFront();
    }
    //Menu ver, boton histograma acumulado
    else if(event.getSource() == histoAcumulado) {
      BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
      VentanaHistograma histograma = new VentanaHistograma(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).isGris(), false, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(), ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getPixelFondo(), ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().isDirecto());
      desktop.add(histograma);
      histograma.moveToFront();
    }
    //Menu Informacion de la iamgen
    else if(event.getSource() == infoImagen) {
    	
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	VentanaInformacion vInformacion= new VentanaInformacion(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).isGris());
    	vInformacion.mostrarVentanaInformacion();
    }
    //Menu opciones, boton escala de grises
    else if (event.getSource() == escalaGrises) {
      BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().escalaGris();
      
      calcularVersiones();
      
      VentanaAuxiliar nueva =  new VentanaAuxiliar(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
    		  ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
      nueva.setGris(true);
      desktop.add(nueva);
      nueva.moveToFront(); 
      try {
        nueva.setSelected(true);
      } catch (PropertyVetoException e) {
        e.printStackTrace();
      }
    }
    //Seleccion
    else if (event.getSource() == seleccion) {
      ((VentanaAuxiliar) desktop.getSelectedFrame()).setSeleccionar(true);
      ((VentanaAuxiliar) desktop.getSelectedFrame()).setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    //Recortar
    else if (event.getSource() == recortar) {
      ((VentanaAuxiliar) desktop.getSelectedFrame()).setSeleccionar(false);
      ((VentanaAuxiliar) desktop.getSelectedFrame()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      
      calcularVersiones();
      
      VentanaAuxiliar nueva = new VentanaAuxiliar(((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getSubImagen(), 
    		  ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(), ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
      
      ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().limpiarSeleccion();
      if(((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) 
        nueva.setGris(true);
      desktop.add(nueva);
      nueva.moveToFront();
    }
    //Recta
    else if(event.getSource() == recta) {
    	((VentanaAuxiliar) desktop.getSelectedFrame()).setDibujarRecta(true);
        ((VentanaAuxiliar) desktop.getSelectedFrame()).setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    //Recta histograma
    else if(event.getSource() == rectaHistograma) {
      BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
      Recta recta = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getRecta();
      String titulo = ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo();
      VentanaRectaHistograma ventana = new VentanaRectaHistograma(imagen, recta, titulo);
      ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().limpiarSeleccion();
      desktop.add(ventana);
      ventana.moveToFront();
    }
    //Guardar imagen
    else if(event.getSource() == guardar) {
    	FileNameExtensionFilter jpg = new FileNameExtensionFilter(".jpg", ".jpg");
    	FileNameExtensionFilter bmp = new FileNameExtensionFilter(".bmp", ".bmp");
    	FileNameExtensionFilter gif = new FileNameExtensionFilter(".gif", ".gif");
    	JFrame parentFrame = new JFrame();
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File(ruta));
    	fileChooser.setFileFilter(jpg);
    	fileChooser.setFileFilter(bmp);
    	fileChooser.setFileFilter(gif);
    	fileChooser.setDialogTitle("Guardar");   
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		     try {
	    		File fichero = new File(fileChooser.getCurrentDirectory() +"/" + fileToSave.getName()+fileChooser.getFileFilter().getDescription());
	    		String formato = fileChooser.getFileFilter().getDescription().substring(1, fileChooser.getFileFilter().getDescription().length());
	    		BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
	    		ImageIO.write(imagen, formato, fichero);
			} catch (IOException e) {
				System.out.println("Error de escritura");
			}
		}
    }
    //Ajustar Brillo y contraste
    else if(event.getSource() == ajusteBrilloContraste) {
    	if(((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
	    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
	    	double brillo = new Brillo().calcularBrillo(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).isGris());
	    	double contraste = new Contraste().calcularContraste(imagen, brillo, ((VentanaAuxiliar) desktop.getSelectedFrame()).isGris());
	    	VentanaAjusteByC ventanaAjustes = new VentanaAjusteByC(brillo, contraste, imagen);
	    	BufferedImage imagenSalida = ventanaAjustes.getImagenSalida();
	    	
	    	calcularVersiones();
	    	crearVentana(imagenSalida);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "La Imagen debe estar en blanco y negro", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    //Transformacion por tramos
    else if(event.getSource() == ajusteTramos) {
      if(((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
        BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
        VentanaTramos ventanaTramos = new VentanaTramos(imagen);
        
        calcularVersiones();
        crearVentana(ventanaTramos.getImagenSalida());
      }
      else 
        JOptionPane.showMessageDialog(null, "La Imagen debe estar en blanco y negro", "ERROR", JOptionPane.INFORMATION_MESSAGE); 	
    }
    //Ecualziar Histograma
    else if(event.getSource() == ecualizar) {
      if (((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
        BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
        Ecualizar ecualizar = new Ecualizar(imagen);
        BufferedImage imagenSalida = ecualizar.ecualizarImagen();
        
        calcularVersiones();
        crearVentana(imagenSalida);
      }
      else 
        JOptionPane.showMessageDialog(null, "La Imagen debe estar en blanco y negro", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }
    //Corregir gamma
    else if(event.getSource() == gamma) {
    	if (((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
    		BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    		VentanaGamma vGamma = new VentanaGamma(imagen);
    		BufferedImage imagenSalida = vGamma.calcularGamma();
    		
    		calcularVersiones();
    		crearVentana(imagenSalida);
    	}
    	else 
            JOptionPane.showMessageDialog(null, "La Imagen debe estar en blanco y negro", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }
    //Diferencia de imagenes
    else if(event.getSource() == diferenciaImagenes){
    	JInternalFrame[] imagenesAbiertas = desktop.getAllFrames();
    	ArrayList<String> nombreImagenes = new ArrayList<String>();
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	BufferedImage imagenDos = null;
    	int[] pos = new int[imagenesAbiertas.length];
    	int it = 0;
    	for(int i = 0; i < imagenesAbiertas.length; i++) {
    		if(((VentanaAuxiliar)imagenesAbiertas[i]) != ((VentanaAuxiliar) desktop.getSelectedFrame())) {
    		  pos[it] = i;
          it++;
    			nombreImagenes.add(((VentanaAuxiliar)imagenesAbiertas[i]).getTitulo() +  " (" +((VentanaAuxiliar)imagenesAbiertas[i]).getImagen().getVersionOriginal()+")");
    		}
    	}
    	
		if (nombreImagenes.size()==0){
			JOptionPane.showMessageDialog(null, "No hay imágenes con las que btener la diferencia");
		}
		else {
			Object[] opciones = nombreImagenes.toArray();
			Object seleccion = JOptionPane.showInputDialog(null, "Seleccione otra imagen \npara obtener la diferencia", 
					"Seleccion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			for(int i = 0; i < nombreImagenes.size(); i++) {
				if(nombreImagenes.get(i).equals(seleccion)) {
					imagenDos = ((VentanaAuxiliar) imagenesAbiertas[pos[i]]).getImagen().getImagen();
				}
			}
			BufferedImage imagenSalida = new DiferenciaNormal().calcularDiferencia(imagen, imagenDos);	
			calcularVersiones();
			crearVentana(imagenSalida);
		}
    }
    //Diferencia de imagenes con umbral
    else if(event.getSource() == diferenciaUmbral){
    	JInternalFrame[] imagenesAbiertas = desktop.getAllFrames();
    	ArrayList<String> nombreImagenes = new ArrayList<String>();
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	BufferedImage imagenDos = null;
    	int[] pos = new int[imagenesAbiertas.length];
      int it = 0;
    	for(int i = 0; i < imagenesAbiertas.length; i++) {
    		if(((VentanaAuxiliar)imagenesAbiertas[i]) != ((VentanaAuxiliar) desktop.getSelectedFrame())) {
    		  pos[it] = i;
          it++;
    			nombreImagenes.add(((VentanaAuxiliar)imagenesAbiertas[i]).getTitulo() +  " (" +((VentanaAuxiliar)imagenesAbiertas[i]).getImagen().getVersionOriginal()+")");
    		}
    	}
    	
		if (nombreImagenes.size()==0){
			JOptionPane.showMessageDialog(null, "No hay imágenes con las que btener la diferencia");
		}
		else {
			Object[] opciones = nombreImagenes.toArray();
			Object seleccion = JOptionPane.showInputDialog(null, "Seleccione otra imagen \npara obtener la diferencia", 
					"Seleccion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			for(int i = 0; i < nombreImagenes.size(); i++) {
				if(nombreImagenes.get(i).equals(seleccion)) {
					imagenDos = ((VentanaAuxiliar) imagenesAbiertas[pos[i]]).getImagen().getImagen();
				}
			}
			BufferedImage imagenSalida = new DiferenciaNormal().calcularDiferencia(imagen, imagenDos);
			
			calcularVersiones();
			
			VentanaDiferencia ventana = new VentanaDiferencia(imagen, imagenSalida, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
					((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
			  	
	    	VentanaAuxiliar ventanaAux = new VentanaAuxiliar(imagenSalida, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
	    			((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
	    	desktop.add(ventanaAux);
	    	ventanaAux.moveToFront();
	    	ventana.setBounds(ventanaAux.getWidth(), 0, ventana.getWidth(), ventana.getHeight());
	    	desktop.add(ventana);
	    	ventana.moveToFront();
		}
    }
    //Especificacion histograma
    else if(event.getSource() == especHistograma) {
    	JInternalFrame[] imagenesAbiertas = desktop.getAllFrames();
    	ArrayList<String> nombreImagenes = new ArrayList<String>();
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	BufferedImage imagenDos = null;
    	int[] pos = new int[imagenesAbiertas.length];
    	int it = 0;
    	for(int i = 0; i < imagenesAbiertas.length; i++) {
    		if(((VentanaAuxiliar)imagenesAbiertas[i]) != ((VentanaAuxiliar) desktop.getSelectedFrame())) {
    		  pos[it] = i;
    		  it++;
    			nombreImagenes.add(((VentanaAuxiliar)imagenesAbiertas[i]).getTitulo() +  " (" +((VentanaAuxiliar)imagenesAbiertas[i]).getImagen().getVersionOriginal()+")");
    		}
    	}
    	
		if (nombreImagenes.size()==0){
			JOptionPane.showMessageDialog(null, "No hay imágenes con las que obtener el histograma");
		}
		else {
			Object[] opciones = nombreImagenes.toArray();
			Object seleccion = JOptionPane.showInputDialog(null, "Seleccione otra imagen \npara obtener el histograma", 
					"Seleccion", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
			for(int i = 0; i < nombreImagenes.size(); i++) {
				if(nombreImagenes.get(i).equals(seleccion)) {
					imagenDos = ((VentanaAuxiliar) imagenesAbiertas[pos[i]]).getImagen().getImagen();
				}
			}
			VentanaEspecHistograma vEHistograma = new VentanaEspecHistograma(imagen, imagenDos);
			BufferedImage imagenSalida = vEHistograma.calcularHistograma();
			
			calcularVersiones();		
			crearVentana(imagenSalida);
		}
    }
    //Negativo
    else if(event.getSource() == negativo){
    	if (((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
    		BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    		Negativo negativo = new Negativo(imagen);
    		BufferedImage imagenSalida = negativo.calcularNegativo();
    		
    		calcularVersiones();
    		
    		VentanaAuxiliar ventana = new VentanaAuxiliar(imagenSalida, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
    				((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
    		desktop.add(ventana);
    		ventana.moveToFront();
    		try {
    			ventana.setSelected(true);
    		} catch (PropertyVetoException e) {
    			e.printStackTrace();
    		}
    	}
    	else
    		JOptionPane.showMessageDialog(null, "La Imagen debe estar en blanco y negro", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }
    else if(event.getSource() == binarizar) {
    	if(((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().isGris()) {
    		BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    		int valor = Integer.parseInt((JOptionPane.showInputDialog("Introduce Valor", 0)));
    		BufferedImage imagenSalida = new Binarizar().binarizarImagen(imagen, valor);
    		
    		calcularVersiones();
    		crearVentana(imagenSalida);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "La imagen debe estar en blanco y negro");
    	}
    }
    //Espejo vertical
    else if (event.getSource() == espejoVertical) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Espejo espejo = new Espejo(imagen);
    	BufferedImage imagenSalida = espejo.espejoVertical();
    	crearVentana(imagenSalida);
    }
    //Espejo horizontal
    else if (event.getSource() == espejoHorizontal) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Espejo espejo = new Espejo(imagen);
    	BufferedImage imagenSalida = espejo.espejoHorizontal();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == traspuesta) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Traspuesta traspuesta = new Traspuesta(imagen);
    	BufferedImage imagenSalida = traspuesta.trasponer();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == d90) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Rotar rotar = new Rotar(imagen);
    	BufferedImage imagenSalida = rotar.rotarDerecha();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == d180) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Rotar rotar = new Rotar(imagen);
    	imagen = rotar.rotarDerecha();
    	rotar = new Rotar(imagen);
    	BufferedImage imagenSalida = rotar.rotarDerecha();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == d270) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Rotar rotar = new Rotar(imagen);
    	imagen = rotar.rotarDerecha();
    	rotar = new Rotar(imagen);
    	imagen = rotar.rotarDerecha();
    	rotar = new Rotar(imagen); 
    	BufferedImage imagenSalida = rotar.rotarDerecha();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == i90) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Rotar rotar = new Rotar(imagen);
    	BufferedImage imagenSalida = rotar.rotarIzquierda();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == i180) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Rotar rotar = new Rotar(imagen);
    	imagen = rotar.rotarIzquierda();
    	rotar = new Rotar(imagen);
    	BufferedImage imagenSalida = rotar.rotarIzquierda();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == i270) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	Rotar rotar = new Rotar(imagen);
    	imagen = rotar.rotarIzquierda();
    	rotar = new Rotar(imagen);
    	imagen = rotar.rotarIzquierda();
    	rotar = new Rotar(imagen); 
    	BufferedImage imagenSalida = rotar.rotarIzquierda();
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == bilineal)  {
    	if (((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
    		BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    		VentanaRotacion ventanaRotacion = new VentanaRotacion(imagen);
    		BufferedImage imagenSalida = ventanaRotacion.calcularBilineal();
    		crearVentana(imagenSalida, ventanaRotacion.getPixelFondo());
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "La imagen debe estar en blanco y negro");
    	}
    }
    else if(event.getSource() == vecinosProximos)  {
    	if (((VentanaAuxiliar) desktop.getSelectedFrame()).isGris()) {
    		BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    		VentanaRotacion ventanaRotacion = new VentanaRotacion(imagen);
    		BufferedImage imagenSalida = ventanaRotacion.calcularVecinos();
    		crearVentana(imagenSalida, ventanaRotacion.getPixelFondo());
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "La imagen debe estar en blanco y negro");
    	}
    }
    else if(event.getSource() == escalarBilineal) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	VentanaEscalar ventanaEscalar= new VentanaEscalar(imagen, 1);
    	BufferedImage imagenSalida = ventanaEscalar.getImagenSalida();
    	
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == escalarVecinos) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();  	
    	VentanaEscalar ventanaEscalar= new VentanaEscalar(imagen, 0);
    	BufferedImage imagenSalida = ventanaEscalar.getImagenSalida();
    	
    	crearVentana(imagenSalida);
    }
    else if(event.getSource() == directo) {
    	BufferedImage imagen = ((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getImagen();
    	
    	VentanaRotacion ventanaRotacion = new VentanaRotacion(imagen);
		BufferedImage imagenSalida = ventanaRotacion.mapeoDirecto();
    	
    	VentanaAuxiliar ventana = new VentanaAuxiliar(imagenSalida, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
				((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
    	System.out.println(ventanaRotacion.getPixelFondo());
    	ventana.setPixelFondo(ventanaRotacion.getPixelFondo());
    	ventana.getImagen().setDirecto(true);
        desktop.add(ventana);
        ventana.moveToFront();
    }
  }
  
  public void crearVentana(BufferedImage imagen) {
	  VentanaAuxiliar ventana = new VentanaAuxiliar(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
				((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
	  desktop.add(ventana);
	  ventana.moveToFront();
	  try {
		  ventana.setSelected(true);
	  } catch (PropertyVetoException e) {
		  e.printStackTrace();
	  }
  }
  
  public void crearVentana(BufferedImage imagen, int pixelFondo) {
	  VentanaAuxiliar ventana = new VentanaAuxiliar(imagen, ((VentanaAuxiliar) desktop.getSelectedFrame()).getTitulo(),
				((VentanaAuxiliar) desktop.getSelectedFrame()).getImagen().getVersion());
	  ventana.setPixelFondo(pixelFondo);
	  desktop.add(ventana);
	  ventana.moveToFront();
	  try {
		  ventana.setSelected(true);
	  } catch (PropertyVetoException e) {
		  e.printStackTrace();
	  }
  }
  
  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    SplashPhotoArt execute = new SplashPhotoArt();
    
  }
}
