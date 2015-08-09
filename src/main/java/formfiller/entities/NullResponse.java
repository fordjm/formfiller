package formfiller.entities;

public class NullResponse implements Response {

	public int getId() {
		return -1;
	}

	public String getContent() {
		return "";
	}

	public boolean satisfiesConstraint() {
		return false;
	}
}
