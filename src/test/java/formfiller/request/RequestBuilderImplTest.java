package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.WhichQuestion;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.request.models.AskQuestionRequest;
import formfiller.request.models.Request;

public class RequestBuilderImplTest {
	private RequestBuilderImpl builder;
	
	private Request buildRequest(String requestName, Arguments args) {
		return builder.build(requestName, args);
	}
	
	private Arguments makeArguments(String key, Object value) {
		Arguments result = new Arguments();
		result.add(key, value);
		return result;
	}
	
	@Before
	public void setUp() {
		builder = new RequestBuilderImpl();
	}
	
	@Test
	public void canBuildHandleUnfoundUseCaseRequest() {
		Request handleUnfoundUseCaseRequest = 
				builder.build("handleUnfoundUseCase", new Arguments());
		
		assertThat(handleUnfoundUseCaseRequest, 
				is(instanceOf(HandleUnfoundUseCaseRequest.class)));
		assertThat(handleUnfoundUseCaseRequest.name, 
				is("HandleUnfoundUseCaseRequest"));
	}
	
	@Test
	public void canBuildAskQuestionRequest() {
		Request askQuestionRequest = 
				buildRequest("askQuestion", makeArguments("which", WhichQuestion.NEXT));
		String name = askQuestionRequest.name;
		AskQuestionRequest castRequest = 
				(AskQuestionRequest) askQuestionRequest;
		
		assertThat(askQuestionRequest, 
				is(instanceOf(AskQuestionRequest.class)));
		assertThat(name, is("AskQuestion"));
		assertThat(castRequest.which, is(WhichQuestion.NEXT));
	}
	
	@Test
	public void canBuildNoRequest() {
		Request noRequest = builder.build("unknown", new Arguments());
		
		assertThat(noRequest.name, is("NoRequest"));
	}
}
