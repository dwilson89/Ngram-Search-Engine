package ngram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class NGramStoreTest {
	
	/**
	 * Test Suite for NGramStoreTest Class
	 * 
	 * @author Dustin Wilson - n6325157
	 * 
	 * The following tests exercise all of the NGramStore class methods
	 * by testing their functionality
	 */

	//Test Objects
	NGramStore testStore;
	
	NGramNode testNode;
	
	NGramNode testNode2;
	
	//Test Variables for the testNode
	
	private final String TEST_PHRASE = "be or not to";
	private final String TEST_PHRASE_2 = "going for a walk";
	private final String [] PREDICTIONS = {"be", "mention", "exceed", "say", "the"};
	private final String [] PREDICTIONS_2 = {"today", "when", "if", "in", "for"};
	private final Double [] PROBABILITIES = {0.136, 0.066, 0.032, 0.028, 0.024};
	private final Double [] PROBABILITIES_2 = {0.245, 0.189, 0.025, 0.021, 0.010};
	
	/* Create a new NGramNode for each test*/
	
	//Test case 0.1 - Constructs the NGramNode and new NGramStore
	@Before
	@Test
	public void setUp() throws NGramException {
	
		testNode = new NGramNode(TEST_PHRASE, PREDICTIONS, PROBABILITIES);
		testNode2 = new NGramNode(TEST_PHRASE_2, PREDICTIONS_2, PROBABILITIES_2);

		testStore = new NGramStore();		
	}
	
	/*Test cases for AddNGram*/
	
	//Adds Ngram to Map
	@Test
	public void addsNgram() {
		
		testStore.addNGram(testNode);
		
		assertEquals(testStore.getNGram(TEST_PHRASE), testNode);		
	}
	
	//If context already exists updates
	@Test
	public void updatesNGram() {
		
		testStore.addNGram(testNode);
		testStore.addNGram(testNode);
		
		assertEquals(testStore.getNGram(TEST_PHRASE), testNode);
		
	}
	
	//If map contains more than one Node
		@Test
		public void addMultipleNodes1() {
			
			testStore.addNGram(testNode);
			testStore.addNGram(testNode2);
			
			assertEquals(testStore.getNGram(TEST_PHRASE), testNode);			
			
		}
	
	//If map contains more than one Node
		@Test
		public void addMultipleNodes2() {
					
			testStore.addNGram(testNode);
			testStore.addNGram(testNode2);		
					
			assertEquals(testStore.getNGram(TEST_PHRASE_2), testNode2);
					
		}	
	
	/*Test case removeNGram*/
	
	//If no context, removes nothing
	@Test
	public void removesNothing() {
		
		testStore.removeNGram(TEST_PHRASE);
		assertEquals(testStore.getNGram(TEST_PHRASE), null);		
	}
	
	//If context exists removes it
	@Test
	public void removesContext() {
		
		testStore.addNGram(testNode);
		testStore.removeNGram(TEST_PHRASE);		
		
		assertEquals(testStore.getNGram(TEST_PHRASE), null);
	}
	
	//If context exists removes correct NGramNode
		@Test
		public void removesCorrectNode() {
			
			testStore.addNGram(testNode);
			testStore.addNGram(testNode2);
			testStore.removeNGram(TEST_PHRASE);		
			
			assertEquals(testStore.getNGram(TEST_PHRASE), null);
			assertEquals(testStore.getNGram(TEST_PHRASE_2), testNode2);
		}
}
