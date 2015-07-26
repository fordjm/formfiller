package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public abstract class FreeEntryFormatTest<T> {
	
	protected FreeEntryFormat<T> format;
	
	protected abstract FreeEntryFormat<T> makeFormat();

	@Before
	public void setup(){
		format = makeFormat();
	}

	@Test
	public void givenNull_isNotValidResponse() {
		assertFalse(format.satisfiesConstraint(null));
	}
	
	@Test
	public abstract void givenProperDataType_isValidResponse();
}
