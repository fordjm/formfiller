package formfiller.deprecated;

import java.util.Collection;
import java.util.Map;

import formfiller.entities.Constraint;
import formfiller.enums.ContentConstraint;

public class AddAnswer implements Transaction {
	ConstrainableAnswer answer;
	
	public AddAnswer(Object content) {
		int id = FormWidget.getNextAnswerId();
		this.answer = new ConstrainableAnswer(id, content);
	}
	
	public void execute() {
		wrapResponse();
		checkTransactionIsLegal();
		FormWidget.addAnswer(answer);
	}

	private void wrapResponse(){
		Map<ContentConstraint, Constraint> constraints = FormWidget.getConstraints();
		Collection<Constraint> constraintValues = constraints.values();
		for (Constraint constraint : constraintValues){
			constraint.wrap(answer);
			answer = constraint;
		}
	}
	
	private void checkTransactionIsLegal() throws IllegalStateException, IllegalArgumentException{
		if (!FormWidget.hasQuestion()) throw new IllegalStateException(
				"There is no question to answer!");
		if (answer.getId() == -1) throw new IllegalStateException(
				"This question only allows one response!");
		if (!responseSatisfiesConstraints()) throw new IllegalArgumentException(
				"Response is not valid!");
	}
	
	private boolean responseSatisfiesConstraints(){
		return answer.isSatisfied();
	}
}
