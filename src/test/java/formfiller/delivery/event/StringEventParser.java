package formfiller.delivery.event;

import java.util.ArrayList;
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
		
		String[] split = makeSplitArray(input);
		if (split.length > 0)
			result.method = split[0];
		if (split.length > 1)
			result.parameters = makeParams(split);
		return result;
	}

	private String[] makeSplitArray(String input) {
		input = input.trim();
		if (input.contains(QUOTATION_MARK))
			return parseStringWithQuotes(input);
		else	
			return input.split("\\s+");
	}

	private String[] parseStringWithQuotes(String input) {
		List<String> strings = new ArrayList<String>();
		String quotedString = "";
		//	 Split on space and combine elements?
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
		return strings.toArray(new String[0]);
	}

	private List<String> makeParams(String[] split) {
		List<String> result = new ArrayList<String>();
		for (int i=1; i<split.length; ++i)
			result.add(split[i]);
		return  result;
	}
}
