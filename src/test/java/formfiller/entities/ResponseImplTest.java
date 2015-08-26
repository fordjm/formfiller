package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseImplTest {
	public abstract static class GivenAResponseImpl{
		int id;
		Object content;
		AnswerImpl response;
		
		public abstract int getId();
		public abstract Object getContent();
		
		@Before
		public void givenAResponseImpl(){
			id = getId();
			content = getContent();
			response = new AnswerImpl(id, content);
		}
		
		void assertResponseDataIsConsistent(){
			assertSame(id, response.getId());
			assertSame(content, response.getContent());
		}
		
		@Test
		public void whenFieldValuesAreCompared_ObjecthenValuesAreConsistent(){
			assertResponseDataIsConsistent();
		}
		
	}
	
	public static class GivenAnIllegalIdAndLegalContent extends GivenAResponseImpl{

		@Override
		public int getId() {
			return -12;
		}

		@Override
		public Object getContent() {
			return (Object) "";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ObjecthenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}
	}
	
	public static class GivenALegalIdAndIllegalContent extends GivenAResponseImpl{

		@Override
		public int getId() {
			return 32;
		}

		@Override
		public Object getContent() {
			return null;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ObjecthenItReturnsFalse(){
			assertFalse(response.satisfiesConstraint());
		}		
	}
	
	public static class GivenALegalIdAndLegalContent extends GivenAResponseImpl{

		@Override
		public int getId() {
			return 0;
		}

		@Override
		public Object getContent() {
			return (Object) "Joe";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ObjecthenItReturnsObjectrue(){
			assertTrue(response.satisfiesConstraint());
		}			
	}
}
