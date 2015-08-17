package formfiller.gateways;

import formfiller.entities.Answer;

public interface AnswerGateway {
	public Answer findAnswerByIndexOffset(int offset);
	public Answer getAnswer();
	public void save(Answer response);
}
