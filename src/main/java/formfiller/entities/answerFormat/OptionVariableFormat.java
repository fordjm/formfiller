package formfiller.entities.answerFormat;

import java.util.List;

public class OptionVariableFormat extends AnswerFormat {
	public List<Object> options;

	public boolean matchesContent(Object objectUnderTest) {
		return options.contains(objectUnderTest);
	}
}
