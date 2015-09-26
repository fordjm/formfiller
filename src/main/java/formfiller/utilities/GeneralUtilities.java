package formfiller.utilities;

public class GeneralUtilities {
	private static final String QUOTATION_MARK = "\"";
	
	public static String makeQuotedString(String input) {
		return QUOTATION_MARK + input + QUOTATION_MARK;
	}

	public static boolean isStringNullOrEmpty(String input) {
		return input == null || input == "";
	}
}
