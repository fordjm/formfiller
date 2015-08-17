package formfiller;

import formfiller.boundaries.HandleUnfoundControllerPresenter;
import formfiller.boundaries.NavigationPresenter;
import formfiller.boundaries.QuestionPresenter;
import formfiller.gateways.AnswerGateway;
import formfiller.gateways.QuestionGateway;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class ApplicationContext {
	public static QuestionGateway questionGateway;
	public static AnswerGateway answerGateway;
	public static NavigationPresenter navigationPresenter;
	public static QuestionPresenter questionPresenter;
	public static HandleUnfoundControllerPresenter handleUnfoundControllerPresenter;
}
