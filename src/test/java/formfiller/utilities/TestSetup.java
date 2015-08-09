package formfiller.utilities;
import formfiller.gateways.ApplicationContext;
import formfiller.gateways.MockQuestionGateway;
import formfiller.gateways.MockResponseGateway;

public class TestSetup {
	public static void setupContext(){
		ApplicationContext.questionGateway = new MockQuestionGateway();
		ApplicationContext.responseGateway = new MockResponseGateway();
	}
}