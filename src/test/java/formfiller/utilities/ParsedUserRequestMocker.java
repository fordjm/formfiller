package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.delivery.userRequestParser.ParsedUserRequest;

public class ParsedUserRequestMocker {

	public static ParsedUserRequest makeMockParsedUserRequest(String method){
		return makeMockParsedUserRequest(method, "");
	}
	
	public static ParsedUserRequest makeMockParsedUserRequest(String method, String param){
		ParsedUserRequest result = Mockito.mock(ParsedUserRequest.class);
		Mockito.when(result.getMethod()).thenReturn(method);
		Mockito.when(result.getParam()).thenReturn(param);
		return result;
	}
}
