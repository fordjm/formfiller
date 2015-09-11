package formfiller.request.models;

import formfiller.enums.WhichQuestion;

public class AskQuestionRequest extends Request {
	public WhichQuestion which = WhichQuestion.CURRENT;
}