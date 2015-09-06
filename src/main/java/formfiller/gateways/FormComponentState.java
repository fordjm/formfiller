package formfiller.gateways;

import formfiller.entities.FormComponent;
import formfiller.enums.Direction;

public interface FormComponentState {

	FormComponent getCurrent();

	boolean isAtEnd();
	
	boolean isAtStart();

	void update(Direction direction);
}