package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import formfiller.utilities.ConstraintName;
import formfiller.utilities.TestUtil;

public class SelectionFormatTest<T> {
	
	public static abstract class GivenASelectionFormat<T>{
		List<T> selections;
		SelectionFormat<T> format;
		
		List<T> makeSelectionsList(T... selections){
			return Arrays.asList(selections);
		}
		
		protected abstract List<T> makeSelections();
			
		@Before
		public void givenASelectionFormat(){
			selections = makeSelections();
			format = new SelectionFormat<T>(selections);
		}
		
		@Test
		public void whenGetNameRuns_ThenItReturnsCorrectName(){
			assertSame(ConstraintName.FORMAT_SELECTION, format.getName());
		}
		
		@Test
		public void whenGetSelectionsRuns_ThenItReturnsGivenSelections(){
			assertSame(selections, format.getSelections());			
		}
	}
	
	public static class GivenANewSelectionFormat<T> extends GivenASelectionFormat<T>{

		@Override
		protected List<T> makeSelections() {
			return makeSelectionsList((T) "a", (T) "b", (T) "c");
		}
		
		@Before
		public void givenANewSelectionFormatAndFullSelectionsList(){
			makeSelections();
		}
		
		@Test
		public void whenFormatIsNew_ThenItWrapsANullResponse(){
			assertFalse(format.hasResponse());
			assertSame(-1, format.getId());
			assertSame("", format.getContent());
			assertFalse(format.satisfiesConstraint());
		}
	}
	
	public static abstract class GivenFormatWrapsResponse<T> extends GivenASelectionFormat<T>{
		int responseId;
		T responseContent;
		boolean satisfiesConstraint;
		Response<T> response;
		
		Response<T> makeResponse(int id, T content, boolean satisfied){
			responseId = id;
			responseContent = content;
			satisfiesConstraint = satisfied;
			response = TestUtil.makeMockResponse(responseId, responseContent, 
					satisfiesConstraint);
			return response;
		}

		@Override
		protected List<T> makeSelections() {
			return makeSelectionsList();
		}
		
		public void setupResponse(int id, T content, boolean satisfied){
			makeResponse(id, content, satisfied);
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
	}
	
	public static class GivenAnEmptySelectionsList<T> extends GivenFormatWrapsResponse<T>{
		
		@Test
		public void whenResponseIsInvalid_ThenConstraintIsUnsatisfied(){
			setupResponse(-5, (T) "", false);			
			assertFormatHasResponse();
			assertResponseDataIsConsistent();
			assertSame(satisfiesConstraint, format.satisfiesConstraint());
			assertConstraintIsSatisfied(false);
		}
		
		@Test
		public void whenResponseIsValid_ThenConstraintIsUnsatisfied(){
			setupResponse(0, (T) "Joe", true);			
			assertFormatHasResponse();
			assertResponseDataIsConsistent();
			assertNotSame(satisfiesConstraint, format.satisfiesConstraint());
			assertConstraintIsSatisfied(false);
		}
	}
	
	public static class GivenAFullSelectionsList<T> extends GivenFormatWrapsResponse<T>{

		@Override
		protected List<T> makeSelections() {
			return makeSelectionsList((T) "a", (T) "b", (T) "c");
		}		
	}
	
	public static class GivenANullToWrap<T> extends GivenAFullSelectionsList<T>{
		
		@Before
		public void givenANullToWrap(){
			response = null;
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
			format.wrap(response);
		}
	}
	
	public static class GivenAnInvalidResponse<T> extends GivenAFullSelectionsList<T>{
		
		@Before
		public void givenAnInvalidResponse(){
			setupResponse(-5, (T) "", false);
		}
		
		@Test
		public void whenFormatWrapsInvalidResponse_ThenConstraintNotSatisfied(){
			assertFormatHasResponse();
			assertResponseDataIsConsistent();
			assertSame(satisfiesConstraint, format.satisfiesConstraint());
			assertConstraintIsSatisfied(false);
		}
	}
	
	public static class GivenAValidNonSelectionResponse<T> extends GivenAFullSelectionsList<T>{
		
		@Before
		public void givenAValidNonSelectionResponse(){
			setupResponse(0, (T) "Joe", true);
		}
		
		@Test
		public void whenFormatWrapsNonSelectionResponse_ThenConstraintNotSatisfied(){
			assertFormatHasResponse();
			assertResponseDataIsConsistent();
			assertNotSame(satisfiesConstraint, format.satisfiesConstraint());
			assertConstraintIsSatisfied(false);
		}
	}
	
	public static class GivenAValidSelectionResponse<T> extends GivenAFullSelectionsList<T>{

		@Before
		public void givenAValidSelectionResponse(){
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
