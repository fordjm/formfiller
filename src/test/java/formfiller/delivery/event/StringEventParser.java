package formfiller.delivery.event;

import formfiller.delivery.EventParser;

//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/RequestParser.java
//Retrieved 2015-08-14

public class StringEventParser implements EventParser {

	public ParsedEvent parse(String input) {
		ParsedEvent result = new ParsedEvent();
		if (input == null) return result;
		
		String[] split = preprocessInputString(input);
		if (split.length > 0)
			result.method = split[0];
		//	TODO:	For each loop and add params to List<String>
		if (split.length > 1)
			result.param = split[1];
		return result;
	}

	private String[] preprocessInputString(String input) {
		input = input.trim();
		String[] split = input.split("\\s+");
		return split;
	}
}
