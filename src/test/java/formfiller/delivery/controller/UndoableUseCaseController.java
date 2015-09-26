package formfiller.delivery.controller;

import java.util.List;

import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.request.models.Request;
import formfiller.usecases.LocalUseCase;
import formfiller.usecases.undoable.UndoableUseCase;

public abstract class UndoableUseCaseController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		assignRequiredParameters(parsedEvent.parameters);
		Arguments arguments = makeArguments();
		Request request = makeRequest(arguments);
		UndoableUseCase useCase = makeUseCase();
		LocalUseCase local = new LocalUseCase(useCase);
		
		local.execute(request);
	}

	protected String assignRequiredParameter(List<String> parameters, int index) {
		if (parameters == null || index >= parameters.size()) 
			return "";
		
		return parameters.get(index);
	}
	
	protected abstract void assignRequiredParameters(List<String> parameters);

	protected abstract Arguments makeArguments();
	
	protected abstract Request makeRequest(Arguments arguments);
	
	protected abstract UndoableUseCase makeUseCase();
}
