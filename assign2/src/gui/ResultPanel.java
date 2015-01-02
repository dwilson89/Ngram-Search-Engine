package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


/**
 * Panel Class for NGram, ResultPanel
 * 
 * @author Dustin Wilson - n6325157
 * 
 * A simple panel to hold a text area to allow text based reporting of results
 *  
 */
public class ResultPanel extends JPanel{

	//ResultPanel Class Variables
	
	private static final long serialVersionUID = -7031008862559936404L;
	
	//Variables for TextArea
	private JTextArea results;
	private JScrollPane resultsScrollPane;
	
	/**
	 * Constructor for the ResultPanel Class, creates a panel that holds a text area,
	 * for the reporting of results
	 */
	public ResultPanel() {
		
		createResultsPanel();
		
	}//end ResultPanel

	/**
	 * Convenient method to create a Result Panel by adding all required components
	 * to the panel 
	 */
	private void createResultsPanel() {
	
		JPanel textInfoPanel;
		JPanel westBorder;
		JPanel eastBorder;
		JTextArea textInfo;
		
		//create search panel
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
	    //Text heading for search query
	    textInfo = new JTextArea("NGram Results");
	    textInfo.setEditable(false);
	    
	    //Textinfo panel 
	    textInfoPanel = createPanel(Color.WHITE);
	    textInfoPanel.add(textInfo);
	    
	    this.add(textInfoPanel, BorderLayout.NORTH);
	    
	    //Creation and addition of border panels
		westBorder = createPanel(Color.WHITE);
		eastBorder = createPanel(Color.WHITE);
		this.add(westBorder,BorderLayout.WEST);
		this.add(eastBorder,BorderLayout.EAST);
		
		createResultTextArea();
	    
	    //Puts results inside an scroll pane
	    resultsScrollPane = new JScrollPane(results);
	    
	    //Adds the resultsScrollPane to the panel
		this.add(resultsScrollPane,BorderLayout.CENTER);
		
	}//end createResultPanel

	/**
	 * Convenient method to construct the Results TextArea 
	 */
	private void createResultTextArea() {
		
	    results = new JTextArea("");
	    
	    results.setEditable(false);
	    
	    //Text wrapping
	    results.setLineWrap(true);
	    results.setWrapStyleWord(true);
	    
	    //Adds a border and a margin to the textArea
	    Border border = BorderFactory.createLineBorder(Color.WHITE);
	    results.setBorder(BorderFactory.createCompoundBorder(border, 
	                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	
	}//end createResultTextArea

	/**
	 * Convenient method to create panels of a certain colour
	 * 
	 * @param - c - colour for the panel
	 * @return - returns a newly created panel
	 */
	private JPanel createPanel(Color c) {
		
		JPanel panel = new JPanel();
		panel.setBackground(c);
	
		return panel;
	
	}//end createPanel

	
	/**
	 * Setter method to append results/errors gathered from the NGram search
	 * to the TextArea attach to the panel
	 * 
	 * @param - message - message to append to the results textArea
	 */
	public void appendResults(String message){
		
		results.setText(results.getText() + message);
		
	}//end appendResults
	
}//end ResultPanel
