package formfiller.gateways;

import formfiller.entities.Answer;

public interface AnswerGateway {
	public Answer findAnswerByIndex(int index);
	public Answer findAnswerByQuestionId(String questionId);
	public int numAnswers();
	public void save(Answer response);
	public void save(String questionId, Answer response);
}