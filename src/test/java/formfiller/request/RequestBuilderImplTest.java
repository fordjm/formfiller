package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.HandleUnfoundControllerRequest;
import formfiller.request.models.NavigationRequest;
import formfiller.request.models.Request;

public class RequestBuilderImplTest {
	private RequestBuilderImpl impl;
	
	private <K,V> Request buildRequest(String requestName, HashMap<K,V> args) {
		return impl.build(requestName, args);
	}
	
	private <K,V> HashMap<K,V> makeArgsHashmap() {
		return new HashMap<K,V>();
	}
	
	private <K,V> HashMap<K,V> makeArgsHashmap(K key, V value) {
		HashMap<K,V> result = new HashMap<K,V>();
		result.put(key, value);
		return result;
	}
	
	@Before
	public void setUp() {
		impl = new RequestBuilderImpl();
	}
	
	@Test
	public void canBuildHandleUnfoundControllerRequest() {
		Request handleUnfoundControllerRequest = 
				buildRequest("handleUnfoundController", makeArgsHashmap());
		
		assertThat(handleUnfoundControllerRequest, 
				is(instanceOf(HandleUnfoundControllerRequest.class)));
	}
	
	@Test
	public void canBuildNavigationRequest() {
		Request navigationRequest = 
				buildRequest("navigation", makeArgsHashmap("direction", Direction.FORWARD));
		String name = navigationRequest.name;
		NavigationRequest castNavigationRequest = (NavigationRequest) 
				navigationRequest;
		
		assertThat(navigationRequest, 
				is(instanceOf(NavigationRequest.class)));
		assertThat(name, is("Navigation"));
		assertThat(castNavigationRequest.direction, is(Direction.FORWARD));
	}
	
	@Test
	public void canBuildNoRequest() {
		Request noRequest = impl.build("unknown", makeArgsHashmap());
		
		//noRequest.name = "newName";
		
		assertThat(noRequest.name, is("NoRequest"));
	}
}
