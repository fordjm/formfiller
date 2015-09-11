package formfiller.gateways;

import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.WhichQuestion;

public interface FormComponentState {
	FormComponent getCurrent();

	boolean isAtEnd();
	
	boolean isAtStart();

	void update(WhichQuestion which);
}