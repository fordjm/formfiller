package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import formfiller.gateways.Transporter.Direction;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.implementations.HandleUnfoundControllerRequestImpl;
import formfiller.request.implementations.NavigationRequestImpl;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

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
				is(instanceOf(HandleUnfoundControllerRequestImpl.class)));
	}
	
	@Test
	public void canBuildNavigationRequest() {
		Request navigationRequest = 
				buildRequest("navigation", makeArgsHashmap("direction", Direction.FORWARD));
		String name = navigationRequest.getName();
		NavigationRequest castNavigationRequest = (NavigationRequest) 
				navigationRequest;
		
		assertThat(navigationRequest, 
				is(instanceOf(NavigationRequestImpl.class)));
		assertThat(name, is("Navigation"));
		assertThat(castNavigationRequest.getDirection(), is(Direction.FORWARD));
	}
	
	@Test
	public void canBuildNoRequest() {
		Request noRequest = impl.build("unknown", makeArgsHashmap());
		
		noRequest.setName("newName");
		
		assertThat(noRequest.getName(), is("NoRequest"));
	}

}
