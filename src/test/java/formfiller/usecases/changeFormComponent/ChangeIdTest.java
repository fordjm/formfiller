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
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.usecases.undoable.UndoableUseCaseExecution.UnsuccessfulUseCaseUndo;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.TestSetup;

public class ChangeIdTest {
	private ChangeIdUseCase useCase;
	private ChangeIdRequest mockRequest;
	private FormComponent original;
	private FormComponent found;
	private boolean isNull;
	
	//	TODO:	Get rid of found and test original id field directly.
	@Before
	public void setUp() {
		TestSetup.setupContext();
		useCase = new ChangeIdUseCase();
	}


	//	TODO:	Fix duplication in ChangeFormatTest
	private ChangeIdRequest makeMockEmptyChangeRequest() {
		return Mockito.mock(ChangeIdRequest.class);
	}

	private ChangeIdRequest makeMockChangeRequestWithFieldValues() {
		ChangeIdRequest result = makeMockEmptyChangeRequest();
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

	//	TODO:	Fix duplication in ChangeFormatTest
	@Test(expected = UnsuccessfulUseCaseUndo.class)
	public void undoingBeforeExecutingThrowsException(){
		useCase.undo();
	}

	@Test(expected = MalformedRequest.class)
	public void executingEmptyRequestThrowsException() {
		mockRequest = makeMockEmptyChangeRequest();		
		useCase.execute(mockRequest);
	}
	//	End duplication
	
	@Test(expected = AbsentFormComponentChange.class)
	public void changingAbsentComponentIdThrowsException() {
		mockRequest = makeMockChangeRequestWithFieldValues();	
		useCase.execute(mockRequest);		
	}
	
	@Test
	public void executingWellFormedRequestChangesComponentId() {
		addUnknownFormComponent();
		mockRequest = makeMockChangeRequestWithFieldValues();
		
		useCase.execute(mockRequest);	
		found = FormComponentUtilities.findFormComponent("name");
		isNull = FormComponentUtilities.isComponentNull(found);
		
		assertThat(isNull, is(false));
		assertThat(original, is(found));
	}
	
	@Test
	public void undoingSuccessfulIdChangeRevertsId() {
		addUnknownFormComponent();
		mockRequest = makeMockChangeRequestWithFieldValues();		
		useCase.execute(mockRequest);
		
		UndoableUseCase mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();
		
		found = FormComponentUtilities.findFormComponent("unknown");
		isNull = FormComponentUtilities.isComponentNull(found);
		
		assertThat(isNull, is(false));
		assertThat(original, is(found));
	}

}
