package formfiller.delivery.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import formfiller.delivery.EventParser;

//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/RequestParser.java
//Retrieved 2015-08-14

//	TODO:	Determine where in the class/package name the "Verbatim" modifier belongs.
public class StringEventParser implements EventParser {
	private final String QUOTATION_MARK = "\"";
	
	public ParsedEvent parse(String input) {
		ParsedEvent result = new ParsedEvent();
		if (input == null) return result;
		
		List<String> split = splitParameters(input);
		result.method = split.remove(0);
		if (split.size() > 0)
			result.parameters = split;
		return result;
	}

	private List<String> splitParameters(String input) {
		List<String> result;
		input = input.trim();
		if (input.contains(QUOTATION_MARK))
			result = splitQuotedParameters(input);
		else	{
			result = new ArrayList<String>();
			result.addAll(Arrays.asList(input.split("\\s+")));
		}
		joinCommaSeparatedElements(result);
		return result;
	}

	//	TODO:	Could just insist these elements not be separated by " "
	private void joinCommaSeparatedElements(List<String> input) {
		for (int i=0; i<input.size(); ++i){
			String temp = input.get(i);
			if (temp.endsWith(","))
				input.set(i, temp + input.remove(i+1));
		}
	}

	//	TODO:	Consider separating into quote and non-quote parser classes.
	private List<String> splitQuotedParameters(String input) {
		List<String> strings = new ArrayList<String>();
		String quotedString = "";
		for (String word : input.split("\\s+")){
			if (quotedString == "" && !word.contains(QUOTATION_MARK))
				strings.add(word);
			else if (quotedString == "" && word.indexOf(QUOTATION_MARK) == 0)
				quotedString = word + " ";
			else if (quotedString != "" && word.indexOf(QUOTATION_MARK) == word.length()-1){
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
}
