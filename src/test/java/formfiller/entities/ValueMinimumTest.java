package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValueMinimumTest extends ConstraintTest {
	
	public static class GivenAnInvalidValueForInvalidResponse<T> extends GivenAnInvalidResponse<T>{
		
		@Override
		protected Constraint<T> makeDecorator() {
			return new ValueMinimum<T>((T) "min");
		}
		
		@Override
		protected T makeResponseContent(){
			return (T) "a";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(decorator.satisfiesConstraint());
		}			
	}
	
	public static class GivenAValidValueForInvalidResponse<T> extends GivenAnInvalidResponse<T>{
		
		@Override
		protected Constraint<T> makeDecorator() {
			return new ValueMinimum<T>((T) "min");
		}
		
		@Override
		protected T makeResponseContent(){
			return (T) "z";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(decorator.satisfiesConstraint());
		}			
	}
	
	public static class GivenAnInvalidValueForValidResponse<T> extends GivenAValidResponse<T>{
		
		@Override
		protected Constraint<T> makeDecorator() {
			return new ValueMinimum<T>((T) "min");
		}
		
		@Override
		protected T makeResponseContent(){
			return (T) "a";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsFalse(){
			assertFalse(decorator.satisfiesConstraint());
		}			
	}
	
	public static class GivenAValidValueForValidResponse<T> extends GivenAValidResponse<T>{
		
		@Override
		protected Constraint<T> makeDecorator() {
			return new ValueMinimum<T>((T) "min");
		}
		
		@Override
		protected T makeResponseContent(){
			return (T) "z";
		}
		
		@Test
		public void whenSatisfiesConstraintRuns_ThenItReturnsTrue(){
			assertTrue(decorator.satisfiesConstraint());
		}			
	}
}
