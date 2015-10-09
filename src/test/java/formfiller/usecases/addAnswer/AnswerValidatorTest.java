package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.constrainable.ValueMatches;
import formfiller.entities.constrainable.ValueOverBoundary;
import formfiller.entities.constrainable.ValueUnderBoundary;

public class AnswerValidatorTest {
	private AnswerValidator answerValidator;
	private Answer mockAnswer;

	private Answer makeEmptyMockAnswer(){
		return Mockito.mock(Answer.class);
	}
	
	private Answer makeMockAnswer(Object content){
		Answer result = makeEmptyMockAnswer();
		result.questionId = "questionId";
		result.content = content;
		return result;
	}

	private Constrainable makeUnsatisfiedConstraint(
			Class<? extends Constrainable> clazz, Object object) {
		return makeMockConstraint(clazz, false, object);
	}
	
	private Constrainable makeMockConstraint(
			Class<? extends Constrainable> clazz, boolean isSatisfied, Object object) {
		Constrainable result = Mockito.mock(clazz);
		Mockito.when(result.isSatisfiedBy(object)).thenReturn(isSatisfied);
		return result;
	}

	private Constrainable makeSatisfiedConstraint(
			Class<? extends Constrainable> clazz, Object object) {
		return makeMockConstraint(clazz, true, object);
	}

	private void addConstraints(Constrainable... mockConstraints) {
		for (Constrainable constraint : mockConstraints)
			answerValidator.addConstraint(constraint);
	}

	@Before
	public void setUp() {
		answerValidator = new AnswerValidator();
	}

	//	TODO:	Belongs in ConstraintsTest class.
	@Test
	public void canAddOnlyOneAnswerType() {
		AnswerType type1 = new AnswerType(int.class);
		AnswerType type2 = new AnswerType(double.class);
		Answer intAnswer = makeMockAnswer(10);
		Answer doubleAnswer = makeMockAnswer(10.0);
		
		answerValidator.addConstraint(type1);
		answerValidator.addConstraint(type2);

		assertThat(answerValidator.isValid(intAnswer), is(false));
		assertThat(answerValidator.isValid(doubleAnswer), is(true));
	}

	@Test
	public void nullAnswerIsInvalid() {
		assertThat(answerValidator.isValid(null), is(false));
	}
	
	@Test
	public void emptyAnswerIsInvalid() {
		assertThat(answerValidator.isValid(makeEmptyMockAnswer()), is(false));
	}
	
	@Test
	public void nonEmptyAnswerWithNoConstraintsIsValid() {
		this.mockAnswer = makeMockAnswer("Banana");
		
		assertThat(answerValidator.isValid(mockAnswer), is(true));
	}
	
	@Test
	public void answerThatViolatesAllConstraintsIsInvalid() {
		this.mockAnswer = makeMockAnswer(10);
		addConstraints(makeUnsatisfiedConstraint(ValueUnderBoundary.class, 10));
		
		assertThat(answerValidator.isValid(mockAnswer), is(false));
	}
	
	@Test
	public void validAnswerThatSatisfiesAllConstraintsIsValid() {
		this.mockAnswer = makeMockAnswer(3);
		addConstraints(makeSatisfiedConstraint(ValueMatches.class, 3));
		
		assertThat(answerValidator.isValid(mockAnswer), is(true));
	}
	
	@Test
	public void validAnswerThatViolatesOneConstraintIsInvalid() {
		this.mockAnswer = makeMockAnswer(3);
		addConstraints(makeUnsatisfiedConstraint(ValueOverBoundary.class, 10), 
				makeSatisfiedConstraint(ValueUnderBoundary.class, 3));
		
		assertThat(answerValidator.isValid(mockAnswer), is(false));
	}
	
}
