package formfiller.delivery.router;

import formfiller.delivery.controller.AddFormComponentController;
import formfiller.delivery.controller.AskQuestionController;

public class PlaceholderRouterFactory {
	
	public static Router makeRouter(){
		Router result = new Router();
		// TODO:  result.addMethod("AddAns", new AddAnswerController());
		result.addMethod("AddFC", new AddFormComponentController());
		result.addMethod("AskQues", new AskQuestionController());
		return result;
	}
}
