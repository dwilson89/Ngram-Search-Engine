package ngram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class NGramNodeTest {

	/**
	 * Test Suite for NGramNodeTest Class
	 * 
	 * @author Dustin Wilson - n6325157
	 * 
	 * @edited Chris Connolly - n6873146
	 * 
	 * The following tests exercise all of the NGramNode class methods
	 * by testing them with values, that fit into the following case categories
	 * normal, exceptional and boundary.
	 */
	
	//Test Variables
	
	private final String TEST_PHRASE = "be or not to";
	private final String [] TEST_WORDS = {"be", "or", "not","to"};
	private final String [] PREDICTIONS = {"be", "mention", "exceed", "say", "the"};
	private final Double [] PROBABILITIES = {0.136, 0.066, 0.032, 0.028, 0.024};
	
	//constructor 1 - will mainly be used for all other test cases
	NGramNode testNode1;
	
	//constructor 2
	NGramNode testNode2;

	/* Create a new NGramNode for each test*/
	
	//Test case 0.1 - Constructs the NGramNode first constructor correctly
	@Before
	@Test
	public void setUpNodeOne() throws NGramException {
	
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, PROBABILITIES);

	}
	
	//Test case 0.2 - Constructs the NGramNode second constructor correctly
	@Before
	@Test
	public void setUpNodeTwo() throws NGramException/*throws NGramException*/ {
	
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, PROBABILITIES);
		
	}
	
	/*Exception cases for Constructor Methods*/
	
	/*Constructor Method 1*/
	
	/*Sets initial values correctly*/
	
	//Test case 1 - Normal case - Sets initial words 
	@Test
	public void setUpNodeOneInitialWords() throws NGramException {
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, PROBABILITIES);
		
		assertEquals(TEST_PHRASE, testNode1.getContext());
		
	}
	
	//Test case 2 - Normal case - Sets initial predictions
	@Test
	public void setUpNodeOneInitialPredictions() throws NGramException {
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, PROBABILITIES);
		
		assertArrayEquals(PREDICTIONS, testNode1.getPredictions());
		
	}
	
	//Test case 3 - Normal case - Sets initial probabilities
	@Test
	public void setUpNodeOneInitialProbabilities() throws NGramException {
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, PROBABILITIES);
		
		assertArrayEquals(PROBABILITIES, testNode1.getProbabilities());
		
	}
	
	//Test case 4 - Probability is 1
	@Test
	public void NGramNodeOneProbabilityIsOne() throws NGramException {
		
		Double [] probabilitiesOfOne = {1.0, 0.066, 0.032, 0.028, 0.024};
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, probabilitiesOfOne);
		
		assertArrayEquals(probabilitiesOfOne, testNode1.getProbabilities());
		
	}
	
	/*Null Exception cases for constructor 1*/
	
	//Test case 5 - Null words
	@Test(expected = NGramException.class)
	public void NGramNodeOneNullWords() throws NGramException {
		
		String[] test = null;
		
		testNode1 = new NGramNode(test, PREDICTIONS, PROBABILITIES);
		
	} 
	
	//Test case 6 - Null predictions
	@Test(expected = NGramException.class)
	public void NGramNodeOneNullPredictions() throws NGramException {
		
		testNode1 = new NGramNode(TEST_WORDS, null, PROBABILITIES);
		
	}
	
	//Test case 7 - Null Probabilities
	@Test(expected = NGramException.class)
	public void NGramNodeOneNullProbabilities() throws NGramException {
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, null);
		
	}
	
	/*Empty Array Exceptions for Constructor 1*/
	
	//Test case 8 - Empty Words 
	@Test(expected = NGramException.class)
	public void NGramNodeOneEmptyWords() throws NGramException {
		
		String [] emptyWords = {};
		
		testNode1 = new NGramNode(emptyWords, PREDICTIONS, PROBABILITIES);
		
	}
	
	//Test case 9 - Empty Predictions
	@Test(expected = NGramException.class)
	public void NGramNodeOneEmptyPredictions() throws NGramException {
		
	String [] emptyPredictions = {};
		
	testNode1 = new NGramNode(TEST_WORDS, emptyPredictions, PROBABILITIES);
		
	}
	
	/*Empty String Exceptions for Constructor 1*/
	
	//Test case 10 - Empty word strings
	@Test(expected = NGramException.class)
	public void NGramNodeOneEmptyWordsStrings() throws NGramException {
		
		String [] emptyStrings = {"be","","not","to"};
		
		testNode1 = new NGramNode(emptyStrings, PREDICTIONS, PROBABILITIES);
		
	}
	
	@Test(expected = NGramException.class)
	public void NGramNodeOneEmptyWordsStrings2() throws NGramException {
		
		String [] emptyStrings = {"be"," ","not","to"};
		
		testNode1 = new NGramNode(emptyStrings, PREDICTIONS, PROBABILITIES);
		
	}
	
	//Test case 11 - Empty Prediction Strings
	@Test(expected = NGramException.class)
	public void NGramNodeOneEmptyPredictionsStrings() throws NGramException {
		
		String [] emptyPredictionsStrings = {"be", "", "", "say", "the"};
		
		testNode1 = new NGramNode(TEST_WORDS, emptyPredictionsStrings, PROBABILITIES);
		
	}
	
	@Test(expected = NGramException.class)
	public void NGramNodeOneEmptyPredictionsStrings2() throws NGramException {
		
		String [] emptyPredictionsStrings = {"be", " ", " ", "say", "the"};
		
		testNode1 = new NGramNode(TEST_WORDS, emptyPredictionsStrings, PROBABILITIES);
		
	}
	
	/*Null values in Arrays*/
	
	@Test(expected = NGramException.class)
	public void NGramNodeOneNullInWords() throws NGramException {
		
		String [] nullStrings = {"be",null,"not","to"};
		
		testNode1 = new NGramNode(nullStrings, PREDICTIONS, PROBABILITIES);
		
	}
	
	@Test(expected = NGramException.class)
	public void NGramNodeOneNullinPredictions() throws NGramException {
		
		String [] nullPredictionsStrings = {"be", null, null, "say", "the"};
		
		testNode1 = new NGramNode(TEST_WORDS, nullPredictionsStrings, PROBABILITIES);
		
	}
		
	@Test(expected = NGramException.class)
	public void NGramNodeOneProbabilityContainsNull() throws NGramException {
		
		Double [] nullProbabilities = {0.136, null, 0.032, null, 0.024};
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, nullProbabilities);
	
	}
	
	/*Additional Exceptions for Constructor 1*/
	
	//Test case 12 - Probability is zero
	@Test(expected = NGramException.class)
	public void NGramNodeOneProbabilityIsZero() throws NGramException {
		
		Double [] zeroProbabilities = {0.136, 0.0, 0.032, 0.0, 0.024};
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, zeroProbabilities);
	}
	
	//Test case 13 - Probability is negative
	@Test(expected = NGramException.class)
	public void NGramNodeOneProbabilityIsNegative() throws NGramException {
		
		Double [] negativeProbabilities = {0.136, -0.066, 0.032, -0.028, 0.024};
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, negativeProbabilities);
		
	}
	
	//Test case 14 - Probability is greater than 1
	@Test(expected = NGramException.class)
	public void NGramNodeOneProbabilityGreaterThanOne() throws NGramException {
		
		Double [] greaterProbabilities = {1.136, 0.066, 0.032, 0.028, 0.024};
		
		testNode1 = new NGramNode(TEST_WORDS, PREDICTIONS, greaterProbabilities);
		
	}
	
	//Test case 15 - Prediction length is different from Probabilities' length
	@Test(expected = NGramException.class)
	public void NGramNodeOneDifferentArrayLengths() throws NGramException {
		
		String [] smallerPredictionLength = {"be", "mention", "exceed", "say"};
		Double [] probabilities = {0.136, 0.066, 0.032, 0.028, 0.024};
		
		testNode1 = new NGramNode(TEST_WORDS, smallerPredictionLength, probabilities);
		
	}
	
	/*Constructor Method 2*/
	
	/*Sets initial values correctly*/
	
	//Test case 16 - Normal case - Sets initial words 
	@Test
	public void setUpNodeTwoInitialPhrase() throws NGramException {
		
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, PROBABILITIES);
		
		assertEquals(TEST_PHRASE, testNode1.getContext());
		
	}
	
	//Test case 17 - Normal case - Sets initial predictions
	@Test
	public void setUpNodeTwoInitialPredictions() throws NGramException {
		
		testNode2 = new NGramNode(TEST_WORDS, PREDICTIONS, PROBABILITIES);
		
		assertArrayEquals(PREDICTIONS, testNode1.getPredictions());
		
	}
	
	//Test case 18 - Normal case - Sets initial probabilities
	@Test
	public void setUpNodeTwoInitialProbabilities() throws NGramException {
		
		testNode2 = new NGramNode(TEST_WORDS, PREDICTIONS, PROBABILITIES);
		
		assertArrayEquals(PROBABILITIES, testNode1.getProbabilities());
		
	}
	
	//Test case 19 - Probability is 1
	@Test
	public void NGramNodeTwoProbabilityIsOne() throws NGramException {
		
		Double [] probabilitiesOfOne = {1.0, 0.066, 0.032, 0.028, 0.024};
		
		testNode2 = new NGramNode(TEST_WORDS, PREDICTIONS, probabilitiesOfOne);
		
		assertArrayEquals(probabilitiesOfOne, testNode2.getProbabilities());
		
	}
	
	/*Null Exception cases for constructor 2*/
	
	//Test case 20 - Null Phrase
	@Test(expected = NGramException.class)
	public void NGramNodeTwoNullPhrase() throws NGramException {
		
		String test = null;
		
		testNode2 = new NGramNode(test, PREDICTIONS, PROBABILITIES);
		
	} 
	
	//Test case - Empty Phrase
	@Test(expected = NGramException.class)
	public void NGramNodeTwoEmptyPhrase() throws NGramException {
		
		String test = "";
		
		testNode2 = new NGramNode(test, PREDICTIONS, PROBABILITIES);
		
	} 
	
	//Test case 21 - Null predictions
	@Test(expected = NGramException.class)
	public void NGramNodeTwoNullPredictions() throws NGramException {
		
		testNode2 = new NGramNode(TEST_PHRASE, null, PROBABILITIES);
		
	}
	
	//Test case 22 - Null Probabilities
	@Test(expected = NGramException.class)
	public void NGramNodeTwoNullProbabilities() throws NGramException {
		
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, null);
		
	}
	
	/*Test case for prediction parameter constructor method 2*/
	
	//Test case 23 - Empty Predictions
	@Test(expected = NGramException.class)
	public void NGramNodeTwoEmptyPredictions() throws NGramException {
		
		String [] emptyPredictions = {};
		
		testNode2 = new NGramNode(TEST_PHRASE, emptyPredictions, PROBABILITIES);
		
	}
	
	//Test case 24 - Empty Prediction Strings
	@Test(expected = NGramException.class)
	public void NGramNodeTwoEmptyPredictionsStrings() throws NGramException {
		
		String [] emptyPredictionsStrings = {"be", "", "", "say", "the"};
		
		testNode2 = new NGramNode(TEST_PHRASE, emptyPredictionsStrings, PROBABILITIES);
		
	}
	
	@Test(expected = NGramException.class)
	public void NGramNodeTwoEmptyPredictionsStrings2() throws NGramException {
		
		String [] emptyPredictionsStrings = {"be", " ", " ", "say", "the"};
		
		testNode2 = new NGramNode(TEST_PHRASE, emptyPredictionsStrings, PROBABILITIES);
		
	}
	
	/*Null values in Arrays*/
	
	
	@Test(expected = NGramException.class)
	public void NGramNodeTwoNullinPredictions() throws NGramException {
		
		String [] nullPredictionsStrings = {"be", null, null, "say", "the"};
		
		testNode2 = new NGramNode(TEST_PHRASE, nullPredictionsStrings, PROBABILITIES);
		
	}
		
	@Test(expected = NGramException.class)
	public void NGramNodeTwoProbabilityContainsNull() throws NGramException {
		
		Double [] nullProbabilities = {0.136, null, 0.032, null, 0.024};
		
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, nullProbabilities);
	
	}
	
	/*Additional Exceptions for Constructor 2*/
	
	//Test case 25 - Probability is zero
	@Test(expected = NGramException.class)
	public void NGramNodeTwoProbabilityIsZero() throws NGramException {
		
		Double [] zeroProbabilities = {0.136, 0.0, 0.032, 0.0, 0.024};
		
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, zeroProbabilities);
	}
	
	//Test case 26 - Probability is negative
	@Test(expected = NGramException.class)
	public void NGramNodeTwoProbabilityIsNegative() throws NGramException {
		
		Double [] negativeProbabilities = {0.136, -0.066, 0.032, -0.028, 0.024};
		
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, negativeProbabilities);
		
	}
	
	//Test case 27 - Probability is greater than 1
	@Test(expected = NGramException.class)
	public void NGramNodeTwoProbabilityGreaterThanOne() throws NGramException {
		
		Double [] greaterProbabilities = {1.136, 0.066, 0.032, 0.028, 0.024};
		
		testNode2 = new NGramNode(TEST_PHRASE, PREDICTIONS, greaterProbabilities);
		
	}
	
	//Test case 28 - Prediction length is different from Probabilities' length
	@Test(expected = NGramException.class)
	public void NGramNodeTwoDifferentArrayLengths() throws NGramException {
		
		String [] smallerPredictionLength = {"be", "mention", "exceed", "say"};
		Double [] probabilities = {0.136, 0.066, 0.032, 0.028, 0.024};
		
		testNode2 = new NGramNode(TEST_PHRASE, smallerPredictionLength, probabilities);
		
	}
	
	//getters will not be tested - as they are generally tested with the setters?
	
	/*Test Cases for setContext method 1 - string parameter*/
	
	//Test case 29 - Normal Case - normal input
	@Test
	public void setContextOneString() throws NGramException {
		
		String newTestPhrase = "to be or not"; 
		
		testNode2.setContext(newTestPhrase);
		
		assertEquals(newTestPhrase, testNode2.getContext());
		
	}
	
	@Test
	public void setContextOneOneLetterString() throws NGramException {
		
		String newTestPhrase = "A"; 
		
		testNode2.setContext(newTestPhrase);
		
		assertEquals(newTestPhrase, testNode2.getContext());
		
	}
	
	//Test case 30 - Boundary Case - empty string
	@Test(expected = NGramException.class)
	public void setContextOneEmptyString() throws NGramException {
		
		String newTestPhrase = ""; 
		
		testNode2.setContext(newTestPhrase);
		
	}
	
	@Test(expected = NGramException.class)
	public void setContextOneEmptyString2() throws NGramException {
		
		String newTestPhrase =  " "; 
		
		testNode2.setContext(newTestPhrase);
		
	}
	
	//Test case 31 - Exception Cases - null string
	@Test(expected = NGramException.class)
	public void setContextOneNullString() throws NGramException {
		
		String newTestPhrase = null; 
		
		testNode2.setContext(newTestPhrase);
		
		assertEquals(newTestPhrase, testNode1.getContext());
		
	}
	
	/*Test Cases for setContext method 2 - string array parameter*/
	
	//Test case 32 - Normal Case - General Maximum length
	@Test
	public void setContextTwoStringArray() throws NGramException {
		
		String [] newTestWords = {"to", "be", "or", "not"};
		
		String newTestPhrase = "to be or not"; 
		
		testNode2.setContext(newTestWords);
		
		assertEquals(newTestPhrase, testNode2.getContext());
		
	}
	
	//Test case 33 - Boundary case - Array Length of 1
	@Test
	public void setContextMinimumLengthArray() throws NGramException{
		
		String [] newTestWords = {"to"};
		
		String newTestPhrase = "to"; 
		
		testNode2.setContext(newTestWords);
		
		assertEquals(newTestPhrase, testNode2.getContext());
		
	}
	
	//Test case 34 - Boundary case - String length of 1
	@Test
	public void setContextMinimumLengthString() throws NGramException{
		
		String [] newTestWords = {"A"};
		
		String newTestPhrase = "A"; 
		
		testNode2.setContext(newTestWords);
		
		assertEquals(newTestPhrase, testNode2.getContext());
		
	}
	
	//Test case 35 - Exception Case - Null entry
	@Test(expected = NGramException.class)
	public void setContextTwoNullEntry() throws NGramException {
		
		String [] newTestWords = null;
		testNode2.setContext(newTestWords);
		
	}
	
	//Test case 36 - Exception Case - Empty Array
	@Test(expected = NGramException.class)
	public void setContextTwoEmptyArray() throws NGramException {
		
		String [] newTestWords = {};
		testNode2.setContext(newTestWords);
		
	}
	
	//Test case 37 - Exception Case - Empty Strings
	@Test(expected = NGramException.class)
	public void setContextTwoEmptyStrings() throws NGramException {
		
		String [] newTestWords = {"to", "", "", "not"};
		
		testNode2.setContext(newTestWords);
	}
	
	@Test(expected = NGramException.class)
	public void setContextTwoEmptyStrings2() throws NGramException {
		
		String [] newTestWords = {"to", " ", " ", "not"};
		
		testNode2.setContext(newTestWords);
	}
	
	@Test(expected = NGramException.class)
	public void setContextTwoNullStrings() throws NGramException {
		
		String [] newTestWords = {"to", null, null, "not"};
		
		testNode2.setContext(newTestWords);
	}
	
	
	/*Test Cases for setPredictions*/
	
	//Test case 38 - Normal Case - Maximum amount of predictions
	@Test
	public void setPredictionsNormalCase() throws NGramException {
	
		String [] newPredictions = {"be", "me", "exceed", "hello", "the"};
		
		testNode2.setPredictions(newPredictions);
		
		assertArrayEquals(newPredictions, testNode1.getPredictions());
		
	}
	
	//Test case 39 - Normal case - Minimum amount of predictions
	@Test
	public void setPredictionsMinimumArrayLength() throws NGramException {
	
		String [] newPredictions = {"be", "bee", "beee", "bea", "beea"};
		
		testNode2.setPredictions(newPredictions);
		
		assertArrayEquals(newPredictions, testNode1.getPredictions());
		
	}
	
	//Test case 39 - Normal case - Minimum string length
	@Test
	public void setPredictionsMinimumStringLength() throws NGramException {
	
		String [] newPredictions = {"A", "B", "C", "D", "E"};
		
		testNode2.setPredictions(newPredictions);
		
		assertArrayEquals(newPredictions, testNode1.getPredictions());
		
	}
	
	
	//Test case 40 - Exception Cases - Null
	@Test(expected = NGramException.class)
	public void setPredictionsNullEntry() throws NGramException {
		
		String [] newPredictions = null;
		
		testNode2.setPredictions(newPredictions);
		
	}
	
	//Test case 41 - Exception Cases - Empty Array
	@Test(expected = NGramException.class)
	public void setPredictionsEmptyArray() throws NGramException {
		
		String [] newPredictions = {};
		
		testNode2.setPredictions(newPredictions);
		
	}
	
	//Test case 42 - Exception Cases - Empty String
	@Test(expected = NGramException.class)
	public void setPredictionsEmptyStrings() throws NGramException {
		
		String [] newPredictions = {"be", "", "exceed", "", "the"};
		
		testNode2.setPredictions(newPredictions);
		
	}
	
	@Test(expected = NGramException.class)
	public void setPredictionsEmptyStrings2() throws NGramException {
		
		String [] newPredictions = {"be", " ", "exceed", " ", "the"};
		
		testNode2.setPredictions(newPredictions);
		
	}
	
	@Test(expected = NGramException.class)
	public void setPredictionsNullStrings() throws NGramException {
		
		String [] newPredictions = {"be", null, "exceed", null, "the"};
		
		testNode2.setPredictions(newPredictions);
		
	}
	
	/*Test Cases for setProbabilities*/
	
	//Test case 43 - Normal Case
	@Test
	public void setProbabilitiesNormalCase() throws NGramException {
		
		Double [] newProbabilities = {0.21, 0.074, 0.035, 0.028, 0.024};
		
		testNode2.setProbabilities(newProbabilities);
		
		assertArrayEquals(newProbabilities, testNode2.getProbabilities());
		
	}
	
	//Test case 44 - Normal Case - Probability of one
	@Test 
	public void setProbabilitiesValueOfOne() throws NGramException{
		
		Double [] newProbabilities = {1.0, 0.074, 0.035, 0.028, 0.024};
		
		testNode2.setProbabilities(newProbabilities);
		
		assertArrayEquals(newProbabilities, testNode2.getProbabilities());
		
	}
	
	
	//Test case 45 - Exception Cases - null
	@Test(expected = NGramException.class)
	public void setProbabilitiesNullEntry() throws NGramException {
		
		Double [] newProbabilities = null;
		
		testNode2.setProbabilities(newProbabilities);
		
	}
	
	//Test case 46 - Exception Cases - zero entry
	@Test(expected = NGramException.class)
	public void setProbabilitiesZeroEntry() throws NGramException {
		
		Double [] newProbabilities = {0.21, 0.0, 0.0, 0.028, 0.0};
		
		testNode2.setProbabilities(newProbabilities);
		
	}
	
	//Test case 47 - Exception Cases - negative entry
	@Test(expected = NGramException.class)
	public void setProbabilitiesNegativeEntry() throws NGramException {
		
		Double [] newProbabilities = {-0.21, 0.074, -0.035, 0.028, 0.024};
		
		testNode2.setProbabilities(newProbabilities);
		
	}
	
	//Test case 48 - Exception Cases - greater than 1 entry
	@Test(expected = NGramException.class)
	public void setProbabilitiesGreaterThanOne() throws NGramException {
		
		Double [] newProbabilities = {1.21, 0.074, 1.035, 0.028, 0.024};
		
		testNode2.setProbabilities(newProbabilities);
			
	}
	
	@Test(expected = NGramException.class)
	public void setProbabilitiesNullEntryArray() throws NGramException {
		
		Double [] newProbabilities = {null, 0.074, null, 0.028, 0.024};
		
		testNode2.setProbabilities(newProbabilities);
			
	}
	
	@Test
	public void toStringTest() {
		
		String results = testNode2.toString();
		
		String answer = "";
		
		for (int i = 0; i < PREDICTIONS.length; i++){			
			
			String prob = String.format("%1$,.6f", PROBABILITIES[i]);
			answer += ((TEST_PHRASE + " | " + PREDICTIONS[i] + " : " + prob + "\n"));			 
		
		}		
		
		results += "\n";
		
		assertEquals(answer, results);
		
	}
	
}
