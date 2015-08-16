package formfiller.utilities;
import formfiller.ApplicationContext;
import formfiller.entities.Question;
import formfiller.gateways.MockQuestionGateway;
import formfiller.ui.presenter.NavigationPresenter;
import formfiller.ui.presenter.QuestionPresenter;
import formfiller.gateways.MockAnswerGateway;

// TODO:  Credit CleanCoders JCS TestSetup
public class TestSetup {
	public static void setupContext(){
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.answerGateway = new MockAnswerGateway();
		ApplicationContext.navigationResponseBoundary = new NavigationPresenter();
		ApplicationContext.presentQuestionResponseBoundary = new QuestionPresenter();
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