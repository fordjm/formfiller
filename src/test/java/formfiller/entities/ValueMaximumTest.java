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
	
	public class GivenACandidate{
		Object candidate;
		
		public class GivenAnInvalidCandidate{
			
			@Before
			public void givenAnInvalidCandidate(){
				candidate = null;
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.isSatisfiedBy(candidate));
			}
		}	
		
		public class GivenAValidCandidateLessThanMaximum{
			
			@Before
			public void givenAValidCandidateLessThanMinimum(){
				candidate = "joe";
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
				assertTrue(valueMaximum.isSatisfiedBy(candidate));
			}
		}
		
		public class GivenAValidCandidateGreaterThanMaximum{
			
			@Before
			public void givenAValidCandidateGreaterThanMaximum(){
				candidate = "moe";
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertFalse(valueMaximum.isSatisfiedBy(candidate));
			}
		}
	}
}
