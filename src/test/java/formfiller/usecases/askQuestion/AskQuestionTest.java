package formfiller.usecases.askQuestion;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.entities.*;
import formfiller.entities.formComponent.*;
import formfiller.entities.format.*;
import formfiller.enums.QuestionAsked;
import formfiller.request.models.AskQuestionRequest;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.utilities.*;

@RunWith(HierarchicalContextRunner.class)
public class AskQuestionTest {	
	private AskQuestionUseCase askQuestionUseCase;
	private AskQuestionRequest mockRequest;
	private FormComponent expectedFormComponent;

	private void setupAskQuestionTest(QuestionAsked which, 
			FormComponent formComponent) {
		setMockRequestQuestion(which);
		setExpectedFormComponent(formComponent);
	}
	
	private void setMockRequestQuestion(QuestionAsked which){
		mockRequest.which = which;
	}
	
	private void setExpectedFormComponent(FormComponent expected) {
		expectedFormComponent = expected;
	}

	private void undoAskQuestionUseCase() {
		askQuestionUseCase.undo();
	}

	private void executeAskQuestionRequest(AskQuestionRequest request) {
		askQuestionUseCase.execute(request);
	}

	private void assertThat_CurrentAndExpectedComponents_AreTheSame() {
		assertThat(getCurrentFormComponent(), is(expectedFormComponent));
	}
	
	private FormComponent getCurrentFormComponent() {
		return Context.formComponentState.getCurrent();
	}

	private void assertThat_ExecutedUseCase_IsMostRecent() {
		assertEquals(askQuestionUseCase, checkMostRecent());
	}

	private UndoableUseCase checkMostRecent() {
		return Context.executedUseCases.getMostRecent();
	}

