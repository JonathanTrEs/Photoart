package vision.photoart.imagen;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import vision.photoart.ventanas.VentanaPrincipal;

public class SplashPhotoArt extends JWindow {
  private static int count;
  private static Timer timer1;
  
  public SplashPhotoArt() {
    JPanel panel = new JPanel();
    ImageIcon icono = new ImageIcon("splash/PhotoArtLogo.png");
    JLabel label = new JLabel(icono);
    panel.add(label);
    add(panel);
    
    loadTimer();
    setSize(icono.getIconWidth(), icono.getIconWidth() - 62);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  private void loadTimer() {
    ActionListener al = new ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            count++;

            if (count == 50) {
                try {
                  createFrame();
                } catch (HeadlessException | ClassNotFoundException
                    | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e) {
                  e.printStackTrace();
                }
                timer1.stop();
            }

        }

        private void createFrame() throws HeadlessException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
            setVisible(false);
            UIManager.put("nimbusBase", new Color(160, 160, 160));
            UIManager.put("nimbusBlueGrey", new Color(160, 160, 160));
            UIManager.put("control", new Color(160, 160, 160));

            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
          
            VentanaPrincipal ventana = new VentanaPrincipal();
            Image icono = Toolkit.getDefaultToolkit().getImage("icono.png");
            ventana.setSize(900, 600);
            ventana.setTitle("PhotoArt");
            ventana.setIconImage(icono);
            ventana.getContentPane().setBackground(Color.GRAY);
            ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        }
    };
    timer1 = new Timer(50, al);
    timer1.start();
  }
}
