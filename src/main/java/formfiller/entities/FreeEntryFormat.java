package formfiller.entities;

public class FreeEntryFormat<T> extends ResponseFormat<T> {

	public boolean satisfiesConstraint(T response) {
		if (response == null)
			return false;
		return true;
	}
}
