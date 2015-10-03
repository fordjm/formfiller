package formfiller.usecases.addFormatConstraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddOptionRequest;
import formfiller.usecases.addFormatConstraint.AddOptionUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

public class AddOptionTest {
	private AddOptionUseCase useCase;
	private AddOptionRequest mockRequest;
	private FormComponent foundComponent;
	private OptionVariable castFormat;
	
	@Before
	public void setUp() {
		useCase = new AddOptionUseCase();
		AddFormatConstraintTestUtilities.addFormComponentToChange();
	}

	private AddOptionRequest makeEmptyMockAddOptionRequest() {
		return Mockito.mock(AddOptionRequest.class);
	}

	private AddOptionRequest makeWellFormedMockAddOptionRequest() {
		AddOptionRequest result = makeEmptyMockAddOptionRequest();
		result.componentId = "toChange";
		result.option = "agree";
		return result;
	}
	
	@Test
	public void commonTestsPass() {
		boolean result = 
				UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
		assertThat(result, is(true));
	}
	
	@Test(expected = UndoableUseCaseExecution.MalformedRequest.class)
	public void executingMalformedRequestThrowsException() {
		mockRequest = makeEmptyMockAddOptionRequest();
		useCase.execute(mockRequest);
	}

	@Test
	public void executingWellFormedRequestAddsOption() {
		mockRequest = makeWellFormedMockAddOptionRequest();
		
		useCase.execute(mockRequest);
		foundComponent = FormComponentUtilities.find(mockRequest.componentId);
		castFormat = (OptionVariable) foundComponent.format;
		
		assertThat(foundComponent.format, instanceOf(OptionVariable.class));
		assertThat(castFormat.options.contains(mockRequest.option), is(true));
	}
	
	@Test
	public void undoingSuccessfulAdditionRemovesOption() {
		mockRequest = makeWellFormedMockAddOptionRequest();
		
		useCase.execute(mockRequest);
		useCase.undo();
		foundComponent = FormComponentUtilities.find(mockRequest.componentId);
		castFormat = (OptionVariable) foundComponent.format;
		
		assertThat(castFormat.options.contains(mockRequest.option), is(false));
	}
	
}
