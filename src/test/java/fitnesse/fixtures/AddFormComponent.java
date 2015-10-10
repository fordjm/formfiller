package fitnesse.fixtures;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;

public class AddFormComponent {
	private StringEventManager stringEventManager;
	private String componentId;
	private String questionContent;
	private String format;
	protected FormComponent addedComponent;

	public AddFormComponent() {
		stringEventManager = new StringEventManager();
	}
	
	public void givenAQuestionIdAndQuestionContentAndAnswerFormat(String questionId, 
			String questionContent, String answerFormat){
		this.componentId = questionId;
		this.questionContent = questionContent;
		this.format = answerFormat;
	}

	public void whenTheUserAddsAFormComponent(){
		String event = makeConsoleRequiredParametersString();
		stringEventManager.updateHandler(event);
	}
	
	protected String makeConsoleRequiredParametersString() {
		return String.format("%s " + "%s " + "%s " + "%s",
				"AddFC", componentId, questionContent, format);
	}
	
	public void addedComponent() {
		this.addedComponent = Context.formComponentGateway.find(componentId);
	}
	
	public String addedId() {
		return addedComponent.id;
	}
	
	public String addedContent() {
		return addedComponent.question.getContent();
	}
	
	public String addedFormat() {
		return addedComponent.format.getName();
	}
	
}
