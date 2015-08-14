package formfiller.utilities;
import formfiller.ApplicationContext;
import formfiller.gateways.MockQuestionGateway;
import formfiller.ui.consoleUi.QuestionPresenter;
import formfiller.gateways.MockAnswerGateway;

public class TestSetup {
	public static void setupContext(){
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.answerGateway = new MockAnswerGateway();
		ApplicationContext.presentQuestionResponseBoundary = new QuestionPresenter();
	}
}