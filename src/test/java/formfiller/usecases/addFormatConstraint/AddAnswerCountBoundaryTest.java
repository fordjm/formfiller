package formfiller.usecases.addFormatConstraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.*;
import formfiller.entities.format.Format.MaximumLessThanMinimum;
import formfiller.request.models.AddAnswerCountBoundaryRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

//	TODO:	Add a context for each format.
@RunWith(HierarchicalContextRunner.class)
public class AddAnswerCountBoundaryTest {
	private AddAnswerCountBoundaryUseCase useCase;
	private AddAnswerCountBoundaryRequest mockRequest;
	private FormComponent foundComponent;
	private int boundaryValue;

	@Before
	public void setUp() {
		useCase = new AddAnswerCountBoundaryUseCase();
		UnitTestSetupUtilities.addFormComponentToChange(
				new MultiOptionVariable());
	}

	private AddAnswerCountBoundaryRequest makeEmptyMockAddAnswerCountBoundaryRequest() {
		return Mockito.mock(AddAnswerCountBoundaryRequest.class);
	}

	//	TODO:	Make null form components immutable.
	private AddAnswerCountBoundaryRequest makeMockAddAnswerCountBoundaryRequest(
			String componentId, String boundary) {
		AddAnswerCountBoundaryRequest result = 
				makeEmptyMockAddAnswerCountBoundaryRequest();
		result.componentId = componentId;
		result.boundary = boundary;
		result.count = boundaryValue;
		return result;
	}
	
	@Test
	public void commonTestsPass() {
		boolean result = 
				UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
		assertThat(result, is(true));
	}
	
	@Test(expected = UndoableUseCaseExecution.MalformedRequest.class)
	public void executingEmptyRequestThrowsException() {
		mockRequest = makeEmptyMockAddAnswerCountBoundaryRequest();
		useCase.execute(mockRequest);
	}

	@Test(expected = UndoableUseCaseExecution.MalformedRequest.class)
	public void executingRequestWithoutComponentIdThrowsException() {
		mockRequest = makeMockAddAnswerCountBoundaryRequest(
				"", "minimum");		
		useCase.execute(mockRequest);
	}

	@Test(expected = UndoableUseCaseExecution.MalformedRequest.class)
	public void executingRequestWithoutBoundaryThrowsException() {
		mockRequest = makeMockAddAnswerCountBoundaryRequest(
				"toChange", "");		
		useCase.execute(mockRequest);
	}
	
	//	TODO:	Run Unstructured thru same suite of tests.
	//			(Changing to Unstructured in setUp() passes.)
	public class SingleOptionVariableFormatContext {
		@Before
		public void setUp() {
			UnitTestSetupUtilities.addFormComponentToChange(
					new SingleOptionVariable());
		}

		@Test(expected = IllegalArgumentException.class)
		public void illegalMinimumValueThrowsException() {
			boundaryValue = -1;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");		
			useCase.execute(mockRequest);
		}

		@Test(expected = IllegalArgumentException.class)
		public void unrecognizedBoundaryThrowsException() {
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "boundary");		
			useCase.execute(mockRequest);
		}

		@Test
		public void executingWellFormedRequestAddsAnswerCountMinimum() {
			boundaryValue = 1;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");
			
			useCase.execute(mockRequest);
			foundComponent = FormComponentUtilities.find(mockRequest.componentId);
			
			assertThat(foundComponent.format.getMinAnswers(), is(boundaryValue));
		}

		@Test(expected = IllegalStateException.class)
		public void addingMaximumThrowsException() {
			boundaryValue = 2;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "maximum");			
			useCase.execute(mockRequest);
		}

		@Test(expected = MaximumLessThanMinimum.class)
		public void addingMinimumGreaterThanMaximumThrowsException() {
			boundaryValue = 3;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");		
			useCase.execute(mockRequest);
		}
		
		@Test
		public void undoingSuccessfulAdditionRevertsBoundary() {
			boundaryValue = 1;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");
			
			useCase.execute(mockRequest);
			useCase.undo();
			foundComponent = FormComponentUtilities.find(mockRequest.componentId);
			
			assertThat(foundComponent.format.getMinAnswers(), is(0));
		}
	}
	
	public class MultiOptionVariableFormatContext {
		@Before
		public void setUp() {
			UnitTestSetupUtilities.addFormComponentToChange(
					new MultiOptionVariable());
		}

		@Test(expected = IllegalArgumentException.class)
		public void illegalMinimumValueThrowsException() {
			boundaryValue = -1;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");		
			useCase.execute(mockRequest);
		}

		@Test(expected = IllegalArgumentException.class)
		public void unrecognizedBoundaryThrowsException() {
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "boundary");		
			useCase.execute(mockRequest);
		}

		@Test
		public void executingWellFormedRequestAddsAnswerCountMinimum() {
			boundaryValue = 1;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");
			
			useCase.execute(mockRequest);
			foundComponent = FormComponentUtilities.find(mockRequest.componentId);
			
			assertThat(foundComponent.format.getMinAnswers(), is(boundaryValue));
		}

		@Test
		public void executingWellFormedRequestAddsAnswerCountMaximum() {
			boundaryValue = 2;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "maximum");
			
			useCase.execute(mockRequest);
			foundComponent = FormComponentUtilities.find(mockRequest.componentId);
			
			assertThat(foundComponent.format.getMaxAnswers(), is(boundaryValue));
		}

		@Test(expected = MaximumLessThanMinimum.class)
		public void addingMaximumLessThanMinimumThrowsException() {
			boundaryValue = 3;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "minimum");		
			useCase.execute(mockRequest);
		}
		
		@Test
		public void undoingSuccessfulAdditionRevertsBoundary() {
			boundaryValue = 3;
			mockRequest = makeMockAddAnswerCountBoundaryRequest(
					"toChange", "maximum");
			
			useCase.execute(mockRequest);
			useCase.undo();
			foundComponent = FormComponentUtilities.find(mockRequest.componentId);
			
			assertThat(foundComponent.format.getMaxAnswers(), is(2));
		}
	}	
	
}
