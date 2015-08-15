package formfiller.ui.userRequestParser;

// Adapted from:
// https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/src/cleancoderscom/http/ParsedRequest.java
// Retrieved 2015-08-14
public class ParsedUserRequest {
	private String method = "";
	private String param = "";

	public ParsedUserRequest(String method, String param) {
		this.method = method;
		this.param = param;
	}
	public ParsedUserRequest(){ }
	
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