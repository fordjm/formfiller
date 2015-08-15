package formfiller;

import formfiller.ui.presenter.QuestionPresenter;
import formfiller.ui.router.Router;
import formfiller.ui.userRequestParser.ConsoleUserRequestParser;
import formfiller.ui.userRequestParser.ParsedUserRequest;
import formfiller.ui.userRequestParser.UserRequestParser;
import formfiller.ui.view.ConsoleAnswerView;
import formfiller.ui.view.ConsoleQuestionView;
import formfiller.ui.view.QuestionView;
import formfiller.ui.view.UserRequestSource;
import formfiller.usecases.navigation.NavigationController;
import formfiller.usecases.presentQuestion.PresentQuestionController;
import formfiller.utilities.TestSetup;

public class Main {
	private static QuestionPresenter questionPresenter;
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
		System.out.println("Please enter a user request.");
	}
	private static void setupClassVariables() {
		questionPresenter = makeQuestionPresenter(new ConsoleQuestionView());
		ApplicationContext.presentQuestionResponseBoundary = questionPresenter;
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
	private static QuestionPresenter makeQuestionPresenter(QuestionView questionView){
		QuestionPresenter result = new QuestionPresenter();
		result.addObserver(questionView);
		return result;
	}
	private static void routeUserRequests(Router router) {
		String userRequestString = userRequestSource.getUserRequestString();
		ParsedUserRequest parsedRequest = userRequestParser.parse(userRequestString);
		router.route(parsedRequest);
	}	
}
