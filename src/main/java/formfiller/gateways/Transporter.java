package formfiller.gateways;

import formfiller.entities.FormComponent;
import formfiller.enums.Direction;

public interface Transporter {	
	FormComponent getCurrent();
	
	void move(Direction direction);
}