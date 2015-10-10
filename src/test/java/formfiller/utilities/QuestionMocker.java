package formfiller.utilities;

import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import formfiller.entities.QuestionImpl;

public class QuestionMocker {
	
	public static QuestionImpl makeMockNameQuestion() {
		return makeMockQuestion("name", "What is your name?");
	}
	
	public static QuestionImpl makeMockAgeQuestion() {
		return makeMockQuestion("age", "What is your age?");
	}
	
	public static QuestionImpl makeMockBirthDateQuestion() {
		return makeMockQuestion("birthDate", "What is your birth date?");
	}
	
	public static QuestionImpl makeMockQuestion(String id, String content){
		QuestionImpl result = mock(QuestionImpl.class);
		Mockito.when(result.getId()).thenReturn(id);
		Mockito.when(result.getContent()).thenReturn(content);
		return result;
	}
}
