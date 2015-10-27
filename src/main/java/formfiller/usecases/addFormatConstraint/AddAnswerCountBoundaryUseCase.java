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
		oldBoundaryValue = getBoundaryValue(componentFormat);
		try {
			updateBoundaryValue(castRequest.count, castRequest.boundary);
		} catch (RuntimeException r) {
			throw r;
		}
	}

	//	TODO:	Format must be MOV.
	private Format getComponentFormat() {
		FormComponent component = 
				FormComponentUtilities.find(castRequest.componentId);
		return component.format;
	}

	private int getBoundaryValue(Format format) {
		String boundary = castRequest.boundary;
		if (Context.stringMatcher.matches("minimum", boundary))
				return format.getMinAnswers();
		else if (Context.stringMatcher.matches("maximum", boundary))
				return format.getMaxAnswers();
		else throw new IllegalArgumentException(
					"Could not match boundary string " + boundary);
	}

	private void updateBoundaryValue(int count, String boundary) {
		if (Context.stringMatcher.matches(boundary, "minimum"))
			updateMinimum(componentFormat, count);
		else 
			updateMaximum(componentFormat, count);
	}

	private void updateMinimum(Format format, int num) {
		format.setMinAnswers(num);
	}

	private void updateMaximum(Format format, int num) {
		format.setMaxAnswers(num);
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
			return result + ".";
		else
			return result + "s.";
	}

	public void undo() {
		ensureUseCaseStateIsUndoable();
		componentFormat = getComponentFormat();
		updateBoundaryValue(oldBoundaryValue, castRequest.boundary);
	}
	
}
