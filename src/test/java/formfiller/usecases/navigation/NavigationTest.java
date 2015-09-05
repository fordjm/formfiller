package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.entities.AnswerImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.gateways.FormComponentGateway;
import formfiller.gateways.InMemoryFormComponentGateway;
import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.gateways.Transporter;
import formfiller.request.models.NavigationRequest;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase navigationUseCase;
	private NavigationRequest mockRequest;
	private FormComponent foundFormComponent;
	
	private void setMockRequestDirection(Direction direction){
		mockRequest.direction = direction;
	}
	
	private FormComponent findFormComponentByIndex(int index){
		return getInMemoryFormComponentGateway().findByIndex(index);
	}
	
	private InMemoryFormComponentGateway getInMemoryFormComponentGateway(){
		InMemoryFormComponentGateway result = (InMemoryFormComponentGateway)
				getFormComponentGatewayFromContext();		
		return result;
	}

	private FormComponentGateway getFormComponentGatewayFromContext() {
		return ApplicationContext.formComponentGateway;
	}
	
	private FormComponent makeMockFormComponent(Prompt question){
		return FormComponentMocker.makeMockFormComponent(question, AnswerImpl.NONE);
	}
	
	private FormComponent getCurrentFormComponent() {
		Transporter transporter = getFormComponentGatewayFromContext().getTransporter();
		return transporter.getCurrent();
	}

	private void setFoundFormComponentToIndex(int index) {
		foundFormComponent = findFormComponentByIndex(index);
	}
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		navigationUseCase = new NavigationUseCase();
		mockRequest = mock(NavigationRequest.class);		
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
				foundFormComponent = findFormComponentByIndex(-1);
				
				navigationUseCase.execute(mockRequest);
				
				assertEquals(navigationUseCase, ApplicationContext.executedUseCases.peek().useCase);
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
				foundFormComponent = findFormComponentByIndex(0);
				
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
				foundFormComponent = findFormComponentByIndex(0);
				
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
				getFormComponentGatewayFromContext().save(formComponent);
		}
		
		@Test
		public void formComponentGatewayStoresGivenComponents(){
			assertEquals(mockNameFormComponent, findFormComponentByIndex(0));
			assertEquals(mockAgeFormComponent, findFormComponentByIndex(1));
		}
		
		public class GivenTransporterIsAtStart {
			
			@Test
			public void gettingCurrent_ReturnsMockNameFormComponent(){
				Transporter transporter = getFormComponentGatewayFromContext().getTransporter();
				
				assertThat(transporter.getCurrent().id, is("name"));
				assertThat(transporter.getCurrent().question.requiresAnswer(), is(false));
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
			public void movingForward_OutputsSecondComponent(){
				setMockRequestDirection(Direction.FORWARD);
				setFoundFormComponentToIndex(1);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("age"));
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
				Transporter transporter = getFormComponentGatewayFromContext().getTransporter();
				
				assertThat(transporter.getCurrent().id, is("age"));
				assertThat(transporter.getCurrent().question.requiresAnswer(), is(true));
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
			public void movingForward_DoesNotChangeComponent(){
				setMockRequestDirection(Direction.FORWARD);
				setFoundFormComponentToIndex(1);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
				assertThat(foundFormComponent.id, is("age"));
			}			
		}		
	}
}
