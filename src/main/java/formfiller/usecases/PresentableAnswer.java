package formfiller.usecases;

public class PresentableAnswer<T> {
	public Integer id;
	public T content;
	
	public PresentableAnswer(int id, T content){
		this.id = id;
		this.content = content;
	}
	
}