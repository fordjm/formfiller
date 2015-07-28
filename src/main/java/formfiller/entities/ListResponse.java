package formfiller.entities;

import java.util.ArrayList;
import java.util.List;

public class ListResponse<T> extends AbstractResponse<List<Response<T>>> {
	
	public ListResponse(int id, final T content){
		super(id, new ArrayList<Response<T>>(){{ 
			add(new ResponseImpl<T>(0, content)); }});
	}

	public int getId() {
		return id;
	}

	public List<Response<T>> getContent(){
		return content;
	}

	public boolean satisfiesConstraint(){
		return content.size() > 0 && allElementsSatisfyConstraints();
	}
	
	private boolean allElementsSatisfyConstraints(){
		for (Response<T> response : content){
			if (!response.satisfiesConstraint())
				return false;
		}
		return true;
	}

	public void addResponse(Response<T> response){
		super.content.add(response);
	}
}
