package formfiller.entities;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class ResponseTypeTest<T> {

	@Test
	public void stringSatisfiesDataTypeConstraint() {
		String string = "Joe";
		ResponseType<String> r = new ResponseType<String>(string.getClass());
		assertTrue(r.satisfiesConstraint(string));
	}

	@Test
	public void integerSatisfiesDataTypeConstraint() {
		Integer integer = 23;
		ResponseType<Integer> r = new ResponseType<Integer>(integer.getClass());
		assertTrue(r.satisfiesConstraint(integer));
	}

	@Test
	public void dateSatisfiesDataTypeConstraint() {
		Date date = new GregorianCalendar().getTime();
		ResponseType<Date> r = new ResponseType<Date>(date.getClass());
		assertTrue(r.satisfiesConstraint(date));
	}
}
