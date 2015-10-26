package formfiller.utilities;

import formfiller.ExecutedUseCases;
import formfiller.Context;
import formfiller.delivery.View;
import formfiller.delivery.presenter.FormComponentPresenter;
import formfiller.delivery.presenter.ResponsePresenter;
import formfiller.delivery.ui.consoleUi.ConsoleView;
import formfiller.delivery.viewModel.PresentableResponseViewModel;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.QuestionImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.gateways.impl.InMemoryFormComponentGateway;
import formfiller.gateways.impl.InMemoryFormComponentState;
import formfiller.utilities.valueMatcher.CaseIgnoringStringMatcher;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	
	public static void setupContext(){
		View consoleView = new ConsoleView();
		
		Context.stringMatcher = new CaseIgnoringStringMatcher();
		Context.formComponentState = new InMemoryFormComponentState();
		Context.formComponentGateway = new InMemoryFormComponentGateway();
		Context.executedUseCases = new ExecutedUseCases();
		Context.answerPresenter = makeResponsePresenter(consoleView);
		Context.outcomePresenter = makeResponsePresenter(consoleView);
		Context.formComponentPresenter = makeFormComponentPresenter(consoleView);
		Context.questionPresenter = makeResponsePresenter(consoleView);
	}

	private static ResponsePresenter makeResponsePresenter(View view) {
		return new ResponsePresenter(makePresentableResponseViewModel(view));
	}

	private static PresentableResponseViewModel makePresentableResponseViewModel(View view) {
		PresentableResponseViewModel result = new PresentableResponseViewModel();
		result.addObserver(view);
		return result;
	}

	private static FormComponentPresenter makeFormComponentPresenter(View view) {
		return new FormComponentPresenter(makePresentableResponseViewModel(view));
	}
	
	public static void setupSampleFormComponents(){
		setupContext();
		Context.formComponentGateway.save(
				makeFormComponent(false, 
						makeQuestion("name", "What is your name?")));
		Context.formComponentGateway.save(
				makeFormComponent(false, 
						makeQuestion("birthDate", "What is your birth date?"), 
						makeAnswer("November 12, 1955")));
		Context.formComponentGateway.save(
				makeFormComponent(true, 
						makeQuestion("age", "What is your age?")));
	}
	
	private static FormComponent makeFormComponent(boolean requiresAnswer, 
			QuestionImpl question){		
		return makeFormComponent(requiresAnswer, question, AnswerImpl.NONE);
	}
	
	private static FormComponent makeFormComponent(boolean requiresAnswer, 
			QuestionImpl question, Answer answer){
		FormComponent result = new FormComponent();
		result.requiresAnswer = requiresAnswer;
		result.id = question.getId();
		result.question = question;
		result.answer = answer;		
		return result;
	}
	
	private static QuestionImpl makeQuestion(String id, String content){
		QuestionImpl result = new QuestionImpl(id, content);
		return result;
	}
	
	private static Answer makeAnswer(String content){
		AnswerImpl result = new AnswerImpl();
		result.setContent(content);
		return result;
	}
}