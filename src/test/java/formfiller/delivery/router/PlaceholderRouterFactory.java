package formfiller.delivery.router;

import formfiller.delivery.controller.AddAnswerCountBoundaryController;
import formfiller.delivery.controller.AddFormComponentController;
import formfiller.delivery.controller.AddOptionController;
import formfiller.delivery.controller.AskQuestionController;
import formfiller.delivery.controller.ChangeIdController;
import formfiller.delivery.controller.ChangeOptionVariableController;
import formfiller.delivery.controller.ChangeUnstructuredController;
import formfiller.delivery.controller.DeleteFormComponentController;

public class PlaceholderRouterFactory {	
	public static Router makeRouter(){
		Router result = new Router();
		//	TODO:	Use the same command string for both ChgFmts.
		result.addMethod("AddAnsBnd", new AddAnswerCountBoundaryController());
		result.addMethod("AddFC", new AddFormComponentController());
		result.addMethod("AddOpt", new AddOptionController());
		result.addMethod("AskQues", new AskQuestionController());
		result.addMethod("ChangeId", new ChangeIdController());
		result.addMethod("ChgFmtU", new ChangeUnstructuredController());
		result.addMethod("ChgFmtV", new ChangeOptionVariableController());
		result.addMethod("DelFC", new DeleteFormComponentController());
		return result;
	}
	
}