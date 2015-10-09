package formfiller.usecases.addAnswer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.lang.reflect.Type;

import org.junit.Test;
import org.mockito.Mockito;

import formfiller.entities.constrainable.AnswerType;

public class ConstraintsTest {		
	@Test
	public void testConstraints() {
		String content = "content";
		Constraints constraints = new Constraints();
		constraints.add(makeMockAnswerType(String.class, content));
		
		assertThat(constraints.areSatisfiedBy(""), is(false));
		assertThat(constraints.areSatisfiedBy(content), is(true));
	}

	private AnswerType makeMockAnswerType(Type type, Object content) {
		AnswerType result = Mockito.mock(AnswerType.class);
		Mockito.when(result.requiresType(type)).thenReturn(true);
		Mockito.when(result.isSatisfiedBy(content)).thenReturn(true);
		return result;
	}

}
