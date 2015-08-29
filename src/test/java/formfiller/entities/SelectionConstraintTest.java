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
import formfiller.utilities.AnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class SelectionConstraintTest {
	List<String> selections;
	SelectionConstraint<String> selectionConstraint;	
	List<String> makeSelectionsList(String... selections){
		return Arrays.asList(selections);
	}

	@Before
	public void givenASelectionFormat(){
		selections = makeSelectionsList("a", "b", "c");
		selectionConstraint = new SelectionConstraint<String>(selections);
	}
	
	public class GivenANewSelectionFormat {
		@Test
		public void whenGetNameRuns_ThenItReturnsCorrectName(){
			assertSame(ContentConstraint.SELECTION, selectionConstraint.getName());
		}	
		@Test
		public void whenGetSelectionsRuns_ThenItReturnsGivenSelections(){
			assertSame(selections, selectionConstraint.getSelections());			
		}
		@Test
		public void whenFormatIsNew_ThenItWrapsANullResponse(){
			assertFalse(selectionConstraint.hasResponse());
			assertSame(-1, selectionConstraint.getId());
			assertSame("", selectionConstraint.getContent());
			assertFalse(selectionConstraint.satisfiesConstraint());
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
			assertTrue(selectionConstraint.hasResponse());
		}
		private void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(selectionConstraint.satisfiesConstraint());
			else
				assertFalse(selectionConstraint.satisfiesConstraint());
		}

		public class GivenANullToWrap {		
			@Before
			public void givenANullToWrap(){
				answer = null;
			}		
			@Test(expected = IllegalArgumentException.class)
			public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
				selectionConstraint.wrap(answer);
			}
		}		
		public class GivenAnInvalidResponse{
			@Before
			public void givenAnInvalidResponse(){
				answer = AnswerMocker.makeMockAnswer(false);
				selectionConstraint.wrap(answer);
			}		
			@Test
			public void whenResponseIsInvalid_ThenConstraintIsUnsatisfied(){
				assertFormatHasResponse();
				assertSame(answer.getId(), selectionConstraint.getId());
				assertSame(answer.getContent(), selectionConstraint.getContent());
				assertSame(answer.satisfiesConstraint(), selectionConstraint.satisfiesConstraint());
				assertConstraintIsSatisfied(false);
			}
		}
		public class GivenAValidResponse{
			public class GivenANonSelectionResponse{
				@Before
				public void givenAValidResponse(){
					answer = AnswerMocker.makeMockNameAnswer("Joe");
					selectionConstraint.wrap(answer);
				}
				@Test
				public void whenResponseIsValid_ThenConstraintIsUnsatisfied(){
					assertFormatHasResponse();
					assertSame(answer.getId(), selectionConstraint.getId());					
					assertSame(answer.getContent(), selectionConstraint.getContent());
					assertNotSame(answer.satisfiesConstraint(), selectionConstraint.satisfiesConstraint());
					assertConstraintIsSatisfied(false);
				}
			}
			public class GivenASelectionResponse{
				@Before
				public void givenASelectionResponse(){
					answer = makeMockAnswer(0, "b", true);
					selectionConstraint.wrap(answer);
				}
				@Test
				public void whenFormatWrapsSelectionResponse_ThenConstraintIsSatisfied(){
					assertFormatHasResponse();
					assertSame(answer.getId(), selectionConstraint.getId());
					assertSame(answer.getContent(), selectionConstraint.getContent());
					assertSame(answer.satisfiesConstraint(), selectionConstraint.satisfiesConstraint());
					assertConstraintIsSatisfied(true);
				}
			}

		}
	}
}