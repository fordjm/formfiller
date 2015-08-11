package formfiller.utilities;
import formfiller.gateways.ApplicationContext;
import formfiller.gateways.MockQuestionGateway;
import formfiller.gateways.MockResponseGateway;
import formfiller.usecases.PresentQuestionBoundary;

public class TestSetup {
	public static void setupContext(){
		ApplicationContext.presentQuestionBoundary = new PresentQuestionBoundary();
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.responseGateway = new MockResponseGateway();
	}
}