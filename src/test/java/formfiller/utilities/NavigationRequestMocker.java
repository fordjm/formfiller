package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.interfaces.NavigationRequest;

public class NavigationRequestMocker {

	public static NavigationRequest makeMockNavigationRequest(Direction direction){
		NavigationRequest result = Mockito.mock(NavigationRequest.class);
		Mockito.when(result.getDirection()).thenReturn(direction);
		return result;
	}
}
