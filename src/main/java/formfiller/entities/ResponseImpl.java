package formfiller.entities;

public class ResponseImpl<T> extends AbstractResponse<T> implements Response<T> {
	int id;
	
	public ResponseImpl(int id, T data){
		super(data);
		this.id = id;
	}
	
	public int id(){
		return id;
	}

	@Override
	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}
}
