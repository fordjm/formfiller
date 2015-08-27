package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.utilities.AnswerMocker;
import formfiller.utilities.TestSetup;

public class AnswerStateTest {
	private AnswerState answerState;
	private int answerIndex;
	private Answer foundAnswer;
	
	// TODO:	GivenNoAnswer
	@Before
	public void setUp() {
		TestSetup.setupContext();
		ApplicationContext.answerGateway.save(AnswerMocker.makeMockNameAnswer("myName"));
		ApplicationContext.answerGateway.save(AnswerMocker.makeMockAgeAnswer(67));
		answerIndex = 0;
		answerState = new AnswerState(answerIndex);
	}
	
	@Test
	public void canGetPrevAnswer() {
		foundAnswer = ApplicationContext.answerGateway.findAnswerByIndex(answerIndex - 1);
		
		assertEquals(answerState.findAnswerByIndexOffset(-1), foundAnswer);
	}
	@Test
	public void canGetCurrentAnswer() {
		foundAnswer = ApplicationContext.answerGateway.findAnswerByIndex(answerIndex);

		assertEquals(answerState.getAnswer(), foundAnswer);
	}
	@Test
	public void canGetNextAnswer() {
		foundAnswer = ApplicationContext.answerGateway.findAnswerByIndex(answerIndex + 1);
		
		assertEquals(answerState.findAnswerByIndexOffset(1), foundAnswer);
	}

}
