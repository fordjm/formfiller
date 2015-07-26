package formfiller.transactions;

import formfiller.entities.Response;
import formfiller.entities.ResponseImpl;
import formfiller.persistence.FormWidget;

public class AddResponse<T> implements Transaction {
	Response<T> response;

	public AddResponse(T content) {
		this.response = new ResponseImpl<T>(0, content);
	}

	public void execute() {		
		FormWidget.setResponse(response);
	}
}
