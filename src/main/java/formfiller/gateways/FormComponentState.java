package formfiller.gateways;

import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.QuestionAsked;

public interface FormComponentState {
	FormComponent getCurrent();

	boolean isAtEnd();
	
	boolean isAtStart();

	void update(QuestionAsked which);
}