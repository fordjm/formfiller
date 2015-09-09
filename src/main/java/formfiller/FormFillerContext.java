package formfiller;

import formfiller.appBoundaries.Presenter;
import formfiller.gateways.FormComponentGateway;
import formfiller.gateways.InMemoryFormComponentState;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06

public class FormFillerContext {
	public static InMemoryFormComponentState formComponentState;
	public static FormComponentGateway formComponentGateway;
	public static ExecutedUseCases executedUseCases;
	public static Presenter answerPresenter;
	public static Presenter responsePresenter;
	public static Presenter formComponentPresenter;
	public static Presenter questionPresenter;
}
