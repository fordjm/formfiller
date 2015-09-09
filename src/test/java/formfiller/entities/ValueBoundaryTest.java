package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

//	TODO:	Pull out equals case into ValueMatches constraint.  Use OR of constraints.
@RunWith(HierarchicalContextRunner.class)
public class ValueBoundaryTest {
	private ValueBoundary valueBoundary;
	
	public class ValueMaximumContext {		
		@Before
		public void setUp() {
			valueBoundary = new ValueMaximum("max");
		}
		
		@Test
		public void null_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy(null), is(false));
		}
		
		@Test
		public void lowValue_SatisfiesConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("joe"), is(true));
		}	
		
		@Test
		public void equalValue_SatisfiesConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("max"), is(true));
		}	
		
		@Test
		public void highValue_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("moe"), is(false));
		}
	}
	
	public class ValueMinimumContext {		
		@Before
		public void setUp() {
			valueBoundary = new ValueMinimum("min");
		}
		
		@Test
		public void null_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy(null), is(false));
		}
		
		@Test
		public void lowValue_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("joe"), is(false));
		}
		
		@Test
		public void equalValue_SatisfiesConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("min"), is(true));
		}	
		
		@Test
		public void highValue_SatisfiesConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("moe"), is(true));
		}
	}
}
