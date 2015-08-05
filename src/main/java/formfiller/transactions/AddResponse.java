package formfiller.transactions;

import java.util.Collection;
import java.util.Map;

import formfiller.entities.Constraint;
import formfiller.entities.Response;
import formfiller.entities.ResponseImpl;
import formfiller.enums.ContentConstraint;
import formfiller.persistence.FormWidget;

public class AddResponse<T> implements Transaction {
	Response<T> response;
	public AddResponse(T content) {
		int id = FormWidget.getNextResponseId();
		this.response = new ResponseImpl<T>(id, content);
	}
	public void execute() {
		wrapResponse();
		checkTransactionIsLegal();
		FormWidget.addResponse(response);
	}
	// TODO:  Fix illegal casting.
	private void wrapResponse(){
		Map<ContentConstraint, Constraint<?>> constraints = FormWidget.getConstraints();
		Collection<Constraint<?>> constraintValues = constraints.values();
		for (Constraint<?> constraint : constraintValues){
			Constraint<T> castConstraint = (Constraint<T>) constraint;
			castConstraint.wrap(response);
			response = castConstraint;
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
