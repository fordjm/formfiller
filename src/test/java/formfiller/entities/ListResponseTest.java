package formfiller.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import formfiller.utilities.TestUtil;

public class ListResponseTest<T> {
	// Ugly comment:  Much duplicate code needs cleaning-up.
	public static abstract class GivenAListResponse<T>{
		int parentId;
		T childContent;
		ListResponse<T> response;

		public abstract int makeId();
		public abstract T makeContent();
		
		@Before
		public void givenAListResponse(){
			parentId = makeId();
			childContent = makeContent();
			response = new ListResponse<T>(parentId, childContent);
		}
		
		void assertResponseDataIsConsistent(){
			List<Response<T>> parentContent = response.getContent();
			Response<T> createdResponse = parentContent.get(0);
			assertSame(parentId, response.getId());
			assertSame(childContent, createdResponse.getContent());
		}
		
		@Test
		public void whenFieldValuesAreCompared_ThenValuesAreConsistent(){
			assertResponseDataIsConsistent();
		}
	}
	
	public static class GivenAnIllegalIdAndLegalContent<T> extends GivenAListResponse<T>{

		@Override
		public int makeId() {
			return -12;
		}

		@Override
		public T makeContent() {
			return (T) "";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}
	}
	
	public static class GivenALegalIdAndIllegalContent<T> extends GivenAListResponse<T>{

		@Override
		public int makeId() {
			return 32;
		}

		@Override
		public T makeContent() {
			return null;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}		
	}
	
	public static class GivenALegalIdAndLegalContent<T> extends GivenAListResponse<T>{

		@Override
		public int makeId() {
			return 0;
		}

		@Override
		public T makeContent() {
			return (T) "Joe";
		}			
	}
	
	public static class GivenResponseHasOneLegalElement<T> extends GivenALegalIdAndLegalContent<T>{
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(response.satisfiesConstraint());
		}
	}
	
	public static class GivenOnlyElementWasRemoved<T> extends GivenResponseHasOneLegalElement<T>{

		public void givenOnlyElementWasRemoved() {
			response.removeResponse(0);
		}
		
		@Test
		public void whenNewResponseRemoved_ThenListRegainsOriginalState(){
			response.removeResponse(0);
			assertEquals(0, response.getSize());
			assertFalse(response.satisfiesConstraint());
		}		
	}

	public static abstract class GivenAResponseWasAdded<T> extends GivenALegalIdAndLegalContent<T>{

		int newId;
		T newContent;
		boolean newSatisfied;
		Response<T> newResponse;
		
		Response<T> makeResponse(int id, T content, boolean satisfied){
			newId = id;
			newContent = content;
			newSatisfied = satisfied;
			newResponse = TestUtil.makeMockResponse(newId, newContent, 
					newSatisfied);
			return newResponse;
		}
		
		public abstract Response<T> makeNewResponse();
		
		public void assertListResponseSatisfiesConstraint(boolean flag){
			if (flag)
				assertTrue(response.satisfiesConstraint());
			else
				assertFalse(response.satisfiesConstraint());
		}
		
		@Before
		public void givenANewResponseWasAdded(){
			newResponse = makeNewResponse();
			response.addResponse(newResponse);
		}
		
		@Test
		public void whenNewResponseAdded_ThenListContainsNewResponse(){			
			assertEquals(2, response.getSize());
			assertTrue(response.contains(newResponse));
		}
	}
	
	public static class GivenAnIllegalResponseWasAdded<T> extends GivenAResponseWasAdded<T>{

		@Override
		public Response<T> makeNewResponse() {
			return makeResponse(-12, null, false);
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertListResponseSatisfiesConstraint(false);
		}
		
	}
	
	public static class GivenAnIllegalResponseWasRemoved<T> extends GivenAnIllegalResponseWasAdded<T>{

		public void givenAnIllegalResponseWasRemoved() {
			response.removeResponse(1);
		}
		
		@Test
		public void whenNewResponseRemoved_ThenListRegainsOriginalState(){
			response.removeResponse(1);
			assertEquals(1, response.getSize());
			assertFalse(response.contains(newResponse));
			assertTrue(response.satisfiesConstraint());
		}		
	}
	
	public static class GivenALegalResponseWasAdded<T> extends GivenAResponseWasAdded<T>{

		@Override
		public Response<T> makeNewResponse() {
			return makeResponse(1, (T) "Sam", true);
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertListResponseSatisfiesConstraint(true);
		}		
	}
	
	public static class GivenALegalResponseWasRemoved<T> extends GivenALegalResponseWasAdded<T>{

		public void givenALegalResponseWasRemoved() {
			response.removeResponse(1);
		}
		
		@Test
		public void whenNewResponseRemoved_ThenListRegainsOriginalState(){
			response.removeResponse(1);
			assertEquals(1, response.getSize());
			assertFalse(response.contains(newResponse));
			assertTrue(response.satisfiesConstraint());
		}		
	}
}
