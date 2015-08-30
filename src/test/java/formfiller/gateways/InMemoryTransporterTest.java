package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.entities.AnswerImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.gateways.InMemoryTransporter.Direction;
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
	
	public class GivenNoFormComponents {

		@Test
		public void gettingCurrent_ReturnsFormComponentDotEnd() {	
			FormComponent currentFormComponent = transporter.getCurrent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			transporter.move(Direction.BACKWARD);
			
			currentFormComponent = transporter.getCurrent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingForward_ReturnsFormComponentDotEnd(){
			transporter.move(Direction.FORWARD);
			
			currentFormComponent = transporter.getCurrent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
	}
	
	public class GivenAFormComponent {

		private void addQuestionToFormComponentGateway() {
			ApplicationContext.formComponentGateway.save(makeMockNameQuestionComponent());
		}

		private FormComponent makeMockNameQuestionComponent() {			
			return makeMockFormComponent(QuestionMocker.makeMockNameQuestion());
		}
		
		private FormComponent makeMockFormComponent(Prompt question){
			return FormComponentMocker.makeMockFormComponent(question, AnswerImpl.NONE);
		}
		
		@Before
		public void givenAFormComponent(){
			addQuestionToFormComponentGateway();
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			transporter.move(Direction.BACKWARD);
			
			currentFormComponent = transporter.getCurrent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingInPlace_ReturnsTheGivenFormComponent(){
			transporter.move(Direction.NONE);
			
			currentFormComponent = transporter.getCurrent();
			
			assertThat(currentFormComponent.id, is("name"));
		}
		
		@Test
		public void movingForward_ReturnsQuestionDotEnd(){
			transporter.move(Direction.FORWARD);
			
			currentFormComponent = transporter.getCurrent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
	}

}
