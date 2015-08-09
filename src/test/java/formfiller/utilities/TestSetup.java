package formfiller.utilities;
import formfiller.gateways.Context;
import formfiller.gateways.MockQuestionGateway;

public class TestSetup {
	public static void setupContext(){
		Context.questionGateway = new MockQuestionGateway();
	}
}