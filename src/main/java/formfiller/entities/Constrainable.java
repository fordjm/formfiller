package formfiller.entities;

public interface Constrainable {
	boolean isSatisfiedBy(Object objectUnderTest);
}