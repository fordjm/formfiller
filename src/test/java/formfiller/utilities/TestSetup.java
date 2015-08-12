package formfiller.utilities;
import formfiller.gateways.ApplicationContext;
import formfiller.gateways.MockQuestionGateway;
import formfiller.gateways.MockResponseGateway;
import formfiller.usecases.PresentableQuestionBoundary;

public class TestSetup {
	public static void setupContext(){
		ApplicationContext.presentQuestionBoundary = new PresentableQuestionBoundary();
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.responseGateway = new MockResponseGateway();
	}
}