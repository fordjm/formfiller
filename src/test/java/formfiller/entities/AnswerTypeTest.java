package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.enums.ContentConstraint;
import formfiller.utilities.ConstrainableAnswerMocker;

@RunWith(HierarchicalContextRunner.class)
public class AnswerTypeTest<T> {
	Type type;
	AnswerType answerType;
	
	@Before
	public void setUp(){
		T temp = (T) "";
		type = temp.getClass();
		answerType = new AnswerType(type);
	}	
	
	@Test
	public void whenGetNameRuns_ThenItReturnsCorrectName(){
		assertSame(ContentConstraint.TYPE, answerType.getName());
	}	
	
	@Test
	public void whenGetTypeRuns_ThenItReturnsGivenType(){
		assertSame(type, answerType.getType());
	}	
	
	@Test
	public void whenAnswerTypeIsNew_ThenItWrapsANullResponse(){
		assertFalse(answerType.hasAnswer());
		assertSame(-1, answerType.getId());
		assertSame("", answerType.getContent());
		assertFalse(answerType.isSatisfiedBy(null));
	}
	
	public class GivenANullToWrap {
		ConstrainableAnswer answerImpl;	
		
		@Before
		public void givenANullToWrap(){
			answerImpl = null;
		}		
		
		@Test(expected = IllegalArgumentException.class)
		public void whenWrappingNull_ThenIllegalArgumentExceptionIsThrown(){
			answerType.wrap(answerImpl);
		}
	}
	
	public class GivenAnAnswer{
		int answerId;
		T answerContent;
		boolean satisfiesConstraint;
		ConstrainableAnswer answer;
		
		public void assertConstraintHasAnswer(){
			assertTrue(answerType.hasAnswer());
		}
		
		public void assertConstraintIsSatisfied(boolean flag){
			if (flag)
				assertTrue(answerType.isSatisfiedBy(null));
			else
				assertFalse(answerType.isSatisfiedBy(null));
		}
		
		public class GivenAnInvalidAnswer{	
			
			@Before
			public void givenAnInvalidAnswer(){
				answer = ConstrainableAnswerMocker.makeMockAnswer(false);
				answerType.wrap(answer);
			}		
			
			@Test
			public void whenFormatWrapsInvalidAnswer_ThenConstraintNotSatisfied(){
				assertConstraintHasAnswer();
				assertConstraintIsSatisfied(false);
			}
		}
		
		public class GivenAValidAnswer{		
			
			@Before
			public void givenAValidResponse(){
				answer = ConstrainableAnswerMocker.makeMockNameAnswer("Joe");
			}
			
			@Test
			public void whenFormatWrapsAValidAnswer_ThenConstraintIsSatisfied(){
				answerType.wrap(answer);
				assertConstraintHasAnswer();
				assertThat(answerType.getContent().toString(), is(equalTo("Joe")));
				assertConstraintIsSatisfied(true);
			}
		}
	}
}
