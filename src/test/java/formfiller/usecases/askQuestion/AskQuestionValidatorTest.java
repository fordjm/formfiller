package formfiller.usecases.askQuestion;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.WhichQuestion;
import formfiller.usecases.askQuestion.AskQuestionValidator;
import formfiller.utilities.AnswerMocker;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class AskQuestionValidatorTest {
	private FormComponent mockFormComponent;

	private FormComponent makeMockFormComponent(boolean requiresAnswer, 
			Question mockQuestion, Answer mockAnswer) {
		return FormComponentMocker.makeMockFormComponent(
				requiresAnswer, mockQuestion, mockAnswer, AnswerFormat.UNSTRUCTURED);
	}
	
	private void assertThat_AskQuestionIsLegal(WhichQuestion which) {
		assertThat(AskQuestionValidator.isValidQuestion(which), is(true));
	}
	
	private void assertThat_DirectionalMoveIsIllegal(WhichQuestion which) {
		assertThat(AskQuestionValidator.isValidQuestion(which), is(false));
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
	}

	@Test
	public void movingBackwardIsLegal() {
		assertThat_AskQuestionIsLegal(WhichQuestion.PREV);
	}

	@Test
	public void movingNowhereIsLegal() {
		assertThat_AskQuestionIsLegal(WhichQuestion.CURRENT);
	}

	@Test
	public void movingForwardIsLegal() {
		assertThat_AskQuestionIsLegal(WhichQuestion.NEXT);
	}
	
	public class GivenAnswerIsNotRequired {
		
		@Before
		public void givenAnswerIsNotRequired(){
			mockFormComponent = makeMockFormComponent(false, 
					QuestionMocker.makeMockBirthDateQuestion(), Answer.NONE);
			FormFillerContext.formComponentGateway.save(mockFormComponent);
		}
		
		@Test
		public void movingForwardIsLegal() {
			assertThat_AskQuestionIsLegal(WhichQuestion.NEXT);
		}
	}
	
	public class GivenAnswerIsRequired {
		private final int RETIREMENT_AGE = 65;
		
		@Before
		public void givenAnswerIsRequired(){
			mockFormComponent = makeMockFormComponent(true, 
					QuestionMocker.makeMockAgeQuestion(), Answer.NONE);
			FormFillerContext.formComponentGateway.save(mockFormComponent);
		}

		@Test
		public void movingBackwardIsLegal() {
			assertThat_AskQuestionIsLegal(WhichQuestion.PREV);
		}

		@Test
		public void movingNowhereIsLegal() {
			assertThat_AskQuestionIsLegal(WhichQuestion.CURRENT);
		}

		@Test
		public void movingForwardIsIllegal() {
			assertThat_DirectionalMoveIsIllegal(WhichQuestion.NEXT);
		}
		
		@Test
		public void whenAnswerIsPresent_movingForwardIsLegal() {
			mockFormComponent.answer = AnswerMocker.makeMockAnswer(0, RETIREMENT_AGE);

			assertThat_AskQuestionIsLegal(WhichQuestion.NEXT);
		}
	}
}
