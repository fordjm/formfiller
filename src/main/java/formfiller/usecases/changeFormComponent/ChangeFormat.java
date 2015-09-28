package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.formComponent.FormComponent;

public abstract class ChangeFormat extends ChangeFormComponent {
	protected void change(FormComponent component) {
		component.format = getAnswerFormat();
	}

	protected abstract AnswerFormat getAnswerFormat();

	protected String makeSuccessfulMessage() {
		return "You successfully changed the format to " + getName();
	}

	//	TODO:	Remove this and give formats a name field.
	protected abstract String getName();

	//	TODO:	Implement
	public void undo() { }
}