package formfiller.delivery.router;

import formfiller.delivery.controller.AddAnswerController;
import formfiller.delivery.controller.AddAnswerCountBoundaryController;
import formfiller.delivery.controller.AddFormComponentController;
import formfiller.delivery.controller.AddOptionController;
import formfiller.delivery.controller.AskQuestionController;
import formfiller.delivery.controller.ChangeFormatController;
import formfiller.delivery.controller.ChangeIdController;
import formfiller.delivery.controller.DeleteFormComponentController;

public class PlaceholderTextRouterFactory {	
	public static Router makeRouter(){
		Router result = new Router();
		result.addMethod("AddAns", new AddAnswerController());
		result.addMethod("AddAnsBnd", new AddAnswerCountBoundaryController());
		result.addMethod("AddFC", new AddFormComponentController());
		result.addMethod("AddOpt", new AddOptionController());
		result.addMethod("AskQues", new AskQuestionController());
		result.addMethod("ChangeId", new ChangeIdController());
		result.addMethod("ChgFmt", new ChangeFormatController());
		result.addMethod("DelFC", new DeleteFormComponentController());
		return result;
	}
	
}