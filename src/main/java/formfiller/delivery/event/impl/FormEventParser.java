package formfiller.delivery.event.impl;

import java.util.List;

import formfiller.delivery.event.EventParser;
import formfiller.delivery.event.EventParsingStrategy;
import formfiller.utilities.StringUtilities;

/**
 * FormEventParser uses a passed EventParsingStrategy to convert input strings 
 * to parsed event objects.
 */
public class FormEventParser implements EventParser {
	private EventParsingStrategy strategy;
	
	public FormEventParser(EventParsingStrategy strategy) {
		this.strategy = strategy;
	}

	public ParsedEvent parse(String input) {
		if (input == null) return new ParsedEvent();
		
		ParsedEvent result = new ParsedEvent();
		result.method = strategy.parseMethod(input);
		result.parameters = strategy.parseParameters(removeMethod(input, result));
		return result;
	}

	//	TODO:	Make functional.
	private String removeMethod(String input, ParsedEvent event) {
		List<String> split = StringUtilities.splitStringOnWhitespace(event.method);
		for (String word : split)
			input = input.replaceFirst(word, "");
		return input.trim();
	}
	
}
