package formfiller.entities.answerFormat;

import java.util.List;

public class OptionVariableFormat extends AnswerFormat {
	public List<Object> options;
	
	public OptionVariableFormat(int minAnswers, int maxAnswers) {
		super(minAnswers, maxAnswers);
	}

	public boolean matchesFormat(Object objectUnderTest) {
		return options.contains(objectUnderTest);
	}
}
