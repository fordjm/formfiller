package formfiller.entities.constrainable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constraints;

public class ConstraintsTest {	
	private Constraints constraints;

	private AnswerType makeMockAnswerType(Type type, Object content) {
		AnswerType result = Mockito.mock(AnswerType.class);
		Mockito.when(result.requiresType(type)).thenReturn(true);
		Mockito.when(result.isSatisfiedBy(content)).thenReturn(true);
		return result;
	}

	private String getMockClassSimpleName(Constrainable mockConstraint) {
		Class<? extends Constrainable> clazz = mockConstraint.getClass();
		return clazz.getSimpleName();
	}
	
	@Before
	public void setUp() {
		constraints = new Constraints();		
	}
	
	@Test
	public void testConstraints() {
		String content = "content";
		Constrainable mockConstraint = makeMockAnswerType(String.class, content);
		String className = getMockClassSimpleName(mockConstraint);
		constraints.add(mockConstraint);
		
		assertThat(constraints.areSatisfiedBy(""), is(false));
		assertThat(constraints.areSatisfiedBy(content), is(true));
		assertThat(constraints.get(className), instanceOf(AnswerType.class));
	}

	@Test
	public void canAddOnlyOneAnswerType() {
		AnswerType type1 = makeMockAnswerType(int.class, 10);
		AnswerType type2 = makeMockAnswerType(double.class, 10.0);
		
		constraints.add(type1);
		constraints.add(type2);

		assertThat(constraints.areSatisfiedBy(10), is(false));
		assertThat(constraints.areSatisfiedBy(10.0), is(true));
	}

}
