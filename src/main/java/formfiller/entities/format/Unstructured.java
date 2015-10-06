package formfiller.entities.format;

public class Unstructured extends Format {
	public Unstructured() {
		super();
		name = "Unstructured";
	}

	public boolean matchesFormat(Object content) {
		return content != null;
	}
	
}
