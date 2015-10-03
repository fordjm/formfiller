package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.QuestionAsked;
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class InMemoryTransporterTest {
	private Transporter transporter;
	private FormComponent currentFormComponent;
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		transporter = new InMemoryTransporter();		
	}
	
	private FormComponent getCurrentFormComponent() {
		return Context.formComponentState.getCurrent();
	}

	public class GivenNoFormComponents {

		@Test
		public void gettingCurrent_ReturnsFormComponentDotEnd() {	
			FormComponent currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			transporter.moveToElement(QuestionAsked.PREVIOUS);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingBackwardTwice_ReturnsFormComponentDotStart(){
			transporter.moveToElement(QuestionAsked.PREVIOUS);
			transporter.moveToElement(QuestionAsked.PREVIOUS);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingForward_ReturnsFormComponentDotEnd(){
			transporter.moveToElement(QuestionAsked.NEXT);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
		@Test
		public void movingForwardTwice_ReturnsFormComponentDotEnd(){
			transporter.moveToElement(QuestionAsked.NEXT);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
	}
	
	public class GivenAFormComponent {

		private void addQuestionToFormComponentGateway() {
			Context.formComponentGateway.save(makeMockNameQuestionComponent());
		}

		private FormComponent makeMockNameQuestionComponent() {			
			return makeMockFormComponent(QuestionMocker.makeMockNameQuestion());
		}
		
		private FormComponent makeMockFormComponent(Question question){
			return FormComponentMocker.makeMockFormComponent(false, 
					question, Answer.NONE, new Unstructured());
		}
		
		@Before
		public void givenAFormComponent(){
			addQuestionToFormComponentGateway();
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			transporter.moveToElement(QuestionAsked.PREVIOUS);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingInPlace_ReturnsTheGivenFormComponent(){
			transporter.moveToElement(QuestionAsked.CURRENT);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("name"));
		}
		
		@Test
		public void movingForward_ReturnsQuestionDotEnd(){
			transporter.moveToElement(QuestionAsked.NEXT);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}		
	}
}
