package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class ValueMinimumTest {
	ValueMinimum<String> valueMinimum;
	
	@Before
	public void setUp(){
		valueMinimum = new ValueMinimum<String>("min");
	}
	
	public class GivenAnAnswer{
		Answer answer;
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				answer = AnswerMocker.makeMockAnswer(false);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.satisfiesConstraint());
			}
		}	
		
		public class GivenAValidAnswerLessThanMinimum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				answer = AnswerMocker.makeMockNameAnswer("joe");
				valueMinimum.wrap(answer);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.satisfiesConstraint());
			}
		}
		
		public class GivenAValidAnswerGreaterThanMinimum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				answer = AnswerMocker.makeMockNameAnswer("moe");
				valueMinimum.wrap(answer);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMinimum.satisfiesConstraint());
			}
		}
	}
}
