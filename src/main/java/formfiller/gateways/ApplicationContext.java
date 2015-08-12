package formfiller.gateways;

import formfiller.usecases.PresentableQuestionBoundary;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class ApplicationContext {
	public static QuestionGateway questionGateway;
	public static ResponseGateway responseGateway;
	public static PresentableQuestionBoundary presentQuestionBoundary;
}
