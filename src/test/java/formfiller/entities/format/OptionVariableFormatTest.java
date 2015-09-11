package formfiller.entities.format;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

//	TODO:	Separate classes for single and multi option variables?
//			Pass a cardinality object? {SINGLE, ANY, SPECIFIC, RANGE}
public class OptionVariableFormatTest {
	private OptionVariableFormat format;

	private Character[] makeScantronOptions() {
		return new Character[] {'a', 'b', 'c', 'd', 'e'};
	}

	private Object getOption(int index) {
		return format.options.get(index);
	}
	
	@Before
	public void setUp() {
		format = new OptionVariableFormat();
		Object[] options = makeScantronOptions();
		format.options = Arrays.asList(options);
	}

	@Test
	public void nullContent_DoesNotMatchFormat() {
		assertThat(format.matchesContent(null), is(false));
	}

	@Test
	public void containedOption_DoesMatchFormat() {
		Double randomDouble = Math.random() * 5;
		Object randomOption = getOption(randomDouble.intValue());
		
		assertThat(format.matchesContent(randomOption), is(true));
	}

	@Test
	public void uncontainedOption_DoesNotMatchFormat() {
		assertThat(format.matchesContent('f'), is(false));
	}
}
