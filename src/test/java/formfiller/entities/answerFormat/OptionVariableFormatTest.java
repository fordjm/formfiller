package formfiller.entities.answerFormat;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.format.OptionVariable;

//	TODO:	Separate classes for single and multi option variables?
//			Pass a cardinality object? {SINGLE, ANY, SPECIFIC, RANGE}
public class OptionVariableFormatTest {
	private OptionVariable format;

	private Character[] makeScantronOptions() {
		return new Character[] {'a', 'b', 'c', 'd', 'e'};
	}

	private Object getOption(int index) {
		return format.options.get(index);
	}
	
	@Before
	public void setUp() {
		format = new OptionVariable();
		Object[] options = makeScantronOptions();
		format.options = Arrays.asList(options);
	}

	@Test
	public void nullContent_DoesNotMatchFormat() {
		assertThat(format.matchesFormat(null), is(false));
	}

	@Test
	public void containedOption_DoesMatchFormat() {
		Double randomDouble = Math.random() * 5;
		Object randomOption = getOption(randomDouble.intValue());
		
		assertThat(format.matchesFormat(randomOption), is(true));
	}

	@Test
	public void uncontainedOption_DoesNotMatchFormat() {
		assertThat(format.matchesFormat('f'), is(false));
	}
}
