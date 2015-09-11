package formfiller.gateways;

import formfiller.enums.WhichQuestion;

public interface Transporter {	
	void moveToElement(WhichQuestion direction);
}