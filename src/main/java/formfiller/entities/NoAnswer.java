package formfiller.entities;

public class NoAnswer implements Answer {

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
