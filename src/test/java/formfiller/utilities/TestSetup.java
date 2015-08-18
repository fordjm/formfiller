package formfiller.utilities;
import formfiller.ApplicationContext;
import formfiller.delivery.presenter.AnswerPresenter;
import formfiller.delivery.presenter.HandleUnfoundControllerPresenter;
import formfiller.delivery.presenter.NavigationPresenter;
import formfiller.delivery.presenter.QuestionPresenter;
import formfiller.entities.Question;
import formfiller.gateways.MockQuestionGateway;
import formfiller.gateways.MockAnswerGateway;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	public static void setupContext(){
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.answerGateway = new MockAnswerGateway();
		ApplicationContext.answerPresenter = new AnswerPresenter();
		ApplicationContext.handleUnfoundControllerPresenter = new HandleUnfoundControllerPresenter();
		ApplicationContext.navigationPresenter = new NavigationPresenter();
		ApplicationContext.questionPresenter = new QuestionPresenter();
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