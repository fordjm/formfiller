package formfiller.delivery.controller;

import java.util.HashMap;

import formfiller.boundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.interfaces.Request;
import formfiller.usecases.UseCaseFactory;
import formfiller.usecases.UseCaseFactoryImpl;
import formfiller.utilities.*;

public class NavigationController implements Controller {

	public void handle(ParsedUserRequest parsedInput) {
		Direction direction = DirectionParser.parseDirection(parsedInput.getParam());
		Request navigationRequest = makeNavigationRequest(direction);
		UseCase useCase = makeNavigationUseCase(parsedInput);
		useCase.execute(navigationRequest);
	}	
	
	protected Request makeNavigationRequest(Direction direction){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("navigation", 
				makeArgsHashmap("direction", direction));
		return result;
	}
	
	protected UseCase makeNavigationUseCase(ParsedUserRequest parsedUserRequest){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("navigation");
	}
	
	private <K,V> HashMap<K,V> makeArgsHashmap(K key, V value){
		HashMap<K,V> result = new HashMap<K,V>();
		result.put(key, value);
		return result;
	}
}
