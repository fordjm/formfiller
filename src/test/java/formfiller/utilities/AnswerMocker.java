package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import formfiller.entities.Answer;

public class AnswerMocker {
	
	public static Answer makeMockNameAnswer(String name) {
		return makeMockAnswer(0, name, true);
	}
	
	public static Answer makeMockAgeAnswer(int age) {
		return makeMockAnswer(0, age, true);
	}
	
	public static <T> Answer makeMockAnswer(int id, T content, boolean satisfiesConstraint) {
		Answer result = 
				(Answer) mock(Answer.class);
		when(result.getId()).thenReturn(id);
		when(result.getContent()).thenReturn(content);
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
	
	public static <T> Answer makeMockAnswer(boolean satisfiesConstraint) {
		Answer result = 
				(Answer) mock(Answer.class);
		when(result.getContent()).thenReturn("");
		when(result.satisfiesConstraint()).thenReturn(satisfiesConstraint);
		return result;
	}
}
