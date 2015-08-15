package formfiller.ui.userRequestParser;

//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/RequestParser.java
//Retrieved 2015-08-14
public class ConsoleUserRequestParser implements UserRequestParser {

	public ParsedUserRequest parse(String input) {
		ParsedUserRequest result = new ParsedUserRequest();
		if (input == null) return result;
		
		String[] split = preprocessInputString(input);
		if (split.length > 0)
			result.setMethod(split[0]);
		if (split.length > 1)
			result.setParam(split[1]);
		return result;
	}

	String[] preprocessInputString(String input) {
		input = input.trim();
		String[] split = input.split("\\s+");
		return split;
	}

}
