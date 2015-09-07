package formfiller.entities;

public interface Constrainable {
	Constrainable constrain(Object value);
	
	boolean isSatisfied();
}