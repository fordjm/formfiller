package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.AnswerImpl;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.constrainable.ValueMatches;
import formfiller.entities.constrainable.ValueOverBoundary;
import formfiller.entities.constrainable.ValueUnderBoundary;
import formfiller.utilities.AnswerMocker;

public class AnswerValidatorTest {
	private static final int LOWER_INT = 3;
	private static final int HIGHER_INT = 10;
	private AnswerValidator answerValidator;
	private AnswerImpl mockAnswer;
	private Constrainable mockUnsatisfied;
	private Constrainable mockSatisfied;

	private Constrainable makeUnsatisfiedConstraint(
			Class<? extends Constrainable> clazz, Object object) {
		return makeMockConstraint(clazz, false, object);
	}
	
	private Constrainable makeMockConstraint(Class<? extends Constrainable> clazz, 
			boolean isSatisfied, Object object) {
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

	@Test
	public void nullAnswerIsInvalid() {
		assertThat(answerValidator.accepts(null), is(false));
	}
	
	@Test
	public void emptyAnswerIsInvalid() {
		mockAnswer = AnswerMocker.makeMockEmptyAnswer();
		
		assertThat(answerValidator.accepts(mockAnswer), is(false));
	}
	
	@Test
	public void givenNoConstraints_NonEmptyAnswerIsValid() {
		mockAnswer = AnswerMocker.makeMockAnswer("notEmpty");
		
		assertThat(answerValidator.accepts(mockAnswer), is(true));
	}
	
	@Test
	public void answerThatViolatesAllConstraintsIsInvalid() {
		mockAnswer = AnswerMocker.makeMockAnswer(HIGHER_INT);
		mockUnsatisfied = makeUnsatisfiedConstraint(ValueUnderBoundary.class, 
				HIGHER_INT);
		
		addConstraints(mockUnsatisfied);
		
		assertThat(answerValidator.accepts(mockAnswer), is(false));
	}
	
	@Test
	public void validAnswerThatSatisfiesAllConstraintsIsValid() {
		mockAnswer = AnswerMocker.makeMockAnswer(LOWER_INT);
		mockSatisfied = makeSatisfiedConstraint(ValueMatches.class, LOWER_INT);
		
		addConstraints(mockSatisfied);
		
		assertThat(answerValidator.accepts(mockAnswer), is(true));
	}
	
	@Test
	public void validAnswerThatViolatesOneConstraintIsInvalid() {
		mockAnswer = AnswerMocker.makeMockAnswer(LOWER_INT);
		mockUnsatisfied = makeUnsatisfiedConstraint(ValueOverBoundary.class, 
				HIGHER_INT);
		mockSatisfied = makeSatisfiedConstraint(ValueUnderBoundary.class, 
				LOWER_INT);
		
		addConstraints(mockUnsatisfied, mockSatisfied);
		
		assertThat(answerValidator.accepts(mockAnswer), is(false));
	}
	
}
