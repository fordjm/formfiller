package formfiller.entities.constrainable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.constrainable.ValueMatches;

@RunWith(HierarchicalContextRunner.class)
public class ValueMatchesTest {
	private ValueMatches valueMatches;
	
	@Before
	public void setUp() {
		valueMatches = new ValueMatches();
	}
	
	@Test(expected = ValueMatches.NullValueSet.class)
	public void settingValueToMatch_Null_ThrowsException() {
		valueMatches.setValue(null);
	}

	@Test
	public void withoutValueToMatch_Null_DoesNotSatisfyConstraint() {		
		assertThat(valueMatches.isSatisfiedBy(null), is(false));
	}
	
	public class GivenAValueToMatch {
		@Before
		public void setUp() {
			valueMatches.setValue(5);
		}

		@Test
		public void null_DoesNotSatisfyConstraint() {
			assertThat(valueMatches.isSatisfiedBy(null), is(false));
		}

		@Test
		public void nonMatchingValue_DoesNotSatisfyConstraint() {
			assertThat(valueMatches.isSatisfiedBy(3), is(false));
		}

		@Test
		public void matchingValue_SatisfiesConstraint() {
			assertThat(valueMatches.isSatisfiedBy(5), is(true));
		}
	}
}
