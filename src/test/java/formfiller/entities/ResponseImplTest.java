package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseImplTest<T> {
	public abstract static class GivenAResponseImpl<T>{
		int id;
		T content;
		ResponseImpl<T> response;
		
		public abstract int getId();
		public abstract T getContent();
		
		@Before
		public void givenAResponseImpl(){
			id = getId();
			content = getContent();
			response = new ResponseImpl<T>(id, content);
		}
		
		void assertResponseDataIsConsistent(){
			assertSame(id, response.getId());
			assertSame(content, response.getContent());
		}
		
		@Test
		public void whenFieldValuesAreCompared_ThenValuesAreConsistent(){
			assertResponseDataIsConsistent();
		}
		
	}
	
	public static class GivenAnIllegalIdAndLegalContent<T> extends GivenAResponseImpl<T>{

		@Override
		public int getId() {
			return -12;
		}

		@Override
		public T getContent() {
			return (T) "";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}
	}
	
	public static class GivenALegalIdAndIllegalContent<T> extends GivenAResponseImpl<T>{

		@Override
		public int getId() {
			return 32;
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
	
	public static class GivenALegalIdAndLegalContent<T> extends GivenAResponseImpl<T>{

		@Override
		public int getId() {
			return 0;
		}

		@Override
		public T getContent() {
			return (T) "Joe";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(response.satisfiesConstraint());
		}			
	}
}
