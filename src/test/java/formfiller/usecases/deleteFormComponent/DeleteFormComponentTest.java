package formfiller.usecases.deleteFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.request.models.RequestWithComponentId;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.TestSetup;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

public class DeleteFormComponentTest {
	private DeleteFormComponentUseCase useCase;
	private RequestWithComponentId mockRequest;
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
		Context.formComponentGateway.save(mockComponent);
	}

	private RequestWithComponentId makeMockRequest(String requestedId) {
		RequestWithComponentId result = 
				Mockito.mock(RequestWithComponentId.class);
		result.componentId = requestedId;
		return result;
	}

	@Test
	public void commonTestsPass() {
		boolean result = 
				UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
		assertThat(result, is(true));
	}

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
		
		found = Context.formComponentGateway.find(componentId);
		
		assertThat(found, is(NullFormComponents.NULL));
	}

	@Test
	public void undoingSuccessfulDeletionRestoresFormComponent() {
		addFormComponentToChange();
		mockRequest = makeMockRequest(componentId);		
		useCase.execute(mockRequest);

		useCase.undo();		
		found = Context.formComponentGateway.find(componentId);

		assertThat(found, not(NullFormComponents.NULL));
	}
	
}
