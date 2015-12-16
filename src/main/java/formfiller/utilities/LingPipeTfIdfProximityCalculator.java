package formfiller.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliasi.spell.TfIdfDistance;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;


//	TODO:	Extract StringSimilarityCalculator class w/SimilarityCalculationStrategy param (this and Jaccard)?
public class LingPipeTfIdfProximityCalculator {
	private TfIdfDistance tfIdf;
	
	public LingPipeTfIdfProximityCalculator() {
		tfIdf = new TfIdfDistance(IndoEuropeanTokenizerFactory.INSTANCE);
	}
	
	public double calculate(String query, String document) {
		handleDocuments(document);
		return tfIdf.proximity(query, document);
	}
	
	public List<String> rankByProximity(String query, List<String> documents) {
		handleDocuments(documents.toArray(new String[0]));
		Map<String, Double> proximityMap = createUnsortedMap(query, documents);
		Map<String, Double> sortedMap = SortedMapCreator.createSortedMap(proximityMap);
		List<String> result = new ArrayList<String>(sortedMap.keySet());
		return result;
	}
	
	private void handleDocuments(String... documents) {
		for (String document : documents)
			tfIdf.handle(document);
	}
	
	private Map<String, Double> createUnsortedMap(String query, List<String> documents) {
		Map<String, Double> result = new HashMap<String, Double>();
		for (String document : documents)
			result.put(document, tfIdf.proximity(query, document));
		return result;
	}
	
}
