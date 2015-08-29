package formfiller.request.implementations;

import formfiller.gateways.Transporter.Direction;
import formfiller.request.interfaces.NavigationRequest;

public class NavigationRequestImpl extends RequestImpl implements NavigationRequest{
	public Direction direction;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}