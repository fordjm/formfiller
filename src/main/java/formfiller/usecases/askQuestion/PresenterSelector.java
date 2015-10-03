package formfiller.usecases.askQuestion;

import formfiller.Context;
import formfiller.appBoundaries.Presenter;
import formfiller.enums.Outcome;

public class PresenterSelector {
	public static Presenter selectPresenter(Outcome outcome) {
		if (outcome == Outcome.POSITIVE)
			return Context.formComponentPresenter;
		else
			return Context.outcomePresenter;
	}
}