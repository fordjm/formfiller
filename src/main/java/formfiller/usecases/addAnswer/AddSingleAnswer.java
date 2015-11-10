package formfiller.usecases.addAnswer;

import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.formComponent.FormComponent;

public class AddSingleAnswer implements AnswerAdditionStrategy {
	public void addAnswerToComponent(String componentId, Answer answer) {
		FormComponent component = Context.formComponentGateway.find(componentId);
		addAnswer(answer, component);
	}

	private void addAnswer(Answer answer, FormComponent component) {
		if (component.answer == AnswerImpl.NONE)
			component.answer = answer;
		else
			throw new IllegalStateException(
					"The component cannot add more answers.");
	}

}
