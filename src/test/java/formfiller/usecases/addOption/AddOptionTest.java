package formfiller.usecases.addOption;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddOptionRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;

public class AddOptionTest {
	private AddOptionUseCase useCase;
	private AddOptionRequest mockRequest;
	private FormComponent foundComponent;
	private OptionVariable castFormat;
	
	@Before
	public void setUp() {
		useCase = new AddOptionUseCase();
		addFormComponentToChange();
	}

	private void addFormComponentToChange() {
		FormComponent toChange = new FormComponent();
		toChange.id = "isHappy";
		toChange.format = new OptionVariable();
		FormFillerContext.formComponentGateway.save(toChange);
	}

	private AddOptionRequest makeEmptyMockAddOptionRequest() {
		return Mockito.mock(AddOptionRequest.class);
	}

	private AddOptionRequest makeWellFormedMockAddOptionRequest() {
		AddOptionRequest result = makeEmptyMockAddOptionRequest();
		result.componentId = "isHappy";
		result.option = "agree";
		return result;
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
