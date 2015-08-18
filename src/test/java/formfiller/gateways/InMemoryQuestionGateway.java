package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.EndPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.entities.StartPrompt;

public class InMemoryQuestionGateway implements QuestionGateway {
	private Prompt currentQuestion;
	private int currentIndex = -1;
	private List<Question> questionSource;
			
	public InMemoryQuestionGateway(){
		questionSource = new ArrayList<Question>();
		setCurrentQuestionToStartPrompt();
	}

	public boolean isEmpty(){
		return questionSource.size() == 0;
	}
	public void delete(Question question){
		questionSource.remove(question);
		if (isEmpty())
			setCurrentQuestionToStartPrompt();
	}
	private void setCurrentQuestionToStartPrompt() {
		currentQuestion = new StartPrompt();
	}
	public Prompt getQuestion() {
		return currentQuestion;
	}	
	public Prompt findQuestionByIndexOffset(int offset){
		int requestedIndex = currentIndex + offset;
		updateCurrentIndex(requestedIndex);
		updateCurrentQuestion(requestedIndex);
		return currentQuestion;
	}	
	void updateCurrentIndex(int requestedIndex){
		if (isAtStart(requestedIndex)) 
			currentIndex = -1;
		else if (isAtEnd(requestedIndex)) 
			currentIndex = questionSource.size();
		else
			currentIndex = requestedIndex;
	}
	void updateCurrentQuestion(int requestedIndex) {
		if (isAtStart(requestedIndex)) 
			currentQuestion = new StartPrompt();
		else if (isAtEnd(requestedIndex))
			currentQuestion = new EndPrompt();
		else
			currentQuestion = questionSource.get(currentIndex);
	}
	boolean isAtStart(int index) {
		return index < 0;
	}
	boolean isAtEnd(int index) {
		return index >= questionSource.size();
	}
	public void save(Question question) {
		questionSource.add(question);
	}
}
