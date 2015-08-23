package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class SelectionFormatTest {
	List<String> selections;
	SelectionFormat<String> format;	
	List<String> makeSelectionsList(String... selections){
		return Arrays.asList(selections);
	}

	@Before
	public void givenASelectionFormat(){
		selections = makeSelectionsList("a", "b", "c");
		format = new SelectionFormat<String>(selections);
	}
	
	public class GivenANewSelectionFormat {
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
	}	

	public class GivenFormatWrapsAResponse{
		int answerId;
		String answerContent;
		boolean satisfiesConstraint;
		Answer answer;
		
		private Answer makeMockAnswer(int id, String content, boolean satisfied){
			Answer result = Mockito.mock(Answer.class);
			Mockito.when(result.getId()).thenReturn(id);
			Mockito.when(result.getContent()).thenReturn(content);
			Mockito.when(result.satisfiesConstraint()).thenReturn(satisfied);			
			return result;
		}		
		private void assertFormatHasResponse(){
			assertTrue(format.hasResponse());
		}
		private void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(format.satisfiesConstraint());
			else
				assertFalse(format.satisfiesConstraint());
		}

		public class GivenANullToWrap {		
			@Before
			public void givenANullToWrap(){
				answer = null;
			}		
			@Test(expected = IllegalArgumentException.class)
			public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
				format.wrap(answer);
			}
		}		
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				answer = MockCreation.makeMockResponse(false);
				format.wrap(answer);
			}		
			@Test
			public void whenResponseIsInvalid_ThenConstraintIsUnsatisfied(){
				assertFormatHasResponse();
				assertSame(answer.getId(), format.getId());
				assertSame(answer.getContent(), format.getContent());
				assertSame(answer.satisfiesConstraint(), format.satisfiesConstraint());
				assertConstraintIsSatisfied(false);
			}
		}
		public class GivenAValidResponse{
			public class GivenANonSelectionResponse{
				@Before
				public void givenAValidResponse(){
					answer = MockCreation.makeMockNameAnswer("Joe");
					format.wrap(answer);
				}
				@Test
				public void whenResponseIsValid_ThenConstraintIsUnsatisfied(){
					assertFormatHasResponse();
					assertSame(answer.getId(), format.getId());					
					assertSame(answer.getContent(), format.getContent());
					assertNotSame(answer.satisfiesConstraint(), format.satisfiesConstraint());
					assertConstraintIsSatisfied(false);
				}
			}
			public class GivenASelectionResponse{
				@Before
				public void givenASelectionResponse(){
					answer = makeMockAnswer(0, "b", true);
					format.wrap(answer);
				}
				@Test
				public void whenFormatWrapsSelectionResponse_ThenConstraintIsSatisfied(){
					assertFormatHasResponse();
					assertSame(answer.getId(), format.getId());
					assertSame(answer.getContent(), format.getContent());
					assertSame(answer.satisfiesConstraint(), format.satisfiesConstraint());
					assertConstraintIsSatisfied(true);
				}
			}

		}
	}
}
