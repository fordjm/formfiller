package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.enums.Direction;
import formfiller.request.models.NavigationRequest;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase navigationUseCase;
	private NavigationRequest mockRequest;
	private FormComponent expectedFormComponent;

	private void setupNavigationTest(Direction direction, 
			FormComponent formComponent) {
		setMockRequestDirection(direction);
		setExpectedFormComponent(formComponent);
	}
	
	private void setMockRequestDirection(Direction direction){
		mockRequest.direction = direction;
	}
	
	private void setExpectedFormComponent(FormComponent expected) {
		expectedFormComponent = expected;
	}

	private void undoNavigationUseCase() {
		navigationUseCase.undo();
	}

	private void executeNavigationRequest(NavigationRequest request) {
		navigationUseCase.execute(request);
	}

	private void assertThat_CurrentAndExpectedComponents_AreTheSame() {
		assertThat(getCurrentFormComponent(), is(expectedFormComponent));
	}
	
	private FormComponent getCurrentFormComponent() {
		return FormFillerContext.formComponentState.getCurrent();
	}

	private void assertThat_ExecutedUseCase_IsMostRecent() {
		assertEquals(navigationUseCase, checkMostRecent());
	}

	private UndoableUseCase checkMostRecent() {
		return FormFillerContext.executedUseCases.getMostRecent();
	}

	private void assertThat_ExecutedUseCase_IsNotMostRecent() {
		assertNotEquals(navigationUseCase, checkMostRecent());
	}	
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		navigationUseCase = new NavigationUseCase();
		mockRequest = mock(NavigationRequest.class);		
	}
	
	@Test
	public void isAnUndoableUseCase() {
		assertThat(navigationUseCase, is(instanceOf(UndoableUseCase.class)));
	}
	
	//	TODO:	Make the Undo use case.
	@Test
	public void undoBeforeExecutionDoesNotChangeCurrentFormComponent() {
		setExpectedFormComponent(getCurrentFormComponent());
		
		undoNavigationUseCase();
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
	}
	
	@Test
	public void executingNullDoesNotChangeCurrentFormComponent() {
		setExpectedFormComponent(getCurrentFormComponent());
		
		executeNavigationRequest(null);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsNotMostRecent();
	}
	
	@Test
	public void gettingPrevQuestionGetsStartPrompt(){
		setupNavigationTest(Direction.BACKWARD, NullFormComponents.START);
		
		executeNavigationRequest(mockRequest);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsMostRecent();
	}
	
	@Test
	public void gettingCurrentQuestionGetsStartPrompt(){
		setupNavigationTest(Direction.NONE, getCurrentFormComponent());
		
		executeNavigationRequest(mockRequest);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsMostRecent();
	}
	
	@Test
	public void gettingNextQuestionGetsEndPrompt(){
		setupNavigationTest(Direction.FORWARD, getCurrentFormComponent());
		
		executeNavigationRequest(mockRequest);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsMostRecent();
	}	
	
	public class GivenTwoFormComponents {
		FormComponent mockNameFormComponent;
		FormComponent mockAgeFormComponent;
		Question mockQuestion;

		private FormComponent makeMockFormComponent(String id, String content, 
				boolean isRequired) {
			Question mockQuestion = makeMockQuestion(id, content);
			FormComponent result = makeMockFormComponent(isRequired, mockQuestion);
			return result;
		}

		private Question makeMockQuestion(String id, String content) {
			return QuestionMocker.makeMockQuestion(id, content);
		}
		
		private FormComponent makeMockFormComponent(boolean requiresAnswer, 
				Question question){
			return FormComponentMocker.makeMockFormComponent(requiresAnswer, 
					question, Answer.NONE);
		}

		private void saveFormComponents(FormComponent... formComponents) {
			for (FormComponent formComponent : formComponents)
				FormFillerContext.formComponentGateway.save(formComponent);
		}

		private void executeAndUndoNavigationRequest(NavigationRequest navigationRequest) {
			executeNavigationRequest(navigationRequest);
			undoNavigationUseCase();
		}	
		
		@Before
		public void givenTwoFormComponents(){
			mockNameFormComponent = makeMockFormComponent("name", 
					"What is your name?", false);
			mockAgeFormComponent = makeMockFormComponent("age", 
					"What is your age?", true);
			saveFormComponents(mockNameFormComponent, mockAgeFormComponent);
		}
		
		@Test
		public void gettingCurrent_ReturnsMockNameFormComponent(){
			FormComponent currentComponent = getCurrentFormComponent();
			
			assertThat(currentComponent.id, is("name"));
			assertThat(currentComponent.requiresAnswer, is(false));
		}
		
		@Test
		public void movingBack_OutputsStartComponent(){
			setupNavigationTest(Direction.BACKWARD, NullFormComponents.START);
			
			executeNavigationRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void movingNowhere_OutputsFirstComponent(){
			setupNavigationTest(Direction.NONE, getCurrentFormComponent());
			
			executeNavigationRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void undoAfterMovingNowhereDoesNotChangeCurrentFormComponent() {
			setupNavigationTest(Direction.NONE, getCurrentFormComponent());
			
			executeAndUndoNavigationRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void movingForward_OutputsSecondComponent(){
			setupNavigationTest(Direction.FORWARD, mockAgeFormComponent);
			
			executeNavigationRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void undoAfterForwardMove_RevertsCurrentFormComponent() {
			setupNavigationTest(Direction.FORWARD, getCurrentFormComponent());

			executeAndUndoNavigationRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		public class GivenTransporterHasMovedForward {
			
			@Before
			public void givenTransporterIsAtFirstComponent(){
				setMockRequestDirection(Direction.FORWARD);
				executeNavigationRequest(mockRequest);
			}
			
			@Test
			public void gettingCurrent_ReturnsMockAgeFormComponent(){
				FormComponent currentComponent = getCurrentFormComponent();
				
				assertThat(currentComponent.id, is("age"));
				assertThat(currentComponent.requiresAnswer, is(true));
			}
			
			@Test			
			public void movingBack_OutputsFirstComponent(){
				setupNavigationTest(Direction.BACKWARD, mockNameFormComponent);
				
				executeNavigationRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}
			
			@Test
			public void undoAfterBackwardMove_RevertsCurrentFormComponent() {
				setupNavigationTest(Direction.BACKWARD, mockAgeFormComponent);

				executeAndUndoNavigationRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}
			
			@Test
			public void movingForward_DoesNotChangeComponent(){
				setupNavigationTest(Direction.FORWARD, getCurrentFormComponent());
				
				executeNavigationRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}			
			
			@Test
			public void undoAfterFailedExecutionDoesNotChangeCurrentFormComponent() {
				setupNavigationTest(Direction.FORWARD, getCurrentFormComponent());

				executeAndUndoNavigationRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}	
		}		
	}
}
