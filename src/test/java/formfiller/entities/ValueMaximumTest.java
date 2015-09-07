package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class ValueMaximumTest {
	ValueMaximum valueMaximum;
	
	@Before
	public void setUp(){
		valueMaximum = new ValueMaximum("max");
	}
	
	public class GivenAnAnswer{
		Answer answer;

		private Answer makeMockAnswer(String content) {
			Answer result = new Answer();
			result.content = content;
			return result;
		}
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				answer = makeMockAnswer("");
				valueMaximum.constrain(null);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.isSatisfied());
			}
		}	
		
		public class GivenAValidAnswerLessThanMaximum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				answer = makeMockAnswer("joe");
				valueMaximum.constrain(answer.content);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMaximum.isSatisfied());
			}
		}
		
		public class GivenAValidAnswerGreaterThanMaximum{
			
			@Before
			public void givenAValidAnswerGreaterThanMaximum(){
				answer = makeMockAnswer("moe");
				valueMaximum.constrain(answer);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.isSatisfied());
			}
		}
	}
}
