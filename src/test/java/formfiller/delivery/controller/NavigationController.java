package formfiller.delivery.controller;

import formfiller.boundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.utilities.*;

public class NavigationController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		Direction direction = DirectionParser.parseDirection(parsedEvent.param);
		Arguments arguments = makeArguments(direction);
		Request navigationRequest = makeNavigationRequest(arguments);
		UseCase useCase = makeNavigationUseCase();
		useCase.execute(navigationRequest);
	}	
	
	protected Request makeNavigationRequest(Arguments arguments){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("navigation", arguments);
		return result;
	}

	private Arguments makeArguments(Direction direction) {
		Arguments arguments = new Arguments();
		arguments.add("direction", direction);
		return arguments;
	}
	
	protected UseCase makeNavigationUseCase(){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("navigation");
	}
}
