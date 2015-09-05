package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.delivery.controller.Arguments;
import formfiller.enums.Direction;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.request.models.NavigationRequest;
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
	public void canBuildNavigationRequest() {
		Request navigationRequest = 
				buildRequest("navigation", makeArguments("direction", Direction.FORWARD));
		String name = navigationRequest.name;
		NavigationRequest castNavigationRequest = 
				(NavigationRequest) navigationRequest;
		
		assertThat(navigationRequest, 
				is(instanceOf(NavigationRequest.class)));
		assertThat(name, is("Navigation"));
		assertThat(castNavigationRequest.direction, is(Direction.FORWARD));
	}
	
	@Test
	public void canBuildNoRequest() {
		Request noRequest = builder.build("unknown", new Arguments());
		
		assertThat(noRequest.name, is("NoRequest"));
	}
}
