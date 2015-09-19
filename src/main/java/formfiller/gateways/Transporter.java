package formfiller.gateways;

import formfiller.enums.QuestionAsked;

public interface Transporter {	
	void moveToElement(QuestionAsked which);
}