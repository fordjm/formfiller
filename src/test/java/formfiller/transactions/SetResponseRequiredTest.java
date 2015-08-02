package formfiller.transactions;

import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.persistence.FormWidget;

public class SetResponseRequiredTest {

	@Test
	public void canSetResponseRequired() {
		Transaction t = new SetResponseRequired(true);
		t.execute();
		assertTrue(FormWidget.isResponseRequired());
	}
}
