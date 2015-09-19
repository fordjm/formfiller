package formfiller.utilities;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.enums.Outcome;

public class PresenterSelector {
	public static Presenter selectPresenter(Outcome outcome) {
		if (outcome == Outcome.POSITIVE)
			return FormFillerContext.formComponentPresenter;
		else
			return FormFillerContext.errorPresenter;
	}
}