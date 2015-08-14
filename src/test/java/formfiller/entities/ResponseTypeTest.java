package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class ResponseTypeTest<T> {
	Type type;
	AnswerType responseType;
	
	@Before
	public void setUp(){
		T temp = (T) "";
		type = temp.getClass();
		responseType = new AnswerType(type);
	}	
	@Test
	public void whenGetNameRuns_ThenItReturnsCorrectName(){
		assertSame(ContentConstraint.TYPE, responseType.getName());
	}	
	@Test
	public void whenGetTypeRuns_ThenItReturnsGivenType(){
		assertSame(type, responseType.getType());
	}	
	@Test
	public void whenResponseTypeIsNew_ThenItWrapsANullResponse(){
		assertFalse(responseType.hasResponse());
		assertSame(-1, responseType.getId());
		assertSame("", responseType.getContent());
		assertFalse(responseType.satisfiesConstraint());
	}
	
	public class GivenANullToWrap {
		Answer response;		
		@Before
		public void givenANullToWrap(){
			response = null;
		}		
		@Test(expected = IllegalArgumentException.class)
		public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
			responseType.wrap(response);
		}
	}
	
	public class GivenAResponse{
		int responseId;
		T responseContent;
		boolean satisfiesConstraint;
		Answer response;
		
		Answer makeResponse(int id, T content, boolean satisfied){
			responseId = id;
			responseContent = content;
			satisfiesConstraint = satisfied;
			response = MockCreation.makeMockResponse(responseId, responseContent, 
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
		
		public void assertResponseDataIsConsistent(int responseId, T responseContent){
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
		public class GivenAnInvalidResponse{			
			@Before
			public void givenAnInvalidResponse(){
				response = MockCreation.makeMockResponse(false);
				responseType.wrap(response);
			}			
			@Test
			public void whenFormatWrapsInvalidResponse_ThenConstraintNotSatisfied(){
				assertConstraintDecoratorHasResponse();
				assertConstraintIsSatisfied(false);
			}
		}
		public class GivenAValidResponse{		
			@Before
			public void givenAValidResponse(){
				response = MockCreation.makeMockNameResponse("Joe");
			}
			
			@Test
			public void whenFormatWrapsAValidResponse_ThenConstraintIsSatisfied(){
				responseType.wrap(response);
				assertConstraintDecoratorHasResponse();
				assertThat(responseType.getContent().toString(), is(equalTo("Joe")));
				assertConstraintIsSatisfied(true);
			}
		}
	}
}
