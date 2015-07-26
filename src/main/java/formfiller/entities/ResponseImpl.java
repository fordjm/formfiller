package formfiller.entities;

public class ResponseImpl<T> implements Response<T> {
	int id;
	T data;
	
	public ResponseImpl(int id, T data){
		this.id = id;
		this.data = data;
	}

	public T content() {
		return data;
	}
	
	public int id(){
		return id;
	}
}
