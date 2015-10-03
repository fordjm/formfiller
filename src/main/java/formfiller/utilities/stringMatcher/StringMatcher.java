package formfiller.utilities.stringMatcher;

public abstract class StringMatcher {
	public boolean matches(String... strings) {
		String first = strings[0];
		for(String other : strings)
			if(notMatching(first, other))
				return false;
		return true;
	}
	
	protected abstract boolean notMatching(String first, String second);

}