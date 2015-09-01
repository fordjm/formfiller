package formfiller.request.implementations;

import formfiller.request.interfaces.Request;

public class AddAnswerRequest implements Request {
	String questionId;
	Object content;
	
	public AddAnswerRequest(String questionId, Object content){
		this.questionId = questionId;
		this.content = content;
	}

	public Object getContent(){
		return content;
	}
	
	public String getQuestionId(){
		return questionId;
	}
	
	public String getName() {
		return "TestRequest";
	}
	
	public void setName(String name) { }
}
