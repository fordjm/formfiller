package formfiller.entities;
//####################################################################################
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;

import formfiller.enums.ConstraintName;
import formfiller.utilities.TestUtil;

public class ResponseTypeTest<T> {
	
	public static abstract class GivenAResponseType<T>{
		Type type;
		ResponseType<T> responseType;
		
		@Before
		public void givenAResponseType(){
			T temp = (T) "";
			type = temp.getClass();
			responseType = new ResponseType<T>(type);
		}
		
		@Test
		public void whenGetNameRuns_ThenItReturnsCorrectName(){
			assertSame(ConstraintName.TYPE, responseType.getName());
		}
		
		@Test
		public void whenGetTypeRuns_ThenItReturnsGivenType(){
			assertSame(type, responseType.getType());
		}
	}
	
	public static class GivenANewResponseType<T> extends GivenAResponseType<T>{
		
		@Test
		public void whenResponseTypeIsNew_ThenItWrapsANullResponse(){
			assertFalse(responseType.hasResponse());
			assertSame(-1, responseType.getId());
			assertSame("", responseType.getContent());
			assertFalse(responseType.satisfiesConstraint());
		}
	}
	
	public static class GivenANullToWrap<T> extends GivenAResponseType<T>{
		Response<T> response;
		
		@Before
		public void givenANullToWrap(){
			response = null;
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
			responseType.wrap(response);
		}
	}
	
	public static class GivenAResponse<T> extends GivenAResponseType<T>{
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
			responseType.wrap(response);
		}
		
		public void assertConstraintDecoratorHasResponse(){
			assertTrue(responseType.hasResponse());
		}
		
		public void assertResponseDataIsConsistent(){
			T content = responseType.getContent();
			assertSame(responseId, responseType.getId());
			assertSame(responseContent, content);
			assertSame(type, content.getClass());
			assertSame(satisfiesConstraint, responseType.satisfiesConstraint());
		}
		
		public void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(responseType.satisfiesConstraint());
			else
				assertFalse(responseType.satisfiesConstraint());
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
