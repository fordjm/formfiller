package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.Prompt;
import formfiller.entities.Question;

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
	
	// TODO:	Should this throw an exception?
	public Prompt findQuestionByIndex(int index){
		if (isAtStart(index)) 
			return Question.START;
		else if (isAtEnd(index))
			return Question.END;
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
