package formfiller.request.models;

public class Request {
	public static final Request NULL = makeNullRequest();
	
	public String name = "Request";

	private static Request makeNullRequest() {
		Request result = new Request();
		result.name = "NullRequest";
		return result;
	}
}
