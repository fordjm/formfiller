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
import formfiller.utilities.FormComponentMocker;
import formfiller.utilities.QuestionMocker;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class NavigatorTest {
	private Navigator navigator;
	private FormComponent currentFormComponent;
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		navigator = new Navigator();		
	}
	
	public class GivenNoFormComponents {

		@Test
		public void gettingCurrent_ReturnsFormComponentDotEnd() {	
			FormComponent currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
		@Test
		public void movingBackward_ReturnsFormComponentDotStart(){
			navigator.move(Navigator.Direction.BACKWARD);
			
			currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingForward_ReturnsFormComponentDotEnd(){
			navigator.move(Navigator.Direction.FORWARD);
			
			currentFormComponent = navigator.getCurrent();
			
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
			navigator.move(Navigator.Direction.BACKWARD);
			
			currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("start"));
		}
		
		@Test
		public void movingInPlace_ReturnsTheGivenFormComponent(){
			navigator.move(Navigator.Direction.IN_PLACE);
			
			currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("name"));
		}
		
		@Test
		public void movingForward_ReturnsQuestionDotEnd(){
			navigator.move(Navigator.Direction.FORWARD);
			
			currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
	}

}
