package formfiller.entities.answerFormat;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.format.MultiOptionVariable;
import formfiller.entities.format.OptionVariable;
import formfiller.entities.format.SingleOptionVariable;

//	TODO:	Acceptance tests for SOV and MOV (replace OV)
//			Make OptionVariable abstract
@RunWith(HierarchicalContextRunner.class)
public class OptionVariableFormatTest {
	private OptionVariable format;
	private Object[] options;

	private Character[] makeTypicalScantronOptions() {
		return new Character[] {'a', 'b', 'c', 'd', 'e'};
	}	

	private Object getRandomOption() {
		Double randomDouble = Math.random() * 5;
		return getOption(randomDouble.intValue());
	}

	private Object getOption(int index) {
		return format.options.get(index);
	}

	@Before
	public void setUp() {
		options = makeTypicalScantronOptions();
	}
	
	public class SingleOptionVariableContext {
		@Before
		public void setUp() {
			format = new SingleOptionVariable();
			format.options = Arrays.asList(options);
		}

		@Test
		public void nullContent_DoesNotMatchFormat() {
			assertThat(format.matchesFormat(null), is(false));
		}

		@Test
		public void containedOption_DoesMatchFormat() {
			Object randomOption = getRandomOption();
			
			assertThat(format.matchesFormat(randomOption), is(true));
		}

		@Test
		public void uncontainedOption_DoesNotMatchFormat() {
			assertThat(format.matchesFormat('f'), is(false));
		}
		
	}
	
	public class MultiOptionVariableContext {
		@Before
		public void setUp() {
			format = new MultiOptionVariable();
			format.options = Arrays.asList(options);
		}

		@Test
		public void nullContent_DoesNotMatchFormat() {
			assertThat(format.matchesFormat(null), is(false));
		}

		@Test
		public void containedOption_DoesMatchFormat() {
			Object randomOption = getRandomOption();
			
			assertThat(format.matchesFormat(randomOption), is(true));
		}

		@Test
		public void uncontainedOption_DoesNotMatchFormat() {
			assertThat(format.matchesFormat('f'), is(false));
		}
		
	}
}
