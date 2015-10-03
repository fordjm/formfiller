package formfiller.usecases.changeFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeIdRequest;
import formfiller.usecases.changeFormComponent.ChangeFormComponentUseCase.AbsentFormComponentChange;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.utilities.TestSetup;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

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
		Context.formComponentGateway.save(original);
	}

	private FormComponent makeUnknownFormComponent() {
		FormComponent result = new FormComponent();
		result.id = "unknown";
		return result;
	}

	@Test
	public void commonTestsPass() {
		boolean result = 
				UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
		assertThat(result, is(true));
	}

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
		
		UndoableUseCase mostRecent = Context.executedUseCases.getMostRecent();
		mostRecent.undo();
		
		assertThat(original.id, is("unknown"));
	}

}
