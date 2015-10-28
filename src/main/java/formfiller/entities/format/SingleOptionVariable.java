package formfiller.entities.format;

import formfiller.entities.Answer;

public class SingleOptionVariable extends OptionVariable {	
	public SingleOptionVariable() {
		super();
		minAnswers = 0;
		maxAnswers = 1;
		name = "SingleOptionVariable";
	}

	public void setMaxAnswers(int maxAnswers) {
		throw new IllegalStateException(
				"Setting maximum is not a legal operation for format "
						+ name.toLowerCase() + ".");
	}
	
	//	TODO:	Fix duplication in Unstructured.
	public boolean matchesCardinality(Answer toMatch) {
		if (isNotALegalSingleAnswer(toMatch)) 
			return false;
		
		return maxAnswers > 0 && minAnswers < 2;
	}

}
