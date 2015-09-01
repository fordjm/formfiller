package formfiller.gateways;

import formfiller.entities.FormComponent;
import formfiller.gateways.InMemoryTransporter.Direction;

public interface Transporter {	
	FormComponent getCurrent();
	
	NavigationValidator getNavigationValidator();
	
	void move(Direction direction);
}