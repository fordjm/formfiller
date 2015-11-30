package formfiller.utilities;

import formfiller.ExecutedUseCases;
import formfiller.Context;
import formfiller.delivery.presenter.FormComponentPresenter;
import formfiller.delivery.presenter.NotificationPresenter;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.Question;
import formfiller.entities.QuestionImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.gateways.impl.InMemoryFormComponentGateway;
import formfiller.gateways.impl.InMemoryFormComponentState;
import formfiller.utilities.valueMatcher.CaseIgnoringStringMatcher;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	
	public static void setupContext(){
		Context.stringMatcher = new CaseIgnoringStringMatcher();
		Context.formComponentState = new InMemoryFormComponentState();
		Context.formComponentGateway = new InMemoryFormComponentGateway();
		Context.executedUseCases = new ExecutedUseCases();
		Context.answerPresenter = makeResponsePresenter();
		Context.outcomePresenter = makeResponsePresenter();
		Context.formComponentPresenter = makeFormComponentPresenter();
		Context.questionPresenter = makeResponsePresenter();
	}

	private static NotificationPresenter makeResponsePresenter() {
		return new NotificationPresenter();
	}

	private static NotificationViewModel makePresentableResponseViewModel() {
		NotificationViewModel result = new NotificationViewModel();
		return result;
	}

	private static FormComponentPresenter makeFormComponentPresenter() {
		return new FormComponentPresenter();
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
			Question question){		
		return makeFormComponent(requiresAnswer, question, AnswerImpl.NONE);
	}
	
	private static FormComponent makeFormComponent(boolean requiresAnswer, 
			Question question, Answer answer){
		FormComponent result = new FormComponent();
		result.requiresAnswer = requiresAnswer;
		result.id = question.getId();
		result.question = question;
		result.answer = answer;		
		return result;
	}
	
	private static Question makeQuestion(String id, String content){
		Question result = new QuestionImpl(id, content);
		return result;
	}
	
	private static Answer makeAnswer(String content){
		Answer result = new AnswerImpl();
		result.setContent(content);
		return result;
	}
}