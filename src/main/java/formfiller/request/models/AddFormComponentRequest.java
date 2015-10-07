package formfiller.request.models;

import java.lang.reflect.Type;

import formfiller.entities.format.Unstructured;

public class AddFormComponentRequest extends RequestWithComponentIdAndFormat {
	public String questionContent = "";
	public Integer minAnswerCount = 0;
	public Integer maxAnswerCount = 1;
	public Type answerType = Object.class;
	
	public AddFormComponentRequest(){
		format = new Unstructured();
	}
	
}
