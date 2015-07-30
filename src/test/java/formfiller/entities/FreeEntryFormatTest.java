package formfiller.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import formfiller.utilities.ConstraintName;
import formfiller.utilities.TestUtil;

public class FreeEntryFormatTest<T> {

	public static class GivenAFreeEntryFormat<T>{
		FreeEntryFormat<T> format;
		
		@Before
		public void givenAFreeEntryFormat(){
			format = new FreeEntryFormat<T>();
		}
		
		@Test
		public void whenGetNameRuns_ThenItReturnsCorrectName(){
			assertEquals(ConstraintName.FORMAT_FREE_ENTRY, 
					format.getName());
		}
	}
	
	public static class GivenANewFreeEntryFormat<T> extends GivenAFreeEntryFormat<T>{
		
		@Test
		public void whenFormatIsNew_ThenItWrapsANullResponse(){
			assertFalse(format.hasResponse());
			assertSame(-1, format.getId());
			assertSame("", format.getContent());
			assertFalse(format.satisfiesConstraint());
		}
	}
	
	public static class GivenANullToWrap<T> extends GivenAFreeEntryFormat<T>{
		Response<T> response;
		
		@Before
		public void givenANullToWrap(){
			response = null;
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
			format.wrap(response);
		}
	}
	
	public static class GivenAResponse<T> extends GivenAFreeEntryFormat<T>{
		int responseId;
		T responseContent;
		boolean satisfiesConstraint;
		Response<T> response;
		
		Response<T> makeResponse(int id, T content, boolean satisfied){
			responseId = id;
			responseContent = content;
			satisfiesConstraint = satisfied;
			response = TestUtil.makeMockResponse(responseId, responseContent, 
					satisfiesConstraint);
			return response;
		}
		
		public void setupResponse(int id, T content, boolean satisfied){
			makeResponse(id, content, satisfied);
			format.wrap(response);
		}
		
		public void assertConstraintDecoratorHasResponse(){
			assertTrue(format.hasResponse());
		}
		
		public void assertResponseDataIsConsistent(){
			assertSame(responseId, format.getId());
			assertSame(responseContent, format.getContent());
			assertSame(satisfiesConstraint, format.satisfiesConstraint());
		}
		
		public void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(format.satisfiesConstraint());
			else
				assertFalse(format.satisfiesConstraint());
		}
	}
	
	public static class GivenAnInvalidResponse<T> extends GivenAResponse<T>{
		
		@Before
		public void givenAnInvalidResponse(){
			setupResponse(-5, (T) "", false);
		}
		
		@Test
		public void whenFormatWrapsInvalidResponse_ThenConstraintNotSatisfied(){
			assertConstraintDecoratorHasResponse();
			assertResponseDataIsConsistent();
			assertConstraintIsSatisfied(false);
		}
	}
	
	public static class GivenAValidResponse<T> extends GivenAResponse<T>{
		
		@Before
		public void givenAValidResponse(){
			setupResponse(0, (T) "Joe", true);
		}
		
		@Test
		public void whenFormatWrapsAValidResponse_ThenConstraintIsSatisfied(){
			assertConstraintDecoratorHasResponse();
			assertResponseDataIsConsistent();
			assertConstraintIsSatisfied(true);
		}
	}
}
