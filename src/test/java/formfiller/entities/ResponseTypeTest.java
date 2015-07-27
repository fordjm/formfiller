package formfiller.entities;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import formfiller.utilities.TestUtil;

public class ResponseTypeTest<T> {

	// Ugly comment:  Should have a subclass for each data type.
	@Test
	public void stringSatisfiesDataTypeConstraint() {
		String string = "Joe";
		ResponseImpl<String> response = TestUtil.createMockStringResponseImpl(0, string);
		ResponseType<String> t = new ResponseType<String>(response, string.getClass());
		assertTrue(t.satisfiesConstraint(string));
	}

	@Test
	public void integerSatisfiesDataTypeConstraint() {
		Integer integer = 23;
		ResponseImpl<Integer> response = TestUtil.createMockIntegerResponseImpl(0, integer);
		ResponseType<Integer> t = new ResponseType<Integer>(response, integer.getClass());
		assertTrue(t.satisfiesConstraint(integer));
	}

	@Test
	public void dateSatisfiesDataTypeConstraint() {
		Date date = new GregorianCalendar().getTime();
		ResponseImpl<Date> response = TestUtil.createMockDateResponseImpl(0, date);
		ResponseType<Date> t = new ResponseType<Date>(response, date.getClass());
		assertTrue(t.satisfiesConstraint(date));
	}
}
