package formfiller;

import formfiller.delivery.UserRequestParser;
import formfiller.delivery.UserRequestSource;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.controller.PresentAnswerController;
import formfiller.delivery.controller.PresentQuestionController;
import formfiller.delivery.router.Router;
import formfiller.delivery.userRequestParser.ConsoleUserRequestParser;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.delivery.view.ConsolePresentAnswerView;
import formfiller.delivery.view.ConsoleHandleUnfoundControllerView;
import formfiller.delivery.view.ConsoleNavigationView;
import formfiller.delivery.view.ConsolePresentQuestionView;
import formfiller.utilities.TestSetup;

public class Main {
	private static UserRequestSource userRequestSource;
	private static UserRequestParser userRequestParser;
	private static Router router;
	
	public static void main(String[] args){
		TestSetup.setupSampleQuestions();
		setupClassVariables();
		
		outputCheapHackyStartPrompt();
		while (true){
			routeUserRequests(router);
		}
	}
	private static void outputCheapHackyStartPrompt() {
		System.out.print("Please enter a user request:  ");
	}
	private static void setupClassVariables() {
		ApplicationContext.handleUnfoundControllerPresenter.addObserver(
				new ConsoleHandleUnfoundControllerView());
		ApplicationContext.navigationPresenter.addObserver(new ConsoleNavigationView());
		ApplicationContext.questionPresenter.addObserver(new ConsolePresentQuestionView());
		
		userRequestSource = new ConsolePresentAnswerView();
		userRequestParser = new ConsoleUserRequestParser();
		router = makeRouter();
	}
	private static Router makeRouter(){
		Router result = new Router();
		result.addMethod("presentQuestion", new PresentQuestionController());
		result.addMethod("presentAnswer", new PresentAnswerController());
		result.addMethod("navigation", new NavigationController());
		return result;
	}
	private static void routeUserRequests(Router router) {
		String userRequestString = userRequestSource.getUserRequestString();
		ParsedUserRequest parsedRequest = userRequestParser.parse(userRequestString);
		router.route(parsedRequest);
	}	
}
