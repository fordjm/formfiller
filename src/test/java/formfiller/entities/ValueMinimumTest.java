package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class ValueMinimumTest {
	ValueMinimum valueMinimum;
	
	@Before
	public void setUp(){
		valueMinimum = new ValueMinimum("min");
	}
	
	public class GivenACandidate{
		Object candidate;
		
		public class GivenAnInvalidCandidate{
			
			@Before
			public void givenAnInvalidCandidate(){
				candidate = "";
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.isSatisfiedBy(candidate));
			}
		}	
		
		public class GivenAValidCandidateLessThanMinimum{
			
			@Before
			public void givenAValidCandidateLessThanMinimum(){
				candidate = "joe";
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMinimum.isSatisfiedBy(candidate));
			}
		}
		
		public class GivenAValidCandidateGreaterThanMinimum{
			
			@Before
			public void givenAValidCandidateLessThanMinimum(){
				candidate = "moe";
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMinimum.isSatisfiedBy(candidate));
			}
		}
	}
}
