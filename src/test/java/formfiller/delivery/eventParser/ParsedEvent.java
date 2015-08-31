package formfiller.delivery.eventParser;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/ParsedRequest.java
// Retrieved 2015-08-14
public class ParsedEvent {
	private String method = "";
	private String param = "";

	public ParsedEvent(String method, String param) {
		this.method = method;
		this.param = param;
	}
	public ParsedEvent(){ }
	
	public String getMethod(){
		return method;
	}	
	public String getParam(){
		return param;
	}
	public void setMethod(String method){
		this.method = method;
	}	
	public void setParam(String param){
		this.param = param;
	}

}