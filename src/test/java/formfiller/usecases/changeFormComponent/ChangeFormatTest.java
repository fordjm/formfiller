package formfiller.usecases.changeFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.entities.format.OptionVariable;
import formfiller.entities.format.Unstructured;
import formfiller.request.models.RequestWithComponentIdAndFormat;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

@RunWith(HierarchicalContextRunner.class)
public class ChangeFormatTest {
	private ChangeFormatUseCase useCase;
	private RequestWithComponentIdAndFormat mockRequest;
	private FormComponent original;

	//	TODO:	How to fix duplicate tests?
	private RequestWithComponentIdAndFormat makeMockEmptyChangeRequest() {
		return Mockito.mock(RequestWithComponentIdAndFormat.class);
	}

	private RequestWithComponentIdAndFormat makeMockWellFormedChangeFormatRequest(Format format) {
		RequestWithComponentIdAndFormat result = makeMockEmptyChangeRequest();
		result.componentId = "name";
		result.format = format;
		return result;
	}

	private void addFormComponentToChange(Class<? extends Format> originalFormat) {
		original = new FormComponent();
		original.id = "name";
		original.format = Mockito.mock(originalFormat);
		Context.formComponentGateway.save(original);
	}

	public class ChangeUnstructuredContext {
		@Before
		public void setUp() {
			useCase = new ChangeFormatUseCase();		
		}
		
		@Test
		public void commonTestsPass() {
			boolean result = 
					UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
			assertThat(result, is(true));
		}

		@Test(expected = MalformedRequest.class)
		public void executingMalformedRequestThrowsException() {
			mockRequest = makeMockEmptyChangeRequest();				
			useCase.execute(mockRequest);
		}

		@Test
		public void executingWellFormedRequestChangesFormat() {
			addFormComponentToChange(OptionVariable.class);
			mockRequest = makeMockWellFormedChangeFormatRequest(new Unstructured());

			useCase.execute(mockRequest);

			assertThat(original.format, instanceOf(Unstructured.class));
		}

		@Test
		public void undoingSuccessfulChangeRevertsFormat() {
			addFormComponentToChange(OptionVariable.class);
			mockRequest = makeMockWellFormedChangeFormatRequest(new Unstructured());

			useCase.execute(mockRequest);
			useCase.undo();

			assertThat(original.format, instanceOf(OptionVariable.class));
		}
		
	}

	public class ChangeOptionVariableContext {
		@Before
		public void setUp() {
			useCase = new ChangeFormatUseCase();		
		}
		
		@Test
		public void commonTestsPass() {
			boolean result = 
					UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
			assertThat(result, is(true));
		}

		@Test(expected = MalformedRequest.class)
		public void executingEmptyRequestThrowsException() {
			mockRequest = makeMockEmptyChangeRequest();	

			useCase.execute(mockRequest);
		}

		@Test
		public void executingWellFormedRequestChangesFormat() {
			addFormComponentToChange(Unstructured.class);
			mockRequest = makeMockWellFormedChangeFormatRequest(new OptionVariable());

			useCase.execute(mockRequest);

			assertThat(original.format, instanceOf(OptionVariable.class));
		}

		@Test
		public void undoingSuccessfulChangeRevertsFormat() {
			addFormComponentToChange(Unstructured.class);
			mockRequest = makeMockWellFormedChangeFormatRequest(new OptionVariable());

			useCase.execute(mockRequest);
			useCase.undo();

			assertThat(original.format, instanceOf(Unstructured.class));
		}
		
	}
}
