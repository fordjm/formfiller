package formfiller.entities;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class ResponseTest<T> {

	@Test
	public void stringResponse_hasCorrectStringData() {
		Response<String> r = new ResponseImpl<String>(0, "Joe");
		assertEquals("Joe", r.content());
	}
	
	@Test
	public void integerResponse_hasCorrectIntegerData() {
		Response<Integer> r = new ResponseImpl<Integer>(0, 23);
		assertSame(23, r.content());
	}
	
	@Test
	public void dateResponse_hasCorrectDateData() {
		Date date = new GregorianCalendar().getTime();
		Response<Date> r = new ResponseImpl<Date>(0, date);
		assertSame(date, r.content());
	}
}
