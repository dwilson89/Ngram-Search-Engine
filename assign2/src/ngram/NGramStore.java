package ngram;

/**
 * NGramStore Class for NGram, NGramStore
 * 
 * @author Dustin Wilson - n6325157
 * 
 * @edited Chris Connolly - n6873146
 * 
 * This class searches for and stores all NGramNodes
 *  
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;

public class NGramStore implements NGramMap {
	
	public static final String Key = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 	
	
	private Map<String, NGramContainer> NGramMap1 = new HashMap<String, NGramContainer>();
	
	public NGramStore() {		
	}	
	
	/**
	 * 
	 * (Silently) Add an ngram to the Map. If the <code>context</code> does not exist in the Map, 
	 * the entry is added.<br>
	 * If the <code>context</code> exists in the Map, then the associated ngram is 
	 * updated. 
	 * 
	 * @param ngram - ngram to be added 
	 */
	public void addNGram(NGramContainer ngram)
	{
		String context = ngram.getContext();		
		
		if(NGramMap1.containsKey(context)) {			
			
			NGramMap1.put(context, ngram);
			
		} else {
			
			NGramMap1.put(context, ngram);
			
		}			
	}
	
	/**
	 * 
	 * (Silently) Remove an ngram from the Map. If the <code>context</code> does not exist in the Map, 
	 * the entry is not removed, but no status is returned.<br>
	 * If the <code>context</code> exists in the Map, then the associated ngram is removed. 
	 * 
	 * @param ngram - ngram to be added 
	 */
	public void removeNGram(String context){
				
		if (NGramMap1.containsKey(context)){
			
			NGramMap1.remove(context);			
		}		
	}

	/**
	 * 
	 * Find the NGram associated with the context if it exists in the Map. 
	 * Return null if the context is not a key in the Map. 
	 * 
	 * @param context
	 * @return null if the context is not a key in the Map or the associated
	 * NGramNode if the context is in the Map
	 */
	public NGramContainer getNGram(String context) {		

		if (NGramMap1.containsKey(context)){
			
			return NGramMap1.get(context);
			
		} 			
		return null;
	}
	
	/**
	 * 
	 * Get the top maxResults ngrams returned from the service.  
	 * 
	 * @param context - the context for the ngram search 
	 * @param maxResults - the maximum number of predictions to be returned
	 * @return true and store the NGram in the Map if the service returns at least one result<br>
	 * return false and do not store the bare context if the service returns no predictions
	 * @throws NGramException if the service fails to connect or if the NGramContainer cannot be 
	 * created. 
	 */
	public boolean getNGramsFromService(String context, int maxResults)
			throws NGramException 
		{						
		String[] predictions;
		Double[] probability;
		
		NgramServiceFactory factory = NgramServiceFactory.newInstance(Key);
		
		if (factory==null) {
		     throw new NGramException("NGram Service unavailable");
		}
		
		GenerationService service = factory.newGenerationService();		
		
		TokenSet tokenSet = service.generate(Key, "bing-body/jun09/3", context, maxResults, null);			
							
		if(tokenSet.getWords().isEmpty())
		{
			return false;	
		}
		else 
		{
			List<Double> logProbs = tokenSet.getProbabilities();
			List<Double> probs = new ArrayList<Double>();
			
			for (Double x : logProbs) 
			{
				probs.add(Math.pow(10.0,x));
			}	
			predictions = toStringArray(tokenSet.getWords());  //converts List to array		
			probability = toDoubleArray(probs);
			
			NGramContainer NGram = new NGramNode(context, predictions, probability);		
		
			
			addNGram(NGram);
		
			return true;		
		}	
	}	
	
	/**
	 * 
	 * Converts List<String> to String[]
	 * 
	 * @param list - a list of strings	 
	 * @return output - a string array consisting of the strings in the list
	 */
	private String[] toStringArray(List<String> list)
	{		
		String[] output = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++)
		{			
			output[i] = list.get(i);
		}			
		return output;		
	}	
	
	/**
	 * 
	 * Converts List<Double> to Double[]
	 * 
	 * @param list - a list of Double values	 
	 * @return output - a Double array consisting of the Double values in the list
	 */
	private Double[] toDoubleArray(List<Double> list)
	{		
		Double[] output = new Double[list.size()];
		
		for (int i = 0; i < list.size(); i++)
		{			
			output[i] = list.get(i);
		}
		return output;		
	}
	
	public String toString()
	{			
		String results = "";
		
		for (Entry<String, NGramContainer> entry : NGramMap1.entrySet())
		{
			results += entry.getValue().toString();
		    
		}
		
		return results;	
	}
}
