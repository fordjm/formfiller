package formfiller.usecases.addAnswer;

import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.CompositeAnswer;
import formfiller.entities.formComponent.FormComponent;

public class AddMultipleAnswer implements AnswerAdditionStrategy {
	public void addAnswerToComponent(String componentId, Answer answer) {
		FormComponent component = Context.formComponentGateway.find(componentId);
		addAnswer(answer, component);
	}

	private void addAnswer(Answer answer, FormComponent component) {
		if (component.answer == AnswerImpl.NONE)
			setComponentAnswerToNewComposite(component);
		
		//	if component has room for answer...
		((CompositeAnswer) component.answer).add(answer);
		
	}

	private void setComponentAnswerToNewComposite(FormComponent component) {
		CompositeAnswer composite = new CompositeAnswer(component.id);
		component.answer = composite;
	}

}
