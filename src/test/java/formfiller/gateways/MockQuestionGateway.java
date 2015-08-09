package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.NullPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.Question;

public class MockQuestionGateway implements QuestionGateway {
	private Prompt currentQuestion = new NullPrompt();
	private int currentIndex = -1;
	@SuppressWarnings("serial")
	private List<Question> questionSource;
			
	public MockQuestionGateway(){
		questionSource = new ArrayList<Question>();
	}

	public void delete(Question question){
		questionSource.remove(question);
	}
	public Prompt getQuestion() {
		return currentQuestion;
	}	
	public Prompt findQuestionByIndexOffset(int offset){
		int requestedIndex = currentIndex + offset;
		updateCurrentQuestion(requestedIndex);
		return currentQuestion;
	}
	void updateCurrentQuestion(int requestedIndex) {
		updateCurrentIndex(requestedIndex);
		currentQuestion = questionSource.get(currentIndex);
	}	
	void updateCurrentIndex(int requestedIndex){
		if (isLegalIndex(requestedIndex))
			currentIndex = requestedIndex;
	}
	boolean isLegalIndex(int index) {
		return index >= 0 && index < questionSource.size();
	}

	public void save(Question question) {
		questionSource.add(question);
	}
}
