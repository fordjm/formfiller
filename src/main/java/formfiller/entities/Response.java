package formfiller.entities;

public interface Response<T> extends Constrainable<T>{

	public int getId();
	public T getContent();
}
