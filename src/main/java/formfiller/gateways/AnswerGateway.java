package formfiller.gateways;

import formfiller.entities.Answer;

public interface AnswerGateway {
	public Answer findAnswerByIndex(int index);
	public int numResponses();
	public void save(Answer response);
}
