package formfiller.request.models;

import java.lang.reflect.Type;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.Unstructured;

public class AddFormComponentRequest extends Request {
	public String questionId = "";
	public String questionContent = "";
	public AnswerFormat format = new Unstructured();
	public Integer minAnswerCount = 0;
	public Integer maxAnswerCount = 1;
	public Type answerType = Object.class;
}
