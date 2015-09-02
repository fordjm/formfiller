package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class ValueMaximumTest {
	ValueMaximum<String> valueMaximum;
	
	@Before
	public void setUp(){
		valueMaximum = new ValueMaximum<String>("max");
	}
	
	public class GivenAnAnswer{
		Answer answer;
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				answer = AnswerMocker.makeMockAnswer(false);
				valueMaximum.wrap(answer);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.satisfiesConstraint());
			}
		}	
		
		public class GivenAValidAnswerLessThanMaximum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				answer = AnswerMocker.makeMockNameAnswer("joe");
				valueMaximum.wrap(answer);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMaximum.satisfiesConstraint());
			}
		}
		
		public class GivenAValidAnswerGreaterThanMaximum{
			
			@Before
			public void givenAValidAnswerGreaterThanMaximum(){
				answer = AnswerMocker.makeMockNameAnswer("moe");
				valueMaximum.wrap(answer);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.satisfiesConstraint());
			}
		}
	}
}
