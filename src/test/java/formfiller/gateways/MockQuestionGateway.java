package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.NullPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.Question;

public class MockQuestionGateway implements QuestionGateway {
	private Prompt currentQuestion = new NullPrompt();
	private int questionIndex = -1;
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
	public void findQuestionByIndexOffset(int offset){
		int requestedIndex = questionIndex + offset;
		if (isLegalIndex(requestedIndex))
			questionIndex = requestedIndex;
		updateCurrentQuestion();
	}

	void updateCurrentQuestion() {
		if (questionIndex >= 0)
			currentQuestion = questionSource.get(questionIndex);
		else
			currentQuestion = new NullPrompt();
	}
	private boolean isLegalIndex(int index) {
		return index >= 0 && index < questionSource.size();
	}

	public void save(Question question) {
		questionSource.add(question);
	}
}
