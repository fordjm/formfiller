package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.ResponseImpl;
import formfiller.persistence.FormWidget;

public class RemoveResponseTest {

	@Before
	public void setUp() throws Exception {
		FormWidget.addResponse(new ResponseImpl(0, "Response"));
	}

	@Test
	public void nothing() {
		Transaction t = new RemoveResponse();
		
		t.execute();
		
		assertEquals("", FormWidget.getResponse().getContent());
	}

}
