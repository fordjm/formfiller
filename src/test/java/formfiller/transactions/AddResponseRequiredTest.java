package formfiller.transactions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.persistence.FormWidget;

public class AddResponseRequiredTest {
	@Test
	public void canSetResponseRequired() {
		Transaction t = new AddResponseRequired(true);
		t.execute();
		assertThat(FormWidget.isResponseRequired(), is(true));
	}
}
