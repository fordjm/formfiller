package formfiller.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class FreeEntryFormatTest<T> {

	public static abstract class GivenAFreeEntryFormat<T>{

		FreeEntryFormat<T> format;
		AbstractResponse<T> mockResponse;
		
		protected abstract AbstractResponse<T> makeMockResponse();
		
		@Before
		public void givenAFreeEntryFormat(){
			mockResponse = makeMockResponse();
			format = new FreeEntryFormat<T>(mockResponse);
		}
	}
	
	public static class GivenAValidResponse<T> extends GivenAFreeEntryFormat<T>{

		@Override
		protected AbstractResponse<T> makeMockResponse() {
			AbstractResponse<T> result = mock(AbstractResponse.class);
			when(result.getContent()).thenReturn((T) "");
			when(result.satisfiesConstraint()).thenReturn(true);			
			return result;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(format.satisfiesConstraint());
		}
	}
	
	public static class GivenAnInvalidResponse<T> extends GivenAFreeEntryFormat<T>{

		@Override
		protected AbstractResponse<T> makeMockResponse() {
			AbstractResponse<T> result = mock(AbstractResponse.class);
			when(result.getContent()).thenReturn(null);
			when(result.satisfiesConstraint()).thenReturn(false);			
			return result;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertFalse(format.satisfiesConstraint());
		}
	}
}
