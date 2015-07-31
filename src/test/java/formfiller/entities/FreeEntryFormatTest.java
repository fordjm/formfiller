package formfiller.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import formfiller.enums.ConstraintName;

public class FreeEntryFormatTest<T> extends ConstraintDecoratorTest {

	public static class GivenAFreeEntryFormat<T>{
		FreeEntryFormat<T> format;
		
		@Before
		public void givenAFreeEntryFormat(){
			format = new FreeEntryFormat<T>();
		}
		
		@Test
		public void whenGetNameRuns_ThenItReturnsCorrectName(){
			assertEquals(ConstraintName.FORMAT_FREE_ENTRY, 
					format.getName());
		}
	}
	
	public static class GivenANewFreeEntryFormat<T> extends GivenAFreeEntryFormat<T>{
		
		@Test
		public void whenFormatIsNew_ThenItWrapsANullResponse(){
			assertFalse(format.hasResponse());
			assertSame(-1, format.getId());
			assertSame("", format.getContent());
			assertFalse(format.satisfiesConstraint());
		}
	}
	
	public static class GivenFormatWrapsInvalidResponse<T> extends GivenAnInvalidResponse<T>{

		@Override
		protected ConstraintDecorator<T> makeDecorator() {
			return new FreeEntryFormat<T>();
		}

		@Override
		protected T makeResponseContent() {
			return (T) "";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertConstraintDecoratorHasResponse();
			assertResponseDataIsConsistent();
			assertConstraintIsSatisfied(false);
		}
	}
	
	public static class GivenFormatWrapsValidResponse<T> extends GivenAValidResponse<T>{

		@Override
		protected ConstraintDecorator<T> makeDecorator() {
			return new FreeEntryFormat<T>();
		}

		@Override
		protected T makeResponseContent() {
			return (T) "";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertConstraintDecoratorHasResponse();
			assertResponseDataIsConsistent();
			assertConstraintIsSatisfied(true);
		}
	}
}
