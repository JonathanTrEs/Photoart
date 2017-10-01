package vision.photoart.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Histograma {
    
    /**
     * Calculamos la media de una variable Color
     * @param color Color del cual se quiere obtener la media
     * @return entero con el valor de la media
     */
    private int calcularMedia(Color color){
        int mediaColor;
        mediaColor=(int) ((int) (0.222 * color.getRed()) + (0.707 * color.getGreen()) + (0.071 * color.getBlue()));
        return mediaColor;
    }
    
    /**
     * Devuelve el histograma de la imagen.
     * @param imagen BufferedImagen de la cual se quiere obtener el histograma
     * @return Devuelve una variable int[5][256], donde el primer campo[0] corresponde
     * al canal Rojo, [1]=verde [2]=azul [3]=alfa [4]=escala grises
     */
    public int[][] histograma(BufferedImage imagen, boolean absoluto){
        Color colorAuxiliar;
        /*Creamos la variable que contendr� el histograma
        El primer campo [0], almacenará el histograma Rojo
        [1]=verde [2]=azul [3]=alfa [4]=escala grises*/
        int histogramaReturn[][]=new int[5][256];
        //Recorremos la imagen
        for( int i = 0; i < imagen.getWidth(); i++ ){
            for( int j = 0; j < imagen.getHeight(); j++ ){
                //Obtenemos color del píxel actual
                colorAuxiliar=new Color(imagen.getRGB(i, j));
                //Sumamos una unidad en la fila roja [0], 
                //en la columna del color rojo obtenido
                histogramaReturn[0][colorAuxiliar.getRed()]+=1;
                histogramaReturn[1][colorAuxiliar.getGreen()]+=1;
                histogramaReturn[2][colorAuxiliar.getBlue()]+=1;
                histogramaReturn[3][colorAuxiliar.getAlpha()]+=1;
                histogramaReturn[4][calcularMedia(colorAuxiliar)]+=1;
            }
        }
        if (!absoluto) {
          for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 256; j++) {
              histogramaReturn[i][j] = (histogramaReturn[i][j] + histogramaReturn[i][j - 1]);
            }
          }
        }
        return histogramaReturn;
    }
}
