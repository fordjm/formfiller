package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;

public class InMemoryAnswerGateway implements AnswerGateway {
	List<Answer> answers;
	
	public InMemoryAnswerGateway(){
		answers = new ArrayList<Answer>();
	}
	public Answer findAnswerByIndex(int index) {
		if (!isLegalIndex(index)) return AnswerImpl.NONE;
		return answers.get(index);
	}
	boolean isLegalIndex(int requestedIndex) {
		return requestedIndex >= 0 && requestedIndex < answers.size();
	}
	public int numResponses() {
		return answers.size();
	}
	public void save(Answer response) {
		answers.add(response);
	}
}
