package formfiller.appBoundaries;

import formfiller.request.models.Request;
/**
 * UseCase is the output boundary interface to the application partition.
 */
public interface UseCase {	
	public void execute(Request request);	
}
