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
		
		if (componentHasRoom(component))
			((CompositeAnswer) component.answer).add(answer);
		else
			throw new IllegalStateException(
					"The component cannot add more answers.");
	}

	private void setComponentAnswerToNewComposite(FormComponent component) {
		CompositeAnswer composite = new CompositeAnswer(component.id);
		component.answer = composite;
	}

	private boolean componentHasRoom(FormComponent component) {
		int maxAnswers = component.format.getMaxAnswers();
		CompositeAnswer castAnswer = (CompositeAnswer) component.answer;
		return castAnswer.size() < maxAnswers;
	}

}
