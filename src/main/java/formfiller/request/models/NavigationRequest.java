package formfiller.request.models;

import formfiller.gateways.InMemoryTransporter.Direction;

public class NavigationRequest extends Request {
	public Direction direction = Direction.NONE;
}