package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;

public class AddFormComponent {
	private String questionId;
	private String questionContent;
	private AnswerFormat format;

	//	TODO:	Fix duplication in FormComponentPresentation.
	private EventSource source;
	private Router router;
	private EventHandler handler;

	public AddFormComponent() {
		source = new ConsoleEventSource();	//	TODO:  More abstract name.
		router = PlaceholderRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	//			End duplication
	
	public void givenAQuestionIdAndQuestionContentAndAnswerFormat(String questionId, String questionContent, String format){
		this.questionId = questionId;
		this.questionContent = questionContent;
		this.format = convertToAnswerFormat(format);
	}
	
	//	TODO:	Condense to "UnstructuredFormat"
	//			Find out how to make a converter FitNesse recognizes.
	private AnswerFormat convertToAnswerFormat(String format) {
		if (format.equalsIgnoreCase("OptionVariable"))
			return new OptionVariable();
		else if (format.equalsIgnoreCase("Unstructured"))
			return new Unstructured();
		else
			throw new IllegalArgumentException();
	}

	//	TODO:	Handle multiple formats.
	public void whenTheUserAddsAFormComponent(){
		handler.update(source, "AddUnstructuredFormComponent " + questionId);
		
		FormComponent component = createFormComponentWithIdAndQuestionAndFormat();
		FormFillerContext.formComponentGateway.save(component);
	}
	
	public String messagePresented() {
		//	TODO:	Generalize name for both successful and failed user messages.
		PresentableResponse response = 
				FormFillerContext.errorPresenter.getPresentableResponse();
		return response.message;
	}

	private FormComponent createFormComponentWithIdAndQuestionAndFormat() {
		FormComponent result = new FormComponent();
		result.id = questionId;
		result.question = createQuestionWithIdAndContent();
		result.format = format;
		return result;
	}

	private Question createQuestionWithIdAndContent() {
		Question result = new Question();
		result.id = questionId;
		result.content = questionContent;
		return result;
	}
}
