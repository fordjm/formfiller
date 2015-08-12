package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StartPromptTest {

	@Test
	public void newStartPromptFunctionsReturnCorrectValues() {
		StartPrompt start = new StartPrompt();
		assertThat(start.getId(), is("start"));
		assertThat(start.getContent(), is("You have reached the start of this form."));
		assertThat(start.requiresAnswer(), is(false));
	}

}
