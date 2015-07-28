package formfiller.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;

public class ResponseTypeTest<T> {
	
	public static abstract class GivenGenericResponseType<T>{
		ResponseType<T> responseType;
		AbstractResponse<T> mockResponse = mock(AbstractResponse.class);
		T content = (T) "";
		Type type = content.getClass();
		
		void setupMockResponse(){
			when(mockResponse.getContent()).thenReturn(content);
			when(mockResponse.satisfiesConstraint()).thenReturn(
					getResponseSatisfiesConstraint());
		}
		
		protected abstract boolean getResponseSatisfiesConstraint();
		
		@Before
		public void givenGenericResponseType(){
			setupMockResponse();
			responseType = new ResponseType<T>(mockResponse, content.getClass());
		}
		
		@Test
		public void whenGetTypeRuns_ThenItReturnsGivenGeneric(){
			assertEquals(type, responseType.getType());
		}
	}
	
	public static class GivenValidResponse<T> extends GivenGenericResponseType<T>{

		@Override
		protected boolean getResponseSatisfiesConstraint() {
			return true;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(responseType.satisfiesConstraint());
		}
	}
	
	public static class GivenInvalidResponse<T> extends GivenGenericResponseType<T>{

		@Override
		protected boolean getResponseSatisfiesConstraint() {
			return false;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(responseType.satisfiesConstraint());
		}
	}
}
