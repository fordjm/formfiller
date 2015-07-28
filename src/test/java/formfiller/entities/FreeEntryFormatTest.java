package formfiller.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class FreeEntryFormatTest<T> {

	FreeEntryFormat<T> format;
	AbstractResponse<T> mockResponse = mock(AbstractResponse.class);
	
	// TODO:  Add case where mockResponse returns false.
	@Before
	public void givenAFreeEntryFormat(){
		format = new FreeEntryFormat<T>(mockResponse);
		when(mockResponse.getContent()).thenReturn((T) "");
		when(mockResponse.satisfiesConstraint()).thenReturn(true);
	}
	
	@Test
	public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
		assertTrue(format.satisfiesConstraint());
	}
}
