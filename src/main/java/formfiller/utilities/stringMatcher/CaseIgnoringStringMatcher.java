package formfiller.utilities.stringMatcher;

//	TODO:	Add JaccardDistanceStringMatcher (notMatching ==  score < threshold)
public class CaseIgnoringStringMatcher extends StringMatcher {
	protected boolean notMatching(String first, String second) {
		boolean matches = first.equalsIgnoreCase(second);
		return !matches;
	}
	
}
