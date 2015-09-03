package formfiller.applicationBoundaryInterfaces;

import formfiller.request.models.Request;

public interface UseCase {	
	public void execute(Request request);	
}
