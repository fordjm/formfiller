package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class ValueMaximumTest<T> {
	ValueMaximum<T> valueMaximum;
	
	@Before
	public void setUp(){
		valueMaximum = new ValueMaximum<T>((T) "max");
	}
	
	public class GivenAnAnswer{
		Answer response;
		
		public class GivenAnInvalidAnswer{
			
			@Before
			public void givenAnInvalidAnswer(){
				response = AnswerMocker.makeMockAnswer(false);
				valueMaximum.wrap(response);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.satisfiesConstraint());
			}
		}	
		
		public class GivenAValidAnswerLessThanMaximum{
			
			@Before
			public void givenAValidAnswerLessThanMinimum(){
				response = AnswerMocker.makeMockNameAnswer("joe");
				valueMaximum.wrap(response);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMaximum.satisfiesConstraint());
			}
		}
		
		public class GivenAValidAnswerGreaterThanMaximum{
			
			@Before
			public void givenAValidAnswerGreaterThanMaximum(){
				response = AnswerMocker.makeMockNameAnswer("moe");
				valueMaximum.wrap(response);
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.satisfiesConstraint());
			}
		}
	}
}
