package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.Navigator;
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
		public void gettingCurrent_ReturnsStartFormComponent() {	
			FormComponent currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("start"));
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
		
		private void move(Navigator.Direction direction, int times){
			for (int i=0; i<times; ++i)
				navigator.move(direction);
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
		public void movingForwardOnce_ReturnsTheGivenFormComponent(){
			navigator.move(Navigator.Direction.FORWARD);
			
			currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("name"));
		}
		
		@Test
		public void movingForwardTwice_ReturnsQuestionDotEnd(){
			move(Navigator.Direction.FORWARD, 2);
			
			currentFormComponent = navigator.getCurrent();
			
			assertThat(currentFormComponent.id, is("end"));
		}
		
	}

}
