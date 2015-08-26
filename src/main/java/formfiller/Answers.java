package formfiller;

import java.util.HashMap;
import java.util.Map;

import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;

public class Answers {
	Map<String, Answer> answerMap;
	
	public Answers(){
		answerMap = new HashMap<String, Answer>();
	}
	
	public void add(String questionId, Answer answer) {
		answerMap.put(questionId, answer);
	}
	
	public Answer get(String questionId){
		Answer result = answerMap.get(questionId);
		if (result == null)
			return AnswerImpl.NONE;
		return result;
	}
}
