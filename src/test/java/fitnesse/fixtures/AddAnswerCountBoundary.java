package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.formComponent.FormComponent;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.stringMatcher.CaseIgnoringStringMatcher;
import formfiller.utilities.stringMatcher.StringMatcher;

public class AddAnswerCountBoundary {
	//	TODO:	How to make matcher interchangable?
	//			(Can't directly take as a constructor arg from fixture)
	StringMatcher stringMatcher = new CaseIgnoringStringMatcher();
	
	public void addAnswerBoundary(int num, String boundary, String componentId){
		executeTemporaryBehaviorCode(num, boundary, componentId);
	}

	private void executeTemporaryBehaviorCode(int num, String boundary, String componentId) {
		FormComponent component = FormComponentUtilities.find(componentId);
		updateBoundaryValue(num, boundary, component);		
		updateMessage(boundary, num);
	}

	private void updateBoundaryValue(int num, String boundary, FormComponent component) {
		if (stringMatcher.matches(boundary, "minimum"))
			updateMinimum(component.format, num);
		else if (stringMatcher.matches(boundary, "maximum"))
			updateMaximum(component.format, num);
		else 
			throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}

	private void updateMessage(String boundary, int num) {
		String message = makeMessage(boundary, num);
		Presenter presenter = FormFillerContext.outcomePresenter;
		PresentableResponse response = createPresentableResponse(message);
		presenter.present(response);
	}

	private String makeMessage(String boundary, int num) {
		String beginning = "You successfully added a ";
		String middle = boundary + " of ";
		String ending = makeSingularOrPluralEnding(num);
		return beginning + middle + ending;
	}

	private String makeSingularOrPluralEnding(int num) {
		String result = num + " answer";
		if (num == 1)
			return result;
		else
			return result + "s";
	}

	private PresentableResponse createPresentableResponse(String message) {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		return result;
	}

	private void updateMinimum(AnswerFormat format, int num) {
		format.minAnswers = num;
	}

	private void updateMaximum(AnswerFormat format, int num) {
		format.maxAnswers = num;
	}
	
	public boolean isBoundaryEqualTo(String componentId, String boundary, int num){
		FormComponent component = FormComponentUtilities.find(componentId);
		int boundaryValue = getBoundaryValue(component.format, boundary);
		return boundaryValue == num;
	}

	private int getBoundaryValue(AnswerFormat format, String boundary) {
		if (stringMatcher.matches(boundary, "minimum"))
			return format.minAnswers;
		else if (stringMatcher.matches(boundary, "maximum"))
			return format.maxAnswers;
		else
			throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}
	
}
