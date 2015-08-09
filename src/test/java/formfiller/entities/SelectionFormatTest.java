package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import formfiller.enums.ContentConstraint;
import formfiller.utilities.TestUtil;

public class SelectionFormatTest<T> {
	List<T> selections;
	SelectionFormat<T> format;	
	List<T> makeSelectionsList(T... selections){
		return Arrays.asList(selections);
	}

	@Before
	public void givenASelectionFormat(){
		selections = makeSelectionsList((T) "a", (T) "b", (T) "c");
		format = new SelectionFormat<T>(selections);
	}
	@Test
	public void whenGetNameRuns_ThenItReturnsCorrectName(){
		assertSame(ContentConstraint.FORMAT, format.getName());
	}	
	@Test
	public void whenGetSelectionsRuns_ThenItReturnsGivenSelections(){
		assertSame(selections, format.getSelections());			
	}
	@Test
	public void whenFormatIsNew_ThenItWrapsANullResponse(){
		assertFalse(format.hasResponse());
		assertSame(-1, format.getId());
		assertSame("", format.getContent());
		assertFalse(format.satisfiesConstraint());
	}

	public class GivenFormatWrapsResponse{
		int responseId;
		T responseContent;
		boolean satisfiesConstraint;
		Response response;

		public void setupResponse(int id, T content, boolean satisfied){
			format.wrap(response);
		}

		public void assertFormatHasResponse(){
			assertTrue(format.hasResponse());
		}

		public void assertResponseDataIsConsistent(){
			assertSame(responseId, format.getId());
			assertSame(responseContent, format.getContent());
		}

		public void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(format.satisfiesConstraint());
			else
				assertFalse(format.satisfiesConstraint());
		}

		public class GivenANullToWrap {		
			@Before
			public void givenANullToWrap(){
				response = null;
			}		
			@Test(expected = IllegalArgumentException.class)
			public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
				format.wrap(response);
			}
		}		
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				response = TestUtil.makeMockResponse(false);
				format.wrap(response);
			}		
			@Test
			public void whenResponseIsInvalid_ThenConstraintIsUnsatisfied(){

				assertFormatHasResponse();
				assertResponseDataIsConsistent();
				assertSame(satisfiesConstraint, format.satisfiesConstraint());
				assertConstraintIsSatisfied(false);
			}
		}
		public class GivenAValidResponse{
			public class GivenANonSelectionResponse{
				@Before
				public void givenAValidResponse(){
					response = TestUtil.makeMockNameResponse("Joe");
					format.wrap(response);
				}
				@Test
				public void whenResponseIsValid_ThenConstraintIsUnsatisfied(){
					assertFormatHasResponse();
					assertResponseDataIsConsistent();
					assertNotSame(satisfiesConstraint, format.satisfiesConstraint());
					assertConstraintIsSatisfied(false);
				}
			}
			public class GivenASelectionResponse{
				@Before
				public void givenASelectionResponse(){
					setupResponse(0, (T) "b", true);
				}

				@Test
				public void whenFormatWrapsSelectionResponse_ThenConstraintIsSatisfied(){
					assertFormatHasResponse();
					assertResponseDataIsConsistent();
					assertSame(satisfiesConstraint, format.satisfiesConstraint());
					assertConstraintIsSatisfied(true);
				}
			}

		}
	}
}
