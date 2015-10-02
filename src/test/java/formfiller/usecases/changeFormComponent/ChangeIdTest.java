package formfiller.usecases.changeFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeIdRequest;
import formfiller.usecases.changeFormComponent.ChangeFormComponentUseCase.AbsentFormComponentChange;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.utilities.TestSetup;

public class ChangeIdTest {
	private ChangeIdUseCase useCase;
	private ChangeIdRequest mockRequest;
	private FormComponent original;
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		useCase = new ChangeIdUseCase();
	}

	private ChangeIdRequest makeMockEmptyChangeIdRequest() {
		return Mockito.mock(ChangeIdRequest.class);
	}

	private ChangeIdRequest makeMockWellFormedChangeIdRequest() {
		ChangeIdRequest result = makeMockEmptyChangeIdRequest();
		result.oldId = "unknown";
		result.newId = "name";
		return result;
	}

	private void addUnknownFormComponent() {
		original = makeUnknownFormComponent();
		FormFillerContext.formComponentGateway.save(original);
	}

	private FormComponent makeUnknownFormComponent() {
		FormComponent result = new FormComponent();
		result.id = "unknown";
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

	@Test(expected = MalformedRequest.class)
	public void executingMalformedRequestThrowsException() {
		mockRequest = makeMockEmptyChangeIdRequest();		
		useCase.execute(mockRequest);
	}
	
	@Test(expected = AbsentFormComponentChange.class)
	public void changingAbsentComponentIdThrowsException() {
		mockRequest = makeMockWellFormedChangeIdRequest();	
		useCase.execute(mockRequest);		
	}
	
	@Test
	public void executingWellFormedRequestChangesComponentId() {
		addUnknownFormComponent();
		mockRequest = makeMockWellFormedChangeIdRequest();
		
		useCase.execute(mockRequest);	
		
		assertThat(original.id, is("name"));
	}
	
	@Test
	public void undoingSuccessfulIdChangeRevertsId() {
		addUnknownFormComponent();
		mockRequest = makeMockWellFormedChangeIdRequest();		
		useCase.execute(mockRequest);
		
		UndoableUseCase mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();
		
		assertThat(original.id, is("unknown"));
	}

}
