package formfiller.usecases.addFormComponent;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.Context;
import formfiller.entities.AnswerImpl;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.entities.format.Format;
import formfiller.entities.format.SingleOptionVariable;
import formfiller.entities.format.Unstructured;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution;

public class AddFormComponentTest {
	private AddFormComponentUseCase useCase;
	private AddFormComponentRequest request;

	@Before
	public void setUp() {
		useCase = new AddFormComponentUseCase();
	}

	private AddFormComponentRequest makeMockEmptyAddFormComponentRequest() {
		return Mockito.mock(AddFormComponentRequest.class);
	}

	private AddFormComponentRequest makeMockAddFormComponentRequestWithFieldValues(
			String componentId, String questionContent, Format format, Type answerType) {
		AddFormComponentRequest result = 
				makeMockEmptyAddFormComponentRequest();
		result.componentId = componentId;
		result.questionContent = questionContent;
		result.format = format;
		result.answerType = answerType;	
		return result;
	}

	private UndoableUseCase getMostRecentlyExecutedUseCase() {
		return Context.executedUseCases.getMostRecent();
	}

	@Test
	public void implementsUndoableUseCase() {		
		assertThat(useCase, is(instanceOf(UndoableUseCase.class)));
	}
	
	@Test(expected = UndoableUseCaseExecution.UnsuccessfulUseCaseUndo.class)
	public void undoingBeforeExecutingThrowsException(){
		useCase.undo();
	}

	@Test(expected = NullPointerException.class)
	public void executingNull_DoesNotAddUseCaseToExecutedUseCases() {
		useCase.execute(null);
	}
	
	@Test(expected = AddFormComponentUseCase.MalformedRequest.class)
	public void testExecutingMalformedRequest() {
		request = makeMockEmptyAddFormComponentRequest();		
		useCase.execute(request);
	}

	@Test
	public void testExecutingWellFormedRequest() {
		request = makeMockAddFormComponentRequestWithFieldValues("componentId", 
						"questionContent", new Unstructured(), String.class);		
		useCase.execute(request);
		
		FormComponent addedComponent = 
				Context.formComponentGateway.find("componentId");
		assertThat(addedComponent.id, is(request.componentId));
		assertThat(addedComponent.answer, is(AnswerImpl.NONE));
		assertThat(addedComponent.format, is(instanceOf(Unstructured.class)));
		
		Question addedQuestion = addedComponent.question;
		assertThat(request.componentId, is(addedQuestion.getId()));
		assertThat(request.questionContent, is(addedQuestion.getContent()));
		
		
		AnswerValidator addedValidator = addedComponent.validator;
		assertNotNull(addedValidator);
		
		UndoableUseCase mostRecent = getMostRecentlyExecutedUseCase();
		assertEquals(mostRecent, useCase);		
	}

	@Test
	public void undoingSuccessfulFormComponentAdditionRemovesFormComponent() {
		request = makeMockAddFormComponentRequestWithFieldValues("componentId", 
						"questionContent", new SingleOptionVariable(), String.class);		
		useCase.execute(request);	
		useCase.undo();
		FormComponent found = Context.formComponentGateway.find("componentId");
		
		assertEquals(found, NullFormComponents.NULL);
	}
	
}
