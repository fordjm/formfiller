package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import formfiller.entities.Constraint;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.entities.Answer;
import formfiller.enums.ContentConstraint;

public class TestUtil {
	public static <T> Constraint makeMockConstraint(int id, boolean satisfiesConstraint) {
		Constraint result = mock(Constraint.class);
		when(result.getName()).thenReturn(ContentConstraint.MOCK);
		when(result.getId()).thenReturn(id);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
	public static Question makeMockNameQuestion() {
		return makeMockQuestion("name", "What is your name?", false);
	}
	public static Question makeMockAgeQuestion() {
		return makeMockQuestion("age", "What is your age?", true);
	}
	public static Question makeMockQuestion(String id, String content, boolean isRequired){
		Question result = mock(Question.class);
		when (result.getId()).thenReturn(id);
		when (result.getContent()).thenReturn(content);
		when (result.requiresAnswer()).thenReturn(isRequired);
		return result;
	}
	public static Answer makeMockNameResponse(String name) {
		return makeMockResponse(0, name, true);
	}
	public static <T> Answer makeMockResponse(int id, T content, boolean satisfiesConstraint) {
		Answer result = 
				(Answer) mock(Answer.class);
		when(result.getId()).thenReturn(id);
		when(result.getContent()).thenReturn(content);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
	public static <T> Answer makeMockResponse(boolean satisfiesConstraint) {
		Answer result = 
				(Answer) mock(Answer.class);
		when(result.getContent()).thenReturn("");
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
}
