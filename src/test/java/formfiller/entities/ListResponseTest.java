package formfiller.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

// TODO:  More tests, better names, test addResponse(), removeResponse(int) and satisfiesConstraint()
public class ListResponseTest<T> {
	
	public static abstract class GivenListResponseWithIntegerIdAndGenericContent<T>{
		int id;
		T inputContent;
		ListResponse<T> response;
		List<Response<T>> contentList;

		public abstract int getId();
		public abstract T getContent();
		
		@Before
		public void givenListResponseWithIntegerIdAndGenericContent(){
			id = getId();
			inputContent = getContent();
			response = new ListResponse<T>(id, inputContent);
		}
	}
	
	public static class GivenLegalIntegerIdAndLegalGenericContent<T> extends 
		GivenListResponseWithIntegerIdAndGenericContent<T>{
		
		@Override
		public int getId(){
			return 0;
		}

		@Override
		public T getContent() {
			return (T) "Joe";
		}
		
		@Test
		public void whenGetIdIsCalled_ThenIdEqualsGivenInteger(){
			assertEquals(id, response.getId());
		}
		
		@Test
		public void whenGetContentIsCalled_ThenContentEqualsGivenGeneric() {
			assertEquals(inputContent, response.getContent().get(0).getContent());
		}
		
		@Test
		public void whenContentGetIdIsCalled_ThenContentIdEqualsZero() {
			assertEquals(0, response.getContent().get(0).getId());
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(response.satisfiesConstraint());
		}
	}
	
	private String getStringContent(int index, List<Response<String>> response){
		return response.get(index).getContent();
	}	

	@Test
	public void canCreateListResponse() {
		ListResponse<String> response = new ListResponse<String>(0, "Fantasia");
		String stringContent = getStringContent(0, response.getContent());

		assertTrue(response instanceof ListResponse);
		assertTrue(response.getContent() instanceof List);
		assertTrue(stringContent instanceof String);
	}
}
