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
	ValueMinimum valueMinimum;
	
	@Before
	public void setUp(){
		valueMinimum = new ValueMinimum("min");
	}
	
	public class GivenAnAnswer{
		ConstrainableAnswer answer;
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				answer = AnswerMocker.makeMockAnswer(false);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.isSatisfied());
			}
		}	
		
		public class GivenAValidAnswerLessThanMinimum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				answer = AnswerMocker.makeMockNameAnswer("joe");
				valueMinimum.constrain("joe");
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.isSatisfied());
			}
		}
		
		public class GivenAValidAnswerGreaterThanMinimum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				answer = AnswerMocker.makeMockNameAnswer("moe");
				valueMinimum.constrain("moe");
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMinimum.isSatisfied());
			}
		}
	}
}
