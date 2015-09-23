package formfiller.usecases.addFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.AddUnstructuredFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCase;

public class AddUnstructuredFormComponentTest {
	private AddUnstructuredFormComponentUseCase addUnstructured;

	@Before
	public void setUp() {
		addUnstructured = new AddUnstructuredFormComponentUseCase();
	}

	private AddUnstructuredFormComponentRequest makeMockAddUnstructuredRequest() {
		return Mockito.mock(AddUnstructuredFormComponentRequest.class);
	}

	private AddUnstructuredFormComponentRequest makeMockAddUnstructuredRequest(
			String questionId, String questionContent, Type answerType) {
		AddUnstructuredFormComponentRequest result = makeMockAddUnstructuredRequest();
		result.questionId = questionId;
		result.questionContent = questionContent;
		result.answerType = answerType;		
		return result;
	}

	@Test
	public void implementsUndoableUseCase() {		
		assertThat(addUnstructured, is(instanceOf(UndoableUseCase.class)));
	}
	
	@Test
	public void testDefaultAddFormComponentRequest(){
		AddFormComponentRequest request = 
				new AddFormComponentRequest();
		assertThat(request, is(instanceOf(Request.class)));
		assertThat(request.name, is("Request"));
		assertThat(request.questionId, is(""));
		assertThat(request.questionContent, is(""));
		assertThat(request.minAnswerCount, is(0));
		assertThat(request.maxAnswerCount, is(1));
		assertEquals(Object.class, request.answerType);
	}

	@Test
	public void executingNull_DoesNotAddUseCaseToExecutedUseCases() {
		addUnstructured.execute(null);
		UndoableUseCase mostRecent = 
				FormFillerContext.executedUseCases.getMostRecent();

		// TODO:	assert correct error-handling behavior
		assertNotEquals(mostRecent, addUnstructured);
	}
	
	//	TODO:	How to handle malformed request?	
	@Test
	public void testExecutingMalformedRequest() {
		AddUnstructuredFormComponentRequest request = 
				makeMockAddUnstructuredRequest();
		
		addUnstructured.execute(request);
		UndoableUseCase mostRecent = 
				FormFillerContext.executedUseCases.getMostRecent();

		// TODO:	assert correct error-handling behavior
		assertNotEquals(mostRecent, addUnstructured);
	}

	@Test
	public void testExecutingWellFormedRequest() {
		AddUnstructuredFormComponentRequest request = 
				makeMockAddUnstructuredRequest("questionId", "questionContent", 
						String.class);		
		addUnstructured.execute(request);
		
		FormComponent addedComponent = 
				FormFillerContext.formComponentGateway.find("questionId");
		assertThat(addedComponent.id, is(request.questionId));
		
		Question addedQuestion = addedComponent.question;
		assertThat(request.questionId, is(addedQuestion.id));
		assertThat(request.questionContent, is(addedQuestion.content));
		
		Answer addedAnswer = addedComponent.answer;
		assertThat(addedAnswer, is(Answer.NONE));
		
		AnswerFormat addedFormat = addedComponent.format;
		assertThat(addedFormat, is(instanceOf(Unstructured.class)));
		
		AnswerValidator addedValidator = addedComponent.validator;
		assertNotNull(addedValidator);
	}
}
