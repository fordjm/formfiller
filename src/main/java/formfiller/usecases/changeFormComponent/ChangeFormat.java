package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.formComponent.FormComponent;

public abstract class ChangeFormat extends ChangeFormComponent {
	protected AnswerFormat oldFormat;
	protected AnswerFormat newFormat;

	protected void createUndoInfo(FormComponent component) {
		oldFormat = component.format;
	}
	
	protected void change(FormComponent component) {
		component.format = newFormat;
	}

	protected String makeSuccessfulMessage() {
		return "You successfully changed the format to " + newFormat.getName();
	}

	//	TODO:	Find component and revert format.
	public void undo() { }
}