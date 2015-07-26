package formfiller.entities;

public interface ResponseConstraint<T> {

	boolean satisfiesConstraint(T response);
}