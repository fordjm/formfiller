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
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.TestSetup;

public class DeleteFormComponentTest {
	private DeleteFormComponentUseCase deleteFormComponent;
	private DeleteFormComponentRequest mockRequest;

	@Before
	public void setUp() {
		TestSetup.setupContext();
		deleteFormComponent = new DeleteFormComponentUseCase();
	}

	private DeleteFormComponentRequest makeMockRequest(String componentId) {
		DeleteFormComponentRequest result = 
				Mockito.mock(DeleteFormComponentRequest.class);
		result.componentId = componentId;
		return result;
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
	public void executingAbsentElementThrowsException() {
		mockRequest = makeMockRequest("notFound");
		deleteFormComponent.execute(mockRequest);
	}

	@Test
	public void executingHappyCaseSucceeds() {
		String componentId = "name";
		FormComponent mockComponent = FormComponentMocker.makeMockFormComponent(componentId);
		FormFillerContext.formComponentGateway.save(mockComponent);
		mockRequest = makeMockRequest(componentId);
		
		deleteFormComponent.execute(mockRequest);
		UndoableUseCase mostRecent = 
				FormFillerContext.executedUseCases.getMostRecent();
		FormComponent foundComponent = FormFillerContext.formComponentGateway.find(componentId);
		
		assertEquals(deleteFormComponent, mostRecent);
		assertThat(foundComponent, is(NullFormComponents.NULL));
	}
}
