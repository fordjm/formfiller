package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.Direction;
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
		return FormFillerContext.formComponentState.getCurrent();
	}

	public class GivenNoFormComponents {

		@Test
		public void gettingCurrent_ReturnsFormComponentDotEnd() {	
			FormComponent currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			transporter.moveInDirection(Direction.BACKWARD);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingBackwardTwice_ReturnsFormComponentDotStart(){
			transporter.moveInDirection(Direction.BACKWARD);
			transporter.moveInDirection(Direction.BACKWARD);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingForward_ReturnsFormComponentDotEnd(){
			transporter.moveInDirection(Direction.FORWARD);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
		@Test
		public void movingForwardTwice_ReturnsFormComponentDotEnd(){
			transporter.moveInDirection(Direction.FORWARD);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
	}
	
	public class GivenAFormComponent {

		private void addQuestionToFormComponentGateway() {
			FormFillerContext.formComponentGateway.save(makeMockNameQuestionComponent());
		}

		private FormComponent makeMockNameQuestionComponent() {			
			return makeMockFormComponent(QuestionMocker.makeMockNameQuestion());
		}
		
		private FormComponent makeMockFormComponent(Prompt question){
			return FormComponentMocker.makeMockFormComponent(question, Answer.NONE);
		}
		
		@Before
		public void givenAFormComponent(){
			addQuestionToFormComponentGateway();
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			transporter.moveInDirection(Direction.BACKWARD);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingInPlace_ReturnsTheGivenFormComponent(){
			transporter.moveInDirection(Direction.NONE);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("name"));
		}
		
		@Test
		public void movingForward_ReturnsQuestionDotEnd(){
			transporter.moveInDirection(Direction.FORWARD);
			
			currentFormComponent = getCurrentFormComponent();
			
			assertThat(currentFormComponent.id, is("end"));
		}		
	}
}
