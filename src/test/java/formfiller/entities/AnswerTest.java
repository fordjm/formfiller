package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnswerTest {
	
	public abstract static class GivenAnAnswer{
		int id;
		Object content;
		ConstrainableAnswer answer;
		
		public abstract int getId();
		public abstract Object getContent();
		
		@Before
		public void givenAnAnswerImpl(){
			id = getId();
			content = getContent();
			answer = new ConstrainableAnswer(id, content);
		}
		
		void assertResponseDataIsConsistent(){
			assertSame(id, answer.getId());
			assertSame(content, answer.getContent());
		}
		
		@Test
		public void whenFieldValuesAreCompared_ObjecthenValuesAreConsistent(){
			assertResponseDataIsConsistent();
		}		
	}
	
	public static class GivenAnIllegalIdAndLegalContent extends GivenAnAnswer{

		public int getId() {
			return -12;
		}

		public Object getContent() {
			return (Object) "";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(answer.isSatisfied());
		}
	}
	
	public static class GivenALegalIdAndIllegalContent extends GivenAnAnswer{

		public int getId() {
			return 32;
		}

		public Object getContent() {
			return null;
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ObjecthenItReturnsFalse(){
			assertFalse(answer.isSatisfied());
		}		
	}
	
	public static class GivenALegalIdAndLegalContent extends GivenAnAnswer{

		public int getId() {
			return 0;
		}

		public Object getContent() {
			return (Object) "Joe";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ObjecthenItReturnsObjectrue(){
			assertTrue(answer.isSatisfied());
		}			
	}
}
