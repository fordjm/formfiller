package formfiller.entities.format;

import formfiller.entities.Answer;

public class Unstructured extends Format {
	public Unstructured() {
		super();
		minAnswers = 0;
		maxAnswers = 1;
		name = "Unstructured";
	}

	public boolean matchesFormat(Object content) {
		return content != null;
	}
	
	//	TODO:	Fix duplication in SingleOptionVariable.
	public boolean matchesCardinality(Answer toMatch) {
		if (isNotALegalSingleAnswer(toMatch)) 
			return false;
		
		return maxAnswers > 0 && minAnswers < 2;
	}

	public void setMaxAnswers(int maxAnswers) {
		throw new IllegalStateException(
				"Setting maximum is not a legal operation for format "
						+ name.toLowerCase() + ".");
	}
	
}
