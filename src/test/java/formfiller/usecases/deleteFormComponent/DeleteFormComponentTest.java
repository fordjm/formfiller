package formfiller.usecases.deleteFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.request.models.DeleteFormComponentRequest;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.TestSetup;

public class DeleteFormComponentTest {
	private DeleteFormComponentUseCase deleteFormComponent;
	private DeleteFormComponentRequest mockRequest;
	private String componentId;
	private UndoableUseCase mostRecent;
	private FormComponent found;

	@Before
	public void setUp() {
		TestSetup.setupContext();
		deleteFormComponent = new DeleteFormComponentUseCase();
		componentId = "name";
	}

	private DeleteFormComponentRequest makeMockRequest(String requestedId) {
		DeleteFormComponentRequest result = 
				Mockito.mock(DeleteFormComponentRequest.class);
		result.componentId = requestedId;
		return result;
	}

	private void executeWellFormedRequest() {
		FormComponent mockComponent = 
				FormComponentMocker.makeMockFormComponent(componentId);
		FormFillerContext.formComponentGateway.save(mockComponent);
		mockRequest = makeMockRequest(componentId);		
		deleteFormComponent.execute(mockRequest);
	}

	private FormComponent findComponent() {
		return FormFillerContext.formComponentGateway.find(componentId);
	}

	@Test
	public void deleteFormComponentImplementsUndoableUseCase() {
		assertThat(deleteFormComponent, instanceOf(UndoableUseCase.class));
	}

	@Test(expected = NullPointerException.class)
	public void executingNullThrowsException() {
		deleteFormComponent.execute(null);
	}

	@Test(expected = DeleteFormComponentUseCase.AbsentElementDeletion.class)
	public void deletingAbsentElementThrowsException() {
		mockRequest = makeMockRequest("notFound");
		deleteFormComponent.execute(mockRequest);
	}
	
	@Test(expected = UndoableUseCaseExecution.UnsuccessfulUseCaseUndo.class)
	public void undoingBeforeExecutingThrowsException(){
		deleteFormComponent.undo();
	}

	//	TODO:	Clean up duplication.
	@Test
	public void executingWellFormedRequestRemovesFormComponent() {
		executeWellFormedRequest();
		
		mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		found = findComponent();
		
		assertEquals(mostRecent, deleteFormComponent);
		assertThat(found, is(NullFormComponents.NULL));
	}

	@Test
	public void undoingSuccessfulDeletionRestoresFormComponent() {
		executeWellFormedRequest();
		
		mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();		
		found = findComponent();

		assertThat(found, not(NullFormComponents.NULL));
	}
	
}
