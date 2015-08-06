package formfiller.utilities;
import formfiller.gateways.Context;
import formfiller.gateways.QuestionGatewayFake;

public class TestSetup {
	public static void setupContext(){
		Context.questionGateway = new QuestionGatewayFake();
	}
}
