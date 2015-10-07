package formfiller.request.models;

import formfiller.entities.format.Format;
import formfiller.entities.format.Unstructured;

public class RequestWithComponentIdAndFormat extends RequestWithComponentId {
	public Format format = new Unstructured();
}
