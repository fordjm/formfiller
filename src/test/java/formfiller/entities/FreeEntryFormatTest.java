package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.TestUtil;

@RunWith(HierarchicalContextRunner.class)
public class FreeEntryFormatTest {
	FreeEntryFormat format;
	
	public <T> void assertResponseDataIsConsistent(int responseId, T responseContent, boolean satisfiesConstraint){
		assertSame(responseId, format.getId());
		assertSame(responseContent, format.getContent());
		assertSame(satisfiesConstraint, format.satisfiesConstraint());
	}
	
	@Before
	public void setUp(){
		format = new FreeEntryFormat();
	}
	
	@Test
	public void whenGetNameRuns_ThenItReturnsCorrectName(){
		assertEquals(ContentConstraint.FORMAT, 
				format.getName());
	}
	
	public class GivenANewFreeEntryFormat {		
		@Test
		public void whenFormatIsNew_ThenItWrapsANullResponse(){
			assertFalse(format.hasResponse());
			assertSame(-1, format.getId());
			assertSame("", format.getContent());
			assertFalse(format.satisfiesConstraint());
		}
	}
	
	public class GivenAResponse{
		Response response;
		
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				response = null;
			}
			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertThat(format.hasResponse(), is(equalTo(false)));
				assertThat(format.isConstraintSatisfied(), is(false));
			}
		}
		public class GivenAValidResponse{
			@Before
			public void givenAValidResponse(){
				response = TestUtil.makeMockNameResponse("Joe");
				format.wrap(response);
			}			
			@Test
			public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
				assertThat(format.hasResponse(), is(equalTo(true)));
				assertResponseDataIsConsistent(response.getId(), response.getContent(), response.satisfiesConstraint());
				assertThat(format.isConstraintSatisfied(), is(true));
			}
		}
	}
}
