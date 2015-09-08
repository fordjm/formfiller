package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.Constrainable;
import formfiller.utilities.AnswerMocker;

public class AnswerValidatorTest {
	private AnswerValidator answerValidator;
	private Answer answer;

	private void setAnswerField(Answer mockAnswer) {
		answer = mockAnswer;
	}
	
	private Answer makeMockAnswer(int id, Object content){
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
		assertThat(answerValidator.isValid(makeMockAnswer(-1, "")), is(false));
	}
	
	@Test
	public void validAnswerWithNoConstraintsIsValid() {
		setAnswerField(makeMockAnswer(0, "Banana"));
		
		assertThat(answerValidator.isValid(answer), is(true));
	}
	
	@Test
	public void answerThatViolatesAllConstraintsIsInvalid() {
		setAnswerField(makeMockAnswer(0, 10));
		addConstraints(makeUnsatisfiedConstraint(10));
		
		assertThat(answerValidator.isValid(answer), is(false));
	}
	
	@Test
	public void validAnswerThatSatisfiesAllConstraintsIsValid() {
		setAnswerField(makeMockAnswer(0, 3));
		addConstraints(makeSatisfiedConstraint(3));
		
		assertThat(answerValidator.isValid(answer), is(true));
	}
	
	@Test
	public void validAnswerThatViolatesOneConstraintIsInvalid() {
		setAnswerField(makeMockAnswer(0, 3));
		addConstraints(makeUnsatisfiedConstraint(10), makeSatisfiedConstraint(3));
		
		assertThat(answerValidator.isValid(answer), is(false));
	}
}
