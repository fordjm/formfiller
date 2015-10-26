package formfiller.entities.constrainable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.constrainable.ValueBoundary;
import formfiller.entities.constrainable.ValueMinimum;
import formfiller.entities.constrainable.ValueMaximum;

@RunWith(HierarchicalContextRunner.class)
public class ValueBoundaryTest {
	private ValueBoundary valueBoundary;
	
	public class ValueMaximumContext {		
		private Date candidate;

		@Before
		public void setUp() {
			Date max = makeDate("Oct-21-2015");
			valueBoundary = new ValueMaximum(max);
		}
		
		private Date makeDate(String input) {
			SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy");
			try {
				return format.parse(input);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Test
		public void null_DoesNotSatisfyConstraint() {
			assertThat(valueBoundary.isSatisfiedBy(null), is(false));
		}
		
		@Test
		public void lowValue_SatisfiesConstraint() {
			candidate = makeDate("May-14-2015");
			assertThat(valueBoundary.isSatisfiedBy(candidate), is(true));
		}	
		
		@Test
		public void equalValue_SatisfiesConstraint() {
			candidate = makeDate("Oct-21-2015");
			assertThat(valueBoundary.isSatisfiedBy(candidate), is(true));
		}	
		
		@Test
		public void highValue_DoesNotSatisfyConstraint() {
			candidate = makeDate("Dec-28-2015");
			assertThat(valueBoundary.isSatisfiedBy(candidate), is(false));
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
