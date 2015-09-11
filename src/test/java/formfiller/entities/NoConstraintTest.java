package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.deprecated.ConstrainableAnswer;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.ConstrainableAnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class NoConstraintTest {
	NoConstraint noConstraint;
	
	public <T> void assertResponseDataIsConsistent(int responseId, T responseContent, boolean satisfiesConstraint){
		assertSame(responseId, noConstraint.getId());
		assertSame(responseContent, noConstraint.getContent());
		assertSame(satisfiesConstraint, noConstraint.isSatisfied());
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
			assertFalse(noConstraint.isSatisfied());
		}
	}
	
	public class GivenAResponse{
		ConstrainableAnswer answer;
		
		public class GivenAnInvalidResponse{
			
			@Before
			public void givenAnInvalidResponse(){
				answer = null;
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
				answer = ConstrainableAnswerMocker.makeMockNameAnswer("Joe");
				noConstraint.wrap(answer);
			}			
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertThat(noConstraint.hasAnswer(), is(equalTo(true)));
				assertResponseDataIsConsistent(answer.getId(), answer.getContent(), answer.isSatisfied());
				assertThat(noConstraint.isConstraintSatisfied(), is(true));
			}
		}
	}
}
