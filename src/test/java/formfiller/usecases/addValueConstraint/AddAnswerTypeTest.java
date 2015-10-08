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
	private FormComponent found;
	private AnswerType typeConstraint;

	private AddAnswerTypeRequest makeEmptyMockAddAnswerTypeRequest() {
		return Mockito.mock(AddAnswerTypeRequest.class);
	}

	private AddAnswerTypeRequest makeMockAddAnswerTypeRequestWithFieldValues(
			Type type) {
		AddAnswerTypeRequest result = makeEmptyMockAddAnswerTypeRequest();
		result.componentId = "toChange";
		result.type = type;
		return result;
	}

	//	TODO:	Find a better way (look at java.lang.Class documentation)
	//			Consider wrapping collection boundary or adding Untyped (null) answer type.
	private AnswerType getTypeConstraint(Collection<Constrainable> constraints) {
		for (Constrainable constraint : constraints)
			if (constraint instanceof AnswerType)
				return (AnswerType) constraint;
		return null;
	}

	private boolean componentRequiresAnswerType(Type type) {
		return typeConstraint.requiresType(type);
	}

	private boolean answerContentSatisfiesConstraint(Object content) {
		return typeConstraint.isSatisfiedBy(content);
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
		mockRequest = makeMockAddAnswerTypeRequestWithFieldValues(int.class);		
		useCase.execute(mockRequest);
		
		found = FormComponentUtilities.find("toChange");
		typeConstraint = getTypeConstraint(found.validator.constraints);
		
		assertThat(componentRequiresAnswerType(int.class), is(true));
		assertThat(answerContentSatisfiesConstraint(EXAMPLE_INT), is(true));
		assertThat(answerContentSatisfiesConstraint(13.0), is(false));
		assertThat(answerContentSatisfiesConstraint("thirteen"), is(false));
	}

	@Test
	public void addingAnswerTypeString_RequiresStringAnswer() {
		mockRequest = makeMockAddAnswerTypeRequestWithFieldValues(String.class);		
		useCase.execute(mockRequest);
		
		found = FormComponentUtilities.find("toChange");
		typeConstraint = getTypeConstraint(found.validator.constraints);
		
		assertThat(componentRequiresAnswerType(String.class), is(true));
		assertThat(answerContentSatisfiesConstraint("myString"), is(true));
	}
	
	@Test
	public void undoingSuccessfulAdditionRemovesTypeRequirement() {
		mockRequest = makeMockAddAnswerTypeRequestWithFieldValues(int.class);
		
		useCase.execute(mockRequest);
		useCase.undo();
		found = FormComponentUtilities.find(mockRequest.componentId);
		typeConstraint = getTypeConstraint(found.validator.constraints);
		
		assertThat(typeConstraint, equalTo(null));
	}

}
