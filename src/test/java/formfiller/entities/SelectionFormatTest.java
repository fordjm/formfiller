package formfiller.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SelectionFormatTest<T> {

	public static abstract class GivenASelectionFormat<T>{
		SelectionFormat<T> format;
		AbstractResponse<T> mockResponse;
		List<T> selections;
		
		protected abstract AbstractResponse<T> makeMockResponse();		
		protected abstract List<T> makeSelectionsList();
		
		@Before
		public void givenASelectionFormat(){
			mockResponse = makeMockResponse();
			selections = makeSelectionsList();
			format = new SelectionFormat<T>(mockResponse, selections);
		}
		
		@Test
		public void whenGetSelectionsRuns_ThenItReturnsGivenSelections(){
			assertSame(selections, format.selections());
		}
	}
	
	public static abstract class GivenAValidResponse<T> extends GivenASelectionFormat<T>{
		
		@Override
		protected AbstractResponse<T> makeMockResponse() {
			AbstractResponse<T> result = mock(AbstractResponse.class);
			when(result.getContent()).thenReturn((T) "a");
			when(result.satisfiesConstraint()).thenReturn(true);
			return result;
		}
		
	}
	
	public static class GivenAnEmptySelectionsList<T> extends GivenAValidResponse<T>{
		
		@Override
		protected List<T> makeSelectionsList(){
			return new ArrayList<T>();
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(format.satisfiesConstraint());
		}
	}
	
	public static class GivenSelectionsListContainsGivenContent<T> extends GivenAValidResponse<T>{
		
		@Override
		protected List<T> makeSelectionsList(){
			return Arrays.asList((T) "a", (T) "b", (T) "c");
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(format.satisfiesConstraint());
		}
	}
	
	public static class GivenSelectionsListWithoutGivenContent<T> extends GivenAValidResponse<T>{
		
		@Override
		protected List<T> makeSelectionsList(){
			return Arrays.asList((T) "x", (T) "y", (T) "z");
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(format.satisfiesConstraint());
		}
	}
	
	public static class GivenAnInvalidResponse<T> extends GivenASelectionFormat<T>{
		
		@Override
		protected AbstractResponse<T> makeMockResponse() {
			AbstractResponse<T> result = mock(AbstractResponse.class);
			when(result.getContent()).thenReturn((T) "z");
			when(result.satisfiesConstraint()).thenReturn(false);
			return result;
		}

		@Override
		protected List<T> makeSelectionsList(){
			return Arrays.asList((T) "x", (T) "y", (T) "z");
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(format.satisfiesConstraint());
		}
	}
}
