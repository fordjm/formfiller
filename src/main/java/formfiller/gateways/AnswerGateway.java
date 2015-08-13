package formfiller.gateways;

import formfiller.entities.Answer;

public interface AnswerGateway {
	public Answer findResponseByIndexOffset(int offset);
	public Answer getResponse();
	public void save(Answer response);
}
