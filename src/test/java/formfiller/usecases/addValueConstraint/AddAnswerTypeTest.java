package formfiller.usecases.addValueConstraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Unstructured;
import formfiller.request.models.AddAnswerTypeRequest;
import formfiller.usecases.addFormatConstraint.UnitTestSetupUtilities;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.TestSetup;
import formfiller.utilities.UndoableUseCaseExecutionCommonTests;

public class AddAnswerTypeTest {
	private AddAnswerTypeUseCase useCase;
	private AddAnswerTypeRequest mockRequest;

	private AddAnswerTypeRequest makeEmptyMockAddAnswerTypeRequest() {
		return Mockito.mock(AddAnswerTypeRequest.class);
	}

	private AddAnswerTypeRequest makeMockAddAnswerTypeRequestWithFieldValues(
			String componentId, Type type) {
		AddAnswerTypeRequest result = makeEmptyMockAddAnswerTypeRequest();
		result.componentId = componentId;
		result.type = type;
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		useCase = new AddAnswerTypeUseCase();
		UnitTestSetupUtilities.addFormComponentToChange(new Unstructured());
	}
	
	@Test
	public void commonTestsPass() {
		boolean result = 
				UndoableUseCaseExecutionCommonTests.runTestsOnUseCase(useCase);
		assertThat(result, is(true));
	}
	
	@Test(expected = UndoableUseCaseExecution.MalformedRequest.class)
	public void executingEmptyRequestThrowsException() {
		mockRequest = makeEmptyMockAddAnswerTypeRequest();
		useCase.execute(mockRequest);
	}

	@Test
	public void addingAnswerTypeInt_RequiresIntAnswer() {
		final int EXAMPLE_INT = 13;
		
		mockRequest = makeMockAddAnswerTypeRequestWithFieldValues("toChange", int.class);		
		useCase.execute(mockRequest);
		
		FormComponent found = FormComponentUtilities.find("toChange");
		AnswerType typeConstraint = getTypeConstraint(found.validator.constraints);
		boolean requiresType = typeConstraint.requiresType(int.class);
		boolean isSatisfied = typeConstraint.isSatisfiedBy(EXAMPLE_INT);
		
		assertThat(requiresType, is(true));
		assertThat(isSatisfied, is(true));
	}

	//	TODO:	Find a better way (look at java.lang.Class documentation)
	private AnswerType getTypeConstraint(Collection<Constrainable> constraints) {
		for (Constrainable constraint : constraints)
			if (constraint instanceof AnswerType)
				return (AnswerType) constraint;
		return null;
	}

}
