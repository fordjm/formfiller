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
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.TestSetup;

public class DeleteFormComponentTest {
	private DeleteFormComponentUseCase useCase;
	private DeleteFormComponentRequest mockRequest;
	private String componentId;
	private FormComponent found;

	@Before
	public void setUp() {
		TestSetup.setupContext();
		useCase = new DeleteFormComponentUseCase();
		componentId = "name";
		addFormComponentToChange();
	}

	private void addFormComponentToChange() {
		FormComponent mockComponent = 
				FormComponentMocker.makeMockFormComponent(componentId);
		FormFillerContext.formComponentGateway.save(mockComponent);
	}

	private DeleteFormComponentRequest makeMockRequest(String requestedId) {
		DeleteFormComponentRequest result = 
				Mockito.mock(DeleteFormComponentRequest.class);
		result.componentId = requestedId;
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

	@Test(expected = DeleteFormComponentUseCase.AbsentElementDeletion.class)
	public void deletingAbsentElementThrowsException() {
		mockRequest = makeMockRequest("notFound");
		useCase.execute(mockRequest);
	}

	//	TODO:	Clean up duplication.
	@Test
	public void executingWellFormedRequestRemovesFormComponent() {
		addFormComponentToChange();
		mockRequest = makeMockRequest(componentId);		
		useCase.execute(mockRequest);
		
		found = FormFillerContext.formComponentGateway.find(componentId);
		
		assertThat(found, is(NullFormComponents.NULL));
	}

	@Test
	public void undoingSuccessfulDeletionRestoresFormComponent() {
		addFormComponentToChange();
		mockRequest = makeMockRequest(componentId);		
		useCase.execute(mockRequest);

		useCase.undo();		
		found = FormFillerContext.formComponentGateway.find(componentId);

		assertThat(found, not(NullFormComponents.NULL));
	}
	
}
