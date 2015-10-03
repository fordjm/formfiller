package fitnesse.fixtures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import formfiller.Context;
import formfiller.appBoundaries.Presenter;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.QuestionAsked;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.undoable.UndoableUseCase;

//	TODO:	Clean this.
public class FormComponentPresentation {
	private StringEventManager fixtureEventHandler;
	private Map<Class<?>, String> prefixes = makePrefixes();

	public FormComponentPresentation() {
		fixtureEventHandler = new StringEventManager();
	}
	
	private Map<Class<?>, String> makePrefixes() {
		Map<Class<?>, String> result = new HashMap<Class<?>, String>();
		result.put(PresentableQuestion.class, "Q: ");
		result.put(PresentableAnswer.class, "A: ");
		result.put(PresentableResponse.class, "E: ");
		return result;
	}
	
	//	New stuff
	public String presentsThePrompt() {
		PresentableResponse response = getPresentableResponse(Context.questionPresenter);
		return response.message;
	}

	public String presentsTheAnswer() {
		PresentableResponse response = getPresentableResponse(Context.answerPresenter);
		return response.message;
	}

	public String presentsTheFormat() {
		return "";
	}

	public String presentsTheError() {
		PresentableResponse response = getPresentableResponse(Context.outcomePresenter);
		return response.message;
	}
	
	public void givenOneFormComponentWithTooManyParameters(String id, 
			String questionContent, String answerContent, boolean requiresAnswer){
		clearFormComponents();
		FormComponent component;
		component = (requiresAnswer == true) 
				? makeRequiredFormComponent(id, questionContent, answerContent) 
				: makeNonrequiredFormComponent(id, questionContent, answerContent);
		Context.formComponentGateway.save(component);
	}
	
	private FormComponent makeRequiredFormComponent(String id, String questionContent, String answerContent) {
		FormComponent result = makeFormComponent(id, questionContent, answerContent);
		result.requiresAnswer = true;
		return result;
	}
	
	private FormComponent makeNonrequiredFormComponent(String id, String questionContent, String answerContent) {
		FormComponent result = makeFormComponent(id, questionContent, answerContent);
		result.requiresAnswer = false;
		return result;
	}

	private FormComponent makeFormComponent(String id, String questionContent, String answerContent) {
		FormComponent result = new FormComponent();
		result.id = id;
		result.question = makeQuestion(id, questionContent);
		result.answer = makeAnswer(answerContent);
		return result;
	}

	//	Old stuff
	
	public void givenOneFormComponentWithRequirementAndAnswer(boolean requiresAnswer, 
			String answerContent){
		clearFormComponents();
		FormComponent component = makeFormComponent(requiresAnswer, answerContent);
		Context.formComponentGateway.save(component);
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
		//	TODO:	Update FitNesse tests to use String answer ID.
		private Answer makeAnswer(String answerContent) {
			if (answerContent.length() == 0) return Answer.NONE;
			
			Answer result = new Answer();		
			result.questionId = "questionId";
			result.content = answerContent;
			return result;
		}

	public void askingTheQuestion(QuestionAsked which) {
		fixtureEventHandler.updateHandler("AskQues " + which.toString());
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
		result.add(Context.questionPresenter);
		result.add(Context.answerPresenter);
		result.add(Context.outcomePresenter);
		return result;
	}

	private PresentableResponse getPresentableResponse(Presenter presenter) {
		return presenter.getPresentableResponse();
	}
	
	public void reset() {
		UndoableUseCase mostRecent = 
				Context.executedUseCases.getMostRecent();
		mostRecent.undo();
	}
	
	public void clearFormComponents() {
		Context.formComponentGateway.removeAll();
	}
}
