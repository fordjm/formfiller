package formfiller.utilities;

import formfiller.Context;
import formfiller.entities.format.*;

public class FormatArgumentParser {
	public static Format parseFormat(String formatString) {
		if (Context.stringMatcher.matches(formatString, "U"))
			return new Unstructured();
		else if (Context.stringMatcher.matches(formatString, "S"))
			return new SingleOptionVariable();
		else if (Context.stringMatcher.matches(formatString, "M"))
			return new MultiOptionVariable();
		else
			throw new IllegalArgumentException(
					"Could not match format string " + formatString + ".");
	}
	
}
