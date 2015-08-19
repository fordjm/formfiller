package formfiller.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.implementations.HandleUnfoundControllerRequestImpl;
import formfiller.request.implementations.NavigationRequestImpl;
import formfiller.request.implementations.RequestImpl;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class RequestBuilderImplTest {
	private RequestBuilderImpl impl;
	
	@Before
	public void setUp() {
		impl = new RequestBuilderImpl();
	}
	@Test
	public void canBuildHandleUnfoundControllerRequest() {
		Request handleUnfoundControllerRequest = 
				impl.build("handleUnfoundController", makeArgsHashmap());
		assertThat(handleUnfoundControllerRequest, 
				is(instanceOf(HandleUnfoundControllerRequestImpl.class)));
	}
	@Test
	public void canBuildPresentQuestionRequest() {
		Request presentQuestionRequest = 
				impl.build("presentQuestion", makeArgsHashmap());
		String name = presentQuestionRequest.getName();
		
		assertThat(presentQuestionRequest, 
				is(instanceOf(RequestImpl.class)));
		assertThat(name, is("PresentQuestion"));
	}
	@Test
	public void canBuildPresentAnswerRequest() {
		Request presentAnswerRequest = 
				impl.build("presentAnswer", makeArgsHashmap());
		String name = presentAnswerRequest.getName();
		
		assertThat(presentAnswerRequest, 
				is(instanceOf(RequestImpl.class)));
		assertThat(name, is("PresentAnswer"));
	}
	@Test
	public void canBuildNavigationRequest() {
		Request navigationRequest = 
				impl.build("navigation", makeArgsHashmap("offset", 1));
		String name = navigationRequest.getName();
		NavigationRequest castNavigationRequest = (NavigationRequest) 
				navigationRequest;
		
		assertThat(navigationRequest, 
				is(instanceOf(NavigationRequestImpl.class)));
		assertThat(name, is("Navigation"));
		assertThat(castNavigationRequest.getOffset(), is(1));
	}
	@Test
	public void canBuildNoRequest() {
		Request noRequest = impl.build("unknown", makeArgsHashmap());
		assertThat(noRequest, 
				is(instanceOf(NoRequestImpl.class)));
	}
	private <K,V> HashMap<K,V> makeArgsHashmap() {
		return new HashMap<K,V>();
	}
	private <K,V> HashMap<K,V> makeArgsHashmap(K key, V value) {
		HashMap<K,V> result = new HashMap<K,V>();
		result.put(key, value);
		return result;
	}

}
