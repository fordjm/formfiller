package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.persistence.PromptWidget;

public class RemoveResponseTest {

	@Before
	public void setUp() throws Exception {
		PromptWidget.setResponse("Response");
	}

	@Test
	public void nothing() {
		Transaction t = new RemoveResponse();
		
		t.execute();
		
		assertEquals("", PromptWidget.getResponse());
	}

}
