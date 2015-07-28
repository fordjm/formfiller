package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseImplTest<T> {
	
	// TODO:  Factor getContent() into separate derived class from getId() (initialize to null)
	public abstract static class GivenResponseWithIntegerIdAndGenericContent<T>{
		int id;
		T content;
		ResponseImpl<T> response;
		
		public abstract int getId();
		public abstract T getContent();
		
		@Before
		public void givenResponseWithIntegerIdAndGenericContent(){
			id = getId();
			content = getContent();
			response = new ResponseImpl<T>(id, content);
		}
	}
	
	public static class GivenLegalIntegerIdAndLegalGenericContent<T> extends 
		GivenResponseWithIntegerIdAndGenericContent<T>{
		
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
			assertEquals(content, response.getContent());
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(response.satisfiesConstraint());
		}
	}

	
	public static class GivenLegalIntegerIdAndNullContent<T> extends 
		GivenResponseWithIntegerIdAndGenericContent<T>{
		
		@Override
		public int getId() {
			return 5;
		}

		@Override
		public T getContent() {
			return null;
		}	
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}
	}
	
	public static class GivenIllegalIntegerIdAndGenericContent<T> extends 
		GivenResponseWithIntegerIdAndGenericContent<T>{
		
		@Override
		public int getId() {
			return -1;
		}

		@Override
		public T getContent() {
			return (T) "Joe";
		}	
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}
	}
}
