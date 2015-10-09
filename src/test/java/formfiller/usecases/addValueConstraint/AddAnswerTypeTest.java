package formfiller.usecases.addValueConstraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.Answer;
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

	private boolean componentRequiresAnswerType(Type type) {
		return found.validator.requiresType(type);
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
		
		assertThat(found.validator.isValid(makeMockAnswer(EXAMPLE_INT)), is(true));
		assertThat(found.validator.isValid(makeMockAnswer(13.0)), is(false));
		assertThat(found.validator.isValid(makeMockAnswer("thirteen")), is(false));
	}

	private Answer makeMockAnswer(Object content) {
		Answer result = Mockito.mock(Answer.class);
		result.questionId = "toChange";
		result.content = content;
		return result;
	}

	@Test
	public void addingAnswerTypeString_RequiresStringAnswer() {
		mockRequest = makeMockAddAnswerTypeRequestWithFieldValues(String.class);		
		useCase.execute(mockRequest);
		
		found = FormComponentUtilities.find("toChange");
		
		assertThat(componentRequiresAnswerType(String.class), is(true));
		assertThat(found.validator.isValid(makeMockAnswer("myString")), is(true));
	}
	
	@Test
	public void undoingSuccessfulAdditionRemovesTypeRequirement() {
		mockRequest = makeMockAddAnswerTypeRequestWithFieldValues(int.class);
		
		useCase.execute(mockRequest);
		useCase.undo();
		found = FormComponentUtilities.find(mockRequest.componentId);
		
		assertThat(componentRequiresAnswerType(String.class), is(false));
	}

}
