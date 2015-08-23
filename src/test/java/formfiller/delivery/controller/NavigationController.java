package formfiller.delivery.controller;

import java.util.HashMap;

import formfiller.boundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.interfaces.Request;
import formfiller.usecases.UseCaseFactory;
import formfiller.usecases.UseCaseFactoryImpl;

public class NavigationController implements Controller {

	public void handle(ParsedUserRequest parsedInput) {
		int offset = Integer.parseInt(parsedInput.getParam());
		Request navigationRequest = makeNavigationRequest(offset);
		UseCase useCase = makeNavigationUseCase(parsedInput);
		useCase.execute(navigationRequest);
		
		invokePresentQuestionController(parsedInput);
	}	
	protected Request makeNavigationRequest(int offset){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("navigation", makeArgsHashmap("offset", offset));
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
	private void invokePresentQuestionController(ParsedUserRequest parsedInput){
		PresentQuestionController pqc = new PresentQuestionController();
		pqc.handle(parsedInput);
	}
	
}
