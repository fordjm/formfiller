package formfiller.entities.constrainable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ContentConstraint;

@RunWith(HierarchicalContextRunner.class)
public class AnswerTypeTest {
	private final int EXAMPLE_INTEGER = 15;
	private Class type;
	private AnswerType answerType;
	
	public class StringTypeContext {
		@Before
		public void setUp(){
			type = String.class;
			answerType = new AnswerType(type);
		}	
		
		@Test
		public void constraintHasCorrectName(){
			assertSame(ContentConstraint.TYPE, answerType.name);
		}	
		
		@Test
		public void constraintHasCorrectType(){
			assertSame(type, answerType.type);
		}	
		
		@Test
		public void null_DoesNotSatisfyConstraint(){
			assertThat(answerType.isSatisfiedBy(null), is(false));
		}	
		
		@Test
		public void integerObject_DoesNotSatisfyConstraint(){
			assertThat(answerType.isSatisfiedBy(EXAMPLE_INTEGER), is(false));
		}	
		
		@Test
		public void stringObject_SatisfiesConstraint(){
			assertThat(answerType.isSatisfiedBy("myString"), is(true));
		}
	}	
	
	public class IntegerTypeContext {
		@Before
		public void setUp(){
			type = Integer.class;
			answerType = new AnswerType(type);
		}	
		
		@Test
		public void constraintHasCorrectName(){
			assertSame(ContentConstraint.TYPE, answerType.name);
		}	
		
		@Test
		public void constraintHasCorrectType(){
			assertSame(type, answerType.type);
		}	
		
		@Test
		public void null_DoesNotSatisfyConstraint(){
			assertThat(answerType.isSatisfiedBy(null), is(false));
		}	
		
		@Test
		public void integerObject_SatisfiesConstraint(){
			assertThat(answerType.isSatisfiedBy(EXAMPLE_INTEGER), is(true));
		}	
		
		@Test
		public void stringObject_DoesNotSatisfyConstraint(){
			assertThat(answerType.isSatisfiedBy("myString"), is(false));
		}
	}	
}
