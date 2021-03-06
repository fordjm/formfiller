package formfiller.delivery.controller;

import java.util.List;

import formfiller.delivery.Controller;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.LocalUseCase;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.undoable.UndoableUseCase;

/**
 * UndoableUseCaseController uses the Template Method pattern to factor 
 * out the generic event handling algorithm used by its derivatives. 
 * It creates a new Arguments object from the parsed event, gets a 
 * new request from the request builder, gets a new Use Case from the 
 * use case factory, and executes the use case.
 */
public abstract class UndoableUseCaseController implements Controller {
	private String name = "";

	public void handle(ParsedEvent parsedEvent) {
		name = getName();	// gets the controller's name
		assignRequiredParameters(parsedEvent.parameters);
		Arguments arguments = makeArguments();
		Request request = makeRequest(arguments);
		UndoableUseCase useCase = makeUseCase();
		LocalUseCase local = new LocalUseCase(useCase);
		
		local.execute(request);
	}
	
	protected abstract String getName();

	protected String assignRequiredParameter(List<String> parameters, int index) {
		if (parameters == null || index >= parameters.size()) 
			return "";
		
		return parameters.get(index);
	}
	
	protected abstract void assignRequiredParameters(List<String> parameters);

	protected abstract Arguments makeArguments();
	
	protected Request makeRequest(Arguments arguments) {
		RequestBuilderImpl builder = new RequestBuilderImpl();
		// uses controller name to build request
		return builder.build(name, arguments);
	}

	protected UndoableUseCase makeUseCase() {
		UseCaseFactory factory = new UseCaseFactoryImpl();
		// uses controller name to make use case
		UndoableUseCase castUseCase = (UndoableUseCase) factory.make(name);
		return castUseCase;
	}
	
}
