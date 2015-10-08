package formfiller.utilities;

import formfiller.Context;
import formfiller.entities.format.Format;
import formfiller.entities.format.OptionVariable;
import formfiller.entities.format.Unstructured;

public class FormatArgumentParser {
	public static Format parseFormat(String formatString) {
		if (Context.stringMatcher.matches(formatString, "U"))
			return new Unstructured();
		else if (Context.stringMatcher.matches(formatString, "V"))
			return new OptionVariable();
		else
			throw new IllegalArgumentException(
					"Could not match format string " + formatString);
	}
	
}
