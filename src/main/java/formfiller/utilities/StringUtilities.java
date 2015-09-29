package formfiller.utilities;

public class StringUtilities {
	private static final String QUOTATION_MARK = "\"";
	
	public static String makeQuotedString(String input) {
		return QUOTATION_MARK + input + QUOTATION_MARK;
	}
	
	public static String makeSpacedString(String... strings) {
		String result = "";
		for (String string : strings)
			result += string + " ";		
		return result;
	}

	public static boolean isStringNullOrEmpty(String input) {
		return input == null || input == "";
	}
}