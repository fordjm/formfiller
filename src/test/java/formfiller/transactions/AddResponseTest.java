package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.persistence.PromptWidget;

public class AddResponseTest {

	@Test
	public void canAddNewResponse() {
		Transaction t = new AddResponse("Joe");
		
		t.execute();
		
		assertEquals("Joe", PromptWidget.getResponse());
	}
}