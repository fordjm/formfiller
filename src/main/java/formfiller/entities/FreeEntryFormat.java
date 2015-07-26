package formfiller.entities;

public class FreeEntryFormat<T> implements ResponseConstraint<T> {

	public boolean satisfiesConstraint(T response) {
		if (response == null)
			return false;
		return true;
	}
}
