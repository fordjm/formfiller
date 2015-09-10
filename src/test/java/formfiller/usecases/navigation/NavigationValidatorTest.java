package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Direction;
import formfiller.utilities.AnswerMocker;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigationValidatorTest {
	private FormComponent mockFormComponent;

	private FormComponent makeMockFormComponent(boolean requiresAnswer, 
			Question mockQuestion, Answer mockAnswer) {
		return FormComponentMocker.makeMockFormComponent(
				requiresAnswer, mockQuestion, mockAnswer);
	}
	
	private void assertThat_DirectionalMoveIsLegal(Direction direction) {
		assertThat(NavigationValidator.isValidDirectionalMove(direction), is(true));
	}
	
	private void assertThat_DirectionalMoveIsIllegal(Direction direction) {
		assertThat(NavigationValidator.isValidDirectionalMove(direction), is(false));
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
	}

	@Test
	public void movingBackwardIsLegal() {
		assertThat_DirectionalMoveIsLegal(Direction.BACKWARD);
	}

	@Test
	public void movingNowhereIsLegal() {
		assertThat_DirectionalMoveIsLegal(Direction.NONE);
	}

	@Test
	public void movingForwardIsLegal() {
		assertThat_DirectionalMoveIsLegal(Direction.FORWARD);
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
			assertThat_DirectionalMoveIsLegal(Direction.FORWARD);
		}
	}
	
	public class GivenAnswerIsRequired {		
		@Before
		public void givenAnswerIsRequired(){
			mockFormComponent = makeMockFormComponent(true, 
					QuestionMocker.makeMockAgeQuestion(), Answer.NONE);
			FormFillerContext.formComponentGateway.save(mockFormComponent);
		}

		@Test
		public void movingBackwardIsLegal() {
			assertThat_DirectionalMoveIsLegal(Direction.BACKWARD);
		}

		@Test
		public void movingNowhereIsLegal() {
			assertThat_DirectionalMoveIsLegal(Direction.NONE);
		}

		@Test
		public void movingForwardIsIllegal() {
			assertThat_DirectionalMoveIsIllegal(Direction.FORWARD);
		}
		
		@Test
		public void whenAnswerIsPresent_movingForwardIsLegal() {
			mockFormComponent.answer = AnswerMocker.makeMockAnswer(0, 65);

			assertThat_DirectionalMoveIsLegal(Direction.FORWARD);
		}
	}
}
