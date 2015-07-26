package formfiller.transactions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import formfiller.persistence.FormWidget;

public class AddResponseTest {

	@Test
	public void canAddNewResponse() {
		Transaction t = new AddResponse<String>("Joe");
		
		t.execute();
		
		assertEquals("Joe", FormWidget.getResponse().content());
	}
}