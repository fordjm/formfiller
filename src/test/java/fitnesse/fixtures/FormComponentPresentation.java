package fitnesse.fixtures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.QuestionAsked;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.undoable.UndoableUseCase;

//	TODO:	FixtureSetup/TestSetup should set up variables.  
//			How to access EventHandler?
public class FormComponentPresentation {	
	private EventSource source;
	private Router router;
	private EventHandler handler;
	private Map<Class<?>, String> prefixes = makePrefixes();

	public FormComponentPresentation() {
		source = new ConsoleEventSource();	//	TODO:  More abstract name.
		router = PlaceholderRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	private Map<Class<?>, String> makePrefixes() {
		Map<Class<?>, String> result = new HashMap<Class<?>, String>();
		result.put(PresentableQuestion.class, "Q: ");
		result.put(PresentableAnswer.class, "A: ");
		result.put(PresentableResponse.class, "E: ");
		return result;
	}
	
	public void givenOneFormComponentWithRequirementAndAnswer(boolean requiresAnswer, 
			String answerContent){
		clearFormComponents();
		FormComponent component = makeFormComponent(requiresAnswer, answerContent);
		FormFillerContext.formComponentGateway.save(component);
	}

	//	TODO:	Fix duplication in GivenAFormComponent
	private FormComponent makeFormComponent(boolean requiresAnswer, String answerContent) {
			FormComponent component = new FormComponent();
			component.id = "name";
			component.question = makeQuestion(component.id, "What is your name?");
			component.requiresAnswer = requiresAnswer;
			component.answer = makeAnswer(answerContent);
			return component;
		}
		
		private Question makeQuestion(String id, String questionContent){
			Question result = new Question();
			result.id = id;
			result.content = questionContent;		
			return result;
		}

		private Answer makeAnswer(String answerContent) {
			if (answerContent.length() == 0) return Answer.NONE;
			
			Answer result = new Answer();		
			result.id = 0;
			result.content = answerContent;
			return result;
		}

	public void askingTheQuestion(QuestionAsked which) {
		handler.update(source, "AskQuestion " + which.toString());
	}
	
	public String presentsTheMessage() {
		return assembleMessageFromCurrentPresenterMessages();
	}
	
	private String assembleMessageFromCurrentPresenterMessages() {
		String result = "";		
		for (PresentableResponse response : getPresentableResponses()){
			result += makeAddedMessageString(response);
		}		
		return trimLastComma(result);
	}

	private String makeAddedMessageString(PresentableResponse response) {
		if (response.message.length() == 0) return "";
		
		String prefix = prefixes.get(response.getClass());
		return prefix + response.message + ", ";
	}

	private String trimLastComma(String result) {
		if (result.length() == 0) return result;
		
		int lastCommaIndex = result.lastIndexOf(',');
		return result.substring(0, lastCommaIndex);
	}
	
	private Collection<PresentableResponse> getPresentableResponses(){
		Collection<PresentableResponse> result = new ArrayList<PresentableResponse>();
		for (Presenter presenter : getPresenters()){
			result.add(getPresentableResponse(presenter));
		}
		return result;
	}

	//	TODO:	Fix duplication in AskQuestionUseCase
	private Collection<Presenter> getPresenters() {
		Collection<Presenter> result = new ArrayList<Presenter>();
		result.add(FormFillerContext.questionPresenter);
		result.add(FormFillerContext.answerPresenter);
		result.add(FormFillerContext.errorPresenter);
		return result;
	}

	private PresentableResponse getPresentableResponse(Presenter presenter) {
		return presenter.getPresentableResponse();
	}
	
	public void reset() {
		UndoableUseCase mostRecent = 
				FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();
	}
	
	public void clearFormComponents() {
		FormFillerContext.formComponentGateway.removeAll();
	}
}
