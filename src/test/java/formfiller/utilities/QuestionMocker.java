package formfiller.utilities;

import static org.mockito.Mockito.mock;

import formfiller.entities.Question;

public class QuestionMocker {
	
	public static Question makeMockNameQuestion() {
		return makeMockQuestion("name", "What is your name?");
	}
	
	public static Question makeMockAgeQuestion() {
		return makeMockQuestion("age", "What is your age?");
	}
	
	public static Question makeMockBirthDateQuestion() {
		return makeMockQuestion("birthDate", "What is your birth date?");
	}
	
	public static Question makeMockQuestion(String id, String content){
		Question result = mock(Question.class);
		result.id = id;
		result.content = content;
		return result;
	}
}
