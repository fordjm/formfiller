package formfiller.request.interfaces;

import formfiller.gateways.Transporter.Direction;

public interface NavigationRequest extends Request {
	public Direction getDirection();
	public void setDirection(Direction direction);
}
