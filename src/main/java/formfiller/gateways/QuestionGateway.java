package formfiller.gateways;

import formfiller.entities.Prompt;
import formfiller.entities.Question;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public interface QuestionGateway {
	public void save(Question question);
	public Prompt findQuestionByIndex(int requestedIndex);
	public int numQuestions();
}