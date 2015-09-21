package formfiller.request.models;

import java.lang.reflect.Type;

public class AddFormComponentRequest extends Request {
	public String name = "AddFormComponentRequest";
	public String questionId = "";
	public String questionContent = "";
	public Integer minAnswerCount = 0;
	public Integer maxAnswerCount = 1;
	public Type answerType = Object.class;
}
