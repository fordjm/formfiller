package formfiller.utilities;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;

import formfiller.delivery.event.impl.ParsedEvent;

public class ParsedEventMocker {

	public static ParsedEvent makeMockParsedEvent(String method){
		return makeMockParsedEvent(method, "");
	}
	
	public static ParsedEvent makeMockParsedEvent(String method, String... params){
		ParsedEvent result = Mockito.mock(ParsedEvent.class);
		result.method = method;		
		result.parameters = makeParameters(params);
		return result;
	}

	private static List<String> makeParameters(String[] params) {
		List<String> result = new ArrayList<String>();
		for (String param : params)
			result.add(param);
		return result;
	}
}