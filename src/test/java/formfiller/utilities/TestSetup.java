package formfiller.utilities;
import java.util.Stack;

import formfiller.ApplicationContext;
import formfiller.delivery.presenter.AnswerPresenter;
import formfiller.delivery.presenter.HandleUnfoundControllerPresenter;
import formfiller.delivery.presenter.NavigationPresenter;
import formfiller.delivery.presenter.QuestionPresenter;
import formfiller.entities.ExecutedUseCase;
import formfiller.entities.FormState;
import formfiller.entities.Question;
import formfiller.gateways.InMemoryQuestionGateway;
import formfiller.gateways.InMemoryAnswerGateway;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	public static void setupContext(){
		ApplicationContext.currentFormState = new FormState(-1);
		ApplicationContext.answerGateway = new InMemoryAnswerGateway();
		ApplicationContext.questionGateway = new InMemoryQuestionGateway();
		ApplicationContext.answerPresenter = new AnswerPresenter();
		ApplicationContext.handleUnfoundControllerPresenter = new HandleUnfoundControllerPresenter();
		ApplicationContext.navigationPresenter = new NavigationPresenter();
		ApplicationContext.questionPresenter = new QuestionPresenter();
		ApplicationContext.executedUseCases = new Stack<ExecutedUseCase>();
	}
	public static void setupSampleQuestions(){
		setupContext();
		ApplicationContext.questionGateway.save(
				makeQuestion("name", "What is your name?", false));
		ApplicationContext.questionGateway.save(
				makeQuestion("age", "What is your age?", true));
		ApplicationContext.questionGateway.save(
				makeQuestion("birthDate", "What is your birth date?", false));
	}
	private static Question makeQuestion(String id, String content, boolean isRequired){
		Question result = new Question(id, content);
		result.setResponseRequired(isRequired);
		return result;
	}
}