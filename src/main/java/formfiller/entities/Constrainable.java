package formfiller.entities;

public interface Constrainable {
	boolean isSatisfied();

	Constrainable constrain(Object value);
}