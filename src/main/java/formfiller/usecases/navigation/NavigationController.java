package formfiller.usecases.navigation;

import java.util.HashMap;
import java.util.Scanner;

import formfiller.ApplicationContext;
import formfiller.Controller;
import formfiller.ui.userRequestParser.ParsedUserRequest;
import formfiller.usecases.Request;
import formfiller.usecases.RequestBuilder;
import formfiller.usecases.RequestBuilderImpl;
import formfiller.usecases.UseCase;
import formfiller.usecases.UseCaseFactory;
import formfiller.usecases.UseCaseFactoryImpl;
import formfiller.usecases.presentQuestion.PresentQuestionController;

public class NavigationController implements Controller {

	public void handle(ParsedUserRequest parsedInput) {
		int offset = Integer.parseInt(parsedInput.getParam());
		Request navigationRequest = makeNavigationRequest(offset);
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		UseCase useCase = useCaseFactory.make("navigation");
		useCase.execute(navigationRequest);
		
		invokePresentQuestionController(parsedInput);
	}
	
	public Request makeNavigationRequest(int offset){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("navigation", makeArgsHashmap(offset));
		return result;
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
