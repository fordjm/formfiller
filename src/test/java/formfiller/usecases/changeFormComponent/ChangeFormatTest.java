package formfiller.usecases.changeFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeFormatRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution.UnsuccessfulUseCaseUndo;

@RunWith(HierarchicalContextRunner.class)
public class ChangeFormatTest {
	private ChangeFormatUseCase useCase;
	private ChangeFormatRequest mockRequest;
	private FormComponent original;

	//	TODO:	Fix test duplication here and in ChangeIdTest
	//			Consider making utility request creator and downcasting
	//			How to fix duplicate tests?
	private ChangeFormatRequest makeMockEmptyChangeRequest() {
		return Mockito.mock(ChangeFormatRequest.class);
	}

	private ChangeFormatRequest makeMockChangeRequestWithFieldValues() {
		ChangeFormatRequest result = makeMockEmptyChangeRequest();
		result.componentId = "name";
		return result;
	}

	private void addFormComponentToChange(Class<? extends AnswerFormat> originalFormat) {
		original = new FormComponent();
		original.id = "name";
		original.format = Mockito.mock(originalFormat);
		FormFillerContext.formComponentGateway.save(original);
	}
	
	public class ChangeUnstructuredContext {
		@Before
		public void setUp() {
			useCase = new ChangeUnstructuredUseCase();		
		}
		
		@Test(expected = UnsuccessfulUseCaseUndo.class)
		public void undoingBeforeExecutingThrowsException(){
			useCase.undo();
		}

		@Test(expected = MalformedRequest.class)
		public void executingEmptyRequestThrowsException() {
			mockRequest = makeMockEmptyChangeRequest();	
			
			useCase.execute(mockRequest);
		}
		
		@Test
		public void executingWellFormedRequestChangesFormat() {
			addFormComponentToChange(OptionVariable.class);
			mockRequest = makeMockChangeRequestWithFieldValues();
			
			useCase.execute(mockRequest);
			
			assertThat(original.format, instanceOf(Unstructured.class));
		}
		
		@Test
		public void undoingSuccessfulChangeRevertsFormat() {
			addFormComponentToChange(OptionVariable.class);
			mockRequest = makeMockChangeRequestWithFieldValues();
			
			useCase.execute(mockRequest);
			useCase.undo();
			
			assertThat(original.format, instanceOf(OptionVariable.class));
		}
	}
	
	public class ChangeOptionVariableContext {
		@Before
		public void setUp() {
			useCase = new ChangeOptionVariableUseCase();		
		}
		
		@Test(expected = UnsuccessfulUseCaseUndo.class)
		public void undoingBeforeExecutingThrowsException(){
			useCase.undo();
		}

		@Test(expected = MalformedRequest.class)
		public void executingEmptyRequestThrowsException() {
			mockRequest = makeMockEmptyChangeRequest();	
			
			useCase.execute(mockRequest);
		}
		
		@Test
		public void executingWellFormedRequestChangesFormat() {
			addFormComponentToChange(Unstructured.class);
			mockRequest = makeMockChangeRequestWithFieldValues();
			
			useCase.execute(mockRequest);
			
			assertThat(original.format, instanceOf(OptionVariable.class));
		}
		
		@Test
		public void undoingSuccessfulChangeRevertsFormat() {
			addFormComponentToChange(Unstructured.class);
			mockRequest = makeMockChangeRequestWithFieldValues();
			
			useCase.execute(mockRequest);
			useCase.undo();
			
			assertThat(original.format, instanceOf(Unstructured.class));
		}
	}

}
