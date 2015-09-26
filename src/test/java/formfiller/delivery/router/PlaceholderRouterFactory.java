package formfiller.delivery.router;

import formfiller.delivery.controller.AddOptionVariableFormComponentController;
import formfiller.delivery.controller.AddUnstructuredFormComponentController;
import formfiller.delivery.controller.AskQuestionController;
import formfiller.delivery.controller.DeleteFormComponentController;

public class PlaceholderRouterFactory {	
	public static Router makeRouter(){
		Router result = new Router();
		// TODO:  result.addMethod("AddAns", new AddAnswerController());
		// TODO:  Find another way to distinguish AddFC controllers.
		result.addMethod("AddFCU", new AddUnstructuredFormComponentController());
		result.addMethod("AddFCOV", new AddOptionVariableFormComponentController());
		result.addMethod("AskQues", new AskQuestionController());
		result.addMethod("DelFC", new DeleteFormComponentController());
		return result;
	}
}
