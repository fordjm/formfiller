package formfiller.entities;

public interface Response<T> {

	public int id();
	public T content();
}
