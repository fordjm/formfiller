package fitnesse.fixtures;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;

public class AddFormComponent {
	private StringEventManager stringEventManager;
	private String questionId;
	private String questionContent;
	private String answerFormat;
	protected FormComponent addedComponent;

	public AddFormComponent() {
		stringEventManager = new StringEventManager();
	}
	
	//	TODO:	Determining subclass by script actor.
	//			The format string does nothing.
	public void givenAQuestionIdAndQuestionContentAndAnswerFormat(String questionId, 
			String questionContent, String answerFormat){
		this.questionId = questionId;
		this.questionContent = questionContent;
		this.answerFormat = answerFormat;
	}

	public void whenTheUserAddsAFormComponent(){
		String event = makeConsoleRequiredParametersString();
		stringEventManager.updateHandler(event);
	}
	
	protected String makeConsoleRequiredParametersString() {
		String command = getCommandString(answerFormat);
		return String.format("%s " + "%s " + "%s " + "%s",
				command, questionId, questionContent, answerFormat);
	}
	
	//	TODO:	Use the same command string.
	private String getCommandString(String answerFormat) {
		if (answerFormat.equalsIgnoreCase("U"))
			return "AddFCU";
		else
			return "AddFC";
	}
	
	public void addedComponent() {
		this.addedComponent = Context.formComponentGateway.find(questionId);
	}
	
	public String addedId() {
		return addedComponent.id;
	}
	
	public String addedContent() {
		return addedComponent.question.content;
	}
	
	public String addedFormat() {
		return addedComponent.format.getName();
	}
}
