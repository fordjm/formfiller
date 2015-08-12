package formfiller.entities;

public class NullAnswer implements Answer {

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
