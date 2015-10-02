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
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;

@RunWith(HierarchicalContextRunner.class)
public class ChangeFormatTest {
	private ChangeFormatUseCase useCase;
	private ChangeFormatRequest mockRequest;
	private FormComponent original;

	//	TODO:	How to fix duplicate tests?
	private ChangeFormatRequest makeMockEmptyChangeRequest() {
		return Mockito.mock(ChangeFormatRequest.class);
	}

	private ChangeFormatRequest makeMockWellFormedChangeFormatRequest() {
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

		//	Boilerplate duplicate tests		===
		@Test
		public void extendsUndoableUseCaseExecution() {		
			assertThat(useCase, instanceOf(UndoableUseCaseExecution.class));
		}

		@Test(expected = UndoableUseCaseExecution.UnsuccessfulUseCaseUndo.class)
		public void undoingBeforeExecutingThrowsException(){
			useCase.undo();
		}

		@Test(expected = NullPointerException.class)
		public void executingNull_DoesNotAddUseCaseToExecutedUseCases() {
			useCase.execute(null);
		}
		//	End boilerplate duplicate tests	===

		@Test(expected = MalformedRequest.class)
		public void executingMalformedRequestThrowsException() {
			mockRequest = makeMockEmptyChangeRequest();				
			useCase.execute(mockRequest);
		}

		@Test
		public void executingWellFormedRequestChangesFormat() {
			addFormComponentToChange(OptionVariable.class);
			mockRequest = makeMockWellFormedChangeFormatRequest();

			useCase.execute(mockRequest);

			assertThat(original.format, instanceOf(Unstructured.class));
		}

		@Test
		public void undoingSuccessfulChangeRevertsFormat() {
			addFormComponentToChange(OptionVariable.class);
			mockRequest = makeMockWellFormedChangeFormatRequest();

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

		//		Boilerplate duplicate tests		===
		@Test
		public void extendsUndoableUseCaseExecution() {		
			assertThat(useCase, instanceOf(UndoableUseCaseExecution.class));
		}

		@Test(expected = UndoableUseCaseExecution.UnsuccessfulUseCaseUndo.class)
		public void undoingBeforeExecutingThrowsException(){
			useCase.undo();
		}

		@Test(expected = NullPointerException.class)
		public void executingNull_DoesNotAddUseCaseToExecutedUseCases() {
			useCase.execute(null);
		}
		//	End boilerplate duplicate tests		===

		@Test(expected = MalformedRequest.class)
		public void executingEmptyRequestThrowsException() {
			mockRequest = makeMockEmptyChangeRequest();	

			useCase.execute(mockRequest);
		}

		@Test
		public void executingWellFormedRequestChangesFormat() {
			addFormComponentToChange(Unstructured.class);
			mockRequest = makeMockWellFormedChangeFormatRequest();

			useCase.execute(mockRequest);

			assertThat(original.format, instanceOf(OptionVariable.class));
		}

		@Test
		public void undoingSuccessfulChangeRevertsFormat() {
			addFormComponentToChange(Unstructured.class);
			mockRequest = makeMockWellFormedChangeFormatRequest();

			useCase.execute(mockRequest);
			useCase.undo();

			assertThat(original.format, instanceOf(Unstructured.class));
		}
		
	}
}
