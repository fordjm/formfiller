package formfiller.delivery.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterBasedEventParsingStrategy implements EventParsingStrategy {
	private final String QUOTATION_MARK = "\"";

	public String parseMethod(String input) {
		if (input == null) return "";
		
		String trimmedInput = input.trim();
		int firstSpace = trimmedInput.indexOf(" ");
		return handleUnspacedInput(trimmedInput, firstSpace);
	}

	private String handleUnspacedInput(String input, int firstSpace) {
		return (firstSpace == -1) ? input : input.substring(0, firstSpace);
	}

	public List<String> parseParameters(String input) {
		if (input == null) return new ArrayList<String>();

		String trimmedInput = input.trim();
		if (trimmedInput.length() == 0) return new ArrayList<String>();
		
		List<String> result = splitParameters(trimmedInput);
		return joinCommaSeparatedElements(result);
	}

	private List<String> splitParameters(String input) {
		if (input.contains(QUOTATION_MARK))
			return splitQuotedParameters(input);
		else 
			return splitParametersOnWhitespace(input);
	}

	//	TODO:	Clean up.
	private List<String> splitQuotedParameters(String input) {
		List<String> strings = new ArrayList<String>();
		String quotedString = "";
		for (String word : input.split("\\s+")){
			if (quotedString == "" && !word.contains(QUOTATION_MARK))
				strings.add(word);
			else if (word.lastIndexOf(QUOTATION_MARK) == 0)
				quotedString = word + " ";
			else if (word.lastIndexOf(QUOTATION_MARK) == word.length()-1){
				quotedString += word;
				strings.add(quotedString);
				quotedString = "";
			}
			else if (quotedString != "" && !word.contains(QUOTATION_MARK))
				quotedString += word + " ";
			else if (word.indexOf(QUOTATION_MARK) < word.length() - 1)
				throw new IllegalArgumentException("Quotation marks cannot appear in the middle of a word.");
		}
		return strings;
	}

	private List<String> splitParametersOnWhitespace(String input) {
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(input.split("\\s+")));
		return result;
	}

	private List<String> joinCommaSeparatedElements(List<String> input) {
		for (int i=0; i<input.size(); ++i){
			String temp = input.get(i);
			if (temp.endsWith(","))
				input.set(i, temp + input.remove(i+1));
		}
		return input;
	}

}
