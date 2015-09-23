package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;

public class AddFormComponent {
	private String questionId;
	private String questionContent;
	private String answerFormat;
	private FormComponent addedComponent;

	//	TODO:	Fix duplication in FormComponentPresentation.
	private EventSource source;
	private Router router;
	private EventHandler handler;

	public AddFormComponent() {
//		TODO:  Make fixture event source
		source = new ConsoleEventSource();
		router = PlaceholderRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	//			End duplication
	
	public void givenAQuestionIdAndQuestionContentAndAnswerFormat(String questionId, 
			String questionContent, String answerFormat){
		this.questionId = questionId;
		this.questionContent = questionContent;
		this.answerFormat = answerFormat;
	}

	public void whenTheUserAddsAFormComponent(){
		handler.update(source, makeConsoleInputString());
	}
	
	private String makeConsoleInputString() {
		return String.format("%s " + "%s " + "%s " + "%s ",
				"AddFC", questionId, questionContent, answerFormat);

	}
	
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
	
	public String messagePresented() {
		PresentableResponse response = 
				FormFillerContext.outcomePresenter.getPresentableResponse();
		return response.message;
	}
}
