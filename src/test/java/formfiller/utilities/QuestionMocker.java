package formfiller.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import formfiller.entities.Question;

public class QuestionMocker {
	
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
}
