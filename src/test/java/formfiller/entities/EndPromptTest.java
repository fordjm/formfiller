package formfiller.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class EndPromptTest {

	@Test
	public void newEndPromptFunctionsReturnCorrectValues() {
		EndPrompt end = new EndPrompt();
		assertThat(end.getId(), is("end"));
		assertThat(end.getContent(), is("You have reached the end of this form."));
		assertThat(end.requiresAnswer(), is(false));
	}

}
