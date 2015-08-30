package formfiller.request.interfaces;

import formfiller.gateways.InMemoryTransporter.Direction;

public interface NavigationRequest extends Request {
	public Direction getDirection();
	public void setDirection(Direction direction);
}