	private void assertThat_ExecutedUseCase_IsNotMostRecent() {
		assertNotEquals(askQuestionUseCase, checkMostRecent());
	}	
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		askQuestionUseCase = new AskQuestionUseCase();
		mockRequest = mock(AskQuestionRequest.class);		
	}
	
	@Test
	public void isAnUndoableUseCase() {
		assertThat(askQuestionUseCase, is(instanceOf(UndoableUseCase.class)));
	}
	
	//	TODO:	Make the Undo use case.
	@Test
	public void undoBeforeExecutionDoesNotChangeCurrentFormComponent() {
		setExpectedFormComponent(getCurrentFormComponent());
		
		undoAskQuestionUseCase();
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
	}
	
	@Test
	public void executingNullDoesNotChangeCurrentFormComponent() {
		setExpectedFormComponent(getCurrentFormComponent());
		
		executeAskQuestionRequest(null);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsNotMostRecent();
	}
	
	@Test
	public void gettingPrevQuestionGetsStartPrompt(){
		setupAskQuestionTest(QuestionAsked.PREVIOUS, NullFormComponents.START);
		
		executeAskQuestionRequest(mockRequest);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsMostRecent();
	}
	
	@Test
	public void gettingCurrentQuestionGetsStartPrompt(){
		setupAskQuestionTest(QuestionAsked.CURRENT, getCurrentFormComponent());
		
		executeAskQuestionRequest(mockRequest);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsMostRecent();
	}
	
	@Test
	public void gettingNextQuestionGetsEndPrompt(){
		setupAskQuestionTest(QuestionAsked.NEXT, getCurrentFormComponent());
		
		executeAskQuestionRequest(mockRequest);
		
		assertThat_CurrentAndExpectedComponents_AreTheSame();
		assertThat_ExecutedUseCase_IsMostRecent();
	}	
	
	public class GivenTwoFormComponents {
		FormComponent mockNameFormComponent;
		FormComponent mockAgeFormComponent;
		Question mockQuestion;

		private FormComponent makeMockFormComponent(String id, String content, 
				boolean requiresAnswer, Format answerFormat) {
			Question mockQuestion = makeMockQuestion(id, content);
			FormComponent result = makeMockFormComponent(requiresAnswer, 
					mockQuestion, answerFormat);
			return result;
		}

		private Question makeMockQuestion(String id, String content) {
			return QuestionMocker.makeMockQuestion(id, content);
		}
		
		private FormComponent makeMockFormComponent(boolean requiresAnswer, 
				Question question, Format answerFormat){
			return FormComponentMocker.makeMockFormComponent(requiresAnswer, 
					question, AnswerImpl.NONE, answerFormat);
		}

		private void saveFormComponents(FormComponent... formComponents) {
			for (FormComponent formComponent : formComponents)
				Context.formComponentGateway.save(formComponent);
		}

		private void executeAndUndoAskQuestionRequest(
				AskQuestionRequest request) {
			executeAskQuestionRequest(request);
			undoAskQuestionUseCase();
		}	

		private OptionVariable makeOptionVariableFormat() {
			OptionVariable result = new OptionVariable();
			result.options = makeLegalAgeOptions();
			return result;
		}
		
		private List<Object> makeLegalAgeOptions(){
			Object[] options = new Integer[] { 18, 35, 49, 64 };
			return Arrays.asList(options);
		}
		//	This seems like a lot of private methods.
		
		@Before
		public void givenTwoFormComponents(){
			mockNameFormComponent = makeMockFormComponent("name", 
					"What is your name?", false, new Unstructured());
			mockAgeFormComponent = makeMockFormComponent("age", 
					"What is your age?", true, makeOptionVariableFormat());
			saveFormComponents(mockNameFormComponent, mockAgeFormComponent);
		}
		
		@Test
		public void gettingCurrent_ReturnsMockNameFormComponent(){
			FormComponent currentComponent = getCurrentFormComponent();
			
			assertThat(currentComponent.id, is("name"));
			assertThat(currentComponent.requiresAnswer, is(false));
			assertThat(currentComponent.format, is(instanceOf(Unstructured.class)));
			//	TODO:	Move below into UnstructuredFormat when it has its own class.
			assertThat(currentComponent.format.matchesFormat("myName"), is(true));
		}
		
		@Test
		public void movingBack_OutputsStartComponent(){
			setupAskQuestionTest(QuestionAsked.PREVIOUS, NullFormComponents.START);
			
			executeAskQuestionRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void movingNowhere_OutputsFirstComponent(){
			setupAskQuestionTest(QuestionAsked.CURRENT, getCurrentFormComponent());
			
			executeAskQuestionRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void undoAfterMovingNowhere_DoesNotChangeCurrentFormComponent() {
			setupAskQuestionTest(QuestionAsked.CURRENT, getCurrentFormComponent());
			
			executeAndUndoAskQuestionRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void movingForward_OutputsSecondComponent(){
			setupAskQuestionTest(QuestionAsked.NEXT, mockAgeFormComponent);
			
			executeAskQuestionRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		@Test
		public void undoAfterForwardMove_RevertsCurrentFormComponent() {
			setupAskQuestionTest(QuestionAsked.NEXT, getCurrentFormComponent());

			executeAndUndoAskQuestionRequest(mockRequest);
			
			assertThat_CurrentAndExpectedComponents_AreTheSame();
		}
		
		public class GivenTransporterHasMovedForward {
			
			@Before
			public void givenTransporterHasMovedForward(){
				setMockRequestQuestion(QuestionAsked.NEXT);
				executeAskQuestionRequest(mockRequest);
			}
			
			@Test
			public void gettingCurrent_ReturnsMockAgeFormComponent(){
				FormComponent currentComponent = getCurrentFormComponent();
				OptionVariable castFormat = 
						(OptionVariable) currentComponent.format;
				List<Object> options = castFormat.options;
				
				assertThat(currentComponent.id, is("age"));
				assertThat(currentComponent.requiresAnswer, is(true));
				assertThat(currentComponent.format, 
						is(instanceOf(OptionVariable.class)));
				assertThat(options, is(makeLegalAgeOptions()));
			}
			
			@Test			
			public void movingBack_OutputsFirstComponent(){
				setupAskQuestionTest(QuestionAsked.PREVIOUS, mockNameFormComponent);
				
				executeAskQuestionRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}
			
			@Test
			public void undoAfterBackwardMove_RevertsCurrentFormComponent() {
				setupAskQuestionTest(QuestionAsked.PREVIOUS, mockAgeFormComponent);

				executeAndUndoAskQuestionRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}
			
			@Test
			public void movingForward_DoesNotChangeComponent(){
				setupAskQuestionTest(QuestionAsked.NEXT, getCurrentFormComponent());
				
				executeAskQuestionRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}			
			
			@Test
			public void undoAfterFailedExecution_DoesNotChangeCurrentFormComponent() {
				setupAskQuestionTest(QuestionAsked.NEXT, getCurrentFormComponent());

				executeAndUndoAskQuestionRequest(mockRequest);
				
				assertThat_CurrentAndExpectedComponents_AreTheSame();
			}	
		}		
	}
}
