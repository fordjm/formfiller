package formfiller.utilities;
import java.util.Stack;

import formfiller.ApplicationContext;
import formfiller.delivery.presenter.AnswerPresenter;
import formfiller.delivery.presenter.HandleUnfoundControllerPresenter;
import formfiller.delivery.presenter.NavigationPresenter;
import formfiller.delivery.presenter.QuestionPresenter;
import formfiller.entities.AnswerImpl;
import formfiller.entities.AnswerState;
import formfiller.entities.ExecutedUseCase;
import formfiller.entities.FormComponent;
import formfiller.entities.QuestionState;
import formfiller.entities.Question;
import formfiller.gateways.InMemoryQuestionGateway;
import formfiller.gateways.InMemoryAnswerGateway;
import formfiller.gateways.InMemoryFormComponentGateway;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	public static void setupContext(){
		ApplicationContext.answerGateway = new InMemoryAnswerGateway();
		ApplicationContext.questionGateway = new InMemoryQuestionGateway();
		ApplicationContext.answerPresenter = new AnswerPresenter();
		ApplicationContext.handleUnfoundControllerPresenter = new HandleUnfoundControllerPresenter();
		ApplicationContext.navigationPresenter = new NavigationPresenter();
		ApplicationContext.questionPresenter = new QuestionPresenter();
		ApplicationContext.executedUseCases = new Stack<ExecutedUseCase>();
		ApplicationContext.currentQuestionState = new QuestionState(0);
		ApplicationContext.currentAnswerState = new AnswerState(0);
		ApplicationContext.formComponentGateway = new InMemoryFormComponentGateway();
	}
	
	public static void setupSampleFormComponents(){
		setupContext();
		ApplicationContext.formComponentGateway.save(
				makeFormComponent("name", "What is your name?", false));
		ApplicationContext.formComponentGateway.save(
				makeFormComponent("age", "What is your age?", true));
		ApplicationContext.formComponentGateway.save(
				makeFormComponent("birthDate", "What is your birth date?", false));
	}
	
	private static FormComponent makeFormComponent(String id, String content, boolean isRequired){
		FormComponent result = new FormComponent();
		result.setQuestion(makeQuestion(id, content, isRequired));
		result.setAnswer(AnswerImpl.NONE);
		return result;
	}
	
	private static Question makeQuestion(String id, String content, boolean isRequired){
		Question result = new Question(id, content);
		result.setResponseRequired(isRequired);
		return result;
	}
}