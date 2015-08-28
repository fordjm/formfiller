package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.request.interfaces.NavigationRequest;

public class NavigationRequestMocker {

	public static NavigationRequest makeMockNavigationRequest(int offset){
		NavigationRequest result = Mockito.mock(NavigationRequest.class);
		Mockito.when(result.getOffset()).thenReturn(offset);
		return result;
	}
}
