package formfiller.usecases.addFormatConstraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.OptionVariable;
import formfiller.request.models.AddAnswerCountBoundaryRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

//	TODO:	Don't allow setting boundary on Unstructured in this use case.
//			(Allow setting minimum = 1 through AddRequiredAnswerUseCase.)
public class AddAnswerCountBoundaryTest {
	private AddAnswerCountBoundaryUseCase useCase;
	private AddAnswerCountBoundaryRequest mockRequest;
	private FormComponent foundComponent;
	private int boundaryValue;

	@Before
	public void setUp() {
		useCase = new AddAnswerCountBoundaryUseCase();
		UnitTestSetupUtilities.addFormComponentToChange(new OptionVariable());
	}

	private AddAnswerCountBoundaryRequest makeEmptyMockAddAnswerCountBoundaryRequest() {
		return Mockito.mock(AddAnswerCountBoundaryRequest.class);
	}

	//	TODO:	Make null form components immutable.
	private AddAnswerCountBoundaryRequest makeMockAddAnswerCountBoundaryRequestWithFieldValues(
			String componentId, String boundary) {
		AddAnswerCountBoundaryRequest result = makeEmptyMockAddAnswerCountBoundaryRequest();
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
		mockRequest = makeMockAddAnswerCountBoundaryRequestWithFieldValues(
				"", "minimum");		
		useCase.execute(mockRequest);
	}

	@Test(expected = UndoableUseCaseExecution.MalformedRequest.class)
	public void executingRequestWithoutBoundaryThrowsException() {
		mockRequest = makeMockAddAnswerCountBoundaryRequestWithFieldValues(
				"toChange", "");		
		useCase.execute(mockRequest);
	}

	@Test
	public void executingWellFormedRequestAddsAnswerCountMinimum() {
		boundaryValue = 1;
		mockRequest = makeMockAddAnswerCountBoundaryRequestWithFieldValues(
				"toChange", "minimum");
		
		useCase.execute(mockRequest);
		foundComponent = FormComponentUtilities.find(mockRequest.componentId);
		
		assertThat(foundComponent.format.minAnswers, is(boundaryValue));
	}

	@Test
	public void executingWellFormedRequestAddsAnswerCountMaximum() {
		boundaryValue = 2;
		mockRequest = makeMockAddAnswerCountBoundaryRequestWithFieldValues(
				"toChange", "maximum");
		
		useCase.execute(mockRequest);
		foundComponent = FormComponentUtilities.find(mockRequest.componentId);
		
		assertThat(foundComponent.format.maxAnswers, is(boundaryValue));
	}

	//	TODO:	Determine whether AnswerFormat class should contain exception.
	@Test(expected = AddAnswerCountBoundaryUseCase.MaximumLessThanMinimum.class)
	public void addingMaximumLessThanMinimumThrowsException() {
		boundaryValue = 3;
		mockRequest = makeMockAddAnswerCountBoundaryRequestWithFieldValues(
				"toChange", "minimum");		
		useCase.execute(mockRequest);
	}
	
	@Test
	public void undoingSuccessfulAdditionRevertsBoundary() {
		boundaryValue = 2;
		mockRequest = makeMockAddAnswerCountBoundaryRequestWithFieldValues(
				"toChange", "maximum");
		
		useCase.execute(mockRequest);
		useCase.undo();
		foundComponent = FormComponentUtilities.find(mockRequest.componentId);
		
		assertThat(foundComponent.format.maxAnswers, is(1));
	}
	
}
