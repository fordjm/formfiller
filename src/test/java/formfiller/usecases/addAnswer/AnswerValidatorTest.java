package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.Constrainable;
import formfiller.entities.ValueMaximum;

public class AnswerValidatorTest {
	private AnswerValidator answerValidator;
	
	private Answer makeMockAnswer(int id, Object content, Collection<Constrainable> constraints){
		Answer result = Mockito.mock(Answer.class);
		result.id = id;
		result.content = content;
		result.constraints = constraints;
		return result;
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
		assertThat(answerValidator.isValid(new Answer()), is(false));
	}
	
	@Test
	public void validAnswerWithNoConstraintsIsValid() {
		Answer validAnswer = makeMockAnswer(0, "Banana", new ArrayList<Constrainable>());
		
		assertThat(answerValidator.isValid(validAnswer), is(true));
	}
	
	//	TODO:	Use mock constraints.
	@Test
	public void answerThatViolatesConstraintsIsInvalid() {
		Collection<Constrainable> constraints = new ArrayList<Constrainable>();
		constraints.add(new ValueMaximum(5));
		Answer answer = makeMockAnswer(0, 10, constraints);
		
		assertThat(answerValidator.isValid(answer), is(false));
	}
	
	@Test
	public void validAnswerThatSatisfiesConstraintsIsValid() {
		Collection<Constrainable> constraints = new ArrayList<Constrainable>();
		constraints.add(new ValueMaximum(5));
		Answer answer = makeMockAnswer(0, 3, constraints);
		
		assertThat(answerValidator.isValid(answer), is(true));
	}
}
