package formfiller.delivery.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formfiller.utilities.LingPipeJaccardProximityCalculator;
import formfiller.utilities.SortedMapCreator;

public class JaccardDistanceEventParsingStrategy implements EventParsingStrategy {
	private List<String> useCases;
	private String input;
	
	public JaccardDistanceEventParsingStrategy() {
		useCases = Arrays.asList("ask question", "add answer");
	}

	public String parseMethod(String input) {
		this.input = input;
		if (input == null || input.length() == 0) return "";
		return findClosestMatch(input);
	}

	private String findClosestMatch(String input) {
		Map<String, Double> proximityMap = createUnsortedMap(input);
		Map<String, Double> sortedMap = SortedMapCreator.createSortedMap(proximityMap);
		String closest = sortedMap.keySet().toArray(new String[]{})[0];
		double score = proximityMap.get(closest);
		return screenIfBelowMinThreshold(closest, score);
	}

	private Map<String, Double> createUnsortedMap(String input) {
		Map<String, Double> result = new HashMap<String, Double>();
		LingPipeJaccardProximityCalculator calculator = new LingPipeJaccardProximityCalculator();
		for (String useCase : useCases)
			result.put(useCase, calculator.calculate(useCase, input));
		return result;
	}

	//	TODO:	Return input if below min threshold
	private String screenIfBelowMinThreshold(String closest, double score) {
		return (score >= 0.5) ? closest : input;
	}

	public List<String> parseParameters(String input) {
		if (input == null || input.length() == 0) return new ArrayList<String>();
		return Arrays.asList(input);
	}

}
