package formfiller.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ListResponseTest<T> {
	
	public static abstract class GivenListResponseWithIntegerIdAndGenericContent<T>{
		int id;
		T inputContent;
		ListResponse<T> response;
		List<Response<T>> contentList;

		public abstract int makeId();
		public abstract T makeContent();
		
		@Before
		public void givenListResponseWithIntegerIdAndGenericContent(){
			id = makeId();
			inputContent = makeContent();
			response = new ListResponse<T>(id, inputContent);
		}
	}
	
	public static class GivenLegalIntegerIdAndLegalGenericContent<T> extends 
		GivenListResponseWithIntegerIdAndGenericContent<T>{
		
		@Override
		public int makeId(){
			return 0;
		}

		@Override
		public T makeContent() {
			return (T) "Joe";
		}
	}
	
	public static class GivenLegalListResponseCreated<T> extends GivenLegalIntegerIdAndLegalGenericContent<T>{
		
		private Response<T> getElement(int index, List<Response<T>> listContent){
			return listContent.get(index);
		}

		@Test
		public void whenListResponseCreated_ThenItsFormatIsValid() {
			Response<T> element = getElement(0, response.getContent());
			assertTrue(response instanceof ListResponse);
			assertTrue(response.getContent() instanceof List);
			assertTrue(element instanceof Response<?>);
		}
		
		@Test
		public void whenGetIdRuns_ThenIdEqualsGivenInteger(){
			assertEquals(id, response.getId());
		}
		
		@Test
		public void whenGetContentRuns_ThenContentEqualsGivenGeneric() {
			assertEquals(inputContent, response.getContent().get(0).getContent());
		}
		
		@Test
		public void whenContentGetIdRuns_ThenContentIdEqualsZero() {
			assertEquals(0, response.getContent().get(0).getId());
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(response.satisfiesConstraint());
		}
	}
	
	public static class GivenNewResponseAdded<T> extends GivenLegalIntegerIdAndLegalGenericContent<T>{
		
		AbstractResponse<T> newResponse;
		
		private AbstractResponse<T> makeMockResponse() {
			AbstractResponse<T> result = mock(AbstractResponse.class);
			when(result.getContent()).thenReturn((T) "a");
			when(result.satisfiesConstraint()).thenReturn(true);
			return result;
		}
		
		@Before
		public void givenNewResponseAdded(){
			newResponse = makeMockResponse();
			response.addResponse(newResponse);
		}
		
		@Test
		public void whenNewResponseAdded_ThenListContainsNewResponse(){			
			assertEquals(2, response.getSize());
			assertTrue(response.contains(newResponse));
			assertTrue(response.satisfiesConstraint());
		}
		
		@Test
		public void whenNewResponseRemoved_ThenListRegainsOriginalState(){
			response.removeResponse(1);
			assertEquals(1, response.getSize());
			assertFalse(response.contains(newResponse));
			assertTrue(response.satisfiesConstraint());
		}
	}
	
	public static class GivenOnlyResponseRemoved<T> extends GivenLegalIntegerIdAndLegalGenericContent<T>{
		
		@Before
		public void givenOnlyResponseRemoved(){
			response.removeResponse(0);
		}
		
		@Test
		public void whenListIsEmpty_ThenListIsInvalid(){			
			assertEquals(0, response.getSize());
			assertFalse(response.satisfiesConstraint());
		}
	}
	
	public static class GivenLegalIntegerIdAndIllegalGenericContent<T> extends 
		GivenListResponseWithIntegerIdAndGenericContent<T>{

		@Override
		public int makeId() {
			return 0;
		}

		@Override
		public T makeContent() {
			return null;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertSame(0, response.getId());
			// TODO	Null element content
			assertFalse(response.satisfiesConstraint());
		}		
	}
	
	public static class GivenIllegalIntegerIdAndLegalGenericContent<T> extends 
		GivenListResponseWithIntegerIdAndGenericContent<T>{

		@Override
		public int makeId() {
			return -1;
		}

		@Override
		public T makeContent() {
			return (T) "Joe";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertSame(-1, response.getId());
			assertFalse(response.satisfiesConstraint());
		}		
	}
}
