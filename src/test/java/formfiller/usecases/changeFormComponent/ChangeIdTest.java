package formfiller.usecases.changeFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeIdRequest;
import formfiller.usecases.changeFormComponent.ChangeFormComponent.AbsentFormComponentChange;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution.UnsuccessfulUseCaseUndo;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.TestSetup;

public class ChangeIdTest {
	private ChangeIdUseCase useCase;
	private ChangeIdRequest mockRequest;
	private FormComponent unknownComponent;
	private FormComponent found;
	private boolean isNull;
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		useCase = new ChangeIdUseCase();
	}

	private ChangeIdRequest makeMockEmptyRequest() {
		return Mockito.mock(ChangeIdRequest.class);
	}

	private ChangeIdRequest makeChangeIdRequest() {
		ChangeIdRequest result = makeMockEmptyRequest();
		result.oldId = "unknown";
		result.newId = "name";
		return result;
	}

	private void addUnknownFormComponent() {
		unknownComponent = makeUnknownFormComponent();
		FormFillerContext.formComponentGateway.save(unknownComponent);
	}

	private FormComponent makeUnknownFormComponent() {
		FormComponent result = new FormComponent();
		result.id = "unknown";
		return result;
	}

	@Test(expected = MalformedRequest.class)
	public void executingEmptyRequestThrowsException() {
		mockRequest = makeMockEmptyRequest();		
		useCase.execute(mockRequest);
	}
	
	@Test(expected = UnsuccessfulUseCaseUndo.class)
	public void undoingBeforeExecutingThrowsException(){
		useCase.undo();
	}
	
	@Test(expected = AbsentFormComponentChange.class)
	public void changingAbsentComponentIdThrowsException() {
		mockRequest = makeChangeIdRequest();	
		useCase.execute(mockRequest);		
	}
	
	@Test
	public void changingPresentComponentIdUpdatesId() {
		addUnknownFormComponent();
		mockRequest = makeChangeIdRequest();
		
		useCase.execute(mockRequest);	
		found = FormComponentUtilities.findFormComponent("name");
		isNull = FormComponentUtilities.isComponentNull(found);
		
		assertThat(isNull, is(false));
		assertThat(unknownComponent, is(found));
	}
	
	@Test
	public void undoingSuccessfulIdChangeRevertsId() {
		addUnknownFormComponent();
		mockRequest = makeChangeIdRequest();		
		useCase.execute(mockRequest);
		
		UndoableUseCase mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();
		
		found = FormComponentUtilities.findFormComponent("unknown");
		isNull = FormComponentUtilities.isComponentNull(found);
		
		assertThat(isNull, is(false));
		assertThat(unknownComponent, is(found));
	}

}
