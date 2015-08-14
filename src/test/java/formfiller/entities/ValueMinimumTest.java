package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class ValueMinimumTest<T> {
	ValueMinimum<T> valueMinimum;
	
	@Before
	public void setUp(){
		valueMinimum = new ValueMinimum<T>((T) "min");
	}
	
	public class GivenAResponse{
		Answer response;
		
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				response = MockCreation.makeMockResponse(false);
			}
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.satisfiesConstraint());
			}
		}	
		public class GivenAValidResponseLessThanMinimum{
			@Before
			public void givenAValidResponseLessThanMinimum(){
				response = MockCreation.makeMockNameResponse("joe");
				valueMinimum.wrap(response);
			}
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.satisfiesConstraint());
			}
		}
		public class GivenAValidResponseGreaterThanMinimum{
			@Before
			public void givenAValidResponseLessThanMinimum(){
				response = MockCreation.makeMockNameResponse("moe");
				valueMinimum.wrap(response);
			}
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMinimum.satisfiesConstraint());
			}
		}
	}
}
