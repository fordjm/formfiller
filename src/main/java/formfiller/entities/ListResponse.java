package formfiller.entities;

public class ListResponse<T> implements Response<Response<T>> {
	int id;
	Response<T> content;
	
	public ListResponse(int id, T data){
		content = new ResponseImpl<T>(id, data);
	}

	public int id() {
		return id;
	}

	public Response<T> content() {
		return content;
	}

}
