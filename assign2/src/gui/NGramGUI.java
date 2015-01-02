package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.microsoft.research.webngram.service.exception.NgramServiceException;

import ngram.NGramException;
import ngram.NGramStore;

/**
 * Frame Class for NGram, NGramGUI
 * 
 * @author Dustin Wilson - n6325157
 * 
 * @edited Chris Connolly - n6873146
 * 
 * The Frame class, that holds all the panels
 *  
 */
public class NGramGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -7031008862559936404L;
	
	//Important Panels
	private JPanel btmPanel;
	private JPanel centerPanel;
	
	//Buttons
	private JButton switchToGraph;
	private JButton newSearch;
	private JButton switchToResults;
	
	//User input test field
	private JTextField userInput;
	
	//CombbBox for number of suggestions
	private JComboBox numQuerySuggestions;
	private String[] searchQueryNumbers = {"1", "2", "3", "4", "5"};
	
	//initiates variables 
	private ResultPanel resultPanel = new ResultPanel();
	private ChartPanel resultChart = null;
	
	//Create NGramStore object
	private NGramStore Map = new NGramStore();
	
	//Number of Determined Suggestions
	private int numOfSuggestions = 1;
	
	//Array for acceptable input string values
	private String[] acceptedValues = {" ","'",",","a","b","c","d","e","f","g","h","i","j","k","l",
			"m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6",
			"7","8","9","0"};
	
	/**
	 * 
	 * Constructor for NGramGui - creates the GUI
	 * 
	 * @param arg0 - name of frame
	 * @throws HeadlessException
	 */
	public NGramGUI(String arg0)throws HeadlessException {
		super(arg0);
		
		createGUI();
		
	}//end NGramGUI

	/**
	 * Convenient method to construct the GUI. Creates the components and adds
	 * them to the frame 
	 * 
	 */
	private void createGUI() {
		
	    setLayout(new BorderLayout());
	    
	    JPanel searchPanel = createSearchPanel();
	    
	    //Sets centerPanel to current panel
	    centerPanel = resultPanel;
		
	    //Adds components to the frame
		this.getContentPane().add(searchPanel, BorderLayout.NORTH);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		//Creates the buttons 
 	    newSearch = createButton("New Search");
 	    switchToGraph = createButton("Switch to Graphical View");
 	    switchToResults = createButton("Switch to Results View");
 	    
 	    //Sets the buttons state
 	    switchToGraph.setEnabled(false);
 	    switchToResults.setVisible(false);
 	    
	    layoutBottomPanel();
	    
	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);	
	    
	}//end createGUI
	
	/**
	 * Convenient method to construct the bottom panel, and add the GUI's buttons
	 * to it
	 */
	private void layoutBottomPanel(){
		
		btmPanel = new JPanel();
	    btmPanel.setBackground(Color.WHITE);
        btmPanel.setLayout(new FlowLayout());
		
        btmPanel.add(newSearch);
        btmPanel.add(switchToGraph);
        btmPanel.add(switchToResults);
        
	}//end layoutBottomPanel
	
	/**
	 * Convenient method to create the buttons used for the GUI
	 * 
	 * @param name - name for the button
	 * @return - newly created button
	 */
	private JButton createButton(String name){
		
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	
	}//end createButton
	
	/**
	 * Creates a search panel to allow user input
	 * 
	 * @return - newly constructed search panel
	 */
	private JPanel createSearchPanel(){
		
		JPanel westBorder;
		JPanel eastBorder;
		JPanel textInfoPanel;
		JTextArea textInfo;
		
		JPanel newSearchPanel = new JPanel();
		
		//Creates the search panel
		newSearchPanel = createPanel(Color.WHITE);
		newSearchPanel.setLayout(new BorderLayout());
		
	    //Text heading for search query
	    textInfo = new JTextArea("Place Search Query Here, and Select the Number of Requested Suggestions");
	    textInfo.setEditable(false);
	    
	    //Heading panel 
	    textInfoPanel = createPanel(Color.WHITE);
	    textInfoPanel.add(textInfo);
	    newSearchPanel.add(textInfoPanel, BorderLayout.NORTH);
	    
	    //border panels	
	    westBorder = createPanel(Color.WHITE);
	    eastBorder = createPanel(Color.WHITE);
	    
		newSearchPanel.add(westBorder,BorderLayout.WEST);
		newSearchPanel.add(eastBorder,BorderLayout.EAST);
		
		JPanel searchInputs = createPanel(Color.WHITE);
		
		searchInputs.setLayout(new BorderLayout());
		
		userInput = new JTextField(40);
		
		//creates comboBox for number of suggestions
		numQuerySuggestions = new JComboBox(searchQueryNumbers);
		numQuerySuggestions.setSelectedIndex(0);
		numQuerySuggestions.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				//gets value in combo box
				JComboBox cb = (JComboBox)e.getSource();
			    String numSuggestion = (String)cb.getSelectedItem();
			    setNumSuggestion(numSuggestion); 
				
			}
		});
		numQuerySuggestions.setBackground(Color.WHITE);
		numQuerySuggestions.setPrototypeDisplayValue("No. of Suggestions");
		
		//Creates a border for the Combobox
		searchInputs.add(numQuerySuggestions, BorderLayout.CENTER);
		searchInputs.add(createPanel(Color.WHITE), BorderLayout.EAST);
		searchInputs.add(createPanel(Color.WHITE), BorderLayout.WEST);
	
		newSearchPanel.add(userInput,BorderLayout.CENTER);
		newSearchPanel.add(searchInputs, BorderLayout.EAST);
		
		return newSearchPanel;
		
	}//end createSearchPanel
	
	/**
	 * Convenient method to create panels of the selected colour choice
	 * 
	 * @param c - panel colour choice
	 * @return newly created panel
	 */
	private JPanel createPanel(Color c) {
		
		JPanel panel = new JPanel();
		panel.setBackground(c);
		return panel;
	
	}//end createPanel
	
	/*
	 * Perform an appropriate action when a button is pushed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String buttonString = e.getActionCommand();
		
	    if (buttonString.equals("New Search")) {		  
	    	
	    	//Switch the current state of certain buttons
	    	switchToGraph.setEnabled(false);
	    	switchToResults.setVisible(false);
	    	switchToGraph.setVisible(true);
	    	
			initiateSearch();
	
	    } else if (buttonString.equals("Switch to Graphical View")) {
	    	
	    	//Changes to the chart
	    	changeToChart(resultChart);
	    	
	    	//Switch the current state of certain buttons
	    	switchToResults.setVisible(true);
	    	switchToGraph.setVisible(false);
	    	
	    } else if (buttonString.equals("Switch to Results View")) {
	    	    		
	    	changeToResults(resultPanel);
	    	switchToResults.setVisible(false);
	    	switchToGraph.setVisible(true);
	    
	    }//end if else
		
	}//end actionPerformed

	
	/**
	 * A method to check for for valid input, and returns the user input in an array of phrases
	 * 
	 * @param phrases - user input
	 * @return - an array of valid inputed phrases
	 * @throws NGramException if input is invalid contains unacceptable characters or is empty
	 */
	private ArrayList<String> parseInput(String phrases) throws NGramException {
	
		if(!checkForInvalidInput(phrases)){
		
			//creates new panels and swaps to them 
			resultPanel = new ResultPanel();
			changeToResults(resultPanel);
			
			userInput.requestFocus();

			throw new NGramException("Invalid input for search query: A query must " +
					"be entered and must only consist of characters, " + 
					"spaces, apostrophes, and commas.");
		
		}//end while
		
		return collectPhrases(phrases);
		
	}//end parseInput	
	
	
	/**
	 * A method that creates the new panels, grabs the user input
	 * and the initiates the NGram search for the inputed phrases 
	 * 
	 */
	private void initiateSearch() {
		
		String searchQuery = userInput.getText();
			
		ArrayList<String> inputtedPhrases;
			
		//creates new panels and swaps to them
		resultPanel = new ResultPanel();
		resultChart = new ChartPanel();
		
		try {
		    
		inputtedPhrases = parseInput(searchQuery);
		
		//refreshes panel
		changeToResults(resultPanel);
		
		ngramSearchResults(inputtedPhrases);
					    			    
		} catch (NGramException exception) {
			
			appendDisplay(exception.toString() + "\n");
		
		} catch (NgramServiceException exception) {
		
			appendDisplay(exception.toString() + "\n");
			
		}//end try catch block
	
	}//end searchForResults

	/**
	 * Changes the result panel to be the centre panel of the frame 
	 * 
	 * @param results - result panel to be changed to
	 */
	private void changeToResults(ResultPanel results) {
		
		//Removes search panel, replaces with results
		this.getContentPane().remove(centerPanel);
		this.getContentPane().add(results, BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.pack();
		
		centerPanel = results;
	
	}//end changeToResults
	
	/**
	 * Changes the chart panel to be the centre panel of the frame
	 * 
	 * @param chart - chart panel to be changed to
	 */
	private void changeToChart(ChartPanel chart) {
		
		//Removes search panel, replaces with results
		this.getContentPane().remove(centerPanel);
		
		this.getContentPane().add(chart, BorderLayout.CENTER);
		
		this.getContentPane().repaint();
		
		this.pack();
		
		centerPanel = chart;
	
	}//end changeToChart
	
	/**
	 * A method to check for invalid user input or empty input
	 * 
	 * @param searchQuery - user input
	 * @return - true if input is valid, false if invalid 
	 */
	//checks for valid input
	private boolean checkForInvalidInput(String searchQuery){
		
		//Check for empty input
		if (searchQuery.isEmpty()){
			
			return false;
			
		}//end if
		
		//Sets all values to lowercase
		String checkString = searchQuery.toLowerCase();
		
		//Removes all accepted values from inputed query
		for (String letter : acceptedValues){
			
			checkString = checkString.replaceAll(letter, "");
			
		}//end for
		
		//Checks if any invalid values remain
		if (!checkString.isEmpty()){
			
			return false;
		
		}//end if
		
		return true;
	
	}//end checkForInvalidInput
	
	//change name of this later - used for setting input into a parameter for the search
	//also only stores the last 5 phrases inputted into the search query
	
	/**
	 * A method that takes the users input and splits it into an array of the separated phrases
	 * from the user input. IF input contains more than 5 phrases, phrases are then reduced down to the 
	 * last 5 inputed.
	 * 
	 * @param searchQuery - user input
	 * @return - An array with that can have a maximum number of 5 inputed phrases
	 */
	private ArrayList<String> collectPhrases(String searchQuery){
		
		int maxPhraseLimit = 5;
		int numOfPhrases;
		
		//An array of inputted phrases
		ArrayList<String> inputtedPhrases = new ArrayList<String>();
		
		String[] currentPhrases;
		
		currentPhrases = searchQuery.split(",");
		
		numOfPhrases = currentPhrases.length;
		
		//This cuts down the phrases to the maximum limit of 5 and removes whitespaces.
		if (numOfPhrases > maxPhraseLimit){

			String[] newPhrases = new String[maxPhraseLimit];
			System.arraycopy(currentPhrases, numOfPhrases - maxPhraseLimit, 
					newPhrases, 0, maxPhraseLimit);
			
			//Formatting the extra white spaces
			for (String phrase : newPhrases) {
				
				inputtedPhrases.add(phrase.trim());
		
			}//end for
			
		} else {
			
			for (String phrase : currentPhrases) {
				
				inputtedPhrases.add(phrase.trim());
		
			}//end for
		}//end if else
		
		return inputtedPhrases;
	
	}//end collectPhrases
	
	/**
	 * A simple method to edit the phrase down to the last four words in the inputed phrase.
	 * 
	 * @param phrase - inputed phrase
	 * @return updated context of 4 words or less
	 */
	private String editPhrase(String phrase){
		
		String newPhrase = "";
		
		String [] words = phrase.split(" ");
		
		int length = words.length;
		
		int maxPhraseLength = 4;
		
		if (length > maxPhraseLength) {
			
			//Cuts the phrase length down to 4 words
			String[] newContext = new String[maxPhraseLength];
			System.arraycopy(words, length - maxPhraseLength, newContext, 0, maxPhraseLength);
		
			for (String word : newContext){
				
				newPhrase += word + " ";
			
			}//end for
			
		} else {
			
			return phrase;
			
		}//end if else
		
		return newPhrase.trim();
	
	}//end editPhrase
	
	/**
	 * 
	 * A method to set the number of requested suggestions
	 * 
	 * @param numSuggestion - selected number of suggestions
	 */
	private void setNumSuggestion(String numSuggestion){
		
		numOfSuggestions =  Integer.parseInt(numSuggestion);
		
	}
	
	/**
	 * A method to search for the nGrams associated with the inputed phrases. Searches through 
	 * the phrase array and appends the results to the result panel display in addition to updating 
	 * the result charts' dataset with the found results.
	 * 
	 * @param phrases - inputed phrase
	 * @throws NGramException 
	 */
	private void ngramSearchResults(ArrayList<String> phrases) throws NGramException {
		
		boolean turnSwitchViewOn = false;
		boolean searchSuccess = false;
		
		for(String phrase: phrases) {
			
			String context = editPhrase(phrase);
			
			searchSuccess = ngramSearch(context);
			
			if (searchSuccess == true) {
				
				//Displays results in display panel
				appendDisplay(toString() + phrase + "\n" + "\n");
				
				appendDisplay(Map.getNGram(context).toString());
				
				//Updates the dataset in the bar chart panel
				resultChart.updateDataSet(phrase, Map.getNGram(context).getPredictions(), Map.getNGram(context).getProbabilities());	
				
				//turns the switch view buttons on if one of the result returns true
				if (turnSwitchViewOn == false) {
					
					turnSwitchViewOn = true;
					
					switchToGraph.setEnabled(turnSwitchViewOn);
					
				}//end if
				
			} else {
				
				appendDisplay(toString() + phrase + "\n" + "\n");
				
				appendDisplay("No ngram predictions were returned. Please try another query.\n" + "\n");
				
			}//end if else
		
		}//end for		
		
	}//end ngramSearchResults
	
	/**
	 * Method that searches for the inputed phrase with a maximum of 5 results, and returns true of false 
	 * depending if results are found.
	 * 
	 * @param phrase - inputed phrase
	 * @return true if search was successful, false if not.
	 * @throws NGramException
	 */
	private boolean ngramSearch(String phrase)throws NGramException { 
		
		return Map.getNGramsFromService(phrase, numOfSuggestions);
		
	}//end ngramSearch
	
	/**
	 * A convenient method to append messages to the result panel
	 * 
	 * @param newText - test to be appended to result panel
	 */
	private void appendDisplay(String newText) {
		
		resultPanel.appendResults(newText);
	
	}//end appendDisplay

	/**
	 * A method to correctly format the found NGram Results
	 */
	@Override
	public String toString(){
		
		String results = String.format("NGram Results for Query: ");
		
		return results;
		
	}//end toString
	
}//end NgramGUI
