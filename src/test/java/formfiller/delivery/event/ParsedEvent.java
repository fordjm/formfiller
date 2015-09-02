package formfiller.delivery.event;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/ParsedRequest.java
// Retrieved 2015-08-14
public class ParsedEvent {
	public String method = "";
	public String param = "";

	public ParsedEvent(String method, String param) {
		this.method = method;
		this.param = param;
	}
	
	public ParsedEvent() { }
}