package formfiller.utilities.valueMatcher;

public abstract class ValueMatcher<T> {
	public boolean matches(T... values) {
		T first = values[0];
		for(T other : values)
			if(notMatching(first, other))
				return false;
		return true;
	}

	protected abstract boolean notMatching(T first, T second);
}
