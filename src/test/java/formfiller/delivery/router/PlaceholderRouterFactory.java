package formfiller.delivery.router;

import formfiller.delivery.controller.AddAnswerCountBoundaryController;
import formfiller.delivery.controller.AddOptionController;
import formfiller.delivery.controller.AddOptionVariableFormComponentController;
import formfiller.delivery.controller.AddUnstructuredFormComponentController;
import formfiller.delivery.controller.AskQuestionController;
import formfiller.delivery.controller.ChangeIdController;
import formfiller.delivery.controller.ChangeOptionVariableController;
import formfiller.delivery.controller.ChangeUnstructuredController;
import formfiller.delivery.controller.DeleteFormComponentController;

public class PlaceholderRouterFactory {	
	public static Router makeRouter(){
		Router result = new Router();
		// TODO:  Why don't I pass answer format as a parameter?
		result.addMethod("AddAnsBnd", new AddAnswerCountBoundaryController());
		result.addMethod("AddFCU", new AddUnstructuredFormComponentController());
		result.addMethod("AddFCV", new AddOptionVariableFormComponentController());
		result.addMethod("AddOpt", new AddOptionController());
		result.addMethod("AskQues", new AskQuestionController());
		result.addMethod("ChangeId", new ChangeIdController());
		result.addMethod("ChgFmtU", new ChangeUnstructuredController());
		result.addMethod("ChgFmtV", new ChangeOptionVariableController());
		result.addMethod("DelFC", new DeleteFormComponentController());
		return result;
	}
	
}