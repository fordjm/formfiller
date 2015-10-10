package formfiller.usecases.askQuestion;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.QuestionImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Unstructured;
import formfiller.enums.QuestionAsked;
import formfiller.usecases.askQuestion.AskQuestionValidator;
import formfiller.utilities.AnswerMocker;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class AskQuestionValidatorTest {
	private FormComponent mockFormComponent;

	private FormComponent makeMockFormComponent(boolean requiresAnswer, 
			QuestionImpl mockQuestion, Answer mockAnswer) {
		return FormComponentMocker.makeMockFormComponent(
				requiresAnswer, mockQuestion, mockAnswer, new Unstructured());
	}
	
	private void assertThat_AskQuestionIsLegal(QuestionAsked which) {
		assertThat(AskQuestionValidator.isValidQuestion(which), is(true));
	}
	
	private void assertThat_DirectionalMoveIsIllegal(QuestionAsked which) {
		assertThat(AskQuestionValidator.isValidQuestion(which), is(false));
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
	}

	@Test
	public void movingBackwardIsLegal() {
		assertThat_AskQuestionIsLegal(QuestionAsked.PREVIOUS);
	}

	@Test
	public void movingNowhereIsLegal() {
		assertThat_AskQuestionIsLegal(QuestionAsked.CURRENT);
	}

	@Test
	public void movingForwardIsLegal() {
		assertThat_AskQuestionIsLegal(QuestionAsked.NEXT);
	}
	
	public class GivenAnswerIsNotRequired {
		
		@Before
		public void givenAnswerIsNotRequired(){
			mockFormComponent = makeMockFormComponent(false, 
					QuestionMocker.makeMockBirthDateQuestion(), AnswerImpl.NONE);
			Context.formComponentGateway.save(mockFormComponent);
		}
		
		@Test
		public void movingForwardIsLegal() {
			assertThat_AskQuestionIsLegal(QuestionAsked.NEXT);
		}
	}
	
	public class GivenAnswerIsRequired {
		private final int RETIREMENT_AGE = 65;
		
		@Before
		public void givenAnswerIsRequired(){
			mockFormComponent = makeMockFormComponent(true, 
					QuestionMocker.makeMockAgeQuestion(), AnswerImpl.NONE);
			Context.formComponentGateway.save(mockFormComponent);
		}

		@Test
		public void movingBackwardIsLegal() {
			assertThat_AskQuestionIsLegal(QuestionAsked.PREVIOUS);
		}

		@Test
		public void movingNowhereIsLegal() {
			assertThat_AskQuestionIsLegal(QuestionAsked.CURRENT);
		}

		@Test
		public void movingForwardIsIllegal() {
			assertThat_DirectionalMoveIsIllegal(QuestionAsked.NEXT);
		}
		
		@Test
		public void whenAnswerIsPresent_movingForwardIsLegal() {
			mockFormComponent.answer = AnswerMocker.makeMockAnswer("questionId", RETIREMENT_AGE);

			assertThat_AskQuestionIsLegal(QuestionAsked.NEXT);
		}
	}
}
