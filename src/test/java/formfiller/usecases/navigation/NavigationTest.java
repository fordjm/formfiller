package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.AnswerImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.enums.Direction;
import formfiller.request.models.NavigationRequest;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase navigationUseCase;
	private NavigationRequest mockRequest;
	private FormComponent expectedFormComponent;
	
	private void setMockRequestDirection(Direction direction){
		mockRequest.direction = direction;
	}
	
	private void setExpectedFormComponent(FormComponent expected) {
		expectedFormComponent = expected;
	}
	
	private FormComponent getCurrentFormComponent() {
		return FormFillerContext.formComponentState.getCurrent();
	}
	
	private FormComponent makeMockFormComponent(Prompt question){
		return FormComponentMocker.makeMockFormComponent(question, AnswerImpl.NONE);
	}
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		navigationUseCase = new NavigationUseCase();
		mockRequest = mock(NavigationRequest.class);		
	}
	
	@Test
	public void isAUseCase() {
		assertThat(navigationUseCase, is(instanceOf(UseCase.class)));
	}
	
	@Test
	public void isUndoable() {
		assertThat(navigationUseCase, is(instanceOf(Undoable.class)));
	}
	
	@Test
	public void undoBeforeExecutionDoesNotChangeCurrentFormComponent() {
		setExpectedFormComponent(getCurrentFormComponent());
		
		navigationUseCase.undo();
		
		assertThat(getCurrentFormComponent(), is(expectedFormComponent));
	}
	
	@Test
	public void canHandleNull() {
		navigationUseCase.execute(null);
	}

	public class GivenNoFormComponents{
		
		public class GivenPrevQuestionRequest{
			
			@Before
			public void givenPrevQuestionRequest(){
				setMockRequestDirection(Direction.BACKWARD);
			}
			
			@Test
			public void gettingQuestionGetsStartPrompt(){			
				setExpectedFormComponent(FormComponent.START);
				
				navigationUseCase.execute(mockRequest);
				
				assertEquals(navigationUseCase, 
						FormFillerContext.executedUseCases.peek());
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}
		}
		
		public class GivenCurrentQuestionRequest{
			
			@Before
			public void givenCurrentQuestionRequest(){
				setMockRequestDirection(Direction.NONE);
			}
			
			@Test
			public void gettingQuestionGetsStartPrompt(){
				setExpectedFormComponent(getCurrentFormComponent());
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}			
		}
		
		public class GivenNextQuestionRequest{
			
			@Before
			public void givenNextQuestionRequest(){
				setMockRequestDirection(Direction.FORWARD);
			}
			
			@Test
			public void gettingQuestionGetsEndPrompt(){
				setExpectedFormComponent(getCurrentFormComponent());
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}		
		}
	}
	
	public class GivenTwoFormComponents {
		FormComponent mockNameFormComponent;
		FormComponent mockAgeFormComponent;
		Question mockQuestion;
		
		private void setMockQuestion(Question mockQuestion){
			this.mockQuestion = mockQuestion;
		}
		
		@Before
		public void givenTwoFormComponents(){
			setMockQuestion(QuestionMocker.makeMockNameQuestion());
			mockNameFormComponent = makeMockFormComponent(mockQuestion);
			setMockQuestion(QuestionMocker.makeMockAgeQuestion());
			mockAgeFormComponent = makeMockFormComponent(mockQuestion);
			saveFormComponents(mockNameFormComponent, mockAgeFormComponent);
		}

		private void saveFormComponents(FormComponent... formComponents) {
			for (FormComponent formComponent : formComponents)
				FormFillerContext.formComponentGateway.save(formComponent);
		}
		
		public class GivenTransporterIsAtStart {
			
			@Test
			public void gettingCurrent_ReturnsMockNameFormComponent(){
				FormComponent currentComponent = getCurrentFormComponent();
				
				assertThat(currentComponent.id, is("name"));
				assertThat(currentComponent.question.requiresAnswer(), is(false));
			}
			
			@Test
			public void movingBack_OutputsStartComponent(){
				setMockRequestDirection(Direction.BACKWARD);
				setExpectedFormComponent(FormComponent.START);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
				assertThat(expectedFormComponent.id, is("start"));
			}
			
			@Test
			public void movingNowhere_OutputsFirstComponent(){
				setMockRequestDirection(Direction.NONE);
				setExpectedFormComponent(getCurrentFormComponent());
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
				assertThat(expectedFormComponent.id, is("name"));
			}
			
			@Test
			public void undoAfterMovingNowhereDoesNotChangeCurrentFormComponent() {
				setMockRequestDirection(Direction.NONE);
				setExpectedFormComponent(getCurrentFormComponent());
				
				navigationUseCase.execute(mockRequest);				
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}
			
			@Test
			public void movingForward_OutputsSecondComponent(){
				setMockRequestDirection(Direction.FORWARD);
				setExpectedFormComponent(mockAgeFormComponent);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}
			
			@Test
			public void undoAfterForwardMove_RevertsCurrentFormComponent() {
				setMockRequestDirection(Direction.FORWARD);
				setExpectedFormComponent(getCurrentFormComponent());

				navigationUseCase.execute(mockRequest);
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}		
		}
		
		public class GivenTransporterHasMovedForward {
			
			@Before
			public void givenTransporterIsAtFirstComponent(){
				setMockRequestDirection(Direction.FORWARD);
				navigationUseCase.execute(mockRequest);
			}
			
			@Test
			public void gettingCurrent_ReturnsMockAgeFormComponent(){
				FormComponent currentComponent = getCurrentFormComponent();
				
				assertThat(currentComponent.id, is("age"));
				assertThat(currentComponent.question.requiresAnswer(), is(true));
			}
			
			@Test			
			public void movingBack_OutputsFirstComponent(){
				setMockRequestDirection(Direction.BACKWARD);
				setExpectedFormComponent(mockNameFormComponent);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}
			
			@Test
			public void undoAfterBackwardMove_RevertsCurrentFormComponent() {
				setMockRequestDirection(Direction.BACKWARD);
				setExpectedFormComponent(mockAgeFormComponent);

				navigationUseCase.execute(mockRequest);
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}
			
			@Test
			public void movingForward_DoesNotChangeComponent(){
				setMockRequestDirection(Direction.FORWARD);
				setExpectedFormComponent(getCurrentFormComponent());
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
				assertThat(expectedFormComponent.id, is("age"));
			}			
			
			@Test
			public void undoAfterFailedExecutionDoesNotChangeCurrentFormComponent() {
				setMockRequestDirection(Direction.FORWARD);
				setExpectedFormComponent(getCurrentFormComponent());

				navigationUseCase.execute(mockRequest);
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(expectedFormComponent));
			}	
		}		
	}
}
