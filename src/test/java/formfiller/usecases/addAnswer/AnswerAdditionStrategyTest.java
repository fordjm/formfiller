package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.CompositeAnswer;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.entities.format.MultiOptionVariable;
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class AnswerAdditionStrategyTest {
	private AnswerAdditionStrategy strategy;
	private Answer mockNewAnswer;
	private FormComponent component;
	
	//	TODO:	@BeforeClass setup methods?
	
	private void setupMockFormComponent(Answer answer) {
		component = Mockito.mock(FormComponent.class);
		component.id = "componentId";
		component.answer = answer;
		Context.formComponentGateway.save(component);
	}

	public class AddSingleAnswerContext {
		@Before
		public void setUp() {
			strategy = new AddSingleAnswer();
			mockNewAnswer = AnswerMocker.makeMockAnswer("answer content");
		}

		@Test
		public void testWithNoAnswer() {
			setupMockFormComponent(AnswerImpl.NONE);
			
			strategy.addAnswerToComponent("componentId", mockNewAnswer);
			
			assertThat(component.answer, is(mockNewAnswer));
		}

		//	TODO:	Determine whether to throw exception.
		@Test
		public void testWithExistingAnswer() {
			Answer mockCurrentAnswer = 
					AnswerMocker.makeMockAnswer("existing content");
			setupMockFormComponent(mockCurrentAnswer);
			
			strategy.addAnswerToComponent("componentId", mockNewAnswer);
			
			assertThat(component.answer, is(mockCurrentAnswer));
		}
	}
	
	public class AddMultipleAnswerContext {
		@Before
		public void setUp() {
			strategy = new AddMultipleAnswer();
			mockNewAnswer = AnswerMocker.makeMockAnswer("answer content");
		}
		
		//	TODO:	Extract Cardinality class and use instead.
		private void addComponentFormat(Format format) {
			component.format = format;
		}

		@Test
		public void testWithNoAnswer() {
			setupMockFormComponent(AnswerImpl.NONE);
			addComponentFormat(new MultiOptionVariable());
			
			strategy.addAnswerToComponent("componentId", mockNewAnswer);
			CompositeAnswer composite = ((CompositeAnswer) component.answer);
			
			assertThat(composite.contains(mockNewAnswer), is(true));
		}
	}

}
