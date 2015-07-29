package formfiller.entities;

import java.util.ArrayList;
import java.util.List;

public class ListResponse<T> extends AbstractResponse<List<Response<T>>> {
	
	public ListResponse(int id, final T content){
		super(id, new ArrayList<Response<T>>(){{ 
			add(new ResponseImpl<T>(0, content)); }});
	}

	public List<Response<T>> getContent(){
		return content;
	}

	public int getSize(){
		return content.size();
	}
	
	public boolean contains(Response<T> response){
		return content.contains(response);
	}

	public boolean satisfiesConstraint(){
		return listResponseSatisfiesConstraints() && containedElementsSatisfyConstraints();
	}
	
	private boolean listResponseSatisfiesConstraints(){
		return id >= 0 && content.size() > 0;
	}
	
	private boolean containedElementsSatisfyConstraints(){
		for (Response<T> response : content)
			if (!response.satisfiesConstraint())
				return false;
		return true;
	}

	public void addResponse(Response<T> response){
		super.content.add(response);
	}

	public void removeResponse(int responseIndex){
		super.content.remove(responseIndex);
	}
}
