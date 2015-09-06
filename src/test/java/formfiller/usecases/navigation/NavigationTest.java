package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.AnswerImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.enums.Direction;
import formfiller.request.models.NavigationRequest;
import formfiller.utilities.InMemoryFormComponentLiason;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

//	TODO:	Break the index (InMemory) dependency.
@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase navigationUseCase;
	private NavigationRequest mockRequest;
	private FormComponent foundFormComponent;
	
	private void setMockRequestDirection(Direction direction){
		mockRequest.direction = direction;
	}

	private void setFoundFormComponentToIndex(int index) {
		foundFormComponent = InMemoryFormComponentLiason.findFormComponentByIndex(index);
	}
	
	private FormComponent makeMockFormComponent(Prompt question){
		return FormComponentMocker.makeMockFormComponent(question, AnswerImpl.NONE);
	}
	
	private FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentState.getCurrent();
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
		setFoundFormComponentToIndex(0);
		
		navigationUseCase.undo();
		
		assertThat(getCurrentFormComponent(), is(foundFormComponent));
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
				setFoundFormComponentToIndex(-1);
				
				navigationUseCase.execute(mockRequest);
				
				assertEquals(navigationUseCase, ApplicationContext.executedUseCases.peek());
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}
		}
		
		public class GivenCurrentQuestionRequest{
			
			@Before
			public void givenCurrentQuestionRequest(){
				setMockRequestDirection(Direction.NONE);
			}
			
			@Test
			public void gettingQuestionGetsStartPrompt(){
				setFoundFormComponentToIndex(0);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}			
		}
		
		public class GivenNextQuestionRequest{
			
			@Before
			public void givenNextQuestionRequest(){
				setMockRequestDirection(Direction.FORWARD);
			}
			
			@Test
			public void gettingQuestionGetsEndPrompt(){
				setFoundFormComponentToIndex(0);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
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
				ApplicationContext.formComponentGateway.save(formComponent);
		}
		
		@Test
		public void formComponentGatewayStoresGivenComponents(){
			assertEquals(mockNameFormComponent, InMemoryFormComponentLiason.findFormComponentByIndex(0));
			assertEquals(mockAgeFormComponent, InMemoryFormComponentLiason.findFormComponentByIndex(1));
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
				setFoundFormComponentToIndex(-1);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("start"));
			}
			
			@Test
			public void movingNowhere_OutputsFirstComponent(){
				setMockRequestDirection(Direction.NONE);
				setFoundFormComponentToIndex(0);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("name"));
			}
			
			@Test
			public void undoAfterMovingNowhereDoesNotChangeCurrentFormComponent() {
				setMockRequestDirection(Direction.NONE);
				setFoundFormComponentToIndex(0);
				
				navigationUseCase.execute(mockRequest);				
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}
			
			@Test
			public void movingForward_OutputsSecondComponent(){
				setMockRequestDirection(Direction.FORWARD);
				setFoundFormComponentToIndex(1);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("age"));
			}
			
			@Test
			public void undoAfterForwardMove_RevertsCurrentFormComponent() {
				setMockRequestDirection(Direction.FORWARD);
				setFoundFormComponentToIndex(0);

				navigationUseCase.execute(mockRequest);
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
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
				setFoundFormComponentToIndex(0);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("name"));
			}
			
			@Test
			public void undoAfterBackwardMove_RevertsCurrentFormComponent() {
				setMockRequestDirection(Direction.BACKWARD);
				setFoundFormComponentToIndex(1);

				navigationUseCase.execute(mockRequest);
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}
			
			@Test
			public void movingForward_DoesNotChangeComponent(){
				setMockRequestDirection(Direction.FORWARD);
				setFoundFormComponentToIndex(1);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("age"));
			}			
			
			@Test
			public void undoAfterFailedExecutionDoesNotChangeCurrentFormComponent() {
				setMockRequestDirection(Direction.FORWARD);
				setFoundFormComponentToIndex(1);

				navigationUseCase.execute(mockRequest);
				navigationUseCase.undo();
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}	
		}		
	}
}
