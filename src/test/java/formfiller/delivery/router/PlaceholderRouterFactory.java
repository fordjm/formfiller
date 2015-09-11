package formfiller.delivery.router;

import formfiller.delivery.controller.AskQuestionController;

public class PlaceholderRouterFactory {
	
	public static Router makeRouter(){
		Router result = new Router();
		result.addMethod("askquestion", new AskQuestionController());
		return result;
	}
}
