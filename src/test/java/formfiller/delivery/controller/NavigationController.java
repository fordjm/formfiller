package formfiller.delivery.controller;

import formfiller.boundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.UseCaseFactory;
import formfiller.usecases.UseCaseFactoryImpl;
import formfiller.utilities.*;

public class NavigationController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		Direction direction = DirectionParser.parseDirection(parsedEvent.param);
		Request navigationRequest = makeNavigationRequest(direction);
		UseCase useCase = makeNavigationUseCase();
		useCase.execute(navigationRequest);
	}	
	
	protected Request makeNavigationRequest(Direction direction){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("navigation", 
				ArgsMaker.makeArgs("direction", direction));
		return result;
	}
	
	protected UseCase makeNavigationUseCase(){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("navigation");
	}
}
