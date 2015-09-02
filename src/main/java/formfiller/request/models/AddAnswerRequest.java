package formfiller.request.models;

public class AddAnswerRequest extends Request {
	public String name = "AddAnswerRequest";
	public String questionId;
	public Object content;
}
