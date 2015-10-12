package formfiller.utilities;

import java.lang.reflect.Type;
import java.util.Collection;

import formfiller.entities.Answer;
import formfiller.usecases.addAnswer.AnswerValidator;

public class TypeRequirementTester {
	private final SampleAnswers samples;
	private AnswerValidator validator;
	private String typeString;
	
	public TypeRequirementTester() {
		samples = new SampleAnswers();
	}
	
	public boolean requiresType(AnswerValidator validator, Type type) {
		this.validator = validator;
		this.typeString = shortenTypeName(type);
		return onlyAcceptsCorrectType();
	}

	private String shortenTypeName(Type type) {
		String typeName = type.toString();
		int beginIndex = typeName.lastIndexOf('.') + 1;
		return typeName.substring(beginIndex);
	}
	
	private boolean onlyAcceptsCorrectType() {
		Answer accepted = samples.get(typeString);
		boolean acceptsCorrectly = validator.accepts(accepted);
		boolean rejectsCorrectly = rejectsAllExcept(accepted);
		return acceptsCorrectly && rejectsCorrectly;
	}

	private boolean rejectsAllExcept(Answer accepted) {
		Collection<Answer> wrongAnswers = samples.getAllExcept(accepted);
		for (Answer answer : wrongAnswers)
			if (validator.accepts(answer))
				return false;
		return true;
	}

}
