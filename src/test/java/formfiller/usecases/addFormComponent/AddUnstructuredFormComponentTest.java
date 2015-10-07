package formfiller.usecases.addFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.entities.format.Unstructured;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution;

public class AddUnstructuredFormComponentTest {
	private AddFormComponentUseCase addUnstructured;
	private AddFormComponentRequest request;

	@Before
	public void setUp() {
		addUnstructured = new AddFormComponentUseCase();
	}

	private AddFormComponentRequest makeMockAddUnstructuredRequest() {
		return Mockito.mock(AddFormComponentRequest.class);
	}

	private AddFormComponentRequest makeMockAddUnstructuredRequest(
			String questionId, String questionContent, Type answerType) {
		AddFormComponentRequest result = 
				makeMockAddUnstructuredRequest();
		result.componentId = questionId;
		result.questionContent = questionContent;
		result.answerType = answerType;	
		result.format = new Unstructured();
		return result;
	}

	private UndoableUseCase getMostRecentlyExecutedUseCase() {
		return Context.executedUseCases.getMostRecent();
	}

	@Test
	public void implementsUndoableUseCase() {		
		assertThat(addUnstructured, is(instanceOf(UndoableUseCase.class)));
	}
	
	@Test(expected = UndoableUseCaseExecution.UnsuccessfulUseCaseUndo.class)
	public void undoingBeforeExecutingThrowsException(){
		addUnstructured.undo();
	}
	
	@Test
	public void testDefaultAddFormComponentRequest(){
		request = new AddFormComponentRequest();
		assertThat(request, is(instanceOf(Request.class)));
		assertThat(request.name, is("Request"));
		assertThat(request.componentId, is(""));
		assertThat(request.questionContent, is(""));
		assertThat(request.minAnswerCount, is(0));
		assertThat(request.maxAnswerCount, is(1));
		assertEquals(Object.class, request.answerType);
	}

	@Test(expected = NullPointerException.class)
	public void executingNull_DoesNotAddUseCaseToExecutedUseCases() {
		addUnstructured.execute(null);
	}
	
	@Test(expected = AddFormComponentUseCase.MalformedRequest.class)
	public void testExecutingMalformedRequest() {
		request = makeMockAddUnstructuredRequest();		
		addUnstructured.execute(request);
	}

	@Test
	public void testExecutingWellFormedRequest() {
		request = makeMockAddUnstructuredRequest("questionId", 
						"questionContent", String.class);		
		addUnstructured.execute(request);
		
		FormComponent addedComponent = 
				Context.formComponentGateway.find("questionId");
		assertThat(addedComponent.id, is(request.componentId));
		assertThat(addedComponent.answer, is(Answer.NONE));
		assertThat(addedComponent.format, is(instanceOf(Unstructured.class)));
		
		Question addedQuestion = addedComponent.question;
		assertThat(request.componentId, is(addedQuestion.id));
		assertThat(request.questionContent, is(addedQuestion.content));
		
		
		AnswerValidator addedValidator = addedComponent.validator;
		assertNotNull(addedValidator);
		
		UndoableUseCase mostRecent = getMostRecentlyExecutedUseCase();
		assertEquals(mostRecent, addUnstructured);		
	}

	@Test
	public void undoingSuccessfulFormComponentAdditionRemovesFormComponent() {
		request = makeMockAddUnstructuredRequest("questionId", 
						"questionContent", String.class);		
		addUnstructured.execute(request);	
		
		UndoableUseCase mostRecent = getMostRecentlyExecutedUseCase();
		mostRecent.undo();
		FormComponent found = Context.formComponentGateway.find("questionId");
		
		assertEquals(found, NullFormComponents.NULL);
	}
	
}
