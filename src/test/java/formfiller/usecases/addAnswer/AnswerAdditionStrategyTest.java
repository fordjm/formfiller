package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
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
	private static List<Answer> mockAnswers;
	private AnswerAdditionStrategy strategy;
	private FormComponent component;
	private Object content;
	
	@BeforeClass
	public static void setUpMockAnswers() {
		mockAnswers = new ArrayList<Answer>();
		mockAnswers.add(AnswerMocker.makeMockAnswer("mock answer 0"));
		mockAnswers.add(AnswerMocker.makeMockAnswer("mock answer 1"));
		mockAnswers.add(AnswerMocker.makeMockAnswer("mock answer 2"));
	}
	
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
		}

		@Test
		public void testWithNoAnswer() {
			setupMockFormComponent(AnswerImpl.NONE);
			Answer mockAnswer = mockAnswers.get(0);
			
			strategy.addAnswerToComponent("componentId", mockAnswer);
			content = component.answer.getContent();

			assertThat(content, is(mockAnswer.getContent()));
			assertThat(component.answer, is(mockAnswer));
		}

		@Test(expected = IllegalStateException.class)
		public void testWithExistingAnswer() {
			Answer mockAnswer = mockAnswers.get(0);
			setupMockFormComponent(mockAnswer);
			
			strategy.addAnswerToComponent("componentId", mockAnswers.get(1));
			content = component.answer.getContent();

			assertThat(content, is(mockAnswer.getContent()));
			assertThat(component.answer, is(mockAnswer));
		}
	}
	
	public class AddMultipleAnswerContext {
		private CompositeAnswer composite;
		private Answer mockAnswer;

		@Before
		public void setUp() {
			strategy = new AddMultipleAnswer();
		}
		
		//	TODO:	Extract Cardinality class and use instead.
		private void addComponentFormat(Format format) {
			component.format = format;
		}

		@Test
		public void testWithNoAnswer() {
			setupMockFormComponent(AnswerImpl.NONE);
			mockAnswer = mockAnswers.get(0);
			addComponentFormat(new MultiOptionVariable());
			
			strategy.addAnswerToComponent("componentId", mockAnswer);
			composite = ((CompositeAnswer) component.answer);
			content = composite.getContent();
			List<Object> contentList = (List<Object>) content;
			
			assertThat(contentList.get(0), is(mockAnswer.getContent()));
			assertThat(composite.contains(mockAnswer), is(true));
		}

		//	TODO:	What else?
		@Test
		public void testWithOneAnswer() {
			setupMockFormComponent(AnswerImpl.NONE);
			addComponentFormat(new MultiOptionVariable());
			mockAnswer = mockAnswers.get(0);
			strategy.addAnswerToComponent("componentId", mockAnswer);
			
			strategy.addAnswerToComponent("componentId", mockAnswers.get(1));
			composite = ((CompositeAnswer) component.answer);

			assertThat(composite.contains(mockAnswer), is(true));
			assertThat(composite.contains(mockAnswers.get(1)), is(true));
		}

		//	TODO:	What else?
		@Test(expected = IllegalStateException.class)
		public void testWithTwoAnswers() {
			setupMockFormComponent(AnswerImpl.NONE);
			addComponentFormat(new MultiOptionVariable());
			mockAnswer = mockAnswers.get(0);
			strategy.addAnswerToComponent("componentId", mockAnswer);			
			strategy.addAnswerToComponent("componentId", mockAnswers.get(1));
			
			strategy.addAnswerToComponent("componentId", mockAnswers.get(2));
			composite = ((CompositeAnswer) component.answer);

			//	TODO:	What else?
			assertThat(composite.contains(mockAnswer), is(true));
			assertThat(composite.contains(mockAnswers.get(1)), is(true));
			assertThat(composite.contains(mockAnswers.get(2)), is(false));
		}
	}

}
