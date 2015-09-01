package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class ValueMinimumTest<T> {
	ValueMinimum<T> valueMinimum;
	
	@Before
	public void setUp(){
		valueMinimum = new ValueMinimum<T>((T) "min");
	}
	
	public class GivenAnAnswer{
		Answer response;
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				response = AnswerMocker.makeMockAnswer(false);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.satisfiesConstraint());
			}
		}	
		
		public class GivenAValidAnswerLessThanMinimum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				response = AnswerMocker.makeMockNameAnswer("joe");
				valueMinimum.wrap(response);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.satisfiesConstraint());
			}
		}
		
		public class GivenAValidAnswerGreaterThanMinimum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				response = AnswerMocker.makeMockNameAnswer("moe");
				valueMinimum.wrap(response);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMinimum.satisfiesConstraint());
			}
		}
	}
}
