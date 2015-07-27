package formfiller.transactions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import formfiller.persistence.FormWidget;

public class AddResponseTest {

	@Test
	public void canAddNewStringResponse() {
		Transaction t = new AddResponse<String>("Joe");
		
		t.execute();
		
		assertEquals("Joe", FormWidget.getResponse().content());
	}

	@Test
	public void canAddNewMultiStringResponse() {
		// Ugly comment:  Should never have a list in one response.
		List<String> names = Arrays.asList("Bill", "Dan", "Joe");
		Transaction t = new AddResponse<List<String>>(names);
		
		t.execute();
		
		assertEquals(names, FormWidget.getResponse().content());
	}
}