package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.Question;

public class QuestionGatewayFake implements QuestionGateway {
	private Question currentQuestion = null;
	private int questionIndex = -1;
	@SuppressWarnings("serial")
	private List<Question> questionSource;
			
	public QuestionGatewayFake(){
		questionSource = new ArrayList<Question>();
	}

	public void delete(Question question){
		questionSource.remove(question);
	}
	public Question getQuestion() {
		return currentQuestion;
	}	
	public void findQuestionByIndexOffset(int offset){
		int requestedIndex = questionIndex + offset;
		if (isLegalIndex(requestedIndex)){
			questionIndex = requestedIndex;
			currentQuestion = questionSource.get(questionIndex);			
		}
		else
			currentQuestion = null;
	}
	private boolean isLegalIndex(int index) {
		return index >= 0 && index < questionSource.size();
	}

	public void save(Question question) {
		questionSource.add(question);
	}
}
