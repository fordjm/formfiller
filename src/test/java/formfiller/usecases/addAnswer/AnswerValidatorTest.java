package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.utilities.AnswerMocker;

public class AnswerValidatorTest {
	private AnswerValidator answerValidator;
	private Answer mockAnswer;

	private void setAnswerField(Answer mockAnswer) {
		this.mockAnswer = mockAnswer;
	}
	
	private Answer makeMockAnswer(String id, Object content){
		return AnswerMocker.makeMockAnswer(id, content);
	}

	private Constrainable makeUnsatisfiedConstraint(Object object) {
		return makeMockConstraint(false, object);
	}

	private Constrainable makeSatisfiedConstraint(Object object) {
		return makeMockConstraint(true, object);
	}
	
	private Constrainable makeMockConstraint(boolean isSatisfied, Object object){
		Constrainable result = Mockito.mock(Constrainable.class);
		Mockito.when(result.isSatisfiedBy(object)).thenReturn(isSatisfied);
		return result;
	}

	private void addConstraints(Constrainable... mockConstraints) {
		for (Constrainable constraint : mockConstraints)
			answerValidator.addConstraint(constraint);
	}

	@Before
	public void setUp() {
		answerValidator = new AnswerValidator();
	}

	@Test
	public void nullAnswerIsInvalid() {
		assertThat(answerValidator.isValid(null), is(false));
	}
	
	@Test
	public void newAnswerIsInvalid() {
		assertThat(answerValidator.isValid(makeMockAnswer("", "")), is(false));
	}
	
	@Test
	public void anyAnswerWithNoConstraintsIsValid() {
		setAnswerField(makeMockAnswer("questionId", "Banana"));
		
		assertThat(answerValidator.isValid(mockAnswer), is(true));
	}
	
	@Test
	public void answerThatViolatesAllConstraintsIsInvalid() {
		setAnswerField(makeMockAnswer("questionId", 10));
		addConstraints(makeUnsatisfiedConstraint(10));
		
		assertThat(answerValidator.isValid(mockAnswer), is(false));
	}
	
	@Test
	public void validAnswerThatSatisfiesAllConstraintsIsValid() {
		setAnswerField(makeMockAnswer("questionId", 3));
		addConstraints(makeSatisfiedConstraint(3));
		
		assertThat(answerValidator.isValid(mockAnswer), is(true));
	}
	
	@Test
	public void validAnswerThatViolatesOneConstraintIsInvalid() {
		setAnswerField(makeMockAnswer("questionId", 3));
		addConstraints(makeUnsatisfiedConstraint(10), makeSatisfiedConstraint(3));
		
		assertThat(answerValidator.isValid(mockAnswer), is(false));
	}
	
	//	Added 2015-09-21
	
	@Test
	public void testAnswerValidator() {
		Collection<Constrainable> constraints = new ArrayList<Constrainable>();
		constraints.add(new AnswerType(String.class));
		AnswerValidator validator = new AnswerValidator(constraints);
		Answer validAnswer = makeValidAnswer();
		
		assertThat(validator.isValid(new Answer()), is(false));
		assertThat(validator.isValid(validAnswer), is(true));
	}
	
	private Answer makeValidAnswer() {
		Answer result = makeAnswer("answerContent");
		return result;
	}

	private Answer makeAnswer(Object questionContent) {
		Answer result = new Answer();
		result.questionId = "questionId";
		result.content = questionContent;
		return result;
	}
}
