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
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigationValidatorTest {

	private FormComponent makeMockFormComponent(boolean requiresAnswer, 
			Question mockQuestion) {
		return FormComponentMocker.makeMockFormComponent(
				requiresAnswer, mockQuestion, Answer.NONE);
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
			FormComponent mockComponent = makeMockFormComponent(false, 
					QuestionMocker.makeMockBirthDateQuestion());
			FormFillerContext.formComponentGateway.save(mockComponent);
		}
		
		@Test
		public void movingForwardIsLegal() {
			assertThat_DirectionalMoveIsLegal(Direction.FORWARD);
		}
	}
	
	public class GivenAnswerIsRequired {
		
		@Before
		public void givenAnswerIsRequired(){
			FormComponent mockComponent = makeMockFormComponent(true, 
					QuestionMocker.makeMockAgeQuestion());
			FormFillerContext.formComponentGateway.save(mockComponent);
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
	}
}
