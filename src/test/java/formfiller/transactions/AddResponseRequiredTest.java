package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.persistence.FormWidget;

public class AddResponseRequiredTest {

	@Test
	public void canSetResponseRequired() {
		Transaction t = new AddResponseRequired(true);
		t.execute();
		assertTrue(FormWidget.isResponseRequired());
	}
}
