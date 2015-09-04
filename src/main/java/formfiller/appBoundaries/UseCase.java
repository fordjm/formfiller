package formfiller.appBoundaries;

import formfiller.request.models.Request;

public interface UseCase {	
	public void execute(Request request);	
}
