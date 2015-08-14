package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class ValueMaximumTest<T> {
	ValueMaximum<T> valueMaximum;
	
	@Before
	public void setUp(){
		valueMaximum = new ValueMaximum<T>((T) "max");
	}
	
	public class GivenAResponse{
		Answer response;
		
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				response = MockCreation.makeMockResponse(false);
				valueMaximum.wrap(response);
			}
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.satisfiesConstraint());
			}
		}	
		public class GivenAValidResponseLessThanMaximum{
			@Before
			public void givenAValidResponseLessThanMinimum(){
				response = MockCreation.makeMockNameResponse("joe");
				valueMaximum.wrap(response);
			}
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMaximum.satisfiesConstraint());
			}
		}
		public class GivenAValidResponseGreaterThanMaximum{
			@Before
			public void givenAValidResponseGreaterThanMaximum(){
				response = MockCreation.makeMockNameResponse("moe");
				valueMaximum.wrap(response);
			}
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.satisfiesConstraint());
			}
		}
	}
}
