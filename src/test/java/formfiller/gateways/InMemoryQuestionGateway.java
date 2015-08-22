package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.EndPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.entities.StartPrompt;

public class InMemoryQuestionGateway implements QuestionGateway {
	private List<Question> questionSource;
			
	public InMemoryQuestionGateway(){
		questionSource = new ArrayList<Question>();
	}

	public boolean isEmpty(){
		return questionSource.size() == 0;
	}
	public void delete(Question question){
		questionSource.remove(question);
	}
	public Prompt findQuestionByIndex(int index){
		if (isAtStart(index)) 
			return new StartPrompt();
		else if (isAtEnd(index))
			return new EndPrompt();
		else
			return questionSource.get(index);
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
	public int numQuestions() {
		return questionSource.size();
	}
}
