package formfiller.utilities;
import formfiller.ApplicationContext;
import formfiller.gateways.MockQuestionGateway;
import formfiller.gateways.MockAnswerGateway;
import formfiller.ui.QuestionPresenter;

public class TestSetup {
	public static void setupContext(){
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.answerGateway = new MockAnswerGateway();
		ApplicationContext.presentQuestionResponseBoundary = new QuestionPresenter();
	}
}