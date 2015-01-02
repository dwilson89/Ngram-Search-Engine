package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main Class for NGram, Suggestions
 * 
 * @author Dustin Wilson - n6325157
 * 
 * This class contains the main program for the NGram Search 
 *  
 */
public class Suggestions {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	/*
	 * Program entry point.
	 * 
	 * Creates the GUI, ready for users to input search queries.
	 * 
	 */
	public static void main(String[] args) {
		
    	//Creates a search query simulation 
	 	NGramGUI searchQuery = new NGramGUI("NGram Search Query");
	 	
	 	//Terminates the program if the user closes the frame
	 	searchQuery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	 	//Sets the size of the frame
	 	searchQuery.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		searchQuery.setMinimumSize(new Dimension(WIDTH,HEIGHT));
	 	searchQuery.pack();
	 	
	 	//Makes the simulation visible
        searchQuery.setVisible(true);
        
	}//end main
	
}//end Suggestions
