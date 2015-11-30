package fitnesse.fixtures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import formfiller.Context;
import formfiller.appBoundaries.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.delivery.viewModel.FormComponentViewModel;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.QuestionImpl;
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
	
	//	Start version 3
	public void whenTheSubjectRequestsTheQuestion(QuestionAsked requested) {
		fixtureEventHandler.updateHandler("AskQues " + requested.toString());
	}
	
	public String presentsTheMessage() {
		//	TODO:	Fix.
		return presentsThePrompt();
	}
	
	public void givenAFormComponentWithQuestionAndAnswerAndRequirementStatus(String question, String answerContent, 
			boolean requiresAnswer){
		clearFormComponents();
		FormComponent component = makeFormComponent(question, answerContent, requiresAnswer);
		Context.formComponentGateway.save(component);
	}	
	
	public void clearFormComponents() {
		Context.formComponentGateway.removeAll();
	}

	private FormComponent makeFormComponent(String question, String answerContent, boolean requiresAnswer) {
			FormComponent component = new FormComponent();
			component.id = "question";
			component.question = makeQuestion(component.id, question);
			component.answer = makeAnswer(answerContent);
			component.requiresAnswer = requiresAnswer;
			return component;
	}

	public String presentsTheAnswer() {
		FormComponentViewModel castViewModel = 
				getCastFormComponentViewModel(getViewModel(Context.formComponentPresenter));
		return castViewModel.answerMessage;
	}
	
	//	TODO:	Doesn't reset if multiple questions are asked.
	public void reset() {
		UndoableUseCase mostRecent = 
				Context.executedUseCases.getMostRecent();
		mostRecent.undo();
	}
	//	End version 3
	
	private Map<Class<?>, String> makePrefixes() {
		Map<Class<?>, String> result = new HashMap<Class<?>, String>();
		result.put(PresentableQuestion.class, "Q: ");
		result.put(PresentableAnswer.class, "A: ");
		result.put(PresentableResponse.class, "E: ");
		return result;
	}
	
	//	V2 stuff
	public String presentsThePrompt() {
		FormComponentViewModel castViewModel = 
				getCastFormComponentViewModel(getViewModel(Context.formComponentPresenter));
		return castViewModel.questionMessage;
	}

	private FormComponentViewModel getCastFormComponentViewModel(ViewModel response) {
		return (FormComponentViewModel) response;
	}

	public String presentsTheFormat() {
		return "";
	}

	public String presentsTheError() {
		ViewModel response = getViewModel(Context.outcomePresenter);
		NotificationViewModel castResponse = (NotificationViewModel) response;
		return castResponse.message;
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
	
	private FormComponent makeNonrequiredFormComponent(String id, 
			String questionContent, String answerContent) {
		FormComponent result = makeFormComponent(id, questionContent, answerContent);
		result.requiresAnswer = false;
		return result;
	}

	private FormComponent makeFormComponent(String id, 
			String questionContent, String answerContent) {
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
		
		private QuestionImpl makeQuestion(String id, String questionContent){
			QuestionImpl result = new QuestionImpl();
			result.setId(id);
			result.setContent(questionContent);		
			return result;
		}
		//	TODO:	Update FitNesse tests to use String answer ID.
		private Answer makeAnswer(String answerContent) {
			if (answerContent.length() == 0) return AnswerImpl.NONE;
			
			Answer result = new AnswerImpl();		
			result.setId("question");
			result.setContent(answerContent);
			return result;
		}

	public void askingTheQuestion(QuestionAsked which) {
		fixtureEventHandler.updateHandler("AskQues " + which.toString());
	}

	private ViewModel getViewModel(Presenter presenter) {
		return presenter.getViewModel();
	}
	
}
