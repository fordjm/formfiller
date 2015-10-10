package formfiller.utilities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;

public class TestSetupTest {
	private FormComponent component;
	private String id;
	private String questionContent; 
	private String answerContent; 
	private boolean requiresAnswer;

	private void setExpectedValues(String id, String questionContent, 
			String answerContent, boolean requiresAnswer) {
		this.id = id;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.requiresAnswer = requiresAnswer;
	}

	private void assertThat_ReturnedComponent_HasExpectedFieldValues(
			FormComponent formComponent) {
		assertThat_FormComponent_HasExpectedId(formComponent);
		assertThat_FormComponent_HasExpectedQuestionContent(formComponent);
		assertThat_FormComponent_HasExpectedAnswerContent(formComponent);
		assertThat_FormComponent_HasExpectedAnswerRequirement(formComponent);
	}

	private void assertThat_FormComponent_HasExpectedId(FormComponent formComponent) {
		assertThat(formComponent.id, is(id));
	}

	private void assertThat_FormComponent_HasExpectedQuestionContent(FormComponent formComponent) {
		assertThat(formComponent.question.getContent(), is(questionContent));
	}

	private void assertThat_FormComponent_HasExpectedAnswerContent(FormComponent formComponent) {
		assertThat(formComponent.answer.getContent().toString(), is(answerContent));
	}

	private void assertThat_FormComponent_HasExpectedAnswerRequirement(FormComponent formComponent) {
		assertThat(formComponent.requiresAnswer, is(requiresAnswer));
	}
	
	@Before
	public void setUp() {
		TestSetup.setupSampleFormComponents();
	}

	@Test
	public void testNameComponent() {
		setExpectedValues("name", "What is your name?", "", false);
		
		component = Context.formComponentGateway.find("name");
		
		assertThat_ReturnedComponent_HasExpectedFieldValues(component);
	}

	@Test
	public void testBirthDateComponent() {
		setExpectedValues("birthDate", "What is your birth date?", 
				"November 12, 1955", false);
		
		component = Context.formComponentGateway.find("birthDate");
		
		assertThat_ReturnedComponent_HasExpectedFieldValues(component);
	}

	@Test
	public void testAgeComponent() {
		setExpectedValues("age", "What is your age?", "", true);
		
		component = Context.formComponentGateway.find("age");
		
		assertThat_ReturnedComponent_HasExpectedFieldValues(component);
	}
}
