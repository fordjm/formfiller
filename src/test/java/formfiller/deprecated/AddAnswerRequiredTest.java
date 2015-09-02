package formfiller.deprecated;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import formfiller.deprecated.AddAnswerRequired;
import formfiller.deprecated.FormWidget;
import formfiller.deprecated.Transaction;

public class AddAnswerRequiredTest {
	
	@Test
	public void canSetAnswerRequired() {
		Transaction t = new AddAnswerRequired(true);
		t.execute();
		assertThat(FormWidget.isAnswerRequired(), is(true));
	}
}
