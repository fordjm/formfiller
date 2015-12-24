package formfiller;

import formfiller.delivery.presenter.FormComponentPresenter;
import formfiller.delivery.presenter.NotificationPresenter;
import formfiller.gateways.FormComponentGateway;
import formfiller.gateways.FormComponentState;
import formfiller.utilities.valueMatcher.StringMatcher;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06

public class Context {
	public static FormComponentState formComponentState;
	public static FormComponentGateway formComponentGateway;
	public static ExecutedUseCases executedUseCases;
	public static NotificationPresenter answerPresenter;
	public static NotificationPresenter outcomePresenter;
	public static FormComponentPresenter formComponentPresenter;
	public static NotificationPresenter questionPresenter;
	public static StringMatcher stringMatcher;
}
