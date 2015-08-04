package formfiller.transactions;

import formfiller.entities.Response;
import formfiller.entities.ResponseImpl;
import formfiller.persistence.FormWidget;

public class AddResponse<T> implements Transaction {
	Response<T> response;
	public AddResponse(T content) {
		int id = FormWidget.getNextResponseId();
		this.response = new ResponseImpl<T>(id, content);
	}
	public void execute() {
		checkTransactionIsLegal();
		FormWidget.addResponse(response);
	}
	private void checkTransactionIsLegal() throws IllegalStateException, IllegalArgumentException{
		if (!FormWidget.hasPrompt()) throw new IllegalStateException(
				"There is no question to answer!");
		if (response.getId() == -1) throw new IllegalStateException(
				"This question only allows one response!");
		if (!responseSatisfiesConstraints()) throw new IllegalArgumentException(
				"Response is not valid!");
	}
	private boolean responseSatisfiesConstraints(){
		return response.satisfiesConstraint();
	}
}
