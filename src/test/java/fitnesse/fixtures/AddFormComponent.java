package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;

public abstract class AddFormComponent {
	private StringEventManager fixtureEventHandler;
	private String questionId;
	private String questionContent;
	private String answerFormat;
	protected FormComponent addedComponent;

	public AddFormComponent() {
		fixtureEventHandler = new StringEventManager();
	}
	
	public void givenAQuestionIdAndQuestionContentAndAnswerFormat(String questionId, 
			String questionContent, String answerFormat){
		this.questionId = questionId;
		this.questionContent = questionContent;
		this.answerFormat = answerFormat;
	}

	public void whenTheUserAddsAFormComponent(){
		String event = makeConsoleRequiredParametersString();
		fixtureEventHandler.updateHandler(event);
	}
	
	protected String makeConsoleRequiredParametersString() {
		String command = getCommandString();
		return String.format("%s " + "%s " + "%s " + "%s ",
				command, questionId, questionContent, answerFormat);
	}
	
	protected abstract String getCommandString();
	
	public void addedComponent() {
		this.addedComponent = FormFillerContext.formComponentGateway.find(questionId);
	}
	
	public String addedId() {
		return addedComponent.id;
	}
	
	public String addedContent() {
		return addedComponent.question.content;
	}
	
	public String addedFormat() {
		String className = addedComponent.format.getClass().getName();
		int startIndex = className.lastIndexOf('.') + 1;
		return className.substring(startIndex);
	}

	//	TODO:	Fix duplication in DeleteFormComponent fixture.
	public String messagePresented() {
		PresentableResponse response = 
				FormFillerContext.outcomePresenter.getPresentableResponse();
		return response.message;
	}
}
