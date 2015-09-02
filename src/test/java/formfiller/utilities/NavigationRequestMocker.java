package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.models.NavigationRequest;

public class NavigationRequestMocker {

	public static NavigationRequest makeMockNavigationRequest(Direction direction){
		NavigationRequest result = Mockito.mock(NavigationRequest.class);
		result.direction = direction;
		return result;
	}
}
