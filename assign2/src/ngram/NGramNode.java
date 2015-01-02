package ngram;

/**
 * NGramNode Class for NGram, NGramNode
 * 
 * @author Chris Connolly - n6873146 * 
 * 
 * @edited Dustin Wilson - n6325157
 * 
 * This class creates the NGram and stores its values
 *  
 */

public class NGramNode implements NGramContainer {			
	
	
	public String singleContext;
	public String[] contextArray;
	public String[] wordArray;
	public String[] predictionArray;
	public Double[] probabilitiesArray;			
	
	
	public NGramNode(String[] words, String[] predictions, Double[] probabilities) throws NGramException {			
		
		//Exceptions
		if(words == null){
			throw new NGramException("Words: Input contains a null value");
		}
		
		if(words.length == 0){
			throw new NGramException("Words: is empty");
		}		
		
		if(predictions == null){
			throw new NGramException("Predictions: Input contains a null value");
		}	
		
		if(predictions.length == 0){
			throw new NGramException("Predictions: is empty");
		}	
		
		if(probabilities == null)
		{
			throw new NGramException("Probabilities: Input contains a null value");
		}	
		
		if(probabilities.length != predictions.length)
		{
			throw new NGramException("Number of predictions does not equal number of probabilities");
		}
		
		for(String word : words ) {
			
			if ((word == "") ||(word == " ") || (word == null)) {
				
				throw new NGramException("Words: Input contains an empty Strings or an null value");				
			}
		}
		
		for(String prediction : predictions) {
			
			if ((prediction == "")|| (prediction == " ") || (prediction == null)){
				
				throw new NGramException("Predictions: Input contains an empty Strings or an null value");				
			}	
		}
		
		for(Double probability : probabilities) {
			
			if (probability == null) {
				
				throw new NGramException("Probabilities: Input contains an null value");
				
			} else if ((probability <= 0.0) ||(probability > 1.0)) {
				
				throw new NGramException("Probabilities: Input contains a number greater one, equal to zero or negative");				
			}			
		}	
		
		//Initialized Variables
		wordArray = words;
		predictionArray = predictions;
		probabilitiesArray = probabilities;
		singleContext = stringArrayToString(words);
	}
	
	public NGramNode(String context, String[] predictions, Double[] probabilities) throws NGramException{
		
		//Exceptions
		if(context == null){
			throw new NGramException("Context: Input is a null value");
		}	
		
		if (context == ""){	
		throw new NGramException("Context: Input is a empty string");
		}
		
		if(predictions == null){
			throw new NGramException("Predictions: Input contains a null value");
		}
		
		if(predictions.length == 0){
			throw new NGramException("Predictions: is empty");
		}		
		
		if(probabilities == null){
			throw new NGramException("Probabilities: Input contains a null value");
		}	
		
		if(probabilities.length != predictions.length){
			throw new NGramException("Number of predictions does not equal number of probabilities");
		}
		
		if(context.equals("") ){
			throw new NGramException("Context: Input contains an empty Strings");
		}
		
		for(String prediction : predictions){
			
			if ((prediction == "")||(prediction == " ") || (prediction == null)){
				
				throw new NGramException("Predictions: Input contains an empty Strings or an null value");				
			}	
		}
		
		for(Double probability : probabilities){
			
			if (probability == null){
				
				throw new NGramException("Probabilities: Input contains an null value");
				
			} else if ((probability <= 0.0) ||(probability > 1.0)){
				
				throw new NGramException("Probabilities: Input contains a number greater one, equal to zero or negative");				
			}			
		}
		
		//Initialized Variables
		singleContext = context;
		predictionArray = predictions;
		probabilitiesArray = probabilities;
	}

	/**
	 * 
	 * Format for output of probabilities 
	 */
	public static final String DecFormat = "%1$,.6f";
	
	/**
	 * 
	 * Simple getter method for the context string
	 * 
	 * @return String containing context phrase for predictions
	 */
	public String getContext() {	
			 
		return singleContext;		
	}
	
	/**
	 * 
	 * Simple setter method for the context string
	 * 
	 * @param context - single String containing context phrase for predictions
	 * @throws NGramException if <code>context</code> is null 
	 */
	public void setContext(String context) throws NGramException {
		
		if (context == null){
			
			throw new NGramException("Context: is a null value");
		
		} else if ((context == "")||(context == " ")){
			
			throw new NGramException("Context: is a empty string");	
			
		}
		
		singleContext = context;
			
	}

