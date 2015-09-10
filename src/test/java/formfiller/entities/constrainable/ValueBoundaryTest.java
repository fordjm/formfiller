package formfiller.entities.constrainable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.constrainable.ValueBoundary;
import formfiller.entities.constrainable.ValueOverBoundary;
import formfiller.entities.constrainable.ValueUnderBoundary;

@RunWith(HierarchicalContextRunner.class)
public class ValueBoundaryTest {
	private ValueBoundary valueBoundary;
	
	public class ValueUnderBoundaryContext {		
		@Before
		public void setUp() {
			valueBoundary = new ValueUnderBoundary("max");
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
		public void equalValue_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("max"), is(false));
		}	
		
		@Test
		public void highValue_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("moe"), is(false));
		}
	}
	
	public class ValueOverBoundaryContext {		
		@Before
		public void setUp() {
			valueBoundary = new ValueOverBoundary("min");
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
		public void equalValue_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("min"), is(false));
		}	
		
		@Test
		public void highValue_SatisfiesConstraint() {
			assertThat(valueBoundary.isSatisfiedBy("moe"), is(true));
		}
	}
}
