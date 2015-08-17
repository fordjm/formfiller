package formfiller.delivery.controller;

import java.util.HashMap;
import java.util.Scanner;

import formfiller.ApplicationContext;
import formfiller.delivery.Controller;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.usecases.Request;
import formfiller.usecases.RequestBuilder;
import formfiller.usecases.RequestBuilderImpl;
import formfiller.usecases.UseCase;
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
		Request result = requestBuilder.build("navigation", makeArgsHashmap(offset));
		return result;
	}
	protected UseCase makeNavigationUseCase(ParsedUserRequest parsedUserRequest){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("navigation");
	}
	private HashMap makeArgsHashmap(int offset){
		HashMap result = new HashMap();
		result.put("offset", offset);
		return result;
	}
	private void invokePresentQuestionController(ParsedUserRequest parsedInput){
		PresentQuestionController pqc = new PresentQuestionController();
		pqc.handle(parsedInput);
	}
	
}
