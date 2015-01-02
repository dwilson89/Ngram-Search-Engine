package gui;

import java.awt.BorderLayout;

import java.awt.Color;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * Panel Class for NGram, ChartPanel
 * 
 * @author Dustin Wilson - n6325157
 * 
 * @edited Chris Connolly - n6873146
 * 
 * A simple panel to hold the jfreechart bar chart object for display
 *  
 */
public class ChartPanel extends JPanel {

	private static final long serialVersionUID = -7031008862559936404L;
	
	//Border Panels
	private JPanel westBorder;
	private JPanel eastBorder;
	
	//Dataset for Chart
	private CategoryDataset dataset;
	
	/**
	 * Constructor for the ChartPanel Class, creates a JFreeChart that displays the results
	 * in the form of a chart
	 */
	public ChartPanel(){

        //Create the dataset       
		dataset = createDataset();
		
        //Create the chart panel        
		createChart();
		
	}//end ChartPanel
	
	/**
	 * 
	 * A method for creating the chart panel, by setting dimensions, the borders, and constructing
	 * the graph 
	 * 
	 */
	private void createChart(){
		
        //Based on the dataset we create the chart       
		JFreeChart chart = createChart(dataset, "NGram Results");
	      
        //Puts the chart into a panel
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart);
        
        //Default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       
        //Add it to our application
        this.setLayout(new BorderLayout());
        
        //Creates Borders
        westBorder = createPanel(Color.WHITE);
        eastBorder = createPanel(Color.WHITE);
        
        //Adds components
        this.add(chartPanel, BorderLayout.CENTER);
        this.add(westBorder, BorderLayout.WEST);
        this.add(eastBorder, BorderLayout.EAST);
	
	}//end createChart
	
	/**
	 * 
	 * A convenient method to create a panel of a specific colour
	 * 
	 * @param c - colour of new panel
	 * @return - a newly constructed panel of selected colour
	 */
	private JPanel createPanel(Color c) {
		
		JPanel panel = new JPanel();
		panel.setBackground(c);
	
		return panel;
	
	}//end createPanel
	
	/**
	 * 
	 * Creates a new dataset
	 * 
	 * @return the currentDataset
	 */
	private DefaultCategoryDataset createDataset() {	
       
		DefaultCategoryDataset currentDataset = new DefaultCategoryDataset(); 
		
        return currentDataset;
	
	}//end createDataset   
    
	/**
	 * Updates the dataset with the current results
	 * 
	 * @param phrase - current phrase string array
     * @param predictions - current predictions array
     * @param probability - current probability array
	 */
	public void updateDataSet(String phrase, String[] predictions, Double[] probability){
		
      for(int i = 0; i < predictions.length; i++) {    
    	  
      	((DefaultCategoryDataset) dataset).setValue(probability[i], phrase, predictions[i]);
      	
      }//end for
		
	}//end updateDataSet
	
    /**
     * Creates a JFreeChart to specification
     * 
     * @param - dataset - dataset for the base of the chart
     * @param - title - title for the chart
     * @return - returns a newly constructed JFreeChart
     */
    private JFreeChart createChart(CategoryDataset dataset, String title) {
    	
    	JFreeChart chart = ChartFactory.createBarChart3D(
    		title, 
     		"Phrase _____",
    		"Probability",
    		dataset, 
    		PlotOrientation.VERTICAL, 
    		true, 
    		false, 
    		false
    	);
    	
    	//Formats the chart
    	chart.setBackgroundPaint(Color.WHITE);
    	chart.setBorderVisible(true);
    	chart.setBorderPaint(Color.BLACK);
    	chart.getTitle().setPaint(Color.BLACK);   	
    	
    	//Changes the x Axis label alignment
    	CategoryPlot p = chart.getCategoryPlot(); 
    	CategoryAxis categoryAxis = (CategoryAxis) p.getDomainAxis();
    	categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);  			
    	
        return chart;
    
    }//end createChart
     	
}//end ResultChart