	/**
	 * 
	 * Simple setter method for the context string from multiple words
	 * 
	 * @param words - array of words in order that make up the context
	 * @throws NGramException if <code>words</code> is null or empty or contains empty strings
	 */
	public void setContext(String[] words) throws NGramException {
				
		if (words == null) {
			throw new NGramException("Words: contains null value");
		}
		
		if(words.length == 0) {
			throw new NGramException("Words: is empty");
		}	
		
		for(String word : words ) {
			
			if ((word == "") || (word == " ") || (word == null)) {
				
				throw new NGramException("Words: Input contains an empty Strings or an null value");
				
			}
		}
		
		String currentContext = words[0];		
		
		contextArray = new String[words.length];
		
		for (int i = 0; i < words.length; i++) {				
			contextArray[i] = words[i];
		}
		
		for (int i = 1; i < contextArray.length; i++) {
			currentContext = currentContext + " " + contextArray[i];
		}		
		singleContext = currentContext;		
	}
	
	/**
	 * 
	 * Simple getter method for the prediction strings
	 * 
	 * @return array of alternative next words in the phrase as predicted by the model
	 */
	public String[] getPredictions() {		
				
		return predictionArray;		
	}
	
	/**
	 * 
	 * Simple setter method for the predictions string array
	 * 
	 * @param predictions - next word in the phrase as predicted by the model
	 * @throws NGramException if <code>predictions</code> is null or empty or contains empty strings
	 */
	public void setPredictions(String[] predictions) throws NGramException {
				
		if (predictions == null) {
			throw new NGramException("Predictions: contains null value");
		}
		
		if(predictions.length == 0) {
			throw new NGramException("Predictions: is empty");
		}
		
		for(String prediction : predictions) {
			
			if ((prediction == "")||(prediction == " ") || (prediction == null)){
				
				throw new NGramException("Predictions: Input contains an empty Strings or an null value");				
			}	
		}
		
		for(int i = 0; i < predictions.length; i++) {			
			
			predictionArray[i] = predictions[i];			
		}		
	}
	
	/**
	 * 
	 * Simple getter method for the probabilities
	 * 
	 * @return array of probabilities of context>prediction w.r.t. model
	 */
	public Double[] getProbabilities() {
				
		return probabilitiesArray;		
	}
	
	/**
	 * 
	 * Simple setter method for the probabilities 
	 * 
	 * @param probabilities - array of probabilities of context>prediction w.r.t. model
	 * @throws NGramException if <code>probabilities</code> is null or contains an entry which is zero, negative or greater than 1.0.
	 */
	public void setProbabilities(Double[] probabilities) throws NGramException {
		
		if (probabilities == null) {
			throw new NGramException("Probabilities: contains null value");
		}
		
		for(Double probability : probabilities) {
			
			if (probability == null) {
				
				throw new NGramException("Probabilities: Input contains an null value");
				
			} else if ((probability <= 0.0) ||(probability > 1.0)) {
				
				throw new NGramException("Probabilities: Input contains a number greater one, equal to zero or negative");
				
			}	
		}
		
		probabilitiesArray = new Double[probabilities.length];
		
		for (int i = 0; i < probabilities.length; i++) {

			probabilitiesArray[i] = probabilities[i];		
		}	
		
	}
	
	@Override
	public String toString() {		
		
		String output = "";
		
		for (int i = 0; i < predictionArray.length; i++) {			
			
			String prob = String.format(DecFormat, probabilitiesArray[i]);
			output = output + ((singleContext + " | " + predictionArray[i] + " : " + prob + "\n"));			 
		}		
		
		//adds newline at the end
		return output + "\n";			
	}
	
	/**
	 * 
	 * Converts String[] to String
	 * 
	 * @param list - a String array	 
	 * @return output - a String containing the combination of all the strings in the array
	 */
	private String stringArrayToString(String[] array) {
		
		String outputString = array[0];
		
		for(int i = 1; i < array.length; i++)
		{
			outputString = outputString + " " + array[i];
		}
		return outputString;		
	}
}
