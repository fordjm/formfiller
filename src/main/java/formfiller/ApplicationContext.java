package formfiller;

import java.util.Stack;

import formfiller.appBoundaries.Presenter;
import formfiller.entities.ExecutedUseCase;
import formfiller.gateways.FormComponentGateway;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06

public class ApplicationContext {
	public static FormComponentGateway formComponentGateway;
	public static Stack<ExecutedUseCase> executedUseCases;
	public static Presenter answerPresenter;
	public static Presenter responsePresenter;
	public static Presenter formComponentPresenter;
	public static Presenter questionPresenter;
}
