package formfiller.usecases;

public class PresentableResponse<T> {
	public Integer id;
	public T content;
	public PresentableResponse(int id, T content){
		this.id = id;
		this.content = content;
	}
}