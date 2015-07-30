package formfiller.entities;

public class ResponseImpl<T> extends AbstractResponse<T> {
	
	public ResponseImpl(int id, T content){
		super(id, content);
	}

	@Override
	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}
}
