package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Unstructured;
import formfiller.request.models.AddAnswerRequest;
import formfiller.usecases.addAnswer.AddAnswerUseCase;
import formfiller.usecases.undoable.UndoableUseCaseExecution.MalformedRequest;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class AddAnswerTest {
	private AddAnswerUseCase addAnswer;
	private AddAnswerRequest addAnswerRequest;
	private Answer foundAnswer;	

	private AddAnswerRequest makeAddAnswerRequest(String questionId, Object content) {
		AddAnswerRequest result = new AddAnswerRequest();
		result.componentId = questionId;
		result.content = content;
		return result;
	}
	
	private Answer findAnswerByComponentName(String name) {
		FormComponent component = Context.formComponentGateway.find(name);
		return component.answer;
	}
	
	private void addMockFormComponentsToGateway(){
		FormComponent nameComponent = makeMockFormComponent(
				QuestionMocker.makeMockNameQuestion());
		FormComponent ageComponent = makeMockFormComponent(
				QuestionMocker.makeMockAgeQuestion());
		saveFormComponents(nameComponent, ageComponent);
	}
	
	private FormComponent makeMockFormComponent(Question mockQuestion){
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = mockQuestion.getId();
		result.question = mockQuestion;
		result.answer = AnswerImpl.NONE;
		result.format = Mockito.mock(Unstructured.class);
		result.validator = makeMockAnswerValidator();
		return result;
	}

	private AnswerValidator makeMockAnswerValidator() {
		AnswerValidator result = Mockito.mock(AnswerValidator.class);
		Mockito.when(result.accepts(Mockito.any(Answer.class))).thenReturn(true);
		return result;
	}

	private void saveFormComponents(FormComponent... formComponents) {
		for (FormComponent formComponent : formComponents)
			Context.formComponentGateway.save(formComponent);
	}

	@Before
	public void setUp() {
		TestSetup.setupContext();
		addMockFormComponentsToGateway();
		addAnswer = new AddAnswerUseCase();
	}
	
	@Test
	public void isAUseCase() {		
		assertThat(addAnswer, is(instanceOf(UseCase.class)));
	}
	
	//	TODO:	Write exception scoped to class.
	@Ignore
	@Test(expected = MalformedRequest.class)
	public void cannotAddEmptyStringAnswer() {
		addAnswerRequest = makeAddAnswerRequest("name", "");
		
		addAnswer.execute(addAnswerRequest);
		foundAnswer = findAnswerByComponentName("name");
	}
	
	@Test
	public void canAddNonEmptyStringAnswer() {
		addAnswerRequest = makeAddAnswerRequest("name", "myName");
		
		addAnswer.execute(addAnswerRequest);
		foundAnswer = findAnswerByComponentName("name");
		Object content = foundAnswer.getContent();
		
		// Presenter result should be,  "Added answer, 'myName.'"
		assertEquals("myName", content);
	}
	
	@Test
	public void canAddIntAnswer() {
		addAnswerRequest = makeAddAnswerRequest("age", 65);
		
		addAnswer.execute(addAnswerRequest);
		foundAnswer = findAnswerByComponentName("age");
		Object content = foundAnswer.getContent();
		
		// Presenter result should be,  "Added answer, '65.'"
		assertEquals(65, content);
	}
	//	TODO:	Handle validation and multiple answers.
}