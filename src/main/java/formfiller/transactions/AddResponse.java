package formfiller.transactions;

import java.util.Collection;
import java.util.Map;

import formfiller.entities.Constraint;
import formfiller.entities.Response;
import formfiller.entities.ResponseImpl;
import formfiller.enums.ContentConstraint;
import formfiller.persistence.FormWidget;

public class AddResponse implements Transaction {
	Response response;
	public <T> AddResponse(T content) {
		int id = FormWidget.getNextResponseId();
		this.response = new ResponseImpl<T>(id, content);
	}
	public void execute() {
		wrapResponse();
		checkTransactionIsLegal();
		FormWidget.addResponse(response);
	}

	private void wrapResponse(){
		Map<ContentConstraint, Constraint> constraints = FormWidget.getConstraints();
		Collection<Constraint> constraintValues = constraints.values();
		for (Constraint constraint : constraintValues){
			constraint.wrap(response);
			response = constraint;
		}
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
