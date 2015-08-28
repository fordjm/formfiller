package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.utilities.AnswerMocker;
import formfiller.utilities.QuestionMocker;

// Q:	Would extracting duplicate set() calls obscure what we're testing?
public class FormComponentTest {	
	private FormComponent formComponent;

	@Before
	public void setUp(){
		formComponent = new FormComponent();
	}
	
	@Test
	public void newFormComponent_HasEmptyStringId(){
		assertThat(formComponent.id, is(""));
	}
	
	@Test
	public void settingANullQuestion_SetsANewNoQuestion(){
		formComponent.setQuestion(null);
		
		assertThat(formComponent.question, is(instanceOf(NoQuestion.class)));
	}
	
	@Test
	public void settingANullAnswer_SetsAnswerImplDotNone(){
		formComponent.setAnswer(null);
		
		assertThat(formComponent.answer, is(AnswerImpl.NONE));
	}

	@Test
	public void settingANonNullQuestion_SetsTheGivenQuestion() {
		Prompt mockQuestion = QuestionMocker.makeMockNameQuestion();
		
		formComponent.setQuestion(mockQuestion);
		
		assertThat(formComponent.question, is(mockQuestion));
	}

	@Test
	public void settingANonNullAnswer_SetsTheGivenAnswer() {
		Answer mockAnswer = AnswerMocker.makeMockNameAnswer("myName");
		
		formComponent.setAnswer(mockAnswer);
		
		assertThat(formComponent.answer, is(mockAnswer));
	}
}
