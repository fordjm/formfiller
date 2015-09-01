package formfiller.transactions;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Answer;
import formfiller.persistence.FormWidget;
import formfiller.utilities.*;

@RunWith(HierarchicalContextRunner.class)
public class RemoveAnswerTest {
	public class GivenARemoveAnswer{
		Transaction removeAnswer;
		
		@Before
		public void givenARemoveAnswer(){
			removeAnswer = new RemoveAnswer();
		}
		
		public class GivenWidgetHasNoAnswer{
			
			@Before
			public void givenWidgetHasNoAnswer(){
				FormWidget.clear();
			}
			
			@Test(expected = IllegalStateException.class)
			public void whenRemoveAnswerRuns_ThenItThrowsAnException(){
				removeAnswer.execute();
			}		
		}
		
		public class GivenWidgetHasOneAnswer{
			Answer mockAnswer;
			
			@Before
			public void givenWidgetHasAAnswer() throws Exception {
				// Line 39 brings more support for mocking FormWidget.
				FormWidget.addPrompt(QuestionMocker.makeMockNameQuestion());
				mockAnswer = AnswerMocker.makeMockAnswer(0, "Answer", true);
				FormWidget.addAnswer(mockAnswer);
			}
			
			@Test
			public void whenRemoveAnswerRuns_ThenWidgetHasNoAnswers() {
				removeAnswer = new RemoveAnswer();				
				removeAnswer.execute();				
				assertFalse(FormWidget.hasAnswer());
			}
		}
	}
}
