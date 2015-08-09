package formfiller.gateways;

import formfiller.entities.Response;

public interface ResponseGateway {
	public Response findResponseByIndexOffset(int offset);
	public Response getResponse();
	public void save(Response response);
}
