package formfiller.request.models;

import formfiller.enums.QuestionAsked;

public class AskQuestionRequest extends Request {
	public QuestionAsked which = QuestionAsked.CURRENT;
}