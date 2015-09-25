package formfiller.entities.answerFormat;

import java.util.Collections;
import java.util.List;

//	TODO:	Make abstract.  Add subclasses Single- and MultiOptionVariable
public class OptionVariable extends AnswerFormat {
	public List<Object> options = Collections.emptyList();
	
	public OptionVariable() {
		super();
	}
	
	public OptionVariable(int minAnswers, int maxAnswers) {
		super(minAnswers, maxAnswers);
	}

	public boolean matchesFormat(Object content) {
		return options.contains(content);
	}
}