package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.response.models.PresentableResponse;

public class FixtureUtilities {
	public static String messagePresented() {
		PresentableResponse response = 
				FormFillerContext.outcomePresenter.getPresentableResponse();
		return response.message;
	}
}
