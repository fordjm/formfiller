package formfiller.entities;

public class NullPrompt implements IPrompt {

	public String content() {
		return "";
	}

	public void setFormat(ResponseConstraint f) {}

	public ResponseConstraint format() {
		return new FreeEntryFormat();
	}

	public String id() {
		return "";
	}

}
