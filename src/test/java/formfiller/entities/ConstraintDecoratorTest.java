package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.utilities.TestUtil;

public abstract class ConstraintDecoratorTest {
	
	public static abstract class GivenAConstraintDecorator<T>{
		ConstraintDecorator<T> decorator;
		
		protected abstract ConstraintDecorator<T> makeDecorator();
		
		@Before
		public void givenAConstraintDecorator(){
			decorator = makeDecorator();
		}
	}
	
	public abstract static class GivenANullToWrap<T> extends GivenAConstraintDecorator<T>{
		Response<T> response;
		
		@Before
		public void givenANullToWrap(){
			response = null;
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
			decorator.wrap(response);
		}
	}
	
	public static abstract class GivenResponseContent<T> extends GivenAConstraintDecorator<T>{
		T responseContent;
		
		protected abstract T makeResponseContent();
		
		@Before
		public void givenResponseContent(){
			responseContent = makeResponseContent();
		}
	}
	
	public static abstract class GivenAResponse<T> extends GivenResponseContent<T>{
		int responseId;
		boolean satisfiesConstraint;
		Response<T> response;
		
		Response<T> makeResponse(int id, boolean satisfied){
			responseId = id;
			satisfiesConstraint = satisfied;
			response = TestUtil.makeMockResponse(responseId, responseContent, 
					satisfiesConstraint);
			return response;
		}
		
		public void setupResponse(int id, boolean satisfied){
			makeResponse(id, satisfied);
			decorator.wrap(response);
		}
		
		public void assertConstraintDecoratorHasResponse(){
			assertTrue(decorator.hasResponse());
		}
		
		public void assertResponseDataIsConsistent(){
			assertSame(responseId, decorator.getId());
			assertSame(responseContent, decorator.getContent());
			assertSame(satisfiesConstraint, decorator.satisfiesConstraint());
		}
		
		public void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(decorator.satisfiesConstraint());
			else
				assertFalse(decorator.satisfiesConstraint());
		}
	}
	
	public static abstract class GivenAnInvalidResponse<T> extends GivenAResponse<T>{
		
		@Before
		public void givenAnInvalidResponse(){
			setupResponse(0, false);
		}
	}
	
	public static abstract class GivenAValidResponse<T> extends GivenAResponse<T>{
		
		@Before
		public void givenAValidResponse(){
			setupResponse(0, true);
		}
	}
}
