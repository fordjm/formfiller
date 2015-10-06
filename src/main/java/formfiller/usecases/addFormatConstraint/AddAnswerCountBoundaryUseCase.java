package formfiller.usecases.addFormatConstraint;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.request.models.AddAnswerCountBoundaryRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddAnswerCountBoundaryUseCase extends UndoableUseCaseExecution {
	private AddAnswerCountBoundaryRequest castRequest;
	private int oldBoundaryValue;
	private Format componentFormat;

	protected void castRequest(Request request) {
		castRequest = (AddAnswerCountBoundaryRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId) ||
				StringUtilities.isStringNullOrEmpty(castRequest.boundary) || 
				castRequest.count < 0;
	}

	protected void execute() {
		componentFormat = getComponentFormat();
		if (hasIllegalAnswerCount(componentFormat.minAnswers, componentFormat.maxAnswers))
			throw new MaximumLessThanMinimum();
		oldBoundaryValue = getBoundaryValue(componentFormat);
		updateBoundaryValue(castRequest.count, castRequest.boundary);
	}

	private Format getComponentFormat() {
		FormComponent component = FormComponentUtilities.find(castRequest.componentId);
		return component.format;
	}

	private int getBoundaryValue(Format format) {
		String boundary = castRequest.boundary;
		if (Context.stringMatcher.matches("minimum", boundary))
				return format.minAnswers;
		else if (Context.stringMatcher.matches("maximum", boundary))
				return format.maxAnswers;
		else throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}

	private boolean hasIllegalAnswerCount(int minAnswers, int maxAnswers) {
		return hasIllegalMinimum(maxAnswers) || hasIllegalMaximum(minAnswers);
	}

	private boolean hasIllegalMinimum(int maxAnswers) {
		return castRequest.boundary.equalsIgnoreCase("minimum") &&
				castRequest.count > maxAnswers;
	}

	private boolean hasIllegalMaximum(int minAnswers) {
		return castRequest.boundary.equalsIgnoreCase("maximum") &&
				minAnswers > castRequest.count;
	}

	private void updateBoundaryValue(int count, String boundary) {
		if (Context.stringMatcher.matches(boundary, "minimum"))
			updateMinimum(componentFormat, count);
		else if (Context.stringMatcher.matches(boundary, "maximum"))
			updateMaximum(componentFormat, count);
		else 
			throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}

	private void updateMinimum(Format format, int num) {
		format.minAnswers = num;
	}

	private void updateMaximum(Format format, int num) {
		format.maxAnswers = num;
	}

	protected String makeSuccessfulMessage() {
		String beginning = "You successfully added a ";
		String middle = castRequest.boundary + " of ";
		String ending = makeSingularOrPluralEnding();
		return beginning + middle + ending;
	}

	private String makeSingularOrPluralEnding() {
		int count = castRequest.count;
		String result = count + " answer";
		if (count == 1)
			return result;
		else
			return result + "s";
	}

	public void undo() {
		ensureUseCaseStateIsUndoable();
		componentFormat = getComponentFormat();
		if (hasIllegalAnswerCount(componentFormat.minAnswers, componentFormat.maxAnswers))
			throw new MaximumLessThanMinimum();
		updateBoundaryValue(oldBoundaryValue, castRequest.boundary);
	}

	public class MaximumLessThanMinimum extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}
