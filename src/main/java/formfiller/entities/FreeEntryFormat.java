package formfiller.entities;

public class FreeEntryFormat<T> extends ResponseFormat<T> {

	public FreeEntryFormat(AbstractResponse<T> component) {
		super(component);
	}

	@Override
	public boolean satisfiesConstraint() {
		return super.getContent() != null && 
				response.satisfiesConstraint();
	}
}
