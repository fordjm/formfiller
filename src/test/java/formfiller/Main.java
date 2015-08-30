package formfiller;

import formfiller.delivery.UserRequestParser;
import formfiller.delivery.UserRequestSource;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.router.Router;
import formfiller.delivery.userRequestParser.ConsoleUserRequestParser;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.delivery.view.PresentAnswerViewModel;
import formfiller.delivery.view.HandleUnfoundControllerViewModel;
import formfiller.delivery.view.NavigationViewModel;
import formfiller.delivery.view.PresentQuestionViewModel;
import formfiller.utilities.TestSetup;

public class Main {
	private static UserRequestSource userRequestSource;
	private static UserRequestParser userRequestParser;
	private static Router router;
	
	public static void main(String[] args){
		TestSetup.setupSampleFormComponents();
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
				new HandleUnfoundControllerViewModel());
		ApplicationContext.navigationPresenter.addObserver(new NavigationViewModel());
		ApplicationContext.questionPresenter.addObserver(new PresentQuestionViewModel());
		
		userRequestSource = new PresentAnswerViewModel();
		userRequestParser = new ConsoleUserRequestParser();
		router = makeRouter();
	}
	
	private static Router makeRouter(){
		Router result = new Router();
		result.addMethod("navigation", new NavigationController());
		return result;
	}
	
	private static void routeUserRequests(Router router) {
		String userRequestString = userRequestSource.getUserRequestInput();
		ParsedUserRequest parsedRequest = userRequestParser.parse(userRequestString);
		router.route(parsedRequest);
	}	
}
