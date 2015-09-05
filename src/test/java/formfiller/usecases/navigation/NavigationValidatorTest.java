package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;
import formfiller.entities.Question;
import formfiller.enums.Direction;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigationValidatorTest {
	private NavigationValidator validator;

	private FormComponent makeMockFormComponent(Question mockQuestion) {
		return FormComponentMocker.makeMockFormComponent(mockQuestion, null);
	}
	
	private void assertThatDirectionalMoveIsLegal(Direction direction) {
		assertThat(validator.isValidMove(direction), is(true));
	}
	
	private void assertThat_DirectionalMoveIsIllegal(Direction direction) {
		assertThat(validator.isValidMove(direction), is(false));
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
		validator = new NavigationValidator();
	}

	@Test
	public void movingBackwardIsLegal() {
		assertThatDirectionalMoveIsLegal(Direction.BACKWARD);
	}

	@Test
	public void movingNowhereIsLegal() {
		assertThatDirectionalMoveIsLegal(Direction.NONE);
	}

	@Test
	public void movingForwardIsLegal() {
		assertThatDirectionalMoveIsLegal(Direction.FORWARD);
	}
	
	public class GivenAnswerIsNotRequired {
		
		@Before
		public void givenAnswerIsNotRequired(){
			FormComponent mockComponent = makeMockFormComponent(QuestionMocker.makeMockBirthDateQuestion());
			ApplicationContext.formComponentGateway.save(mockComponent);
		}
		
		@Test
		public void movingForwardIsLegal() {
			assertThatDirectionalMoveIsLegal(Direction.FORWARD);
		}
	}
	
	public class GivenAnswerIsRequired {
		
		@Before
		public void givenAnswerIsRequired(){
			FormComponent mockComponent = makeMockFormComponent(QuestionMocker.makeMockAgeQuestion());
			ApplicationContext.formComponentGateway.save(mockComponent);
		}

		@Test
		public void movingBackwardIsLegal() {
			assertThatDirectionalMoveIsLegal(Direction.BACKWARD);
		}

		@Test
		public void movingNowhereIsLegal() {
			assertThatDirectionalMoveIsLegal(Direction.NONE);
		}

		@Test
		public void movingForwardIsIllegal() {
			assertThat_DirectionalMoveIsIllegal(Direction.FORWARD);
		}
	}
}
