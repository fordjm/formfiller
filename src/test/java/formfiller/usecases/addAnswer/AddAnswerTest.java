package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.ConstrainableAnswer;
import formfiller.entities.FormComponent;
import formfiller.entities.Question;
import formfiller.request.models.AddAnswerRequest;
import formfiller.usecases.addAnswer.AddAnswerUseCase;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class AddAnswerTest {
	private AddAnswerUseCase addAnswer;
	private AddAnswerRequest addAnswerRequest;
	private ConstrainableAnswer foundAnswer;	

	private AddAnswerRequest makeAddAnswerRequest(String questionId, Object content) {
		AddAnswerRequest result = new AddAnswerRequest();
		result.questionId = questionId;
		result.content = content;
		return result;
	}
	
	private ConstrainableAnswer findAnswerByName(String name) {
		FormComponent component = FormFillerContext.formComponentGateway.find(name);
		return component.answer;
	}
	
	private Object returnAnswerContent(ConstrainableAnswer foundAnswer) {
		return foundAnswer.getContent();
	}
	
	private void addMockFormComponentsToGateway(){
		FormComponent nameComponent = makeMockFormComponent(QuestionMocker.makeMockNameQuestion());
		FormComponent ageComponent = makeMockFormComponent(QuestionMocker.makeMockAgeQuestion());
		saveFormComponents(nameComponent, ageComponent);
	}
	
	private FormComponent makeMockFormComponent(Question mockQuestion){
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = mockQuestion.getId();
		result.question = mockQuestion;
		result.answer = ConstrainableAnswer.NONE;
		return result;
	}

	private void saveFormComponents(FormComponent... formComponents) {
		for (FormComponent formComponent : formComponents)
			FormFillerContext.formComponentGateway.save(formComponent);
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
	
	@Test
	public void cannotAddEmptyStringAnswer() {
		addAnswerRequest = makeAddAnswerRequest("name", "");
		
		addAnswer.execute(addAnswerRequest);
		foundAnswer = findAnswerByName("name");
		
		// Presenter result should be,  "No answer was received.  Please try again."
		assertThat(foundAnswer, is(ConstrainableAnswer.NONE));
	}
	
	@Test
	public void canAddNonEmptyStringAnswer() {
		addAnswerRequest = makeAddAnswerRequest("name", "myName");
		
		addAnswer.execute(addAnswerRequest);
		foundAnswer = findAnswerByName("name");
		Object content = returnAnswerContent(foundAnswer);
		
		// Presenter result should be,  "Added answer, 'myName.'"
		assertEquals("myName", content);
	}
	
	@Test
	public void canAddIntAnswer() {
		final int RETIREMENT_AGE = 65;
		addAnswerRequest = makeAddAnswerRequest("age", RETIREMENT_AGE);
		
		addAnswer.execute(addAnswerRequest);
		foundAnswer = findAnswerByName("age");
		Object content = returnAnswerContent(foundAnswer);
		
		// Presenter result should be,  "Added answer, '65.'"
		assertEquals(RETIREMENT_AGE, content);
	}
	//	TODO:	Handle validation and multiple answers.
}