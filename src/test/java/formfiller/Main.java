package formfiller;

import formfiller.delivery.NavigationView;
import formfiller.delivery.PresentQuestionView;
import formfiller.delivery.UserRequestSource;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.controller.PresentQuestionController;
import formfiller.delivery.presenter.NavigationPresenterImpl;
import formfiller.delivery.presenter.QuestionPresenterImpl;
import formfiller.delivery.router.Router;
import formfiller.delivery.userRequestParser.ConsoleUserRequestParser;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.delivery.userRequestParser.UserRequestParser;
import formfiller.delivery.view.ConsoleAnswerView;
import formfiller.delivery.view.ConsoleNavigationView;
import formfiller.delivery.view.ConsoleQuestionView;
import formfiller.utilities.TestSetup;

public class Main {
	private static NavigationPresenterImpl navigationPresenter;
	private static QuestionPresenterImpl questionPresenter;
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
		navigationPresenter = makeNavigationPresenter(new ConsoleNavigationView());
		ApplicationContext.navigationPresenter = navigationPresenter;
		questionPresenter = makeQuestionPresenter(new ConsoleQuestionView());
		ApplicationContext.questionPresenter = questionPresenter;
		userRequestSource = new ConsoleAnswerView();
		userRequestParser = new ConsoleUserRequestParser();
		router = makeRouter();
	}
	private static Router makeRouter(){
		Router result = new Router();
		result.addMethod("presentQuestion", new PresentQuestionController());
		result.addMethod("navigation", new NavigationController());
		return result;
	}
	private static NavigationPresenterImpl makeNavigationPresenter(NavigationView navigationView){
		NavigationPresenterImpl result = new NavigationPresenterImpl();
		result.addObserver(navigationView);
		return result;
	}
	private static QuestionPresenterImpl makeQuestionPresenter(PresentQuestionView questionView){
		QuestionPresenterImpl result = new QuestionPresenterImpl();
		result.addObserver(questionView);
		return result;
	}
	private static void routeUserRequests(Router router) {
		String userRequestString = userRequestSource.getUserRequestString();
		ParsedUserRequest parsedRequest = userRequestParser.parse(userRequestString);
		router.route(parsedRequest);
	}	
}
