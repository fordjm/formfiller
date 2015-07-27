package formfiller.entities;

public class NullResponse implements Response<String> {

	public int id() {
		return -1;
	}

	public String content() {
		return "";
	}
}
