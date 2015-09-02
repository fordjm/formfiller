package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class NoConstraintTest {
	NoConstraint noConstraint;
	
	public <T> void assertResponseDataIsConsistent(int responseId, T responseContent, boolean satisfiesConstraint){
		assertSame(responseId, noConstraint.getId());
		assertSame(responseContent, noConstraint.getContent());
		assertSame(satisfiesConstraint, noConstraint.satisfiesConstraint());
	}
	
	@Before
	public void setUp(){
		noConstraint = new NoConstraint();
	}
	
	@Test
	public void whenGetNameRuns_ThenItReturnsCorrectName(){
		assertEquals(ContentConstraint.NONE, 
				noConstraint.getName());
	}
	
	public class GivenANewNoConstraint {		
		
		@Test
		public void whenFormatIsNew_ThenItWrapsANullResponse(){
			assertFalse(noConstraint.hasAnswer());
			assertSame(-1, noConstraint.getId());
			assertSame("", noConstraint.getContent());
			assertFalse(noConstraint.satisfiesConstraint());
		}
	}
	
	public class GivenAResponse{
		Answer response;
		
		public class GivenAnInvalidResponse{
			
			@Before
			public void givenAnInvalidResponse(){
				response = null;
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertThat(noConstraint.hasAnswer(), is(equalTo(false)));
				assertThat(noConstraint.isConstraintSatisfied(), is(false));
			}
		}
		
		public class GivenAValidAnswer {
			
			@Before
			public void givenAValidResponse(){
				response = AnswerMocker.makeMockNameAnswer("Joe");
				noConstraint.wrap(response);
			}			
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertThat(noConstraint.hasAnswer(), is(equalTo(true)));
				assertResponseDataIsConsistent(response.getId(), response.getContent(), response.satisfiesConstraint());
				assertThat(noConstraint.isConstraintSatisfied(), is(true));
			}
		}
	}
}
