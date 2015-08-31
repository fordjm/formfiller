package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.delivery.eventParser.ParsedEvent;

public class ParsedEventMocker {

	public static ParsedEvent makeMockParsedEvent(String method){
		return makeMockParsedEvent(method, "");
	}
	
	public static ParsedEvent makeMockParsedEvent(String method, String param){
		ParsedEvent result = Mockito.mock(ParsedEvent.class);
		result.method = method;
		result.param = param;
		return result;
	}
}
