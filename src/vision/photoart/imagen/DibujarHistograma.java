package vision.photoart.imagen;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class DibujarHistograma {
      /**
     * Crea un gráfico de barras y lo asigna al JPanel recibido
     * @param histograma histograma de frecuencias (int[256]).
     * @param jPanelHistograma JPanel donde el histograma será dibujado
     * @param colorBarras color de cuál será dibujado el histograma
     */
    public void crearHistograma(int[] histograma,JPanel jPanelHistograma, Color colorBarras, boolean absoluto, int imagenSize) {
        //Creamos el dataSet y añadimos el histograma
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String serie = "Número de píxeles";
        int media = calcularMedia(histograma, imagenSize, absoluto);
        int max = maxValue(histograma);
        for (int i = 0; i < histograma.length; i++){
        	if (i == media) 
        		dataset.addValue(max, "Media", "" + i);
            dataset.addValue(histograma[i], serie, "" + i);
        }
        //Creamos el chart
        JFreeChart chart;
        if (absoluto)
        	chart = ChartFactory.createBarChart("Histograma absoluto", null, null,
                                    dataset, PlotOrientation.VERTICAL, true, true, false);
        else
        	chart = ChartFactory.createBarChart("Histograma acumulado", null, null,
                    dataset, PlotOrientation.VERTICAL, true, true, false);
        if (colorBarras == Color.DARK_GRAY)
        	chart = ChartFactory.createBarChart("Perfil de la recta", null, null,
                    dataset, PlotOrientation.VERTICAL, true, true, false);
        //Modificamos el dise�o del chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colorBarras);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(214, 217, 223)); 
        jPanelHistograma.removeAll();
        jPanelHistograma.repaint();
        jPanelHistograma.setLayout(new java.awt.BorderLayout());
        jPanelHistograma.add(new ChartPanel(chart));
        jPanelHistograma.validate();    
    }
    
    private int calcularMedia(int[] histograma, int imagenSize, boolean absoluto) {
    	double media = 0.0;
    	for (int i = 0; i < histograma.length; i++) {
    		if (absoluto)
    			media += (i * histograma[i]);
    		else
    			media += histograma[i];
    	}
    	if (absoluto)
    		media = media / (double) imagenSize;
    	else 
    		media = media / (double) imagenSize; 
    	return (int)media;
    }
    
    private int maxValue(int[] histograma) {
    	int max = histograma[0];
    	for (int i = 0; i < histograma.length; i++)
    		if (max < histograma[i])
    			max = histograma[i];
    	return max;
    }
}