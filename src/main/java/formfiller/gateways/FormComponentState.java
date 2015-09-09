package formfiller.gateways;

import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Direction;

public interface FormComponentState {

	FormComponent getCurrent();

	boolean isAtEnd();
	
	boolean isAtStart();

	void update(Direction direction);
}