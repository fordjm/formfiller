package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.Answers;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;

public class InMemoryAnswerGateway implements AnswerGateway {
	List<Answer> answerList;
	Answers answers;
	
	public InMemoryAnswerGateway(){
		answerList = new ArrayList<Answer>();
		answers = new Answers();
	}
	public Answer findAnswerByIndex(int index) {
		if (!isLegalIndex(index)) return AnswerImpl.NONE;
		return answerList.get(index);
	}
	boolean isLegalIndex(int requestedIndex) {
		return requestedIndex >= 0 && requestedIndex < answerList.size();
	}
	public int numAnswers() {
		return answerList.size();
	}
	public void save(Answer answer) {
		answerList.add(answer);
	}
	public void save(String questionId, Answer answer) {
		answers.add(questionId, answer);
	}
	public Answer findAnswerByQuestionId(String questionId) {
		return answers.get(questionId);
	}
}
